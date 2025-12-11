package org.example.notes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController("/")
public class RootController {

    @GetMapping
    public ModelAndView greeting() {
        ModelAndView greeting = new ModelAndView("/greeting");
        greeting.addObject("greeting", "Hello User");
        return greeting;
    }
}
