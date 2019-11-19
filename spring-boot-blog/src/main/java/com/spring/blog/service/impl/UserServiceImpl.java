package com.spring.blog.service.impl;

import com.spring.blog.exception.ResourceNotFoundException;
import com.spring.blog.model.Role;
import com.spring.blog.model.User;
import com.spring.blog.repository.RoleRepository;
import com.spring.blog.repository.UserRepository;
import com.spring.blog.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
        Set<Role> userRoles = new HashSet<>();
        for (Role userRole : user.getRoles()) {
            Optional<Role> role = roleRepository.findByName(userRole.getName());
            if (role.isPresent()){
                userRoles.add(role.get());
            } else {
                log.info("User role with name: {} not exist", userRole.getName());
            }
        }
        user.setRoles(userRoles);
        return userRepository.save(user);
    }
}
