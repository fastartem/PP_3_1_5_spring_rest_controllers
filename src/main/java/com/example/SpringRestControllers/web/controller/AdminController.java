package com.example.SpringRestControllers.web.controller;

import com.example.SpringRestControllers.web.model.Role;
import com.example.SpringRestControllers.web.model.User;
import com.example.SpringRestControllers.web.service.RoleService;
import com.example.SpringRestControllers.web.service.UserService;
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
    @GetMapping
    public String adminPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "admin";
    }
}
