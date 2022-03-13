package com.travelinc.a1.repository;

import com.travelinc.a1.model.User;
import com.travelinc.a1.model.UserProfile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class UserProfileRepo {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");



    public static void create(String username, String password) {
        User user = new User(username,password);
        UserProfile userProfile = new UserProfile(user);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.persist(userProfile);
        em.getTransaction().commit();
        em.close();
    }

    public static List findByName(String username) {
        EntityManager em = emf.createEntityManager();
        Query searchByName = em.createQuery(
                "SELECT UP.user from UserProfile UP WHERE UP.user.username LIKE :search", UserProfile.class
        );
        return searchByName.getResultList();
    }

    public static void updateUserProfile() {

    }

    public static void deleteUserProfile(String username) {

    }
}
