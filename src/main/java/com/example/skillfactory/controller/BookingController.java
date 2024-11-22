package com.example.skillfactory.controller;

import com.example.skillfactory.model.BackupLog;
import com.example.skillfactory.model.Booking;
import com.example.skillfactory.model.Property;
import com.example.skillfactory.model.User;
import com.example.skillfactory.repository.BookingRepository;
import com.example.skillfactory.repository.PropertyRepository;
import com.example.skillfactory.repository.UserRepository;
import com.example.skillfactory.service.BookingService;
import com.example.skillfactory.service.PropertyService;
import com.example.skillfactory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @GetMapping
    public String getBookingsPage(Model model) {
        List<Booking> bookings = bookingRepository.findAll();
        model.addAttribute("bookings", bookings);

        return "bookings/list";
    }

    @GetMapping("/new")
    public String newBooking(Model model) {
        List<User> users = userRepository.findAll();
        List<Property> property = propertyRepository.findAll();

        model.addAttribute("booking", new Booking());
        model.addAttribute("users", users);
        model.addAttribute("properties", property);
        return "bookings/form";
    }

    @PostMapping("/save")
    public String saveBooking(@ModelAttribute Booking booking) {
        bookingRepository.save(booking);
        return "redirect:/bookings";
    }

    @GetMapping("/edit/{id}")
    public String editBooking(@PathVariable int id, Model model) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        List<User> users = userRepository.findAll();
        List<Property> property = propertyRepository.findAll();

        model.addAttribute("booking", booking);
        model.addAttribute("users", users);
        model.addAttribute("properties", property);
        return "bookings/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteBooking(@PathVariable int id) {
        bookingRepository.deleteById(id);
        return "redirect:/bookings";
    }
}



