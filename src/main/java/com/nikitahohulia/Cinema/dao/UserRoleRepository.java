package com.nikitahohulia.Cinema.dao;

import com.nikitahohulia.Cinema.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    List<UserRole> findAllById(Long userId);
}