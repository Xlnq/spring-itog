package com.example.skillfactory.controller;

import com.example.skillfactory.model.Role;
import com.example.skillfactory.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String listRoles(Model model) {
        model.addAttribute("roles", roleService.findAll());
        return "role/list";
    }

    @GetMapping("/new")
    public String newRole(Model model) {
        model.addAttribute("role", new Role());
        return "role/form";
    }

    @PostMapping("/save")
    public String saveRole(@Valid Role role) {
        roleService.save(role);
        return "redirect:/roles";
    }

    @GetMapping("/edit/{id}")
    public String editRole(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("role", roleService.findById(id));
        return "role/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable("id") Integer id) {
        roleService.delete(id);
        return "redirect:/roles";
    }
}

