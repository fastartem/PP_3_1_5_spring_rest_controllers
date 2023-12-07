package com.example.SpringBootstrap.web.dao;

import com.example.SpringBootstrap.web.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    List<Role> listRoles();

    Set<Role> getRoleSetByName(String[] roles);

    Long getIdByRole(String role);
}
