package com.example.SpringBootstrap.web.dao.user;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.SpringBootstrap.web.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDaoImp implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

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