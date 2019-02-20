/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springboot.demospringboot.common;

import javax.xml.bind.JAXBElement;

import com.example.springboot.demospringboot.model.db.Customer;
import com.example.springboot.demospringboot.model.db.Employee;


/**
 *
 * @author emiliano
 */
public class Tools {
	
	public static <T> T toValue(JAXBElement<T> t) {
		if(t!=null) {
			return t.getValue();
		}
        return null;
    }
    
    public static Customer toCustomer(Employee employee) {
        return new Customer();
    }

    public static boolean isEmailValid(String cand) {
        return cand.matches(Constant.VALID_EMAIL_ADDRESS_REGEX);
    }
    
    public static boolean isConpanyEmailValid(String cand) {
        return cand.matches(Constant.VALID_COMPANY_EMAIL_ADDRESS_REGEX);
    }
    
    public static boolean isEmailValidAlternaive(String email) {
    	
    	if(isBlankOrNull(email)) {
    		return false;
    	}
    	
    	//check alphabet
    	if(!email.matches("^[a-zA-Z0-9\\.\\-\\_@]+$")) {
    		//System.out.print("alphabet");
    		return false;
    	}
    	
    	//check  @ is present.....
    	if(!email.matches(".*@{1}.*")) {
    		//System.out.print("start");
    		return false;
    	}
    	
    	//check final  .....com
    	if(!email.matches(".*\\.[a-zA-Z0-9]{2,6}$")) {
    		//System.out.print("final");
    		return false;
    	}
    	
    	//check start William.....
    	if(!email.matches("^[a-zA-Z]+.*")) {
    		//System.out.print("start");
    		return false;
    	}
    	
    	//check two non-character together 
    	boolean hit = false;
    	for (int i = 0; i < email.length(); i++) {
    		String str0 = email.charAt(i) + ""; 
			if(str0.matches("[\\.\\-\\_@]")) {
				if(hit) {
					return false;	
				} else {
					hit = true;
				}
				
			} else {
				hit = false;	
			}
		}
    	
        return true;
    }

	public static boolean isBlankOrNull(String str0) {
		return (str0==null || str0.trim().length()<=0 ? true : false);
	}
}
