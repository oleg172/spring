package com.spring.blog.service;

import com.spring.blog.exception.ResourceNotFoundException;
import com.spring.blog.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findById(Integer id) throws ResourceNotFoundException;

    Optional<User> findByEmail(String email) throws ResourceNotFoundException;

    Optional<User> findByName(String name) throws ResourceNotFoundException;

    List<User> findAll();

    User save(User user);
}
