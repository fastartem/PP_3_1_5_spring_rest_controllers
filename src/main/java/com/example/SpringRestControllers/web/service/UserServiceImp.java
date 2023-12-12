package com.example.SpringRestControllers.web.service;

import com.example.SpringRestControllers.web.model.User;
import com.example.SpringRestControllers.web.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void update(User user) {
        User entity = findById(user.getId());

        entity.setId(user.getId());
        entity.setUsername(user.getUsername());
        entity.setFirstname(user.getFirstname());
        entity.setLastname(user.getLastname());
        entity.setEmail(user.getEmail());
        entity.setRoles(user.getRoles());
        if (!user.getPassword().equals(entity.getPassword())) {
            entity.setPassword(passwordEncoder.encode(user.getPassword()));
        }
    }

    @Override
    public List<User> listUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUserByName(String username) {
        return userRepository.findByUsername(username);
    }
}