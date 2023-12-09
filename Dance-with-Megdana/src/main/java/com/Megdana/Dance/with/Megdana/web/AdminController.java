package com.Megdana.Dance.with.Megdana.web;

import com.Megdana.Dance.with.Megdana.domain.dto.binding.UserRegisterForm;
import com.Megdana.Dance.with.Megdana.domain.dto.models.RoleModel;
import com.Megdana.Dance.with.Megdana.domain.dto.models.UserModel;
import com.Megdana.Dance.with.Megdana.domain.enums.RoleName;
import com.Megdana.Dance.with.Megdana.repositories.UserRepository;
import com.Megdana.Dance.with.Megdana.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping ("/admin")
public class AdminController extends BaseController{
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public AdminController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/console")
    public ModelAndView getAdminConsole (ModelAndView modelAndView) {
        modelAndView.addObject("userList", this.userService.getAllUsers());
        modelAndView.addObject("user", new UserModel());
        return super.view("adminConsole", modelAndView);
    }

    @PostMapping("/selectUser")
    public ModelAndView postSelectUser(ModelAndView modelAndView,
                                       @ModelAttribute UserModel userModel){
        modelAndView.addObject("userToChange", userModel);
        modelAndView.addObject("roleList", new String[]{"ADMIN", "EDITOR"});
        return super.redirect("changeRole");
    }

    @GetMapping("/changeRole")
    public ModelAndView getChangeRole (ModelAndView modelAndView) {
        modelAndView.addObject("roles", new RoleModel());
        return super.view("/changeRole", modelAndView);
    }

    @PostMapping("/changeRole/{action}")
    public ModelAndView postChangeRole (ModelAndView modelAndView,
                                        @ModelAttribute RoleModel role,
                                        @ModelAttribute UserModel userToChange,
                                        @PathVariable String action){
        if(action.equals("add")) {
            this.userService.addRole(userToChange.getId(), role.getRole().toString());
        } else if(action.equals("remove")) {
            this.userService.removeRole(userToChange.getId(), role.getRole().toString());
        }
        return super.view("adminConsole");
    }


    @ModelAttribute("user")
    public UserModel initUserModel() {
        return new UserModel();
    }

    @ModelAttribute("role")
    public String initRole () {return "";}

    @PostMapping("/removeAdminRole/{id}")
    public ModelAndView getRemoveAdminRole (@PathVariable Long id,
                                    ModelAndView modelAndView) {
        this.userService.removeRole(id, "ADMIN" );
        return super.view("adminConsole", modelAndView);
    }

    @PostMapping("/removeEditorRole/{id}")
    public ModelAndView getRemoveEditorRole (@PathVariable Long id,
                                            ModelAndView modelAndView) {
        this.userService.removeRole(id, "EDITOR" );
        return super.view("adminConsole", modelAndView);
    }

    @PostMapping("/addAdminRole/{id}")
    public ModelAndView getAddAdminRole (@PathVariable Long id,
                                            ModelAndView modelAndView) {
        this.userService.addRole(id, "ADMIN" );
        return super.view("adminConsole", modelAndView);
    }

    @PostMapping("/addEditorRole/{id}")
    public ModelAndView getAddEditorRole (@PathVariable Long id,
                                           ModelAndView modelAndView) {
        this.userService.addRole(id, "EDITOR" );
        return super.view("adminConsole", modelAndView);
    }

    //TODO implement removing Editor and adding Admin and Editor


}
