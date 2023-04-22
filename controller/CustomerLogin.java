/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Customer;
import entity.Order1;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author xinying
 */
@WebServlet(name = "CustomerLogin", urlPatterns = {"/CustomerLogin"})
public class CustomerLogin extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    private boolean accFound = false;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
            try{
                HttpSession session = request.getSession();
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                
                List<Customer> customerList = getAllAccounts();
                Customer customer = new Customer();
               
                
                
                //loop for found customer from customer list
                for (int i = 0; i < customerList.size(); i++) {
                    //if username and password match with one of the customer on list
                    if (customerList.get(i).getUsername().equalsIgnoreCase(username) && customerList.get(i).getPassword().equalsIgnoreCase(password) && customerList.get(i).getStatus() == 'A') {
                        
                        customer.setCustomerid(customerList.get(i).getCustomerid());
                        customer.setUsername(username);
                        customer.setPassword(password);
                        customer.setEmail(customerList.get(i).getEmail());
                        customer.setPhonenumber(customerList.get(i).getPhonenumber());
                        customer.setAddress(customerList.get(i).getAddress());
                        
                       
                        accFound = true;
                    }
                }
                
                if(!accFound){
                    
                    response.sendRedirect("customerLoginError.jsp");
                }else{
                    session.setAttribute("customerAcc", customer);
                    response.sendRedirect("index.html");
                    
                    accFound = false;
                    
                }
                
            }catch (Exception ex) {
                Logger.getLogger(CustomerLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        public List<Customer> getAllAccounts() {
        List accountList = em.createNamedQuery("Customer.findAll").getResultList();
        return accountList;
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
