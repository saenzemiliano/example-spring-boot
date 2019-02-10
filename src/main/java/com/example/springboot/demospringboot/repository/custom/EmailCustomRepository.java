/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.springboot.demospringboot.repository.custom;

/**
 *
 * @author esaenz
 */
import com.example.springboot.demospringboot.common.Constant;
import com.example.springboot.demospringboot.model.Email;
import com.example.springboot.demospringboot.model.db.Customer;
import com.example.springboot.demospringboot.model.db.Employee;
import com.example.springboot.demospringboot.repository.CustomerRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class EmailCustomRepository {

    @PersistenceContext(unitName = Constant.PERSIST_UNIT_NAME)
    private EntityManager em;

    public Optional<Email> findByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Email save(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void assign(Customer customer, String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}