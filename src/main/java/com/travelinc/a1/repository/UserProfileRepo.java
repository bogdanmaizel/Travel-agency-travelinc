package com.travelinc.a1.repository;

import com.travelinc.a1.model.UserProfile;
import com.travelinc.a1.model.VacationPackage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class UserProfileRepo {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public void createUser(String username, String password) {
        UserProfile userProfile = new UserProfile(username, password);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(userProfile);
        em.getTransaction().commit();
        em.close();
    }

    public List findByName(String username) {
        EntityManager em = emf.createEntityManager();
        Query searchByName = em.createQuery(
                "SELECT UP from UserProfile UP WHERE UP.username LIKE :search", UserProfile.class
        );
        return searchByName.getResultList();
    }

    public void updateUserProfile(Long id, String username, String password) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        UserProfile up = em.find(UserProfile.class, id);
        up.setUsername(username);
        up.setPassword(password);
        em.merge(up);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteUserProfile(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(UserProfile.class, id));
        em.getTransaction().commit();
        em.close();
    }

    public void bookVacation(Long uid, Long vid) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        VacationPackage vp = em.find(VacationPackage.class, vid);
        UserProfile up = em.find(UserProfile.class, uid);
        up.getPackages().add(vp);
        vp.getUserProfiles().add(up);
        em.merge(up);
        em.merge(vp);
        em.getTransaction().commit();
        em.close();

    }

}
