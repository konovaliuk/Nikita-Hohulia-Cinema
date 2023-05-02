package com.hohulia.cinema.JPA.dao.Implementation;

import com.hohulia.cinema.JPA.Entities.UserList;
import com.hohulia.cinema.JPA.dao.Interfaces.UserDaoInterface;
import com.hohulia.cinema.utilities.Utils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;
import java.util.function.Consumer;

public class UserDaoImplementation implements UserDaoInterface {

    private final EntityManager entityManager; //= Persistence.createEntityManagerFactory("Cinema").createEntityManager();

    public UserDaoImplementation(EntityManager em){
        entityManager = em;
    }
    @Override
    public UserList get(long id) {
        UserList user = entityManager.find(UserList.class, id);
        if(user != null)
            return user;
        else
            return null;
    }

    @Override
    public List<UserList> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM UserList e");
        return query.getResultList();
    }


    @Override
    public void save(UserList user) {
        String password = Utils.hashPassword(user.getPassword());
        user.setPassword(password);
        executeInsideTransaction(entityManager -> entityManager.persist(user));
    }

    @Override
    public void update(UserList user) {
        executeInsideTransaction(entityManager -> entityManager.merge(user));
    }

    @Override
    public void delete(long id) {
        UserList user = entityManager.getReference(UserList.class, id);
        if(user != null)
            executeInsideTransaction(entityManager -> entityManager.remove(user));
    }

    @Override
    public UserList getByEmail(String email) {
        Query query = entityManager.createQuery("SELECT u FROM UserList u WHERE u.email = :email");
        query.setParameter("email", email);
        return (UserList) query.getSingleResult();
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