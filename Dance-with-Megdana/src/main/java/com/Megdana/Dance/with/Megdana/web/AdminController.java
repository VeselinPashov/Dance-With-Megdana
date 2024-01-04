package com.Megdana.Dance.with.Megdana.web;

import com.Megdana.Dance.with.Megdana.domain.dto.models.UserModel;
import com.Megdana.Dance.with.Megdana.domain.dto.view.UserProfileModel;
import com.Megdana.Dance.with.Megdana.domain.dto.view.UserRolesModel;
import com.Megdana.Dance.with.Megdana.domain.entities.Role;
import com.Megdana.Dance.with.Megdana.domain.entities.User;
import com.Megdana.Dance.with.Megdana.repositories.RoleRepository;
import com.Megdana.Dance.with.Megdana.repositories.UserRepository;
import com.Megdana.Dance.with.Megdana.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.rmi.NoSuchObjectException;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping ("/admin")
public class AdminController extends BaseController{
    private final UserService userService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public AdminController(UserService userService, UserRepository userRepository, RoleRepository roleRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/console")
    public ModelAndView getAdminConsole (ModelAndView modelAndView) {
        modelAndView.addObject("userList", this.userService.getAllUsers());
        modelAndView.addObject("user", new UserModel());
        return super.view("adminConsole", modelAndView);
    }

    @GetMapping("/editUser/{id}")
    public ModelAndView getEditUser (@PathVariable Long id,
                                     ModelAndView modelAndView) {
        modelAndView.addObject("userToEdit", this.userRepository.findById(id).get());
        return super.view("editUser", modelAndView);
    }

    @PostMapping("/editUser")
    public ModelAndView postEditUser (@ModelAttribute UserProfileModel userProfileModel,
                                      ModelAndView modelAndView) throws NoSuchObjectException {
        this.userService.editUser(userProfileModel);
        return super.redirect("console");
    }

    @PostMapping("/selectUser")
    public ModelAndView postSelectUser(ModelAndView modelAndView,
                                       @ModelAttribute UserModel userModel){
        modelAndView.addObject("userToChange", userModel);
        modelAndView.addObject("roleList", new String[]{"ADMIN", "EDITOR"});
        return super.redirect("changeRole");
    }

    @GetMapping("/editRoles/{id}")
    public ModelAndView getEditRoles (@PathVariable Long id,
                                      ModelAndView modelAndView) throws NoSuchObjectException {
        Optional<User> userToChangeRoles = this.userRepository.findById(id);
        UserRolesModel userRolesModel = new UserRolesModel();
        userRolesModel.setUserID(id);
        if (userToChangeRoles.isPresent()) {
            Set<Role> roles = userToChangeRoles.get().getRoles();
            modelAndView.addObject("userRolesModel", userRolesModel);
            return super.view("editRoles", modelAndView);
        } else throw new NoSuchObjectException("User with ID:" + id + "is not found");

    }

    @PostMapping("/editRoles/{id}")
    public ModelAndView postEditRoles (@PathVariable String id,
                                       UserRolesModel userRolesModel,
                                       ModelAndView modelAndView) throws NoSuchObjectException {
        this.userService.changeRoles(userRolesModel, id);

        return super.view("adminConsole");
    }

    @GetMapping("/deleteUser/{id}")
    public ModelAndView getDeleteUser (@PathVariable String id,
                                       ModelAndView modelAndView) {
        this.userRepository.delete(this.userRepository.getReferenceById(Long.parseLong(id)));
        return super.view("adminConsole");
    }



    @ModelAttribute("user")
    public UserModel initUserModel() {
        return new UserModel();
    }

    @ModelAttribute("role")
    public String initRole () {return "";}


}
