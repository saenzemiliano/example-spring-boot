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
import com.example.springboot.demospringboot.model.db.DataEmail;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class DataEmailCustomRepository {

    private static final Logger logger = Logger.getLogger(DataEmailCustomRepository.class.getName());
    
    @PersistenceContext(unitName = Constant.PERSIST_UNIT_NAME)
    private EntityManager em;

    @SuppressWarnings("unchecked")
	public List<DataEmail> fetchBySycnState(String sycnState) {
        List<DataEmail> employees = new LinkedList<>();
        try {
            logger.log(Level.INFO, "Starting fetchBySycnState operation");
            employees = (List<DataEmail>) em.createNamedQuery("DataEmail.fetchBySycnState")
                    .setMaxResults(Constant.QUERY_MAX_RESULT_20000)
                    .getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        }

        return employees;

    }

    public void save(DataEmail employee) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
