package com.github.passwordmanager.controllers;

import com.github.passwordmanager.DAO;
import com.github.passwordmanager.entity.Password;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private static Stage mainStage;
    private static DAO dao;
    private static String currentCategory = "ALL";

    public static void setMainStage(Stage stage) {
        mainStage = stage;
    }

    public static void setDAO(DAO daoClass){
        dao = daoClass;
    }

    @FXML
    private MenuItem HelpAbout;

    @FXML
    private Button addNewPassword;

    @FXML
    private ListView<String> categoryList;

    @FXML
    private Button deleteSelectedPassword;

    @FXML
    private Button deleteTheWholeCategory;

    @FXML
    private MenuItem fileClose;

    @FXML
    private TableView<Password> passwordList;

    @FXML
    private TableColumn<Password, String> loginColumn;

    @FXML
    private TableColumn<Password, String> passwordColumn;

    @FXML
    private TableColumn<Password, String> serviceColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categoryList.getItems().addAll("ALL", "Games", "Email", "Social Networks", "Programs", "Job", "Others");

        loginColumn.setCellValueFactory(new PropertyValueFactory<Password, String>("login"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<Password, String>("pass"));
        serviceColumn.setCellValueFactory(new PropertyValueFactory<Password, String>("name"));

        List<Password> passwords = dao.getAllPasswords();
        passwordList.getItems().addAll(passwords);
    }

    @FXML
    void addNewPassword(MouseEvent event) throws Exception {

        AnchorPane secondaryLayout = new AnchorPane();
        Scene secondScene = new Scene(secondaryLayout, 300, 250);

        TextField loginField = new TextField();
        TextField passwordField = new TextField();
        TextField serviceField = new TextField();

        ChoiceBox<String> choiceCategory = new ChoiceBox<>();
        choiceCategory.getItems().addAll(categoryList.getItems());

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
        AnchorPane.setRightAnchor(choiceCategory, 20.0);

        AnchorPane.setTopAnchor(loginField, 10.0);
        AnchorPane.setTopAnchor(passwordField, 60.0);
        AnchorPane.setTopAnchor(serviceField, 110.0);
        AnchorPane.setTopAnchor(choiceCategory, 160.0);

        secondaryLayout.getChildren().addAll(
                loginField
                , passwordField
                , serviceField
                , loginText
                , passwordText
                , serviceText
                , okButton
                , choiceCategory);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.setTitle("Adding the new password");
        newWindow.setScene(secondScene);

        okButton.setOnAction(actionEvent -> {
            if (!loginField.getText().equals("") || !passwordField.getText().equals(""))
                dao.addNewPassword(
                        new Password(
                                serviceField.getText()
                                , choiceCategory.getValue()
                                , passwordField.getText()
                                , loginField.getText()));
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
        if (event.getClickCount() == 2) {
            String selectedCategory = categoryList.getSelectionModel().getSelectedItem();
            List<Password> passwords = dao.getPasswordsFromCategory(selectedCategory);

            currentCategory = selectedCategory;

            passwordList.getItems().clear();
            passwordList.getItems().addAll(passwords);
        }
    }

    @FXML
    void deleteSelectedPassword(MouseEvent event) {
        Password selectedItem = passwordList.getSelectionModel().getSelectedItem();
        if (selectedItem == null)
            return;
        dao.deletePassword(selectedItem);
        passwordList.getItems().remove(selectedItem);
    }

    @FXML
    void deleteTheWholeCategory(MouseEvent event) {

        String selectedCategory = categoryList.getSelectionModel().getSelectedItem();
        if (selectedCategory == null || selectedCategory.equals("ALL"))
            return;

        AnchorPane secondaryLayout = new AnchorPane();
        Scene secondScene = new Scene(secondaryLayout, 300, 100);

        Text text = new Text("Delete all passwords from category - " +
                categoryList.getSelectionModel().getSelectedItem() +
                "?");
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        AnchorPane.setLeftAnchor(text,10.0);
        AnchorPane.setTopAnchor(text,10.0);
        AnchorPane.setBottomAnchor(yesButton,20.0);
        AnchorPane.setBottomAnchor(noButton,20.0);
        AnchorPane.setLeftAnchor(noButton,100.0);
        AnchorPane.setRightAnchor(yesButton,100.0);

        secondaryLayout.getChildren().addAll(
                text
                ,yesButton
                ,noButton);

        Stage newWindow = new Stage();
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.setTitle("Delete the whole category");
        newWindow.setScene(secondScene);

        noButton.setOnAction(actionEvent -> {
            newWindow.close();
        });

        yesButton.setOnAction(actionEvent -> {
            List<Password> passwords = dao.getPasswordsFromCategory(selectedCategory);

            for (Password password : passwords)
                dao.deletePassword(password);

            passwords = dao.getPasswordsFromCategory(currentCategory);
            passwordList.getItems().clear();
            passwordList.getItems().addAll(passwords);
            newWindow.close();
        });

        newWindow.setX(mainStage.getX() + 200);
        newWindow.setY(mainStage.getY() + 100);
        newWindow.setResizable(false);

        newWindow.show();
    }

    @FXML
    void fileClose(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void helpAbout(ActionEvent event) {
        TextArea textArea = new TextArea();
        textArea.setText("bla bla bla");
        ScrollPane scrollPane = new ScrollPane(textArea);

        Scene secondScene = new Scene(scrollPane, 300, 200);
        Stage newWindow = new Stage();
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.setTitle("About");
        newWindow.setScene(secondScene);

        newWindow.setX(mainStage.getX() + 200);
        newWindow.setY(mainStage.getY() + 100);
        newWindow.setResizable(false);

        newWindow.show();
    }
}
