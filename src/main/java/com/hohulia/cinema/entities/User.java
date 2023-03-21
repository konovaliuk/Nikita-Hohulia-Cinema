package com.hohulia.cinema.entities;

import java.util.Objects;

public class User
{
    private long userId;
    private String email;
    private String password;

    public User() {}
    public User(String Email, String Password)
    {
        this.email = Email;
        this.password = Password;
    }
    public User(long UserId, String Email, String Password)
    {
        this.userId = UserId;
        this.email = Email;
        this.password = Password;
    }

    public long getUserId()
    {
        return userId;
    }
    public void setUserId(long value)
    {
        userId = value;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String value)
    {
        email = value;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String value)
    {
        password = value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User userList = (User) o;
        return getUserId() == userList.getUserId() && getEmail().equals(userList.getEmail()) && getPassword().equals(userList.getPassword());
    }


    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getEmail(), getPassword());
    }

    @Override
    public String toString() {
        return "User_list{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}