package com.Megdana.Dance.with.Megdana.domain.dto.binding;

import com.Megdana.Dance.with.Megdana.validation.checkUserExistance.ValidateLoginForm;
import jakarta.validation.constraints.NotNull;

@ValidateLoginForm
public class UserLoginForm {

    @NotNull
    private String username;

    @NotNull
    private String password;

    public UserLoginForm() {
    }

    public String getUsername() {
        return username;
    }

    public UserLoginForm setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginForm setPassword(String password) {
        this.password = password;
        return this;
    }
}
