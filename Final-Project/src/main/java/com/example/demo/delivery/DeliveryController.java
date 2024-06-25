package com.example.demo.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/delivery")
public class DeliveryController {
    @Autowired
    private DeliveryService service;

    @GetMapping("/add")
    public String addForm() {
        return "delivery/add";
    }
}
