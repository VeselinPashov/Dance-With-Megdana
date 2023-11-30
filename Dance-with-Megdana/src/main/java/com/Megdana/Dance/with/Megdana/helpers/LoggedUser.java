package com.Megdana.Dance.with.Megdana.helpers;

import com.Megdana.Dance.with.Megdana.domain.dto.models.RoleModel;
import com.Megdana.Dance.with.Megdana.domain.enums.RoleName;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class LoggedUser {
    private Long id;
    private String username;
    private Set<RoleModel> roles;

    public LoggedUser() {
    }

    public Long getId() {
        return id;
    }

    public LoggedUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public LoggedUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public Set<RoleModel> getRoles() {
        return roles;
    }

    public LoggedUser setRoles(Set<RoleModel> roles) {
        this.roles = roles;
        return this;
    }

    public void clearFields() {
        this.id = null;
        this.username = null;
        this.roles = null;
    }

    public Boolean isAdmin() {
        return roles.stream().anyMatch(role -> role.getRole().equals(RoleName.ADMIN));
    }
}
