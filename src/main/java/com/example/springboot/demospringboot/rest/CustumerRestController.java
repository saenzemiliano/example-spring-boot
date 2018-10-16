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
import javax.ws.rs.PathParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustumerController {

    
    
    @RequestMapping("/customer/{id}")
    public Customer findById(@PathParam(value="id") Integer id) {
        return null;
    }
}
