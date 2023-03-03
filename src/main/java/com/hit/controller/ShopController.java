package com.hit.controller;

import com.hit.dm.Item;
import com.hit.model.Model;
import com.hit.view.View;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ShopController {

    public TextField shop_text;
    public Button shop_search;
    public ListView shop_items;
    public ListView shop_cart;
    public Button shop_buy;
    public Text shop_total;
    private List<Item> cart = new ArrayList<>();
    private float total = 0F;
    public static float balance;
    public static String id;

    @FXML
    protected void onSearchButtonClick() {
        List<Item> itemList = null;
        try {
            itemList = Model.searchItems(shop_text.getText());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        shop_items.setItems(FXCollections.observableList(itemList));
    }

    @FXML
    protected void onItemClick() {
        cart.add((Item) shop_items.getSelectionModel().getSelectedItem());
        if (((Item) shop_items.getSelectionModel().getSelectedItem()).getCountRemaining() > 0) {
            total += ((Item) shop_items.getSelectionModel().getSelectedItem()).getPrice();
            shop_total.setText("Total: " + total + "₪" + "\nBalance: " + balance + "₪");
            shop_cart.setItems(FXCollections.observableList(cart));
        }
    }

    @FXML
    protected void onCartClick() {
        cart.remove((Item) shop_items.getSelectionModel().getSelectedItem());
        total -= ((Item) shop_items.getSelectionModel().getSelectedItem()).getPrice();
        shop_total.setText("Total: " + total + "₪" + "\nBalance: " + balance + "₪");
        shop_cart.setItems(FXCollections.observableList(cart));
    }

    @FXML
    protected void onBuyButtonClick() {
        if (total < balance) {
            System.out.println("id: " + id);
            try {
                Model.buyItems(id, cart);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            shop_items.setItems(FXCollections.observableList(new ArrayList<>()));
            shop_cart.setItems(FXCollections.observableList(new ArrayList<>()));
            shop_text.setText("");
            balance -= total;
            total = 0F;
            shop_total.setText("Total: " + total + "₪" + "\nBalance: " + balance + "₪");
        }
    }
}