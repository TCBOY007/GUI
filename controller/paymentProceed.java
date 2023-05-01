/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import javax.servlet.http.HttpSession;

import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import model.*;

/**
 *
 * @author chook
 */
@WebServlet(name = "paymentProcced", urlPatterns = {"/paymentProcced"})
public class paymentProceed extends HttpServlet {

    @PersistenceContext
    EntityManager em;
    @Resource
    UserTransaction utx;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //LocalDate currentDate = LocalDate.now();
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String formattedDate = dateFormat.format(currentDate);
            Timestamp timestamp = Timestamp.valueOf(formattedDate);

            HttpSession session = request.getSession();

            // the parameter is using the name inside the input tag
            Customer custInfo = (Customer) session.getAttribute("customerInfo");

            String custId = custInfo.getCustomerid();
            String paymentMethod = request.getParameter("paymentMethod");
            String paymentAmount = request.getParameter("totalAmount");
            String paymentDate = timestamp.toString();
            String paymentStatus = "Successful";

            // process and save payment details to database
            PaymentService paymentService = new PaymentService(em);
            List<Payment> allPayment = paymentService.findAll();
            int allPaymentSize = allPayment.size();
            String latestPaymentID = String.format("P%03d", allPaymentSize + 1);

            Payment payment = new Payment();
            payment.setCustomerid(custInfo);
            payment.setPaymentid(latestPaymentID);
            payment.setPaymentamount(paymentAmount);
            payment.setPaymentdate(currentDate);
            payment.setPaymentmethod(paymentMethod);
            payment.setPaymentstatus(paymentStatus);

            utx.begin();
            boolean paymentInsertStatus = paymentService.addPayment(payment);
            utx.commit();


            // process invoice details and save to database
            InvoiceService invoiceService = new InvoiceService(em);
            List<Invoice> allInvoice = invoiceService.findAll();
            int allInvoiceSize = allInvoice.size();
            String latestInvoiceID = String.format("INV%03d", allInvoiceSize + 1);

            Invoice invoice = new Invoice();
            invoice.setCustomerid(custInfo);
            invoice.setInvoiceid(latestInvoiceID);
            invoice.setInvoicedate(currentDate);
            invoice.setPaymentid(payment);
            invoice.setTotalamount(paymentAmount);

            utx.begin();
            boolean invoiceInsertStatus = invoiceService.addInvoice(invoice);
            utx.commit();


            // process order and save to database
            OrderService orderService = new OrderService(em);
            List<Ordertable> allOrder = orderService.findAll();
            int allOrderSize = allOrder.size();
            String latestOrderID = String.format("OR%03d", allOrderSize + 1);
            String custAddress = custInfo.getAddress();

            Ordertable order = new Ordertable();
            order.setOrderid(latestOrderID);
            order.setOrderdate(currentDate);
            order.setShippingaddress(custAddress);
            order.setGrandtotal(paymentAmount);
            order.setOrderstatus("packaging");
            order.setPaymentid(payment);
            order.setCustomerid(custInfo);

            utx.begin();
            boolean orderInsertStatus = orderService.addOrder(order);
            utx.commit();

            
            // process orderDetails and save to database
            OrderDetailsService orderDetailsService = new OrderDetailsService(em);
            List<OrderCart> cartDetails = (List) session.getAttribute("cartDetails");
            
            
            OrderdetailsPK orderDetailsPK = new OrderdetailsPK();
            
            List<Orderdetails> allOrderDetails = new ArrayList<>();
            
            for (int i = 0; i < cartDetails.size(); i++) {
                Orderdetails orderDetails = new Orderdetails();
                
                orderDetailsPK.setOrderid(latestOrderID);
                orderDetailsPK.setProductid(cartDetails.get(i).getProductid().getProductid());
                
                orderDetails.setOrderdetailsPK(orderDetailsPK);
                orderDetails.setDatefororder(currentDate);
                orderDetails.setQuantity(cartDetails.get(i).getQuantity());
                orderDetails.setPrice(cartDetails.get(i).getPrice());
                orderDetails.setProduct(cartDetails.get(i).getProductid());
                
                utx.begin();
                boolean detailsInsertStatus = orderDetailsService.addOrderDetails(orderDetails);
                utx.commit();
                
                
               
                allOrderDetails.add(orderDetails);
            }
            
            
            // setting attribute to pass
            session.setAttribute("customerInfo", custInfo);
            session.setAttribute("invoice", invoice);
            session.setAttribute("allOrderDetails", allOrderDetails);
              
            
            OrderCartService orderCartService = new OrderCartService(em);
            utx.begin();
            boolean deleteAllCart = orderCartService.deleteOneCustAllCart(custId);
            utx.commit();
            response.sendRedirect("invoice.jsp");

        } catch (Exception ex) {
            Logger.getLogger(paymentProceed.class.getName()).log(Level.SEVERE, null, ex);
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
