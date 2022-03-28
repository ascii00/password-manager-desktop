package com.github.passwordmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class PMController {

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
    void addNewPassword(MouseEvent event) {

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
