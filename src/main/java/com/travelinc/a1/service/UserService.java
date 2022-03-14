package com.travelinc.a1.service;

import com.travelinc.a1.repository.UserProfileRepo;

public class UserService {

    UserProfileRepo userProfileRepo;

    public UserService() {
        this.userProfileRepo = new UserProfileRepo();
    }

    public void createUser(String username, String password) {
        try {
            userProfileRepo.createUser(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
