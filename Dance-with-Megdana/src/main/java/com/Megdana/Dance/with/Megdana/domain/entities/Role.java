package com.Megdana.Dance.with.Megdana.domain.entities;

import com.Megdana.Dance.with.Megdana.domain.enums.RoleName;
import com.Megdana.Dance.with.Megdana.web.BaseController;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private RoleName role;

    public Role() {
    }

    public RoleName getRole() {
        return role;
    }

    public Role setRole(RoleName role) {
        this.role = role;
        return this;
    }
}
