package com.example.SpringBootSecurity.web.model;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    List<Role> listRoles();

    Set<Role> getRoleSetByName(String[] roles);

    Long getIdByRole(String role);
}
