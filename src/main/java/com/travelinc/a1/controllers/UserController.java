package com.travelinc.a1.controllers;

import com.travelinc.a1.model.Destination;
import com.travelinc.a1.model.VacationPackage;
import com.travelinc.a1.service.UserService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.Date;

public class UserController {
    private final UserService userService;
    @FXML
    private TableView<VacationPackage> vpTable;
    @FXML
    private TableColumn<VacationPackage, String> vpNameCol;
    @FXML
    private TableColumn<VacationPackage, Destination> vpDestCol;
    @FXML
    private TableColumn<VacationPackage, Date> vpStartCol;
    @FXML
    private TableColumn<VacationPackage, Date> vpEndCol;
    @FXML
    private TableColumn<VacationPackage, Integer> vpPriceCol;
    @FXML
    private TableColumn<VacationPackage, Integer> vpStockCol;
    @FXML
    private TableColumn<VacationPackage, String> vpDescCol;
    @FXML
    private Label welcomeMessage;
    @FXML
    private ComboBox<Destination> destinationFilter;
    @FXML
    private TextField minPriceFilter;
    @FXML
    private TextField maxPriceFilter;

    public UserController() {
        this.userService = new UserService();
    }

    @FXML
    protected void setUsername() {
        welcomeMessage.setText("Hello, " + LoginController.loggedUser.getUsername() + "!");
    }

    @FXML
    protected void displayAllPackages() { // PACKAGE READ / CRUD
        System.out.println("[USER] Showing all vacation packages...");
        ObservableList<VacationPackage> allVP = userService.displayPackages();
        vpNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        vpDestCol.setCellValueFactory(new PropertyValueFactory<>("Destination"));
        vpStartCol.setCellValueFactory(new PropertyValueFactory<>("Start"));
        vpEndCol.setCellValueFactory(new PropertyValueFactory<>("End"));
        vpPriceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
        vpStockCol.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        vpDescCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        vpTable.setItems(allVP);
    }

    @FXML
    protected void displayBookedPackages() {
        ObservableList<VacationPackage> allVP = userService.displayBookedPackages(LoginController.loggedUser);
        vpNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        vpDestCol.setCellValueFactory(new PropertyValueFactory<>("Destination"));
        vpStartCol.setCellValueFactory(new PropertyValueFactory<>("Start"));
        vpEndCol.setCellValueFactory(new PropertyValueFactory<>("End"));
        vpPriceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
        vpStockCol.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        vpDescCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        vpTable.setItems(allVP);
    }

    @FXML
    protected void displayFilteredPackages() {
        System.out.println("Filtering packages...");
        Destination d = destinationFilter.getValue();
        Integer minPrice =
                minPriceFilter.getText().isEmpty() ? 0 :
                        Integer.parseInt(minPriceFilter.getText());
        Integer maxPrice = maxPriceFilter.getText().isEmpty() ? Integer.MAX_VALUE :
                Integer.parseInt(maxPriceFilter.getText());
        ObservableList<VacationPackage> filteredVP = userService.displayFilteredPackages(d, minPrice, maxPrice);
        vpNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        vpDestCol.setCellValueFactory(new PropertyValueFactory<>("Destination"));
        vpStartCol.setCellValueFactory(new PropertyValueFactory<>("Start"));
        vpEndCol.setCellValueFactory(new PropertyValueFactory<>("End"));
        vpPriceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
        vpStockCol.setCellValueFactory(new PropertyValueFactory<>("Stock"));
        vpDescCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        vpTable.setItems(filteredVP);
    }

    @FXML
    protected void backToLogin() throws IOException {
        HelloApplication.setRoot("hello-view");
    }

    @FXML
    protected void refreshDestinations() {
        System.out.println("Refreshing destinations list...");
        ObservableList<Destination> destinations = userService.getAllDestinations();
        destinationFilter.setItems(destinations);
    }

    @FXML
    protected void bookVacation() {
        userService.bookVacation(LoginController.loggedUser, vpTable.getSelectionModel().getSelectedItem());
    }

}
