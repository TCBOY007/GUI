/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Customer;
import model.OrderCart;
import model.Product;

/**
 *
 * @author chook
 */
@WebServlet(name = "getCartForPayment", urlPatterns = {"/getCartForPayment"})
public class getCartForPayment extends HttpServlet {

    @PersistenceContext
    EntityManager em;
        
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            
            HttpSession session = request.getSession();
            String customerId = (String)session.getAttribute("customer_id");
            
            Customer custInfo = findCustomerById(customerId);
            List<OrderCart> cartDetails = getCartDetails(customerId);
            List<Product> allProd = getAllProd(customerId);
            
            session.setAttribute("custInfo",custInfo);
            session.setAttribute("orderCartDetails", cartDetails);
            session.setAttribute("prodDetails", allProd);
                       
            response.sendRedirect("payment.jsp");
            
        } catch (Exception ex) {
            Logger.getLogger(loginTest.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
    public Customer findCustomerById(String customerId) {
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findByCustomerid", Customer.class);
        query.setParameter("customerid", customerId);
        List<Customer> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null; // or throw an exception, depending on your application's requirements
        } else {
            return resultList.get(0); // return the first (and presumably only) element in the list
        }
    }
    
    public List<OrderCart> getCartDetails(String custId){
        List<OrderCart> allCart = em.createNamedQuery("OrderCart.findAll").getResultList();
        List<OrderCart> certainCart = new ArrayList<>();
        
        for(int i=0; i < allCart.size(); i++){
            
            if(allCart.get(i).getCustomerid().getCustomerid().equals(custId)){
                certainCart.add(allCart.get(i));
            }
        }
        
        return certainCart;
    }
    
        public List<Product> getAllProd(String custId){
        List<Product> allProd = em.createNamedQuery("Product.findAll").getResultList();

            return allProd;
    }
    
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
