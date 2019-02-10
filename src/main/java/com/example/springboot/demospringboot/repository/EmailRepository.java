/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springboot.demospringboot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author esaenz
 */
import com.example.springboot.demospringboot.model.db.Email;

@RepositoryRestResource(collectionResourceRel = "email", path = "email")
public interface EmailRepository extends CrudRepository<Email, String> {

}