/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springboot.demospringboot.common;


/**
 *
 * @author emiliano
 */
class Tools {

    static boolean isValidate(String cand) {
        return cand.matches(Constant.VALID_EMAIL_ADDRESS_REGEX);
    }
    
    static boolean isValidateCompany(String cand) {
        return cand.matches(Constant.VALID_COMPANY_EMAIL_ADDRESS_REGEX);
    }
}
