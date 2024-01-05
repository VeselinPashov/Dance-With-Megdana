package com.Megdana.Dance.with.Megdana.services;

import com.Megdana.Dance.with.Megdana.domain.dto.binding.UserLoginForm;
import com.Megdana.Dance.with.Megdana.domain.dto.binding.UserRegisterForm;
import com.Megdana.Dance.with.Megdana.domain.dto.models.UserModel;
import com.Megdana.Dance.with.Megdana.domain.dto.view.UserProfileModel;
import com.Megdana.Dance.with.Megdana.domain.dto.view.UserRolesModel;
import com.Megdana.Dance.with.Megdana.domain.entities.Role;
import com.Megdana.Dance.with.Megdana.domain.entities.User;
import com.Megdana.Dance.with.Megdana.domain.enums.RoleName;
import com.Megdana.Dance.with.Megdana.helpers.LoggedUser;
import com.Megdana.Dance.with.Megdana.repositories.RoleRepository;
import com.Megdana.Dance.with.Megdana.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository, RoleService roleService,
                       ModelMapper modelMapper,
                       LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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

    public UserModel loginUser(UserLoginForm userLoginForm) {
        Optional<User> loginCandidate = this.userRepository.findByUserName(userLoginForm.getUsername());

        UserModel userConfirmation = loginCandidate.isPresent()
                && loginCandidate.get().getUserName().equals(userLoginForm.getUsername())
                ? this.modelMapper.map(loginCandidate.get(), UserModel.class)
                : new UserModel();

        if (userConfirmation.isValid()) {
            this.loggedUser
                    .setId(userConfirmation.getId())
                    .setUsername(userConfirmation.getUserName())
                    .setRoles(userConfirmation.getRoles());
        }

        return userConfirmation;

    }

    public void editUser(UserProfileModel userToEdit) throws NoSuchObjectException {
        final User userToSave = this.modelMapper.map(userToEdit, User.class);

        if(this.userRepository.findById(userToSave.getId()).isPresent()) {
            User originalUser = this.userRepository.findById(userToSave.getId()).get();
            originalUser.setUserName(userToSave.getUserName());
            originalUser.setFirstName(userToSave.getFirstName());
            originalUser.setLastName(userToSave.getLastName());
            originalUser.setEmail(userToSave.getEmail());
            originalUser.setRoles(userToSave.getRoles());
            this.userRepository.saveAndFlush(originalUser);
        } else {
            throw new NoSuchObjectException("User with id:"+userToEdit.getId()+"is not found");
        }
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

    public UserProfileModel getLoggedUserProfile() {
        return this.modelMapper.map(this.userRepository.findById(loggedUser.getId()), UserProfileModel.class);
    }


    public List<UserProfileModel> getAllUsers() {
        return this.userRepository.findAll()
                .stream()
                .map(u -> this.modelMapper.map(u, UserProfileModel.class))
                .collect(Collectors.toList());
    }


    public void changeRoles(UserRolesModel userRolesModel, String id) throws NoSuchObjectException {
        Optional<User> userToChangeRoles = this.userRepository.findById(Long.parseLong(id));
        Set<Role> newRoles = new HashSet<>();
        String[] rolesArray = {userRolesModel.getAdminRole(), userRolesModel.getEditorRole(), userRolesModel.getUserRole()};
        for (String role : rolesArray) {
            if(role != null && !role.isEmpty()) {
                if(role.equals("USER") || role.equals("ADMIN") || role.equals("EDITOR")) {
                    newRoles.add(this.roleRepository.findByRole(RoleName.valueOf(role.toUpperCase())).get());
                } else throw new InvalidPropertyException(User.class, "Role","One or more user roles is not entered correctly");
            }
        }
        if(userToChangeRoles.isPresent()){
            userToChangeRoles.get().setRoles(newRoles);
            this.userRepository.saveAndFlush(userToChangeRoles.get());
        } else throw new NoSuchObjectException("User with ID:" + id + "is not found");
    }
}
