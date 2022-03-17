package com.travelinc.a1.controllers;

import com.travelinc.a1.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import lombok.Getter;

import java.io.IOException;

public class LoginController {

    UserService userService;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label loginStatus;
    @FXML
    private PasswordField passwordResetField;

    protected static String loggedUsername;

    public LoginController() {
        this.userService = new UserService();
    }

    @FXML
    protected void enterTravelAgency() throws IOException {
        HelloApplication.setRoot("agency-controller");
    }

    @FXML
    protected void register() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        userService.createUserProfile(username, password);

        loginStatus.setTextFill(Color.GREEN);
        loginStatus.setText("Register successful!");
    }

    @FXML
    protected void login() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (userService.checkLogin(username, password)) {
            HelloApplication.setRoot("user-controller");
        } else {
            loginStatus.setTextFill(Color.RED);
            loginStatus.setText("Login failed! Try again.");
        }
    }

    @FXML
    protected void resetPassword() {
        boolean check = passwordResetField.isVisible();
        String currentUsername = usernameField.getText();
        String currentPassword = passwordField.getText();
        String newPassword = passwordResetField.getText();
        if (check) {
            if (userService.checkLogin(currentUsername,currentPassword)) {
                userService.changePassword(currentUsername, newPassword);
                loginStatus.setTextFill(Color.GREEN);
                loginStatus.setText("Password changed!");
                passwordResetField.setVisible(false);
                passwordField.setPromptText("Password");
            }
        }
        else {
            passwordResetField.setVisible(true);
            passwordField.setPromptText("Old password");
        }
    }
}