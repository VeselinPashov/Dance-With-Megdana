package com.Megdana.Dance.with.Megdana.web;

import com.Megdana.Dance.with.Megdana.domain.dto.binding.UserLoginForm;
import com.Megdana.Dance.with.Megdana.domain.dto.binding.UserRegisterForm;
import com.Megdana.Dance.with.Megdana.repositories.UserRepository;
import com.Megdana.Dance.with.Megdana.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
public class AuthController extends BaseController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public AuthController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public ModelAndView getRegister(ModelAndView modelAndView) {
        return super.view("register", modelAndView);
    }

    @PostMapping("/register")
    public ModelAndView postRegister(@Validated UserRegisterForm userRegisterForm,
                                     BindingResult bindingResult,
                                     ModelAndView modelAndView) {

        if (this.userRepository.findByUserName(userRegisterForm.getUserName()).isPresent()) {
            bindingResult.rejectValue("email", null, "There is already an account registered with the same email");
        }

        if (bindingResult.hasErrors()) {
            return super.view("register", modelAndView.addObject(userRegisterForm));
        }

        this.userService.registerUser(userRegisterForm);

        return super.redirect("login");
    }

    @GetMapping("/login")
    public ModelAndView getLogin(ModelAndView modelAndView) {
        return super.view("login", modelAndView);
    }

    @PostMapping("/login")
    public ModelAndView postLogin(@Validated UserLoginForm userLoginForm,
                                  BindingResult bindingResult,
                                  ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            return super.view("login", modelAndView.addObject(userLoginForm));
        }

        return this.userService.loginUser(userLoginForm).isValid()
                ? super.redirect("/")
                : super.redirect("login");
    }

    @GetMapping("/logout")
    public ModelAndView postLogout() {
        this.userService.logout();
        return super.redirect("/");
    }


    // Model Attributes
    @ModelAttribute("userRegisterForm")
    public UserRegisterForm initRegisterForm() {
        return new UserRegisterForm();
    }

    @ModelAttribute("userLoginForm")
    public UserLoginForm initLoginForm() {
        return new UserLoginForm();
    }
}
