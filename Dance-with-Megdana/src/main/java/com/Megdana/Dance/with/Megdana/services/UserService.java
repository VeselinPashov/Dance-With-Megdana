package com.Megdana.Dance.with.Megdana.services;

import com.Megdana.Dance.with.Megdana.domain.dto.binding.UserLoginForm;
import com.Megdana.Dance.with.Megdana.domain.dto.binding.UserRegisterForm;
import com.Megdana.Dance.with.Megdana.domain.dto.models.UserModel;
import com.Megdana.Dance.with.Megdana.domain.entities.User;
import com.Megdana.Dance.with.Megdana.helpers.LoggedUser;
import com.Megdana.Dance.with.Megdana.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    @Autowired
    public UserService (UserRepository userRepository,
                        RoleService roleService,
                        ModelMapper modelMapper,
                        LoggedUser loggedUser){
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    public void registerUser(UserRegisterForm userRegister) {
        final UserModel userModel = this.modelMapper.map(userRegister, UserModel.class);

        userModel.setRoles(this.userRepository.count() == 0
                ? this.roleService.findAllRoles()
                : Set.of((this.roleService.findRoleByName("USER"))));

        final User userToSave = this.modelMapper.map(userModel, User.class);

        this.modelMapper.map(this.userRepository.saveAndFlush(userToSave), UserModel.class);
    }

    public UserModel loginUser (UserLoginForm userLoginForm) {
        Optional<User> loginCandidate = this.userRepository.findByUserName(userLoginForm.getUsername());

        UserModel userConfirmation =loginCandidate.isPresent()
                && loginCandidate.get().getUserName().equals(userLoginForm.getUsername())
                ?this.modelMapper.map(loginCandidate.get(), UserModel.class)
                : new UserModel();

        if (userConfirmation.isValid()) {
            this.loggedUser
                    .setId(userConfirmation.getId())
                    .setUsername(userConfirmation.getUserName())
                    .setRoles(userConfirmation.getRoles());
        }

        return  userConfirmation;

    }

    public void logout() {
        this.loggedUser.clearFields();
    }


    public UserModel findByUsername(String username) {
        return this.modelMapper
                .map(this.userRepository
                                .findByUserName(username)
                                .orElse(new User())
                        , UserModel.class);
    }


}