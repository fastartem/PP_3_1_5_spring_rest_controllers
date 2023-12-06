package com.example.SpringBootSecurity.web.service;

import com.example.SpringBootSecurity.web.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> listRoles();

    Set<Role> getRoleSetByName(String[] roles);
}
