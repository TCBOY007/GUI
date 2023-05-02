/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Customer;
import entity.CustomerService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

/**
 *
 * @author xinying
 */
@WebServlet(name = "customerUpdate", urlPatterns = {"/customerUpdate"})
public class CustomerUpdate extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    @Resource
    UserTransaction utx;
    private String accFound = "failed";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try{
            CustomerService custService = new CustomerService(em);
            HttpSession session = request.getSession();
            Customer customer = (Customer) session.getAttribute("customer");
            
            String customerID = customer.getCustomerid();
            String gender = customer.getGender();
            char status = 'A';                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
            Date createDate = customer.getCreateDate();

            String username = customer.getUsername();
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phone");
            
            String password = request.getParameter("pw1");
            if(password.isEmpty()){
                password = request.getParameter("defaultpw");
            }
            String address = request.getParameter("address");
            Timestamp updateDate = new Timestamp(System.currentTimeMillis());
    
           
            List<Customer> customerList = custService.findAll();
            if(!customerList.isEmpty()){
                Customer updCustomer = new Customer(customerID,username,password,email,phoneNumber,address,gender,createDate,updateDate,status);

               utx.begin();
               custService.updateCustomerProfile(updCustomer);
               utx.commit();
               session.setAttribute("message", "success");
               session.setAttribute("customer",updCustomer);
               response.sendRedirect("CustomerUpdate.jsp");
            }
            
            request.setAttribute("message", "failed");
            response.sendRedirect("CustomerUpdate.jsp");
           
           
        } catch (Exception ex) {
            Logger.getLogger(CustomerUpdate.class.getName()).log(Level.SEVERE, null, ex);
        } 
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
