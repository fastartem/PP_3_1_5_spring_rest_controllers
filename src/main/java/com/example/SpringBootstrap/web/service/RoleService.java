package com.example.SpringBootstrap.web.service;

import com.example.SpringBootstrap.web.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> listRoles();

    Set<Role> getRoleSetByName(String[] roles);

    Long getIdByRole(String role);
}
