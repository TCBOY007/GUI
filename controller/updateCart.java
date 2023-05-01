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
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

import model.*;

/**
 *
 * @author chook
 */
@WebServlet(name = "updateCart", urlPatterns = {"/updateCart"})
public class updateCart extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    @Resource
    UserTransaction utx;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            HttpSession session = request.getSession();

            String[] eachQtt = request.getParameterValues("quantity");
            String[] eachprodId = request.getParameterValues("hideProductId");
            Customer custInfo = (Customer) session.getAttribute("customerInfo");
            List<OrderCart> cartDetails = (List) session.getAttribute("cartDetails");

            List<String> qttList = new ArrayList<>();
            List<String> prodIdList = new ArrayList<>();

            if (eachQtt != null) {
                for (String value : eachQtt) {
                    qttList.add(value);
                }
            }

            if (eachprodId != null) {
                for (String value : eachprodId) {
                    prodIdList.add(value);
                }
            }

            out.println(" Cart size       : <b> " + cartDetails.size() + "</b><br/>");
            for (int i = 0; i < cartDetails.size(); i++) {
                
 
                for (int j=0; j < prodIdList.size(); j++){
                        if (cartDetails.get(i).getProductid().getProductid().equals(prodIdList.get(j))) {

                        cartDetails.get(i).setQuantity(Integer.parseInt(qttList.get(j)));
                        OrderCartService cartService = new OrderCartService(em);

                        OrderCart temp = cartDetails.get(i);
                        
                        utx.begin();
                        boolean success = cartService.updateItem(temp);
                        utx.commit();

                        
                        session.setAttribute("updateStatus", success);
                        session.setAttribute("newCart", cartDetails.get(i));
                        session.setAttribute("buttonClicked", "returnBackPosition");
                    }
                }

            }
            

            response.sendRedirect("payment.jsp");
            

        } catch (Exception ex) {
            Logger.getLogger(updateCart.class.getName()).log(Level.SEVERE, null, ex);
        }

            
    }
    
}
