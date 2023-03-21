package com.hohulia.cinema.entities;

import java.util.Objects;

public class UserRole
{
    private long userId;
    private int roleId;

    public UserRole() {}
    public UserRole(long UserId, int RoleId)
    {
        this.userId = UserId;
        this.roleId = RoleId;
    }

    public long getUserId()
    {
        return userId;
    }
    public void setUserId(long value)
    {
        userId = value;
    }
    public int getRoleId()
    {
        return roleId;
    }
    public void setRoleId(int value)
    {
        roleId = value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return getUserId() == userRole.getUserId() && getRoleId() == userRole.getRoleId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getRoleId());
    }

    @Override
    public String toString() {
        return "User_role{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}