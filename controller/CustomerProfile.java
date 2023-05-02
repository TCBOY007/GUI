/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Customer;
import entity.CustomerService;
import entity.OrderService;
import entity.Ordertable;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "CustomerProfile", urlPatterns = {"/CustomerProfile"})
public class CustomerProfile extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    @Resource
    UserTransaction utx;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try{
            
            HttpSession session = request.getSession();
            //get the customer from jsp
           Customer customer = (Customer) session.getAttribute("customerAcc");
          
           if(customer == null){
                response.sendRedirect("CustomerLogin.jsp");
                
            }else{
                CustomerService custService = new CustomerService(em);
                Customer showCustomer = custService.findByCustomerid(customer.getCustomerid());
                session.setAttribute("customer", showCustomer);
               
                List<Ordertable> orderList = getAllOrders();
                
                session.setAttribute("orderList", orderList);
                response.sendRedirect("CustomerProfile.jsp");
            }
            
        }catch (Exception ex) {
                Logger.getLogger(CustomerProfile.class.getName()).log(Level.SEVERE, null, ex);
        }
          
       }
       public List<Ordertable> getAllOrders() {
        List orderList = em.createNamedQuery("Ordertable.findAll").getResultList();
        return orderList;
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
