package com.example.SpringBootstrap.web.dao.user;

import com.example.SpringBootstrap.web.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
