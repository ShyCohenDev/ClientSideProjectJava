package com.hit.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hit.dm.Item;
import com.hit.dm.Request;
import com.hit.dm.Response;
import com.hit.dm.User;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Model {
    public static final int port = 34567;
    public static String MD5_text(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(text.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder hashText = new StringBuilder(no.toString(16));
            while (hashText.length() < 32) {
                hashText.insert(0, "0");
            }
            return hashText.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void signupUser(User user) throws IOException {
        Gson gson = new Gson();
        Socket serverConnection = new Socket("localhost", port);
        Request request = new Request("users/signup", user);
        String requestAsString = gson.toJson(request);
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(serverConnection.getOutputStream()));

        writer.println(requestAsString);
        writer.flush();
        writer.close();
        serverConnection.close();
    }

    public static String loginUser(String username, String hashedPassword) throws IOException {
        Gson gson = new Gson();
        Socket serverConnection = new Socket("localhost", port);
        Request request = new Request("users/login", username + "_" + hashedPassword);
        String requestAsString = gson.toJson(request);
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(serverConnection.getOutputStream()));
        writer.println(requestAsString);
        writer.flush();

        Scanner reader = new Scanner(new InputStreamReader(serverConnection.getInputStream()));
        String responseAsString = reader.nextLine();
        Response response = gson.fromJson(responseAsString, Response.class);
        String balanceAndId = response.getBody().toString();

        writer.close();
        reader.close();
        serverConnection.close();

        return balanceAndId;
    }

    public static List<Item> searchItems(String searchQuery) throws IOException {
        Gson gson = new Gson();
        Socket serverConnection = new Socket("localhost", port);
        Request request = new Request("searchItems", searchQuery);
        String requestAsString = gson.toJson(request);
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(serverConnection.getOutputStream()));
        writer.println(requestAsString);
        writer.flush();

        Scanner reader = new Scanner(new InputStreamReader(serverConnection.getInputStream()));
        String responseAsString = reader.nextLine();
        Response response = gson.fromJson(responseAsString, Response.class);

        Type itemListType = new TypeToken<ArrayList<Item>>(){}.getType();
        List<Item> itemList = new Gson().fromJson(String.valueOf(response.getBody()), itemListType);

        writer.close();
        reader.close();
        serverConnection.close();

        return itemList;
    }

    public static void buyItems(String id, List<Item> items) throws IOException {
        Gson gson = new Gson();
        Socket serverConnection = new Socket("localhost", port);
        String itemListAsString = gson.toJson(items);
        Request request = new Request("buyItems", id + "!" + itemListAsString);
        String requestAsString = gson.toJson(request);
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(serverConnection.getOutputStream()));
        writer.println(requestAsString);
        writer.flush();
        writer.close();
        serverConnection.close();
    }
}
