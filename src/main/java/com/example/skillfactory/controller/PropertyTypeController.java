package com.example.skillfactory.controller;

import com.example.skillfactory.model.PropertyType;
import com.example.skillfactory.service.PropertyTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/property-types")
public class PropertyTypeController {

    @Autowired
    private PropertyTypeService propertyTypeService;

    @GetMapping
    public String listPropertyTypes(Model model) {
        model.addAttribute("propertyTypes", propertyTypeService.findAll());
        return "propertyType/list";
    }

    @GetMapping("/new")
    public String newPropertyType(Model model) {
        model.addAttribute("propertyType", new PropertyType());
        return "propertyType/form";
    }

    @PostMapping("/save")
    public String savePropertyType(@Valid PropertyType propertyType) {
        // Проверяем, если у объекта есть ID и он существует в базе данных
        if (propertyType.getTypeId() != null && propertyTypeService.findById(propertyType.getTypeId()) != null) {
            // Если ID существует, то это обновление
            propertyTypeService.save(propertyType);
        } else {
            // Если ID отсутствует, то это создание нового объекта
            propertyTypeService.save(propertyType);
        }
        return "redirect:/property-types";
    }


    @GetMapping("/edit/{id}")
    public String editPropertyType(@PathVariable("id") Integer id, Model model) {
        PropertyType propertyType = propertyTypeService.findById(id);

        // Проверяем, если объект найден
        if (propertyType == null) {
            // Если объект не найден, перенаправляем на страницу с ошибкой или пустой объект
            return "redirect:/property-types"; // Или можно показать страницу ошибки
        }

        model.addAttribute("propertyType", propertyType);
        return "propertyType/form"; // Форма для редактирования
    }



    @GetMapping("/delete/{id}")
    public String deletePropertyType(@PathVariable("id") Integer id) {
        propertyTypeService.delete(id);
        return "redirect:/property-types";
    }
}

