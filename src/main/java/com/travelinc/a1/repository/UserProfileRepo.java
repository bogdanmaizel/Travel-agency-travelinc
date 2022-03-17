package com.travelinc.a1.repository;

import com.travelinc.a1.model.UserProfile;
import com.travelinc.a1.model.VacationPackage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserProfileRepo {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public void createUser(String username, String password) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        UserProfile userProfile = new UserProfile(username, password);
        em.persist(userProfile);

        em.getTransaction().commit();
        em.close();
    }

    public String getUserPassword(String username) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        UserProfile res = em
                .createQuery("SELECT up from UserProfile up WHERE up.username = :uname", UserProfile.class)
                .setParameter("uname", username)
                .getSingleResult();

        em.close();
        return res.getPassword();
    }

    public void updatePassword(String username, String password) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        UserProfile up = em
                .createQuery("SELECT up from UserProfile up WHERE up.username = :uname", UserProfile.class)
                .setParameter("uname", username)
                .getSingleResult();
        up.setPassword(password);

        em.merge(up);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteUserProfile(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        UserProfile up = em.find(UserProfile.class, id);
        em.remove(up);
        em.getTransaction().commit();
        em.close();
    }

    public void bookVacation(String uid, Long vid) {
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
