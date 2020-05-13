package com.example.system.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "role", targetEntity = User.class)
    @JsonIgnore
    private List<User> users;

    Role() {
        super();
    }
    Role(List<User> users, String roleName) {
        this.users = users;
        this.roleName = roleName;
    }
/**
 * @return the users
 */
public List<User> getUsers() {
    return users;
}/**
 * @return the roleId
 */
public int getRoleId() {
    return roleId;
}/**
 * @param roleId the roleId to set
 */
public void setRoleId(int roleId) {
    this.roleId = roleId;
}/**
 * @param users the users to set
 */
public void setUsers(List<User> users) {
    this.users = users;
}
    

}