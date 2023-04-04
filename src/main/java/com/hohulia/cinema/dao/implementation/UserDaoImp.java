package com.hohulia.cinema.dao.implementation;

import com.hohulia.cinema.dao.interfaces.UserInterface;
import com.hohulia.cinema.entities.User;
import com.hohulia.cinema.exceptions.ServiceException;
import com.hohulia.cinema.utilities.Utils;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImp implements UserInterface {

    private final Connection connection;

    enum COLUMN {
        ID("user_id"), EMAIL("email"), PASSWORD ("password");
        public final String NAME;
        COLUMN(String name) {
            this.NAME = name;
        }
    }

    private static final String selectUserById = "SELECT * FROM user_list WHERE user_list.user_id = ?";
    private static final String selectUserByEmail = "SELECT * FROM user_list WHERE user_list.email = ?";
    private static final String createUser = "INSERT INTO `cinema`.`user_list` (`email`, `password`) VALUES (?, ?)";
    private static final String findAll = "SELECT * FROM `cinema`.`user_list`";
    private static final String deleteById = "DELETE FROM `cinema`.`user_list` WHERE user_list.user_id = ?";


    public UserDaoImp(Connection connection) throws SQLException{
        this.connection = connection;
    }

    public User getUser(ResultSet resultSet) throws SQLException{
        long id = resultSet.getLong(COLUMN.ID.NAME);
        String email = resultSet.getString(COLUMN.EMAIL.NAME);
        String password = resultSet.getString(COLUMN.PASSWORD.NAME);
        return new User(id, email, password);
    }
    @Override
    public void beginTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }
    @Override
    public void endTransaction() throws SQLException {
        connection.commit();
        connection.setAutoCommit(true);
    }
    @Override
    public void rollbackTransaction() throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
    }

    @Override
    public User findById(long id) {

        User user = new User();

        try (PreparedStatement stmt = connection.prepareStatement(selectUserById)) {
            stmt.setLong(1, id);
            try (ResultSet resultSet = stmt.executeQuery()) {
                resultSet.next();
                user = getUser(resultSet);
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User findByEmail(String email) {
        User user = new User();

        try (PreparedStatement stmt = connection.prepareStatement(selectUserByEmail)) {
            stmt.setString(1, email);
            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    user = getUser(resultSet);
                }
            } catch (RuntimeException e){
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public List<User> findAll() {

        List<User> userList = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(findAll)) {
            while (resultSet.next()) {
                User user = getUser(resultSet);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public void createUser(User user) {
        if (user.getUserId()!=0)
            throw new IllegalArgumentException("User_id must not be specified, it will autoincrement automatically");

        try (PreparedStatement stmt = connection.prepareStatement(createUser)) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, Utils.hashPassword(user.getPassword()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(long id) {
        try (PreparedStatement stmt = connection.prepareStatement(deleteById)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
