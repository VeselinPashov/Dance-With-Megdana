package com.Megdana.Dance.with.Megdana.validation.checkUserExistance;



import com.Megdana.Dance.with.Megdana.domain.dto.binding.UserLoginForm;
import com.Megdana.Dance.with.Megdana.domain.dto.models.UserModel;
import com.Megdana.Dance.with.Megdana.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.rmi.NoSuchObjectException;

public class UserLoginValidator implements ConstraintValidator<ValidateLoginForm, UserLoginForm> {

    private final UserService userService;

    public UserLoginValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(ValidateLoginForm constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserLoginForm userLoginModel, ConstraintValidatorContext constraintValidatorContext) {
        UserModel user = this.userService.findByUsername(userLoginModel.getUsername());

        return user.getId() != 0
                && user.getPassword()
                .equals(userLoginModel.getPassword());
    }
}
