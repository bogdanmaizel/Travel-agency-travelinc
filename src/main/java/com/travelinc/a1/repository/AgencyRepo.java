package com.travelinc.a1.repository;

import com.travelinc.a1.model.Destination;
import com.travelinc.a1.model.VacationPackage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;
import java.util.List;

public class AgencyRepo {
    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public void addDestination(String name) {
        Destination d = new Destination(name);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(d);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteDestination(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Destination del = em.find(Destination.class, id);
        em.remove(del);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteDestination(Destination d) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(d);
        em.getTransaction().commit();
        em.close();
    }

    public List<Destination> getAllDestinations() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Destination> destinations = em.createQuery("select d from Destination d").getResultList();
        em.close();
        return destinations;
    }

    public Integer computeBookedPeople(VacationPackage vp) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        return em.createQuery(
                "SELECT COUNT(up) from UserProfile up JOIN VacationPackage vpk ON vpk.id = up.id WHERE vpk.id = :vpid")
                .setParameter("vpid",vp.getId())
                .getFirstResult();
    }

    public void createPackage(String name, Destination destination, Date start, Date end, Integer price, Integer stock, String description) {
        VacationPackage vp = new VacationPackage(name, destination, start, end, price, stock, description);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(vp);
        em.getTransaction().commit();
        em.close();
    }

    public List<VacationPackage> getAllPackages() {
        List<VacationPackage> packages;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        packages = em.createQuery("SELECT vp FROM VacationPackage vp").getResultList();
        em.close();
        return packages;
    }

    public void deletePackage(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        VacationPackage toDel = em.find(VacationPackage.class, id);
        em.remove(toDel);
        em.getTransaction().commit();
        em.close();
    }

    public void editPackage(Long id, String name, Destination destination, Date start, Date end, Integer price, Integer stock, String description) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        VacationPackage toEdit = em.find(VacationPackage.class, id);
        toEdit.setName(name);
        toEdit.setDescription(description);
        toEdit.setDestination(destination);
        toEdit.setStart(start);
        toEdit.setEnd(end);
        toEdit.setPrice(price);
        toEdit.setStock(stock);

        em.getTransaction().commit();
        em.close();
    }
}
