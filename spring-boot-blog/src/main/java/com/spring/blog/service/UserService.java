package com.spring.blog.service;

import com.spring.blog.model.User;

import java.util.List;

public interface UserService {

    User findByName(String name);

    List<User> findAll();

    User save(User user);
}
