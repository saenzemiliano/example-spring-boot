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
import com.example.springboot.demospringboot.model.db.Groups;
import com.example.springboot.demospringboot.model.ResponseRest;
import com.example.springboot.demospringboot.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/group")
public class GroupRestController {

    @Autowired
    private GroupRepository groupRepository;

    @RequestMapping("/{error}")
    public Iterable<Groups> findAll(@PathVariable(value = "error") Integer error) {

        if (error == 0) {
            return groupRepository.findAll();
        } else {
            throw new RuntimeException("Error.......");
        }
    }

    @RequestMapping("/a/{error}")
    public ResponseEntity<Iterable<Groups>> findAllA(@PathVariable(value = "error") Integer error) {
        if (error == 0) {
            return ResponseEntity.ok(groupRepository.findAll());
        } else {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    null
            );
        }
    }

    @RequestMapping("/b/{error}")
    public ResponseRest<Iterable<Groups>> findAllb(@PathVariable(value = "error") Integer error) {

        if (error == 0) {
            return ResponseRest.success(groupRepository.findAll());
        } else {
            return ResponseRest.error()
                    .setErrors("Ocurrio un error inesperado..", "Algo anfuvo mal")
                    .setMessage("Error")
                    .build();
        }
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
    public ResponseRest<String> delete(@PathVariable(value = "id") Integer id) {

        try {
                groupRepository.deleteById(id);
                return ResponseRest.success("Success");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseRest.error()
                    .setErrors("Ocurrio un error inesperado..", e.getMessage())
                    .setMessage("Error")
                    .build();
        }

    }
}
