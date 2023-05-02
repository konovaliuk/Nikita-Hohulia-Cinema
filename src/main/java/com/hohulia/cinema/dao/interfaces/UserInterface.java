package com.hohulia.cinema.dao.interfaces;

import com.hohulia.cinema.entities.*;

import java.sql.SQLException;
import java.util.List;

public interface UserInterface {
    User findById(long id);
    User findByEmail(String email);
    List<User> findAll();
    void createUser(User user);
    void deleteById(long id);
    void beginTransaction() throws SQLException;
    void endTransaction() throws SQLException;
    void rollbackTransaction() throws SQLException;
}
