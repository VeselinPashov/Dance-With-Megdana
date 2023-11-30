package com.Megdana.Dance.with.Megdana.domain.dto.models;

import com.Megdana.Dance.with.Megdana.domain.entities.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;

import java.util.Set;

public class UserModel {

    private Long id;
    private String userName;
    private String password;
    private String email;
    private Set<RoleModel> roles;
    private String firstName;
    private String lastName;

    public UserModel() {
    }

    public boolean isValid () {
        return this.id != null;
    }

    public Long getId() {
        return id;
    }

    public UserModel setId(long id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserModel setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<RoleModel> getRoles() {
        return roles;
    }

    public UserModel setRoles(Set<RoleModel> roles) {
        this.roles = roles;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
