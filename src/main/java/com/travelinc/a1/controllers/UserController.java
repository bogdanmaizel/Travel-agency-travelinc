package com.travelinc.a1.controllers;

import com.travelinc.a1.model.VacationPackage;
import com.travelinc.a1.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.io.IOException;

public class UserController {
    private final UserService userService;
    @FXML
    private TableView<VacationPackage> vpTable;

    @FXML
    private Label welcomeMessage = new Label("Hello, " + LoginController.loggedUsername);

    public UserController() {
        this.userService = new UserService();
    }



    @FXML
    protected void backToLogin() throws IOException {
        HelloApplication.setRoot("hello-view");
    }

}
