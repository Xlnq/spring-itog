package com.example.skillfactory.controller;

import com.example.skillfactory.model.Property;
import com.example.skillfactory.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/home")
    public String showProperties(Model model) {
        List<Property> properties = propertyService.findAll();
        model.addAttribute("properties", properties);
        return "index";
    }

    @GetMapping("/home/property/{id}")
    public String showPropertyDetails(@PathVariable("id") Integer id, Model model) {
        Property property = propertyService.findById(id);
        model.addAttribute("property", property);
        return "property-details";
    }
}
