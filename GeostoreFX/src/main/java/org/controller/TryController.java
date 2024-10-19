package org.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TryController {

    @FXML
    private Label titleHiding;

    @FXML
    protected void hideTitle() {

        if(titleHiding.getOpacity() == 0){
            titleHiding.setOpacity(2);
        }
        else{
            titleHiding.setOpacity(0);
        }
    }
}