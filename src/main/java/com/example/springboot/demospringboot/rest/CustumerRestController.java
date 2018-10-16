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
import javax.ws.rs.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustumerRestController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("/customer/zip/{zip}")
    public List<Customer> findByName(@PathParam(value = "zip") String zip) {
        return customerRepository.findByZip(zip);
    }
}
