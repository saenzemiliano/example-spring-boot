/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springboot.demospringboot.component;

import com.example.springboot.demospringboot.model.Email;
import com.example.springboot.demospringboot.common.Tools;
import com.example.springboot.demospringboot.model.db.Customer;
import com.example.springboot.demospringboot.model.db.Employee;
import com.example.springboot.demospringboot.repository.CustomerRepository;
import com.example.springboot.demospringboot.repository.EmployeeRepository;
import com.example.springboot.demospringboot.repository.custom.EmailCustomRepository;
import com.example.springboot.demospringboot.repository.custom.EmployeeCustomRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 *
 * @author emiliano
 */
@Component
public class HhRrScheduledTasks {
    private static final Logger logger = Logger.getLogger(HhRrScheduledTasks.class.getName());

    @Autowired
    private EmployeeCustomRepository employeeCustomRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private EmailCustomRepository emailCustomRepository;
   
    //@Scheduled(cron = "0 */10 * ? * ?") //Every 10 minutes
    public void scheduleTaskWithCronExpression() {
        
        try {
            logger.info("scheduleTaskWithCronExpression(...) :: Started.....");
            List<Employee> employees = employeeCustomRepository.fetchBySycnState("UDP"); // LIMIT 20000
            for (Employee employee : employees) {
                try {
                    logger.log(Level.INFO, "scheduleTaskWithCronExpression(...) :: Processing..."+employee.getTypeDocument() + ", " + employee.getDocument());
                    Optional<Customer> opCustomer = customerRepository.fetchByAlternativeSource(employee.getTypeDocument(), employee.getDocument());
                    if(!opCustomer.isPresent()) {
                        Customer customer = customerRepository.save(Tools.toCustomer(employee));
                        opCustomer = Optional.of(customer);
                    }
                    Customer customer = opCustomer.get();
                    Optional<Email> opEmail = emailCustomRepository.findByEmail(customer.getEmail());
                    if(!opEmail.isPresent()) { //create email if not exist
                        Email email = emailCustomRepository.save(customer.getEmail());
                        opEmail = Optional.of(email);
                    }
                    Email email = opEmail.get();
                    emailCustomRepository.assign(customer, email.getEmail());
                    employee.setSycnState("SYC");
                    employeeRepository.save(employee);
                } catch (Exception e) {
                    logger.log(Level.SEVERE, e.getMessage(), e);
                    employee.setSycnState("SYC");
                    employeeRepository.save(employee);

                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
        

    }
}