package com.example.SpringBootstrap.web.dao.user;

import com.example.SpringBootstrap.web.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    void update(User user);

    List<User> listUsers();

    void delete(Long id);

    User findById(Long id);

    User getUserByName(String name);
}