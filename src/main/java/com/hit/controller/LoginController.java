package com.hit.controller;

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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class LoginController {
    public TextField login_username;
    public TextField login_password;
    public Button login_submit;
    public Stage stage;
    public Scene scene;
    public Parent root;
    public Text login_label;

    @FXML
    protected void onSubmitButtonClick(ActionEvent event) throws IOException {
        String username =  login_username.getText();
        String password = Model.MD5_text(login_password.getText());
        String balanceAndId = "-1";
        try {
            balanceAndId = Model.loginUser(username, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!Objects.equals(balanceAndId, "-1")) {
            ShopController.balance = Float.parseFloat(balanceAndId.split("!")[0]);
            ShopController.id = balanceAndId.split("!")[1];
            root = FXMLLoader.load(Objects.requireNonNull(View.class.getResource("shop-view.fxml")));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            login_label.setVisible(true);
        }
    }
}