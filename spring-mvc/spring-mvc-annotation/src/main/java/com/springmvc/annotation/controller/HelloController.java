package com.springmvc.annotation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @GetMapping(value = "/hello")
    public ModelAndView hello() {
        ModelAndView model = new ModelAndView("hello");
        model.addObject("msg", "hello world");
        return model;
    }
}
