package com.hohulia.cinema.JPA.dao.Implementation;

import com.hohulia.cinema.JPA.Entities.Movie;
import com.hohulia.cinema.JPA.Entities.Schedule;
import com.hohulia.cinema.JPA.dao.Interfaces.ScheduleDaoInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.time.Instant;
import java.util.List;
import java.util.function.Consumer;

public class ScheduleDaoImplementation implements ScheduleDaoInterface {
    private final EntityManager entityManager;

    public ScheduleDaoImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Schedule get(long id) {
        Schedule schedule = entityManager.find(Schedule.class, (int)id);
        return schedule;
    }

    @Override
    public List<Schedule> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Schedule e");
        return query.getResultList();
    }

    @Override
    public void save(Schedule Schedule) {
        executeInsideTransaction(entityManager -> entityManager.persist(Schedule));
    }

    @Override
    public void update(Schedule Schedule) {
        executeInsideTransaction(entityManager -> entityManager.merge(Schedule));
    }

    @Override
    public void delete(long id) {
        Schedule Schedule = entityManager.getReference(Schedule.class, (int)id);
        if(Schedule != null)
            executeInsideTransaction(entityManager -> entityManager.remove(Schedule));
    }



    @Override
    public List<Schedule> getAllRelevant() {
        Query query = entityManager.createQuery("SELECT e FROM Schedule e WHERE e.startTime >= :now");
        Instant now = Instant.now();
        query.setParameter("now", now);
        return query.getResultList();
    }

    @Override
    public List<Schedule> getAllWithMovieId(int movieId) {
        Query query = entityManager.createQuery("SELECT e FROM Schedule e WHERE e.movieId = :movie");
        query.setParameter("movie", movieId);
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
