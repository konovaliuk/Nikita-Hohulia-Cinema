package com.hohulia.cinema.dao.implementation;

import com.hohulia.cinema.dao.interfaces.RoleInterface;
import com.hohulia.cinema.entities.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImp implements RoleInterface {

    private final Connection connection;

    enum COLUMN {
        ROLE_ID("role_id"), ROLE_NAME("role_name");
        public final String NAME;
        COLUMN(String name) {
            this.NAME = name;
        }
    }

    private static final String SELECT_BY_ID = "SELECT * FROM role WHERE role_id =  ?";
    private static final String SELECT_BY_ROLE_NAME = "SELECT * FROM role WHERE role_name = ?";
    private static final String ADD_ROLE = "INSERT INTO `cinema`.`role` (`role_id`, `role_name`) VALUES (?, ?);";
    private static final String FIND_ALL = "SELECT * FROM role";


    public RoleDaoImp(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public Role getRole(ResultSet resultSet) throws SQLException{
        int role_id = resultSet.getInt(COLUMN.ROLE_ID.NAME);
        String role_name = resultSet.getString(COLUMN.ROLE_NAME.NAME);
        return new Role(role_id, role_name);
    }


    @Override
    public Role findById (long id) {
        Role role = new Role();

        try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ID)) {
            stmt.setLong(1, id);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    role = getRole(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }

    @Override
    public Role findByRoleName(String roleName) {
        Role role = new Role();

        try (PreparedStatement stmt = connection.prepareStatement(SELECT_BY_ROLE_NAME)) {
            stmt.setString(1, roleName);
            try (ResultSet resultSet = stmt.executeQuery()) {
                resultSet.next();
                role = getRole(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }

    @Override
    public List<Role> findAll() {
        List<Role> roleList = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL)) {

            while (resultSet.next()) {
                Role role = getRole(resultSet);
                roleList.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roleList;
    }

    @Override
    public void addRole(Role role) {
        try (PreparedStatement stmt = connection.prepareStatement(ADD_ROLE)) {
            stmt.setInt(1, role.getRoleId());
            stmt.setString(2, role.getRoleName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
