package com.hohulia.cinema.JPA.dao.Implementation;

import com.hohulia.cinema.JPA.Entities.Booking;
import com.hohulia.cinema.JPA.Entities.Movie;
import com.hohulia.cinema.JPA.dao.Interfaces.BookingDaoInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;
import java.util.function.Consumer;

public class BookingDaoImplementetion implements BookingDaoInterface {
    private final EntityManager entityManager;

    public BookingDaoImplementetion(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Booking get(long id) {
        Booking booking = entityManager.find(Booking.class, id);
        return booking;
    }

    @Override
    public List<Booking> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Booking e");
        return query.getResultList();
    }

    @Override
    public void save(Booking booking) {
        executeInsideTransaction(entityManager -> entityManager.persist(booking));
    }

    @Override
    public void update(Booking booking) {
        executeInsideTransaction(entityManager -> entityManager.merge(booking));
    }

    @Override
    public void delete(long id) {
        Booking booking = entityManager.getReference(Booking.class, id);
        if(booking != null)
            executeInsideTransaction(entityManager -> entityManager.remove(booking));
    }

    @Override
    public List<Booking> getByUserId(long userId) {
        Query query = entityManager.createQuery("SELECT e FROM Booking e WHERE e.userId = :userID");
        query.setParameter("userID", userId);
        return query.getResultList();
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
