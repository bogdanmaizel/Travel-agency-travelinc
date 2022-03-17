package com.travelinc.a1.service;

import com.travelinc.a1.model.UserProfile;
import com.travelinc.a1.model.VacationPackage;
import com.travelinc.a1.repository.UserProfileRepo;

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
}
