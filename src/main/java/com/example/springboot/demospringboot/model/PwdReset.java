/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springboot.demospringboot.model;

import java.util.Date;

/**
 *
 * @author emiliano
 */
public class PwdReset {


    
    private String document;
    private String birthDate;
    private String emailAlternative;
    private String operation;
    
    public PwdReset() {
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmailAlternative() {
        return emailAlternative;
    }

    public void setEmailAlternative(String emailAlternative) {
        this.emailAlternative = emailAlternative;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
    
    
}
