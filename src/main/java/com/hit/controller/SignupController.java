package com.hit.controller;

import com.hit.dm.User;
import com.hit.model.Model;
import com.hit.view.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SignupController {
    public TextField signup_username;
    public TextField signup_password;
    public Button signup_submit;
    public Stage stage;
    public Scene scene;
    public Parent root;

    @FXML
    protected void onSubmitButtonClick(ActionEvent event) throws IOException {
        String username =  signup_username.getText();
        String password = Model.MD5_text(signup_password.getText());
        User user = new User(username, password, 100.0F);
        try {
            Model.signupUser(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        root = FXMLLoader.load(Objects.requireNonNull(View.class.getResource("main-view.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}