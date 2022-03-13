package com.travelinc.a1.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    protected void enterTravelAgency() throws IOException{
        HelloApplication.setRoot("agency-controller");
    }

    @FXML
    protected void enterUserProfile() {

    }

}