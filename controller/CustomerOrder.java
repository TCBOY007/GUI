/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Customer;
import entity.Ordertable;
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
@WebServlet(name = "CustomerOrder", urlPatterns = {"/CustomerOrder"})
public class CustomerOrder extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    private boolean accFound = false;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
            try{
                HttpSession session = request.getSession();

                
                List<Ordertable> orderList = getAllOrders();
                Ordertable order = new Ordertable();
                 Customer customer = (Customer) session.getAttribute("customerAcc");
                
                
                //loop for found order from order list
                for (int i = 0; i < orderList.size(); i++) {
                    //if username and password match with one of the order on list
                     if(orderList.get(i).getCustomerid().equals(customer.getCustomerid())){
                        order.setOrderid(orderList.get(i).getOrderid());
                        order.setOrderdate(orderList.get(i).getOrderdate());
                        order.setShippingaddress(orderList.get(i).getShippingaddress());
                        order.setGrandtotal(orderList.get(i).getGrandtotal());
                        order.setPaymentid(orderList.get(i).getPaymentid());
                        order.setOrderstatus(orderList.get(i).getOrderstatus());
                    
                    }
                }
                
                    session.setAttribute("orderAcc", order);

            }catch (Exception ex) {
                Logger.getLogger(CustomerOrder.class.getName()).log(Level.SEVERE, null, ex);
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
