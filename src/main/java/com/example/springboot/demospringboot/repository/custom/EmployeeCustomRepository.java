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
import com.example.springboot.demospringboot.model.db.Employee;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeCustomRepository {

    private static final Logger logger = Logger.getLogger(EmployeeCustomRepository.class.getName());
    
    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
	public List<Employee> fetchBySycnState(String sycnState) {
        List<Employee> employees = new LinkedList<>();
        try {
            logger.log(Level.INFO, "Starting fetchBySycnState operation");
            employees = (List<Employee>) em.createNamedQuery("Employee.fetchBySycnState")
                    .setMaxResults(Constant.QUERY_MAX_RESULT_20000)
                    .getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        }

        return employees;

    }

    public void save(Employee employee) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
