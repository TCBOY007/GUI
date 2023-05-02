package controller;

import entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import javax.annotation.Resource;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet(name = "forgotPassword", urlPatterns = {"/forgotPassword"})
public class ForgotPassword extends HttpServlet {
    @PersistenceContext
    EntityManager em;
    @Resource
    UserTransaction utx;
    private boolean accFound = false;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    String email = request.getParameter("email");
    RequestDispatcher dispatcher = null;
    int otpvalue = 0;
    HttpSession mySession = request.getSession();
    List<Customer> customerList = getAllAccounts();
    Customer customer = new Customer();
    
    
    //loop for found customer from customer list
    for (int i = 0; i < customerList.size(); i++) {
        //if username and password match with one of the customer on list
        if (customerList.get(i).getEmail().equals(email) && customerList.get(i).getStatus() == 'A'){
        
            if(email!=null || !email.equals("")) {
                // sending otp
                Random rand = new Random();
                otpvalue = rand.nextInt(1255650);

                String to = email;// change accordingly
                // Get the session object
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "465");
                Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication("tohxy-wm20@student.tarc.edu.my", "wdgfyrivaoisbusf");// Put your email
                                                                                                                                                                                            // password here
                        }
                });
                // compose message
                try {
                        MimeMessage message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(email));// change accordingly
                        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                        message.setSubject("RESET ACCOUNT PASSWORD");
                        message.setText("NEVER SHARE your OTP!!!\n\n Your OTP is: \t\t" + otpvalue);
                        // send message
                        Transport.send(message);
                        System.out.println("Message sent successfully");
                }

                catch (MessagingException e) {
                        throw new RuntimeException(e);
                }
                accFound = true;
            }
        }
    }
    
    if(!accFound){
                request.setAttribute("message","wrong email");
                dispatcher = request.getRequestDispatcher("forgotPassword.jsp");
                dispatcher.forward(request, response);
    }else{
                dispatcher = request.getRequestDispatcher("EnterOtp.jsp");
                request.setAttribute("message","The OTP is sent to your email id.Please go to check it.");
                //request.setAttribute("connection", con);
                mySession.setAttribute("otp",otpvalue); 
                mySession.setAttribute("email",email); 
                dispatcher.forward(request, response);
                //request.setAttribute("status", "success");
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
