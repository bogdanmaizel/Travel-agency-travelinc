package com.travelinc.a1.service;

import com.travelinc.a1.model.Destination;
import com.travelinc.a1.model.UserProfile;
import com.travelinc.a1.model.VacationPackage;
import com.travelinc.a1.repository.UserProfileRepo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private final UserProfileRepo userProfileRepo;

    public UserService() {
        this.userProfileRepo = new UserProfileRepo();
    }

    public void createUserProfile(String username, String password) {
        try {
            userProfileRepo.createUser(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserProfile findUser(String username) {
        UserProfile found = null;
        try {
            found = userProfileRepo.findUser(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return found;
    }

    public void changePassword(String username, String password) {
        try {
            userProfileRepo.updatePassword(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void bookVacation(UserProfile up, VacationPackage vp) {
        try {
            userProfileRepo.bookVacation(up.getUsername(), vp.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<VacationPackage> displayPackages() { //DESTINATION READ
        List<VacationPackage> allDestinations;
        ObservableList<VacationPackage> displayVP = null;
        try {
            allDestinations = userProfileRepo.getAvblPackages();
            displayVP = FXCollections.observableArrayList(allDestinations);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return displayVP;
    }

    public ObservableList<VacationPackage> displayBookedPackages(UserProfile up) {
        ObservableList<VacationPackage> allPacks = null;
        try {
            allPacks = FXCollections.observableArrayList(up.getPackages());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allPacks;
    }

    public ObservableList<VacationPackage> displayFilteredPackages(Destination destination, Integer minPrice, Integer maxPrice) {
        List<VacationPackage> allDestinations;
        ObservableList<VacationPackage> displayVP = null;
        try {
            allDestinations = userProfileRepo.getAvblPackages()
                    .stream()
                    .filter(t -> t.getDestination().equals(destination))
                    .filter(t -> t.getPrice() >= minPrice && t.getPrice() <= maxPrice)
                    .collect(Collectors.toList());
            displayVP = FXCollections.observableArrayList(allDestinations);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return displayVP;
    }

    public boolean checkLogin(String username, String password) {
        boolean ok = false;
        try {
             String up = userProfileRepo.getUserPassword(username);
            if (up.equals(password)) ok = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }

    public ObservableList<Destination> getAllDestinations() {
        List<Destination> destinations;
        ObservableList<Destination> destinationsFilter = null;
        try {
            destinations = userProfileRepo.getAllDestinations();
            destinationsFilter = FXCollections.observableArrayList(destinations);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return destinationsFilter;
    }
}
