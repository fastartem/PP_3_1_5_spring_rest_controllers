package com.example.SpringBootstrap.web.dao.role;

import com.example.SpringBootstrap.web.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    @Query("SELECT r.id FROM Role r WHERE r.role=:role")
    Long findIdByRole(String role);
}
