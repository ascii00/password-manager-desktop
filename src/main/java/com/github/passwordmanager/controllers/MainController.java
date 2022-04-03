package com.github.passwordmanager.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController {
    private static Stage mainStage;

    public static void setMainStage(Stage stage) {
        mainStage = stage;
    }

    @FXML
    private MenuItem HelpAbout;

    @FXML
    private Button addNewPassword;

    @FXML
    private ListView<?> categoryList;

    @FXML
    private Button deleteSelectedPassword;

    @FXML
    private Button deleteTheWholeCategory;

    @FXML
    private MenuItem fileClose;

    @FXML
    private TableView<?> passwordList;

    @FXML
    void addNewPassword(MouseEvent event) throws Exception {

        AnchorPane secondaryLayout = new AnchorPane();
        Scene secondScene = new Scene(secondaryLayout, 300, 200);

        TextField loginField = new TextField();
        TextField passwordField = new TextField();
        TextField serviceField = new TextField();

        Text loginText = new Text("Login:");
        Text passwordText = new Text("Password:");
        Text serviceText = new Text("Service:");

        Button okButton = new Button("OK");

        AnchorPane.setBottomAnchor(okButton,20.0);
        AnchorPane.setRightAnchor(okButton,20.0);

        AnchorPane.setLeftAnchor(loginText,30.0);
        AnchorPane.setLeftAnchor(passwordText,30.0);
        AnchorPane.setLeftAnchor(serviceText,30.0);

        AnchorPane.setTopAnchor(loginText,13.0);
        AnchorPane.setTopAnchor(passwordText,63.0);
        AnchorPane.setTopAnchor(serviceText,113.0);

        AnchorPane.setRightAnchor(loginField, 20.0);
        AnchorPane.setRightAnchor(passwordField, 20.0);
        AnchorPane.setRightAnchor(serviceField, 20.0);

        AnchorPane.setTopAnchor(loginField, 10.0);
        AnchorPane.setTopAnchor(passwordField, 60.0);
        AnchorPane.setTopAnchor(serviceField, 110.0);

        secondaryLayout.getChildren().addAll(
                loginField
                , passwordField
                , serviceField
                , loginText
                , passwordText
                , serviceText
                , okButton);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.setTitle("Adding the new password");
        newWindow.setScene(secondScene);

        okButton.setOnAction(actionEvent -> {
            System.out.println(1);
            newWindow.close();
        });

        // Set position of second window, related to primary window.
        newWindow.setX(mainStage.getX() + 200);
        newWindow.setY(mainStage.getY() + 100);
        newWindow.setResizable(false);

        newWindow.show();
    }

    @FXML
    void categoryShow(MouseEvent event) {

    }

    @FXML
    void deleteSelectedPassword(MouseEvent event) {

    }

    @FXML
    void deleteTheWholeCategory(MouseEvent event) {

    }

    @FXML
    void fileClose(ActionEvent event) {

    }

    @FXML
    void helpAbout(ActionEvent event) {

    }

    @FXML
    void passwordChosen(MouseEvent event) {

    }

}
