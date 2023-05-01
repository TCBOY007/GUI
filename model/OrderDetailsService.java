/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import javax.annotation.Resource;
import javax.persistence.*;
import model.*;

/**
 *
 * @author chook
 */
public class OrderDetailsService {
    
    
    @PersistenceContext
    EntityManager mgr;
    @Resource
    Query query;

    public OrderDetailsService(EntityManager mgr) {
        this.mgr = mgr;
    }

    public OrderDetailsService(EntityManager mgr, Query query) {
        this.mgr = mgr;
        this.query = query;
    }

    
    public boolean addOrderDetails(Orderdetails orderDetails){
        mgr.persist(orderDetails);
        return true;
    }
    
    public List<Orderdetails> findAll() {
        List orderDetailsList = mgr.createNamedQuery("Orderdetails.findAll").getResultList();
        return orderDetailsList;
    }

    public Orderdetails findByOrderDetailsPK(Orderdetails oPK) {
        Orderdetails OrderDetailsL = mgr.find(Orderdetails.class, oPK);
        return OrderDetailsL;
    }

    public List<Orderdetails> findByOrderId(String id) {
        List orderDetailsList = (List) mgr.createNamedQuery(
                "Orderdetails.findByOrderid")
                .setParameter(1, id)
                .getResultList();

        return orderDetailsList;
    }


 
    
}
