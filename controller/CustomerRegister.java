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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author xinying
 */

@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class CustomerRegister extends HttpServlet {
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
        String customerID =  createCustomerID();
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone");
        String address = request.getParameter("address");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        Timestamp createDate = new Timestamp(System.currentTimeMillis());
        Timestamp updateDate = new Timestamp(System.currentTimeMillis());
        RequestDispatcher rd = null;
        boolean status = true;
        //search customer whether existing
        List<Customer> customerList = em.createNamedQuery("Customer.findAll").getResultList();
        
        if(!customerList.isEmpty()){
            for(Customer customer:customerList){
                if(customer.getUsername().equals(username) || customer.getEmail().equals(email)){
                    status = false;
                    request.setAttribute("failed", status);
                    response.sendRedirect("CustomerRegisterError.jsp");
                    
        //          session.setAttribute("message", "Your username or email already existing.");
//                    response.sendRedirect("RegisterAgain");
                }
            }
        }
        
            Customer customer = new Customer(customerID,username,password,email,phoneNumber,address,gender,createDate,updateDate);
            utx.begin();
            em.persist(customer);
            utx.commit();
            session.setAttribute("success", status);
            response.sendRedirect("CustomerRegisterConfirm.jsp");
//            session.setAttribute("message", "Congratulations! You have successfully registered.");

        



    } catch (Exception ex) {
        Logger.getLogger(CustomerRegister.class.getName()).log(Level.SEVERE, null, ex);
        ex.printStackTrace();
    }
}

    
    private String createCustomerID() {
        
        List<Customer> tempCustomerList = em.createNamedQuery("Customer.findAll").getResultList();
        //if the list is not empty then can create customer id 
        if (!tempCustomerList.isEmpty()) {
            
            char firstIndex = tempCustomerList.get(tempCustomerList.size() - 1).getCustomerid().charAt(1);
            char secondIndex = tempCustomerList.get(tempCustomerList.size() - 1).getCustomerid().charAt(2);
            char thirdIndex = tempCustomerList.get(tempCustomerList.size() - 1).getCustomerid().charAt(3);

            String tempCustomerID = String.valueOf(firstIndex) + String.valueOf(secondIndex) + String.valueOf(thirdIndex);
            int nextCustomerID = Integer.parseInt(tempCustomerID) + 1;
            return "C" + String.format("%03d", nextCustomerID);
        } else {
            return "C" + String.format("%03d", 1);
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
