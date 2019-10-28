package com.springmvc.service;

import com.springmvc.model.User;

import java.util.List;

public interface UserDao {

    void insertUser(User user);

    List<User> getUsers();
}
