/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springboot.demospringboot.common;

import com.example.springboot.demospringboot.model.db.Customer;
import com.example.springboot.demospringboot.model.db.Employee;


/**
 *
 * @author emiliano
 */
public class Tools {
    
    public static Customer toCustomer(Employee employee) {
        return new Customer();
    }

    public static boolean isValidate(String cand) {
        return cand.matches(Constant.VALID_EMAIL_ADDRESS_REGEX);
    }
    
    public static boolean isValidateCompany(String cand) {
        return cand.matches(Constant.VALID_COMPANY_EMAIL_ADDRESS_REGEX);
    }
}
