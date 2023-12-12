package com.Megdana.Dance.with.Megdana.domain.dto.view;

import com.Megdana.Dance.with.Megdana.domain.entities.Role;

public class UserRolesModel {
    private Long userID;
    private String userRole;
    private String editorRole;
    private String adminRole;

    public UserRolesModel() {
    }

    public Long getUserID() {
        return userID;
    }

    public UserRolesModel setUserID(Long userID) {
        this.userID = userID;
        return this;
    }

    public String getUserRole() {
        return userRole;
    }

    public UserRolesModel setUserRole(String userRole) {
        this.userRole = userRole;
        return this;
    }

    public String getEditorRole() {
        return editorRole;
    }

    public UserRolesModel setEditorRole(String editorRole) {
        this.editorRole = editorRole;
        return this;
    }

    public String getAdminRole() {
        return adminRole;
    }

    public UserRolesModel setAdminRole(String adminRole) {
        this.adminRole = adminRole;
        return this;
    }
}
