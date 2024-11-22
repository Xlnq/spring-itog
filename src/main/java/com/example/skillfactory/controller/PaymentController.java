package com.example.skillfactory.controller;

import com.example.skillfactory.model.Booking;
import com.example.skillfactory.model.Payment;
import com.example.skillfactory.repository.BookingRepository;
import com.example.skillfactory.repository.PaymentRepository;
import com.example.skillfactory.service.BookingService;
import com.example.skillfactory.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping
    public String listPayments(Model model) {
        List<Payment> payments = paymentRepository.findAll();
        model.addAttribute("payments", payments);
        return "payment/list";
    }

    @GetMapping("/new")
    public String newPayment(Model model) {
        List<Booking> bookings = bookingRepository.findAll();
        model.addAttribute("payment", new Payment());
        model.addAttribute("bookings", bookings);
        return "payment/form";
    }

    @PostMapping("/save")
    public String savePayment(Payment payment) {
        paymentService.save(payment);
        return "redirect:/payments";
    }

    @GetMapping("/edit/{id}")
    public String editPayment(@PathVariable("id") Integer id, Model model) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        List<Payment> payments = paymentRepository.findAll();

        model.addAttribute("payment", payments);
        model.addAttribute("bookings", booking);
        return "payment/form";
    }

    @GetMapping("/delete/{id}")
    public String deletePayment(@PathVariable("id") Integer id) {
        paymentService.deletePayment(id);
        return "redirect:/payments";
    }
}

