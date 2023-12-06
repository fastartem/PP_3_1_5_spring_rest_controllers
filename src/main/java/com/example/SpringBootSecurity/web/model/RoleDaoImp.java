package com.example.SpringBootSecurity.web.model;

import org.springframework.stereotype.Repository;
import com.example.SpringBootSecurity.web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class RoleDaoImp implements RoleDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> listRoles() {
        return entityManager.createQuery("SELECT u FROM Role u").getResultList();
    }

    @Override
    public Long getIdByRole(String role) {
        return (long) entityManager.createQuery("SELECT r.id FROM Role r WHERE r.role=:role")
                .setParameter("role", role).getSingleResult();
    }

    @Override
    public Set<Role> getRoleSetByName(String[] roles) {
        return Arrays.stream(roles)
                .filter(Objects::nonNull)
                .map(r -> new Role(getIdByRole(r), r))
                .collect(Collectors.toSet());
    }
}
