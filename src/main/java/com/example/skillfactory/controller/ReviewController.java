package com.example.skillfactory.controller;

import com.example.skillfactory.model.Review;
import com.example.skillfactory.service.PropertyService;
import com.example.skillfactory.service.ReviewService;
import com.example.skillfactory.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;
    @Autowired
    private PropertyService propertyService;

    @GetMapping
    public String listReviews(Model model) {
        model.addAttribute("reviews", reviewService.findAll());
        return "review/list";
    }

    @GetMapping("/new")
    public String newReview(Model model) {
        model.addAttribute("review", new Review());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("properties", propertyService.findAll());
        return "review/form";
    }

    @PostMapping("/save")
    public String saveReview(Review review) {
        reviewService.save(review);
        return "redirect:/reviews";
    }

    @GetMapping("/edit/{id}")
    public String editReview(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("review", reviewService.findById(id));
        model.addAttribute("users", userService.findAll());
        model.addAttribute("properties", propertyService.findAll());
        return "review/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteReview(@PathVariable("id") Integer id) {
        reviewService.delete(id);
        return "redirect:/reviews";
    }
}

