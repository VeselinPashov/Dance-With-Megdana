package com.Megdana.Dance.with.Megdana.web;

import com.Megdana.Dance.with.Megdana.repositories.UserRepository;
import com.Megdana.Dance.with.Megdana.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
        return super.view("adminConsole", modelAndView);
    }

    @GetMapping("/removeAdminRole/{id}")
    public ModelAndView getAddRole (@PathVariable Long id,
                                    ModelAndView modelAndView) {
        //TODO implement removing Admin role here
        System.out.println("User ID is: "+ id);
        return super.view("/", modelAndView);
    }

    //TODO implement removing Editor and adding Admin and Editor


}
