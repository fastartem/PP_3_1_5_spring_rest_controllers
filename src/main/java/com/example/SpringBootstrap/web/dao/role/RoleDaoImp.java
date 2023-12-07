package com.example.SpringBootstrap.web.dao.role;

import com.example.SpringBootstrap.web.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleDaoImp implements RoleDao {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> listRoles() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    public Long getIdByRole(String role) {
       return roleRepository.findIdByRole(role);
    }

    @Override
    public Set<Role> getRoleSetByName(String[] roles) {
        return Arrays.stream(roles)
                .filter(Objects::nonNull)
                .map(r -> new Role(getIdByRole(r), r))
                .collect(Collectors.toSet());
    }
}
