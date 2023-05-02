package com.hohulia.cinema.JPA.dao.Interfaces;

import com.hohulia.cinema.JPA.Entities.UserList;

import java.util.List;

public interface UserDaoInterface extends DAO<UserList>{
    UserList getByEmail(String email);

}

