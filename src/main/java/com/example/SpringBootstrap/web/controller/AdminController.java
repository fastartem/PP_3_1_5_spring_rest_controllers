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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    RoleService roleService;

    @GetMapping("/admin")
    public String printUsers(Model model) {
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

    @GetMapping("/user-update")
    public String updateUserForm(@RequestParam("id") Long id, ModelMap model) {
        List<Role> roles = roleService.listRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("user", userService.findById(id));
        return "user-update";
    }

    @PostMapping("/user-update")
    public ModelAndView updateUser(@Valid User user, BindingResult bindingResult, @RequestParam(value = "rolesChecked", required = false) String[] roles) {
        ModelAndView modelAndView = new ModelAndView();
        if (roles == null) {
            bindingResult.rejectValue("roles", "rolesEmpty", "Roles can not be empty");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("roles", roleService.listRoles());
            modelAndView.setViewName("/user-update");
            return modelAndView;
        }

        Set<Role> roleSet = roleService.getRoleSetByName(roles);
        user.setRoles(roleSet);
        userService.update(user);

        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }
}
