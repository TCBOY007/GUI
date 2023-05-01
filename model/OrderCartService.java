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
public class OrderCartService {
    
    @PersistenceContext
    EntityManager mgr;
    @Resource
    Query query;

    public OrderCartService(EntityManager mgr) {
        this.mgr = mgr;
    }

    public OrderCartService(EntityManager mgr, Query query) {
        this.mgr = mgr;
        this.query = query;
    }
    
    public List<OrderCart> findAll() {
        List orderList = mgr.createNamedQuery("OrderCart.findAll").getResultList();
        return orderList;
    }
    
    public boolean addCart(OrderCart orderCart){
        mgr.persist(orderCart);
        return true;
    }
    
    public OrderCart findByCartid(String id) {
        OrderCart orderCart = mgr.find(OrderCart.class, id);
        return orderCart;
    }
    
    
    public boolean deleteCart(String id) {
        OrderCart orderCart = findByCartid(id);
        if (orderCart != null) {
            mgr.remove(orderCart);
            return true;
        }
        return false;
    }
    
    public boolean deleteOneCustAllCart(String custId){
        List<OrderCart> allCart = findAll();
        
        for (int i=0; i < allCart.size(); i++){
            
            if(allCart.get(i).getCustomerid().getCustomerid().equals(custId)){
                mgr.remove(allCart.get(i));
                
            }
        }
        
        for(int i=0; i < allCart.size(); i++){
            if(allCart.get(i).getCustomerid().getCustomerid().equals(custId)){
                
                return false;
            }
            else{
                return true;
            }
        }
        
        return false;
    }
    
    public boolean updateItem(OrderCart orderCart) {
        OrderCart tempCart = findByCartid(orderCart.getCartid());
        if (tempCart != null) {
            tempCart.setQuantity(orderCart.getQuantity());
            return true;
        }
        return false;
    }
    
}
