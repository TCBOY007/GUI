/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author xinying
 */

import java.util.List;
import javax.annotation.Resource;
import javax.persistence.*;

public class CustomerService {

    @PersistenceContext
    EntityManager mgr;
    @Resource
    Query query;

    public CustomerService(EntityManager mgr) {
        this.mgr = mgr;
    }

    public boolean addCustomer(Customer customer) {
        mgr.persist(customer);
        return true;
    }

    //find customer
    public Customer findByCustomerid(String id) {
        Customer customer = mgr.find(Customer.class, id);
        return customer;
    }
    
    public boolean deleteCustomer(String id) {
        Customer customer = findByCustomerid(id);
        if (customer != null) {
            mgr.remove(customer);
            return true;
        }
        return false;
    }
    
    //Update profile
    public boolean updateCustomerProfile(Customer customer) {
        Customer tempCust = findByCustomerid(customer.getCustomerid());
        if (tempCust != null) {
            tempCust.setEmail(customer.getEmail());
            tempCust.setPhonenumber(customer.getPhonenumber());
            tempCust.setPassword(customer.getPassword());
            tempCust.setAddress(customer.getAddress());
            tempCust.setUpdateDate(customer.getUpdateDate());
            return true;
        }
        return false;
    }
    
    //Update password
//    public boolean updateCustomerAccPassword(CustomerAccount customerAcc) {
//        CustomerAccount tempCust = findByUsername(customerAcc.getUsername());
//        if (tempCust != null) {
//            tempCust.setPassword(customerAcc.getPassword());
//            return true;
//        }
//        return false;
//    }

    //default
    public List<Customer> findAll() {
        List customerList = mgr.createNamedQuery("Customer.findAll").getResultList();
        return customerList;
    }
    
 
    //sort by id
    public List<Customer> sortIDAsc() {
        List customerList = mgr.createNamedQuery("Customer.sortIDAsc").getResultList();
        return customerList;
    }
    
    public List<Customer> sortIDDesc() {
        List customerList = mgr.createNamedQuery("Customer.sortIDDesc").getResultList();
        return customerList;
    }

    //sort by name
    public List<Customer> sortNameAsc() {
        List customerList = mgr.createNamedQuery("Customer.sortNameAsc").getResultList();
        return customerList;
    }

    public List<Customer> sortNameDesc() {
        List customerList = mgr.createNamedQuery("Customer.sortNameDesc").getResultList();
        return customerList;
    }

    //sort by ic
    public List<Customer> sortICAsc() {
        List customerList = mgr.createNamedQuery("Customer.sortICAsc").getResultList();
        return customerList;
    }
    
    public List<Customer> sortICDesc() {
        List customerList = mgr.createNamedQuery("Customer.sortICDesc").getResultList();
        return customerList;
    }
    public List<Customer> findByEmail(String email) {
        Query q = mgr.createNamedQuery("Customer.findByEmail").setParameter("email", email);
        return q.getResultList();
    }
    //search query 
    public List<Customer> findBySearch(String searchQuery) {
        Query q = mgr.createNamedQuery("Customer.findBySearch");
	q.setParameter("customerid", searchQuery);
        q.setParameter("customername", "%" + searchQuery + "%");
        q.setParameter("customeric", "%" + searchQuery + "%");
        return q.getResultList();
    }
}
