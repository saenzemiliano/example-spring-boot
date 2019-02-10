/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springboot.demospringboot.repository;

/**
 *
 * @author esaenz
 */
import com.example.springboot.demospringboot.model.db.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource(collectionResourceRel = "customer", path = "customer")
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Iterable<Customer> findByName(@Param("name") String name);
    
    @Query("SELECT c FROM Customer c INNER JOIN c.zip z WHERE z.zipCode=:zipCode")
    Iterable<Customer> fetchByZipCode(@Param("zipCode") String zipCode);
    
    @Query("SELECT c FROM Customer c WHERE c.typeDocument=:typeDocument AND c.document=:document")
    public Optional<Customer> fetchByAlternativeSource(String typeDocument, String document);
}