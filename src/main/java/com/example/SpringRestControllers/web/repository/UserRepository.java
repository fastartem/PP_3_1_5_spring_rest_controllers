package com.example.SpringRestControllers.web.repository;

import com.example.SpringRestControllers.web.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @EntityGraph(attributePaths = "roles")
    User findByUsername(String username);
}
