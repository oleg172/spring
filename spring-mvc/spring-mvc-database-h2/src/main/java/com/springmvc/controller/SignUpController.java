package com.springmvc.controller;

import com.springmvc.dao.UserDao;
import com.springmvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class SignUpController {

    @Autowired
    private UserDao userDao;

    @PostMapping(value = "/signup")
    public String addUser(@Valid User user,
                          BindingResult result,
                          RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "SignUpForm";
        } else {
            if (!isUserPresent(user)) {
                userDao.insertUser(user);
            }

            redirectAttributes.addFlashAttribute("users", userDao.getUsers());
            return "UserInfo";
        }
    }

    @GetMapping()
    public String displayCustomerForm(ModelMap model) {
        model.addAttribute("user", new User());
        return "SignUpForm";

    }

    private boolean isUserPresent(User user) {
        List<User> users = userDao.getUsers();
        return users.stream()
                .filter(u -> u.getName().equals(user.getName()) &&
                        u.getAge().equals(user.getAge())
                )
                .findFirst()
                .orElse(null) != null;
    }
}
