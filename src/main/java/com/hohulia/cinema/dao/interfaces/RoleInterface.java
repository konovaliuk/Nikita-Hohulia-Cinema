package com.hohulia.cinema.dao.interfaces;

import com.hohulia.cinema.entities.Role;

import java.util.List;

public interface RoleInterface {
    Role findById(long id);
    Role findByRoleName(String roleName);
    List<Role> findAll();
    void addRole(Role role);
}
