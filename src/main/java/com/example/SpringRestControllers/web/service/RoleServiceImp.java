package com.example.SpringRestControllers.web.service;

import com.example.SpringRestControllers.web.model.Role;
import com.example.SpringRestControllers.web.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImp implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> listRoles() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    public Long getIdByRole(String role) {
        return roleRepository.findIdByRole(role);
    }

    @Override
    public Set<Role> getRoleSetWithActualIds(Set<Role> roles) {
        return roles.stream()
                .filter(Objects::nonNull)
                .map(r -> new Role(getIdByRole(r.getRole()), r.getRole()))
                .collect(Collectors.toSet());
    }
}