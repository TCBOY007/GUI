/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import javax.annotation.Resource;
import javax.persistence.*;


/**
 *
 * @author chook
 */
public class PaymentService {
    @PersistenceContext
    EntityManager mgr;
    @Resource
    Query query;

    public PaymentService(EntityManager mgr) {
        this.mgr = mgr;
    }

    public PaymentService(EntityManager mgr, Query query) {
        this.mgr = mgr;
        this.query = query;
    }
    
    public boolean addPayment(Payment payment){
        mgr.persist(payment);
        return true;
    }
    

    public List<Payment> findAll() {
        List paymentList = mgr.createNamedQuery("Payment.findAll").getResultList();
        return paymentList;
    }

    public List<Payment> findByPaymentId() {
        List paymentList = mgr.createNamedQuery("Payment.findByPaymentid").getResultList();
        return paymentList;
    }

    
    
}
