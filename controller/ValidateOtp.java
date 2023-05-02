package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
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

/**
 * Servlet implementation class ValidateOtp
 */

@WebServlet(name = "ValidateOtp", urlPatterns = {"/ValidateOtp"})
public class ValidateOtp extends HttpServlet {
    @PersistenceContext
    EntityManager em;
    @Resource
    UserTransaction utx;
    private boolean accFound = false;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
            {
                    int value=Integer.parseInt(request.getParameter("otp"));
                    HttpSession session=request.getSession();
                    int otp=(int)session.getAttribute("otp");
                    RequestDispatcher dispatcher=null;

                    if (value==otp) 
                    {
                            request.setAttribute("email", request.getParameter("email"));
                            request.setAttribute("status", "success");
                            dispatcher=request.getRequestDispatcher("newPassword.jsp");
                            dispatcher.forward(request, response);

                    }
                    else
                    {
                        request.setAttribute("message","wrong otp");
                        dispatcher=request.getRequestDispatcher("EnterOtp.jsp");
                        dispatcher.forward(request, response);

                    }

            }

	

}
