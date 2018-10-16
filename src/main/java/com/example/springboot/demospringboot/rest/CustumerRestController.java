/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springboot.demospringboot.rest;

/**
 *
 * @author esaenz
 */
import com.example.springboot.demospringboot.model.Customer;
import com.example.springboot.demospringboot.repository.CustomerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customer")
public class CustumerRestController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("/zip/{zip}")
    public List<Customer> findByZip(@PathVariable(value = "zip") String zip) {
        return customerRepository.fetchByZipCode(zip);
    }
}
