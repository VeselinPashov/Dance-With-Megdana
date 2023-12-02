package com.Megdana.Dance.with.Megdana.domain.dto.view;

import com.Megdana.Dance.with.Megdana.domain.entities.Role;

import java.util.Set;

public class UserProfileModel {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Set<Role> roles;

    public UserProfileModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
