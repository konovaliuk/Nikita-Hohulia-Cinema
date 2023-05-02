package com.hohulia.cinema.JPA.dao.Implementation;

import com.hohulia.cinema.JPA.Entities.Movie;
import com.hohulia.cinema.JPA.Entities.Role;
import com.hohulia.cinema.JPA.dao.Interfaces.MovieDaoInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;
import java.util.function.Consumer;

public class MovieDaoImplementetion implements MovieDaoInterface {
    private final EntityManager entityManager;

    public MovieDaoImplementetion(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Movie get(long id) {
        Movie movie = entityManager.find(Movie.class, (int)id);
        return movie;
    }

    @Override
    public List<Movie> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Movie e");
        return query.getResultList();
    }

    @Override
    public void save(Movie movie) {
        executeInsideTransaction(entityManager -> entityManager.persist(movie));
    }

    @Override
    public void update(Movie movie) {
        executeInsideTransaction(entityManager -> entityManager.merge(movie));
    }

    @Override
    public void delete(long id) {
        Movie movie = entityManager.getReference(Movie.class, (int)id);
        if(movie != null)
            executeInsideTransaction(entityManager -> entityManager.remove(movie));
    }

    @Override
    public Movie getByTitle(String title) {
        Query query = entityManager.createQuery("SELECT e FROM Movie e WHERE e.title = :title");
        query.setParameter("title", title);
        return (Movie)query.getSingleResult();
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
