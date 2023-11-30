package com.Megdana.Dance.with.Megdana.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHome (Model model) {
        model.addAttribute("message", "Generic text to show Thymeleaf attributes in operation ;)");
        model.addAttribute("url", "test URL");
        return "home";
    }

    @GetMapping("/test")
    public String getTest (Model model) {
        String textAttribute = "Is this gona work someday?";
        model.addAttribute("text", textAttribute);
        return "test";
    }
}
