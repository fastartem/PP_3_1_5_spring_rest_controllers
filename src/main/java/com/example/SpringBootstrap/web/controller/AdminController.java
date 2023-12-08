package com.example.SpringBootstrap.web.controller;

import com.example.SpringBootstrap.web.model.Role;
import com.example.SpringBootstrap.web.model.User;
import com.example.SpringBootstrap.web.service.RoleService;
import com.example.SpringBootstrap.web.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String printUsers(@AuthenticationPrincipal User user, Model model) {
        List<Role> roles = roleService.listRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        model.addAttribute("newUser", new User());
        model.addAttribute("users", userService.listUsers());
        return "admin";
    }

    @PostMapping("/user-add")
    public String addUser(@Valid User user, @RequestParam(value = "rolesChecked", required = false) String[] roles) {
        Set<Role> roleSet = roleService.getRoleSetByName(roles);
        user.setRoles(roleSet);
        userService.add(user);

        return "redirect:/admin";
    }

    @PostMapping("/user-delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @PostMapping("/user-update")
    public String updateUser(User user, @RequestParam(value = "rolesChecked", required = false) String[] roles) {
        Set<Role> roleSet = roleService.getRoleSetByName(roles);
        user.setRoles(roleSet);
        userService.update(user);
        return "redirect:/admin";
    }
}
