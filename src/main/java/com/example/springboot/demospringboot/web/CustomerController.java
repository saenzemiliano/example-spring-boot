package com.example.springboot.demospringboot.web;

import com.example.springboot.demospringboot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {
    
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/page/customer")
    public String showAll(Model model) {
        model.addAttribute("customers", customerRepository.findAll());
        return "customer";
    }

}