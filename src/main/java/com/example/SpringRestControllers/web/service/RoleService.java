package com.example.SpringRestControllers.web.service;

import com.example.SpringRestControllers.web.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> listRoles();

    Set<Role> getRoleSetWithActualIds(Set<Role> roles);

    Long getIdByRole(String role);
}
