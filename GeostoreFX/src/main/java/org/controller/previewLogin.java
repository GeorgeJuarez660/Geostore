package org.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class previewLogin {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label checkingTitle;

    @FXML
    protected void sending() {

        if(!emailField.getText().isEmpty() && !passwordField.getText().isEmpty()){
            checkingTitle.setText("Login Status: SUCCESS");
        }
        else{
            checkingTitle.setText("Login Status: FAIL");
        }
    }

}