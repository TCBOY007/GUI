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
public class OrderService {
    @PersistenceContext
    EntityManager mgr;
    @Resource
    Query query;

    public OrderService(EntityManager mgr) {
        this.mgr = mgr;
    }

    public OrderService(EntityManager mgr, Query query) {
        this.mgr = mgr;
        this.query = query;
    }
    
    public boolean addOrder(Ordertable order){
        mgr.persist(order);
        return true;
    }

    public List<Ordertable> findAll() {
        List orderList = mgr.createNamedQuery("Ordertable.findAll").getResultList();
        return orderList;
    }

    public Ordertable findOrderByID(String id) {
        Ordertable orders = mgr.find(Ordertable.class, id);
        return orders;
    }


    public boolean deleteOrder(String id) {
        Ordertable orders = findOrderByID(id);
        if (orders != null) {
            mgr.remove(orders);
            return true;
        }
        return false;
    }
}
