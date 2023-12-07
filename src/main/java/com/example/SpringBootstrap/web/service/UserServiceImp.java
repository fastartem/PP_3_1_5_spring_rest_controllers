package com.example.SpringBootstrap.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.SpringBootstrap.web.dao.UserDao;
import com.example.SpringBootstrap.web.model.User;

import java.util.List;

@Service("userService")
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional
    public void update(User user) {
        userDao.update(user);
    }

    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional
    public void delete(Long id) {
        userDao.delete(id);
    }

    public User findById(Long id) {
        return userDao.findById(id);
    }
}