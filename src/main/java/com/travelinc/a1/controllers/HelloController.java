package com.travelinc.a1.controllers;

import com.travelinc.a1.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    UserService userService;

    public HelloController() {
        this.userService = new UserService();
    }

    @FXML
    protected void enterTravelAgency() throws IOException {
        HelloApplication.setRoot("agency-controller");
    }

    @FXML
    protected void goToRegister() throws IOException {
        HelloApplication.setRoot("register-view");
    }

    @FXML
    protected void enterUserProfile() {

    }

}