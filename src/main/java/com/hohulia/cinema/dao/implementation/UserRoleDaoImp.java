package com.hohulia.cinema.dao.implementation;

import com.hohulia.cinema.dao.interfaces.UserRoleInterface;
import com.hohulia.cinema.entities.UserRole;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDaoImp implements UserRoleInterface {
    private final Connection connection;

    enum COLUMN {
        USER_ID("user_id"), ROLE_ID("role_id");
        public final String NAME;
        COLUMN(String name) {
            this.NAME = name;
        }
    }

    private static final String SELECT_USER_ROLE_BY_ID = "SELECT * FROM user_role WHERE user_role.user_id = ?";
    private static final String FIND_ALL_WITH_ROLE_ID = "SELECT * FROM user_role WHERE user_role.role_id =  ?";
    private static final String FIND_ALL = "SELECT * FROM user_role";
    private static final String DELETE_BY_ID = "DELETE FROM `cinema`.`user_role` WHERE user_role.user_id = ?";
    private static final String UPDATE = "UPDATE `cinema`.`user_role` SET `role_id` = ? WHERE (`user_id` = ?)";
    private static final String ADD_USER_ROLE = "INSERT INTO `cinema`.`user_role` (`user_id`, `role_id`) VALUES (?, ?)";

    public UserRoleDaoImp(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public UserRole getUserRole(ResultSet resultSet) throws SQLException{
        long userId = resultSet.getLong(COLUMN.USER_ID.NAME);
        int roleId = resultSet.getInt(COLUMN.ROLE_ID.NAME);
        return new UserRole(userId, roleId);
    }


    @Override
    public UserRole findRoleByUserId(long id) {
        UserRole userRole = null;

        try (PreparedStatement stmt = connection.prepareStatement(SELECT_USER_ROLE_BY_ID)) {
            stmt.setLong(1, id);
            try (ResultSet resultSet = stmt.executeQuery()) {
                resultSet.next();
                userRole = getUserRole(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userRole;
    }

    @Override
    public List<UserRole> findByRoleId(long role) {
        List<UserRole> userRoles = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(FIND_ALL_WITH_ROLE_ID)) {
            stmt.setLong(1, role);
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    UserRole userRole = getUserRole(resultSet);
                    userRoles.add(userRole);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userRoles;
    }

    @Override
    public List<UserRole> findAll() {
        List<UserRole> userRoles = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL)) {

            while (resultSet.next()) {
                UserRole userRole = getUserRole(resultSet);
                userRoles.add(userRole);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userRoles;
    }

    @Override
    public void deleteById(long id) {
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_BY_ID)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUserRole(UserRole userRole) {
        try (PreparedStatement stmt = connection.prepareStatement(ADD_USER_ROLE)) {
            stmt.setLong(1, userRole.getUserId());
            stmt.setInt(2, userRole.getRoleId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateByUserId (long id, int roleId) {
        try (PreparedStatement stmt = connection.prepareStatement(UPDATE)) {
            stmt.setInt(1, roleId);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
