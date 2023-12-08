package com.example.SpringBootstrap.web.service;

import com.example.SpringBootstrap.web.model.User;
import com.example.SpringBootstrap.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void update(User user) {
        User entity = findById(user.getId());

        entity.setId(user.getId());
        entity.setUsername(user.getUsername());
        entity.setFirstname(user.getFirstname());
        entity.setLastname(user.getLastname());
        entity.setEmail(user.getEmail());
        entity.setRoles(user.getRoles());
        entity.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    public List<User> listUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByName(String username) {
        return userRepository.findByUsername(username);
    }
}