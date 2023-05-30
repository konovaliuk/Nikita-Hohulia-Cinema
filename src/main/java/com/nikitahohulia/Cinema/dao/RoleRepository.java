package com.nikitahohulia.Cinema.dao;

import com.nikitahohulia.Cinema.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}