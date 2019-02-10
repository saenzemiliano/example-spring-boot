/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springboot.demospringboot.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.demospringboot.model.db.Employee;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController {

	public void save(Employee employee) {
		// TODO Auto-generated method stub
		
	}



    
}
