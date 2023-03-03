package com.hit.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(View.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1410, 785);
        stage.setTitle("Client");
        stage.setScene(scene);
        stage.show();
    }

    public void launchView() {
        launch();
    }
}
