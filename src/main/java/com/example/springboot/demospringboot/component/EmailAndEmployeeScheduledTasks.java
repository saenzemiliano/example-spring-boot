/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springboot.demospringboot.component;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springboot.demospringboot.common.Tools;
import com.example.springboot.demospringboot.model.db.Customer;
import com.example.springboot.demospringboot.model.db.DataEmail;
import com.example.springboot.demospringboot.model.db.Email;
import com.example.springboot.demospringboot.model.db.Employee;
import com.example.springboot.demospringboot.repository.custom.DataEmailCustomRepository;
import com.example.springboot.demospringboot.repository.custom.EmployeeCustomRepository;
import com.example.springboot.demospringboot.rest.CustumerRestController;
import com.example.springboot.demospringboot.rest.DataEmailRestController;
import com.example.springboot.demospringboot.rest.EmailRestController;
import com.example.springboot.demospringboot.rest.EmployeeRestController;


/**
 *
 * @author emiliano
 */
@Component
public class EmailAndEmployeeScheduledTasks {
    private static final Logger logger = Logger.getLogger(EmailAndEmployeeScheduledTasks.class.getName());

    @Autowired
    private EmployeeCustomRepository employeeCustomRepository;
    @Autowired
    private DataEmailCustomRepository dataEmailCustomRepository;
    @Autowired
    private EmployeeRestController employeeRestRepository;
    @Autowired
    private DataEmailRestController dataEmailRestRepository;
    @Autowired
    private CustumerRestController customerRestRepository;
    @Autowired
    private EmailRestController emailRestRepository;
    
    
    //@Scheduled(cron = "0 */10 * ? * ?") //Every 10 minutes
    public void scheduleTaskEmailEmployeeSynchronization() {
        
        try {
            logger.info("scheduleTaskEmailEmployeeSynchronization(...) :: Started.....");
            List<DataEmail> dataEmails = dataEmailCustomRepository.fetchBySycnState("UDP"); // LIMIT 20000
            for (DataEmail dataEmail : dataEmails) {
                try {
                    logger.log(Level.INFO, "Processing..."+dataEmail.getTypeDocument() + ", " + dataEmail.getDocument());
                    Optional<Customer> opCustomer = customerRestRepository.fetchByAlternativeSource(dataEmail.getTypeDocument(), dataEmail.getDocument());
                    if(opCustomer.isPresent()) {
                        Customer customer = opCustomer.get();
                        Optional<Email> opEmail = emailRestRepository.findById(dataEmail.getEmail());
                        if(!opEmail.isPresent()) { //create email if not exist
                           Email email = emailRestRepository.save(new Email());
                           opEmail = Optional.of(email);
                        }
                        Email email = opEmail.get();
                        emailRestRepository.assign(customer, email.getEmail());
                        dataEmail.setSyncState("SYC");
                        dataEmailRestRepository.save(dataEmail);
                    }

                } catch (Exception e) {
                    logger.log(Level.SEVERE, e.getMessage(), e);
                    dataEmail.setSyncState("ERR");
                    dataEmailRestRepository.save(dataEmail);

                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
   
    //@Scheduled(cron = "0 */10 * ? * ?") //Every 10 minutes
    public void scheduleTaskEmployeeSynchronization() {
        
        try {
            logger.info("scheduleTaskEmployeeSynchronization(...) :: Started.....");
            List<Employee> employees = employeeCustomRepository.fetchBySycnState("UDP"); // LIMIT 20000
            for (Employee employee : employees) {
                try {
                    logger.log(Level.INFO, "Processing..."+employee.getTypeDocument() + ", " + employee.getDocument());
                    Optional<Customer> opCustomer = customerRestRepository.fetchByAlternativeSource(employee.getTypeDocument(), employee.getDocument());
                    if(!opCustomer.isPresent()) {
                        Customer customer = customerRestRepository.save(Tools.toCustomer(employee));
                        opCustomer = Optional.of(customer);
                    }
                    employee.setSyncState("SYC");
                    employeeRestRepository.save(employee);
                } catch (Exception e) {
                    logger.log(Level.SEVERE, e.getMessage(), e);
                    employee.setSyncState("ERR");
                    employeeRestRepository.save(employee);

                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}