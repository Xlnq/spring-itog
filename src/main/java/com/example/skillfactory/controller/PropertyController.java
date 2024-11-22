package com.example.skillfactory.controller;

import com.example.skillfactory.model.Property;
import com.example.skillfactory.service.PropertyService;
import com.example.skillfactory.service.PropertyTypeService;
import com.example.skillfactory.service.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;
    @Autowired
    private PropertyTypeService propertyTypeService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String listProperties(Model model) {
        model.addAttribute("properties", propertyService.findAll());
        return "property/list";
    }

    @GetMapping("/new")
    public String newProperty(Model model) {
        model.addAttribute("property", new Property());
        model.addAttribute("types", propertyTypeService.findAll());
        model.addAttribute("users", userService.findAll());
        return "property/form";
    }

    @PostMapping("/save")
    public String saveProperty(@Valid Property property, @RequestParam("photo") MultipartFile photo) {
        if (!photo.isEmpty()) {
            String photoUrl = savePhoto(photo);
            property.setPhotoUrl(photoUrl);
        }
        propertyService.save(property);
        return "redirect:/properties";
    }

    @GetMapping("/edit/{id}")
    public String editProperty(@PathVariable("id") Integer id, Model model) {
        Property property = propertyService.findById(id);
        model.addAttribute("property", property);
        model.addAttribute("types", propertyTypeService.findAll());
        model.addAttribute("users", userService.findAll());
        return "property/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProperty(@PathVariable("id") Integer id) {
        propertyService.delete(id);
        return "redirect:/properties";
    }

    private String savePhoto(MultipartFile photo) {
        try {
            String fileName = photo.getOriginalFilename();
            Path path = Paths.get("src/main/resources/static/images/" + fileName);
            Files.write(path, photo.getBytes());

            return "/images/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

