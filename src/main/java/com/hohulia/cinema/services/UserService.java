package com.hohulia.cinema.services;

import com.hohulia.cinema.connection.DBCPDataSource;
import com.hohulia.cinema.dao.implementation.MovieDaoImp;
import com.hohulia.cinema.dao.implementation.UserDaoImp;
import com.hohulia.cinema.dao.implementation.UserRoleDaoImp;
import com.hohulia.cinema.dao.interfaces.UserInterface;
import com.hohulia.cinema.dao.interfaces.UserRoleInterface;
import com.hohulia.cinema.entities.Movie;
import com.hohulia.cinema.entities.User;
import com.hohulia.cinema.entities.UserRole;
import com.hohulia.cinema.exceptions.CredentialsException;
import com.hohulia.cinema.exceptions.ServiceException;
import com.hohulia.cinema.utilities.Utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class UserService {

    UserInterface userDao;
    UserRoleInterface userRoleDao;


    public User signInUser(String email, String password) throws ServiceException {

        Connection conn = null;
        try {
            conn = DBCPDataSource.getConnection();
            userDao = new UserDaoImp(conn);
        } catch (SQLException e) {
            throw new ServiceException("UserService - Failed to get database connection");
        }

        User currentUser;
        try {
            currentUser = userDao.findByEmail(email);
        } catch (Exception e) {
            throw new ServiceException("Something go wrong");
        }

        if (currentUser == null) {
            throw new ServiceException("Username with " + email + " doesn't exist.");
        }

        if (!Objects.equals(currentUser.getPassword(), Utils.hashPassword(password))) {
            throw new ServiceException("Invalid password.");
        }

        try {
            conn.close();
        } catch (SQLException e) {
            throw new ServiceException("MovieService - Failed to close database connection");
        }

        return currentUser;
    }
    public int getRole(User user) throws ServiceException {

        Connection conn = null;
        try {
            conn = DBCPDataSource.getConnection();
            userRoleDao = new UserRoleDaoImp(conn);
        } catch (SQLException e) {
            throw new ServiceException("UserService - Failed to get database connection");
        }

        UserRole userRole;
        try {
            userRole = userRoleDao.findRoleByUserId(user.getUserId());
        } catch (Exception e) {
            throw new ServiceException("Something go wrong");
        }

        if (userRole.getRoleId() < 1) {
            throw new ServiceException("No role with id = " + userRole.getRoleId());
        }


        try {
            conn.close();
        } catch (SQLException e) {
            throw new ServiceException("MovieService - Failed to close database connection");
        }

        return userRole.getRoleId();
    }

    public User singUpUser(String email, String password) throws ServiceException, CredentialsException {
        Connection conn = null;

        //Validation
        try {
            Utils.validateEmail(email);
            Utils.validatePassword(password);
        } catch (CredentialsException e) {
            throw e;
        }

        try {
            conn = DBCPDataSource.getConnection();
            userDao = new UserDaoImp(conn);
            userRoleDao = new UserRoleDaoImp(conn);
        } catch (SQLException e) {
            throw new ServiceException("UserService - Failed to get database connection");
        }

        User currentUser;
        try {
            currentUser = userDao.findByEmail(email);
        } catch (Exception e) {
            throw new ServiceException("Something go wrong");
        }

        if (currentUser.getUserId() != 0) {
            System.out.println(currentUser);
            throw new CredentialsException("Account with this email already exists");
        }

        try {
            //Transaction
            userDao.beginTransaction();

            currentUser = new User(email, password); // id is set via autoincrement
            userDao.createUser(currentUser);
            currentUser = userDao.findByEmail(email);
            userRoleDao.addUserRole(new UserRole(currentUser.getUserId(), 1));

            userDao.endTransaction();
        } catch (Exception e) {
            try {
                userDao.rollbackTransaction();
            } catch (SQLException se){
                throw new ServiceException("Something go with rollback");
            }
            throw new ServiceException("Something go wrong");
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new ServiceException("MovieService - Failed to close database connection");
            }
        }

        return currentUser;
    }

}
