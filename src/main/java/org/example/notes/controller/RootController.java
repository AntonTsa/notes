package org.example.notes.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class RootController {

    @GetMapping
    public ModelAndView greeting(Authentication authentication) {
        ModelAndView greeting = new ModelAndView("/greeting");
        greeting.addObject(
                "username",
                authentication != null ? authentication.getName() : "Guest"
        );
        return greeting;
    }
}
