package com.hohulia.cinema.JPA.dao.Implementation;

import com.hohulia.cinema.JPA.Entities.UserList;
import com.hohulia.cinema.JPA.Entities.UserRole;
import com.hohulia.cinema.JPA.dao.Interfaces.UserRoleDaoInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;
import java.util.function.Consumer;

public class UserRoleDaoImplementation implements UserRoleDaoInterface {
    private final EntityManager entityManager;

    public UserRoleDaoImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public UserRole get(long id) {
        UserRole userRole = entityManager.find(UserRole.class, id);
        return userRole;
    }

    @Override
    public List<UserRole> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM UserRole e");
        return query.getResultList();
    }

    @Override
    public void save(UserRole userRole) {
        executeInsideTransaction(entityManager -> entityManager.persist(userRole));
    }

    @Override
    public void update(UserRole userRole) {
        executeInsideTransaction(entityManager -> entityManager.merge(userRole));
    }

    @Override
    public void delete(long id) {
        UserList user = entityManager.getReference(UserList.class, id);
        if(user != null)
            executeInsideTransaction(entityManager -> entityManager.remove(user));
    }

    @Override
    public List<UserRole> getAllWithRole(Integer role) {
        Query query = entityManager.createQuery("SELECT u FROM UserRole u WHERE u.role_id = :role");
        query.setParameter("role", role);
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
