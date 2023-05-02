package controller;


import entity.Customer;
import entity.CustomerService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import javax.transaction.TransactionRolledbackException;
import javax.transaction.UserTransaction;

/**
 * Servlet implementation class NewPassword
 */

@WebServlet(name = "newPassword", urlPatterns = {"/newPassword"})
public class NewPassword extends HttpServlet {
    @PersistenceContext
    EntityManager em;
    @Resource
    UserTransaction utx;
    
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    HttpSession session = request.getSession();
    String newPassword = request.getParameter("resetpw1");
    String confPassword = request.getParameter("resetpw2");
    String email = (String) session.getAttribute("email");
    CustomerService custService = new CustomerService(em);

    try {
        // Retrieve the customer with the specified email
        List<Customer> custList = custService.findByEmail(email);
        if (!custList.isEmpty()) {
            Customer customer = custList.get(0);
            customer.setPassword(newPassword);
            
            utx.begin();
            em.merge(customer);
            utx.commit();
 
            RequestDispatcher dispatcher = request.getRequestDispatcher("CustomerLogin.jsp");
            dispatcher.forward(request, response);

        } else {
            throw new Exception("Customer not found");
        }
    } catch (Exception ex) {
        Logger.getLogger(NewPassword.class.getName()).log(Level.SEVERE, null, ex);
        ex.printStackTrace();
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


