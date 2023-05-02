package com.hohulia.cinema.JPA.dao.Implementation;

import com.hohulia.cinema.JPA.Entities.Role;
import com.hohulia.cinema.JPA.Entities.UserList;
import com.hohulia.cinema.JPA.Entities.UserRole;
import com.hohulia.cinema.JPA.dao.Interfaces.RoleDaoInterface;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;
import java.util.function.Consumer;

public class RoleDaoImplementetion implements RoleDaoInterface {

    private final EntityManager entityManager;

    public RoleDaoImplementetion(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Role get(long id) {
        Role role = entityManager.find(Role.class, id);
        return role;
    }

    @Override
    public List<Role> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Role e");
        return query.getResultList();
    }

    @Override
    public void save(Role role) {
        executeInsideTransaction(entityManager -> entityManager.persist(role));
    }

    @Override
    public void update(Role userRole) {
        executeInsideTransaction(entityManager -> entityManager.merge(userRole));
    }

    @Override
    public void delete(long id) {
        Role role = entityManager.getReference(Role.class, id);
        if(role != null)
            executeInsideTransaction(entityManager -> entityManager.remove(role));
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
