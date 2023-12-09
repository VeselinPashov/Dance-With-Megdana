package com.Megdana.Dance.with.Megdana.web;

import com.Megdana.Dance.with.Megdana.domain.dto.models.UserModel;
import com.Megdana.Dance.with.Megdana.domain.dto.view.UserProfileModel;
import com.Megdana.Dance.with.Megdana.repositories.UserRepository;
import com.Megdana.Dance.with.Megdana.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.rmi.NoSuchObjectException;

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

    @ModelAttribute("user")
    public UserModel initUserModel() {
        return new UserModel();
    }

    @ModelAttribute("role")
    public String initRole () {return "";}


}
