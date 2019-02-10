/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springboot.demospringboot.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.demospringboot.model.db.Customer;
import com.example.springboot.demospringboot.model.db.Email;
import com.example.springboot.demospringboot.repository.EmailRepository;

@RestController
@RequestMapping("/api/email")
public class EmailRestController {

    @Autowired
    private EmailRepository emailRepository;
    
   

	public void assign(Customer customer, String email) {
		// TODO Auto-generated method stub
		
	}

	public Email save(Email email) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<Email> findById(String email) {
		// TODO Auto-generated method stub
		return null;
	}


    
}
