package com.example.SpringBootstrap.web.controller;

import com.example.SpringBootstrap.web.model.Role;
import com.example.SpringBootstrap.web.model.User;
import com.example.SpringBootstrap.web.service.RoleService;
import com.example.SpringBootstrap.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

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

    @GetMapping("/user-add")
    public String addUserForm(User user, ModelMap model) {
        List<Role> roles = roleService.listRoles();
        model.addAttribute("roles", roles);
        return "user-add";
    }

    @PostMapping("/user-add")
    public ModelAndView addUser(@Valid User user, BindingResult bindingResult, @RequestParam(value = "rolesChecked", required = false) String[] roles) {
        ModelAndView modelAndView = new ModelAndView();
        if (roles == null) {
            bindingResult.rejectValue("roles", "rolesEmpty", "Roles can not be empty");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("roles", roleService.listRoles());
            modelAndView.setViewName("user-add");
            return modelAndView;
        }

        Set<Role> roleSet = roleService.getRoleSetByName(roles);
        user.setRoles(roleSet);
        userService.add(user);

        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }


    @GetMapping("/user-delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

//    @GetMapping("/user-update")
//    public String updateUserForm(@RequestParam("id") Long id, ModelMap model) {
//        List<Role> roles = roleService.listRoles();
//        model.addAttribute("roles", roles);
//        model.addAttribute("user", userService.findById(id));
//        return "user-update";
//    }

    @PostMapping("/user-update")
    public String updateUser(User user, @RequestParam(value = "rolesChecked",
            required = false) String[] roles, Model model) {
        Set<Role> roleSet = roleService.getRoleSetByName(roles);
        user.setRoles(roleSet);
        userService.update(user);
        return "redirect:/admin";
    }
}
