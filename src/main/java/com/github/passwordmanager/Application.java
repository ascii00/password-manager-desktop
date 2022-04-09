package com.github.passwordmanager;

import com.github.passwordmanager.controllers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws IOException {
        MainController.setMainStage(stage);
        MainController.setDAO(new DAO());
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("mainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 680, 450);
        stage.setTitle("Password Manager");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}