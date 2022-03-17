package com.travelinc.a1.controllers;


import com.travelinc.a1.model.Destination;
import com.travelinc.a1.model.VacationPackage;
import com.travelinc.a1.service.AgencyService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.Date;

public class AgencyController {
    private final AgencyService as;
    //for new
    @FXML
    private TextField vpNewName;
    @FXML
    private TextField vpNewPrice;
    @FXML
    private TextField vpNewStock;
    @FXML
    private TextArea vpNewDesc;
    @FXML
    private DatePicker vpNewStart;
    @FXML
    private DatePicker vpNewEnd;
    @FXML
    private ComboBox<Destination> vpNewDest;

    @FXML
    private TextField destinationName;
    @FXML
    private TableView<Destination> destNameTable;
    @FXML
    private TableColumn<Destination, String> destNameCol;
    @FXML
    private TableView<VacationPackage> vpTable;
    @FXML
    private TableColumn<VacationPackage,String> vpNameCol;
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
    private TableColumn<VacationPackage, String> vpStatusCol;

    public AgencyController() {
        this.as = new AgencyService();
    }

    @FXML
    protected void onBackButtonClick() throws IOException {
        HelloApplication.setRoot("hello-view");
    }

    @FXML
    protected void createDestinationClick() { //DESTINATION CREATE
        System.out.println("Trying to create destination...");
        if (! destinationName.getText().isEmpty())
            as.createDestination(destinationName.getText());
        destinationName.setText("");
    }

    @FXML
    protected void displayAllDestinations() { //DESTINATION READ
        System.out.println("Displaying destinations...");
        ObservableList<Destination> allDest = as.displayAllDestinations();
        destNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        destNameTable.setItems(allDest);
    }

    @FXML
    protected void deleteDestination() { //DESTINATION DELETE
        System.out.println("Deleting destination...");
        as.deleteDestination(destNameTable.getSelectionModel().getSelectedItem());
    }

    @FXML
    protected void refreshDestinations() {
        System.out.println("Refreshing destinations list...");
        ObservableList<Destination> destinations = as.displayAllDestinations();
        vpNewDest.setItems(destinations);
    }

    @FXML
    protected void createNewPackage() { // PACKAGE CREATE / CRUD
        System.out.println("Creating new package...");
        as.createNewPackage(
                vpNewName.getText(),
                vpNewDest.getValue(),
                Date.valueOf(vpNewStart.getValue()),
                Date.valueOf(vpNewEnd.getValue()),
                Integer.valueOf(vpNewPrice.getText()),
                Integer.valueOf(vpNewStock.getText()),
                vpNewDesc.getText());
    }

    @FXML
    protected void displayAllPackages() { // PACKAGE READ / CRUD
        System.out.println("Showing all vacation packages...");
        ObservableList<VacationPackage> allVP = as.displayPackages();
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
    protected void getSelection() { //PACKAGE UPDATE_1 / CRUD
        System.out.println("Selecting package to update / delete...");
        VacationPackage toEdit = vpTable.getSelectionModel().getSelectedItem();
        vpNewName.setText(toEdit.getName());
        vpNewDest.setValue(toEdit.getDestination());
        vpNewStart.setValue(toEdit.getStart().toLocalDate());
        vpNewEnd.setValue(toEdit.getEnd().toLocalDate());
        vpNewPrice.setText(Integer.toString(toEdit.getPrice()));
        vpNewStock.setText(Integer.toString(toEdit.getStock()));
        vpNewDesc.setText(toEdit.getDescription());
    }

    @FXML
    protected void updatePackage() { //PACKAGE UPDATE_2 / CRUD
        System.out.println("Updating package...");
        VacationPackage oldPackage = vpTable.getSelectionModel().getSelectedItem();
        as.updatePackage(
                oldPackage,
                vpNewName.getText(),
                vpNewDest.getValue(),
                Date.valueOf(vpNewStart.getValue()),
                Date.valueOf(vpNewEnd.getValue()),
                Integer.valueOf(vpNewPrice.getText()),
                Integer.valueOf(vpNewStock.getText()),
                vpNewDesc.getText()
                );
    }

    @FXML
    protected void deletePackage() { // PACKAGE DELETE / CRUD
        as.deletePackage(vpTable.getSelectionModel().getSelectedItem());
    }

}
