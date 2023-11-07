package com.Megdana.Dance.with.Megdana.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHome (ModelAndView modelAndView) {
        return "home";
    }
}
