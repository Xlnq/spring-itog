package com.example.skillfactory.controller;

import com.example.skillfactory.enumer.RoleEnum;
import com.example.skillfactory.model.Role;
import com.example.skillfactory.model.User;
import com.example.skillfactory.service.RoleService;
import com.example.skillfactory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Collections;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registrationForm(Model model, Principal principal) {
        if (principal != null) {
            return "redirect:/";
        }
        return "reg-log/registration";
    }

    @GetMapping("/login")
    public String loginPage(Principal principal) {
        if (principal != null) {
            return "redirect:/";
        }
        return "reg-log/login";
    }

    @PostMapping("/registration")
    public String registerUser(@RequestParam String username, @RequestParam String password,
                               @RequestParam String email, @RequestParam String phone, Model model) {
        if (userService.findByUsername(username).isPresent()) {
            model.addAttribute("error", "Пользователь с таким именем уже существует");
            return "reg-log/registration";
        }

        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setPhoneNumber(phone);
        user.setRole(roleService.findRoleByName("USER"));

        userService.save(user);

        return "redirect:/login";
    }


}
