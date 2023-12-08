package com.example.SpringBootstrap.web.service;

import com.example.SpringBootstrap.web.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    void update(User user);

    List<User> listUsers();

    void delete(Long id);

    User findById(Long id);

    User getUserByName(String name);
}