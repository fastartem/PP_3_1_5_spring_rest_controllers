package com.example.SpringBootSecurity.web.controller;

import com.example.SpringBootSecurity.web.model.User;
import com.example.SpringBootSecurity.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user")
    public String userPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", userService.findById(user.getId()));
        return "user";
    }

//    @GetMapping(value = "/{id}")
//    public String userPage(@PathVariable("id") long id, Model model) {
//        model.addAttribute("user", userService.findById(id));
//        return "user";
//    }

//    @PostMapping("/user-add")
//    public String addUser(@Valid User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "user-add";
//        }
//
//        userService.add(user);
//        return "redirect:/users";
//    }

//    @GetMapping("/user-delete")
//    public String deleteUser(@RequestParam("id") Long id) {
//        userService.delete(id);
//        return "redirect:/users";
//    }
//
//    @GetMapping("/user-update")
//    public String updateUserForm(@RequestParam("id") Long id, Model model) {
//        model.addAttribute("user", userService.findById(id));
//        return "user-update";
//    }
//
//    @PostMapping("/user-update")
//    public String updateUser(@Valid User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "user-update";
//        }
//
//        userService.update(user);
//        return "redirect:/users";
//    }
}