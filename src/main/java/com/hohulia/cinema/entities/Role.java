package com.hohulia.cinema.entities;

import java.util.Objects;

public class Role
{
    private int roleId;
    private String roleName;

    public Role(){}
    public Role(int RoleId,String RoleName)
    {
        this.roleId = RoleId;
        this.roleName = RoleName;
    }

    public int getRoleId()
    {
        return roleId;
    }
    public void setRoleId(int value)
    {
        roleId = value;
    }
    public String getRoleName()
    {
        return roleName;
    }
    public void setRoleName(String value)
    {
        roleName = value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return getRoleId() == role.getRoleId() && getRoleName().equals(role.getRoleName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoleId(), getRoleName());
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}