/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springboot.demospringboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author esaenz
 */
import com.example.springboot.demospringboot.model.db.Employee;

@RepositoryRestResource(collectionResourceRel = "employee", path = "employee")
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    
    @Query("SELECT c FROM Employee c WHERE c.typeDocument=:typeDocument AND c.document=:document")
    public Optional<Employee> fetchByAlternativeSource(String typeDocument, String document);
}