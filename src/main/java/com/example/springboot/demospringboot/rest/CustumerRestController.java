/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springboot.demospringboot.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.demospringboot.model.ResponseRest;
import com.example.springboot.demospringboot.model.db.Customer;
import com.example.springboot.demospringboot.repository.CustomerRepository;

@RestController
@RequestMapping("/api/customer")
public class CustumerRestController {

    @Autowired
    private CustomerRepository customerRepository;
    
    
    @RequestMapping("/all")
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/all/pre-authorize")
    public Iterable<Customer> findAllPreAuthorize() {
        return customerRepository.findAll();
    }
    
    
    @RequestMapping("/{error}")
    public Iterable<Customer> findAllError(@PathVariable(value = "error") Integer error) {
        
        if(error==0) {
            return customerRepository.findAll();
        } else {
            throw new RuntimeException("Error.......");
        }
    }
    
    @RequestMapping("/a/{error}")
    public ResponseEntity<Iterable<Customer>> findAllA(@PathVariable(value = "error") Integer error) {
        if(error==0) {
            return ResponseEntity.ok(customerRepository.findAll());
        } else {
           
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    null
            );
        }
    }
    
    @RequestMapping("/b/{error}")
    public ResponseRest<Iterable<Customer>> findAllb(@PathVariable(value = "error") Integer error) {
        
        if(error==0) {
            return ResponseRest.success(customerRepository.findAll());
        } else {
            return ResponseRest.error()
                    .setErrors("Ocurrio un error inesperado..", "Algo anfuvo mal")
                    .setMessage("Error")
                    .build();
        }
    }

    @RequestMapping("/zip/{zip}")
    public Iterable<Customer> findByZip(@PathVariable(value = "zip") String zip) {
        return customerRepository.fetchByZipCode(zip);
    }

	public Optional<Customer> fetchByAlternativeSource(String typeDocument, String document) {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer save(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<Customer> fetchByDocumentAndTypeDocument(String typeDocument, String document) {
		// TODO Auto-generated method stub
		return null;
	}


    
}
