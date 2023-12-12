package com.example.SpringRestControllers.web.controller;

import com.example.SpringRestControllers.web.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {
    @GetMapping(value = "/user")
    public String userPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "user";
    }
}