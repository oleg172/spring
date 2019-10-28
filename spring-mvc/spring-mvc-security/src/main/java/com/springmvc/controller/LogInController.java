package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class LogInController {

    @GetMapping(value = "/login")
    public String login(
            @RequestParam(value = "error", required = false) String error,
            ModelMap model) {

        if (error != null) {
            model.addAttribute("error", "Invalid username or password!");
        }
        return "LoginForm";
    }

    @GetMapping("/admin-page")
    public String adminPage() {
        return "AdminPage";

    }

    @GetMapping("/user-page")
    public String userPage() {
        return "UserPage";

    }

}
