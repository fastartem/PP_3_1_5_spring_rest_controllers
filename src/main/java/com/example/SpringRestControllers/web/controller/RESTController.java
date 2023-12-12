package com.example.SpringRestControllers.web.controller;

import com.example.SpringRestControllers.web.model.Role;
import com.example.SpringRestControllers.web.model.User;
import com.example.SpringRestControllers.web.service.RoleService;
import com.example.SpringRestControllers.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class RESTController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public RESTController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin/users")
    public List<User> getAllUsers() {
        return userService.listUsers();
    }

    @GetMapping("/current-user")
    public User getCurrentUser(@AuthenticationPrincipal User user) {
        return user;
    }

    @GetMapping("/admin/user")
    public User getUser(@RequestParam("id") long id) {
        return userService.findById(id);
    }

    @GetMapping("/admin/roles")
    public List<Role> allRoles() {
        return roleService.listRoles();
    }

    @PostMapping("/admin/user")
    public void saveUser(@RequestBody User webUser) {
        Set<Role> roles = roleService.getRoleSetWithActualIds(webUser.getRoles());
        webUser.setRoles(roles);
        userService.add(webUser);
    }

    @PutMapping("/admin/user")
    public void updateUser(@RequestBody User webUser) {
        Set<Role> roles = roleService.getRoleSetWithActualIds(webUser.getRoles());
        webUser.setRoles(roles);
        userService.update(webUser);
    }

    @DeleteMapping("/admin/user")
    public void deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);
    }
}
