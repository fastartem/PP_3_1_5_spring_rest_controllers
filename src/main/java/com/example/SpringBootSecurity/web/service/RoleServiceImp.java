package com.example.SpringBootSecurity.web.service;

import com.example.SpringBootSecurity.web.model.Role;
import com.example.SpringBootSecurity.web.model.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImp implements RoleService {
    private final RoleDao roleDao;

    @Autowired
    RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> listRoles() {
        return roleDao.listRoles();
    }

    @Override
    public Set<Role> getRoleSetByName(String[] roles) {
        return roleDao.getRoleSetByName(roles);
    }
}