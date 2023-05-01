/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Invoice;

/**
 *
 * @author ASUS
 */

public class InvoiceService {
    @PersistenceContext
    EntityManager mgr;
    @Resource
    Query query;
    
    public InvoiceService(EntityManager mgr){
        this.mgr = mgr ;
    }
    
    public boolean addInvoice(Invoice invoice){
        mgr.persist(invoice);
        return true;
    }
    
    public Invoice findInvoiceByid(String id){
        Invoice invoice = mgr.find(Invoice.class, id);
        return invoice;
    }
    
    public boolean deleteInvoice(String id) {
        Invoice invoice = findInvoiceByid(id);
        if (invoice != null) {
            mgr.remove(invoice);
            return true;
        }
        return false;
    }
    
    public List<Invoice> findAll(){
        List InvoiceList = mgr.createNamedQuery("Invoice.findAll").getResultList();
        return InvoiceList;
    }
    

    
}