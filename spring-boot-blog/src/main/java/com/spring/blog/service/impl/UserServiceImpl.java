package com.spring.blog.service.impl;

import com.spring.blog.exception.ResourceNotFoundException;
import com.spring.blog.model.User;
import com.spring.blog.repository.UserRepository;
import com.spring.blog.service.UserService;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findById(Long id) throws ResourceNotFoundException {

        log.info("Try to load user by id: {}", id);
        return Optional.of(userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "id", id)
                ));
    }

    @Override
    public Optional<User> findByEmail(String email) throws ResourceNotFoundException {

        log.info("Try to load user by e-mail: {}", email);
        return Optional.of(userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "email", email)
                ));
    }

    @Override
    public Optional<User> findByName(String name) throws ResourceNotFoundException {

        log.info("Try to load user by name: {}", name);
        return Optional.of(userRepository.findByName(name)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "name", name)
                ));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
