package com.spring.blog.controller;

import com.spring.blog.model.ApiResponse;
import com.spring.blog.model.User;
import com.spring.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/create")
    public ApiResponse<User> createUser(@Valid @RequestBody User user) {
        return new ApiResponse<>(HttpStatus.OK.value(), "User saved successfully.",userService.save(user));
    }

    @GetMapping 
    public ApiResponse<List<User>> getUsers(){
        return new ApiResponse<>(HttpStatus.OK.value(), "All users.", userService.findAll());
    }
}
