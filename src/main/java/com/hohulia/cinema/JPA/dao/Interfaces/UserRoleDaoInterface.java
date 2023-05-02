package com.hohulia.cinema.JPA.dao.Interfaces;

import com.hohulia.cinema.JPA.Entities.UserRole;
import com.hohulia.cinema.entities.User;

import java.util.List;

public interface UserRoleDaoInterface extends DAO<UserRole> {
    List<UserRole> getAllWithRole(Integer role);
}
