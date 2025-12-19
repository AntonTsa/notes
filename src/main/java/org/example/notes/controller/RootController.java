package org.example.notes.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/v1/")
public class RootController {

    @GetMapping
    public ModelAndView greeting(Authentication authentication) {
        ModelAndView mav = new ModelAndView("greeting");

        boolean isAuthenticated = authentication != null
                && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken);

        mav.addObject("isAuthenticated", isAuthenticated);
        mav.addObject(
                "username",
                isAuthenticated ? authentication.getName() : "Guest"
        );

        return mav;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


}
