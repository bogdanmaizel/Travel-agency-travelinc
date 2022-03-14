package com.travelinc.a1.service;

import com.travelinc.a1.model.Destination;
import com.travelinc.a1.model.VacationPackage;
import com.travelinc.a1.repository.AgencyRepo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.util.List;

public class AgencyService {
    AgencyRepo agencyRepo;

    public AgencyService() {
        this.agencyRepo = new AgencyRepo();
    }

    public void createDestination(String name) { //DESTINATION CREATE
        try {
            agencyRepo.addDestination(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Destination> displayAllDestinations() { //DESTINATION READ
        List<Destination> allDestinations;
        ObservableList<Destination> displayDest = null;
        try {
            allDestinations = agencyRepo.getAllDestinations();
            displayDest = FXCollections.observableArrayList(allDestinations);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return displayDest;
    }

    public void deleteDestination(Destination d) { //DESTINATION DELETE
        try {
            for (VacationPackage vp :d.getPacksList()) {
                agencyRepo.deletePackage(vp.getId());
            }
            agencyRepo.deleteDestination(d.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createNewPackage(String name, Destination destination, Date start, Date end, Integer price, Integer stock, String description) {
        try {
            agencyRepo.createPackage(name,destination,start,end,price,stock,description);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<VacationPackage> displayPackages() {
        List<VacationPackage> allPacks;
        ObservableList<VacationPackage> allPacksDisplaying = null;
        try {
           allPacks = agencyRepo.getAllPackages();
            allPacksDisplaying = FXCollections.observableArrayList(allPacks);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allPacksDisplaying;
    }

    public void updatePackage(VacationPackage oldPackage, String name, Destination destination, Date start, Date end, Integer price, Integer stock, String description) {
        try {
            agencyRepo.editPackage(oldPackage.getId(), name, destination, start, end, price, stock, description);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePackage(VacationPackage vp) {
        try {
            agencyRepo.deletePackage(vp.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer getBookedPeople(VacationPackage vp) {
        int num = 0;
        try {
            num = vp.getUserProfiles().size();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

}
