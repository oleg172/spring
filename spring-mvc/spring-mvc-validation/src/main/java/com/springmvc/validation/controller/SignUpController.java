package com.springmvc.validation.controller;

import com.springmvc.validation.model.User;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class SignUpController {

    @PostMapping(value = "/signup")
    public String addUser(@Valid User user,
                          BindingResult result,
                          RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "SignUpForm";
        } else {
            redirectAttributes.addFlashAttribute("user", user);
            return "UserInfo";
        }
    }

    @GetMapping()
    public String displayCustomerForm(ModelMap model) {
        model.addAttribute("user", new User());
        return "SignUpForm";

    }
}
