package cn.techtutorial.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.mail.MessagingException;

import java.util.Random;

import cn.techtutorial.connection.DbCon;
import cn.techtutorial.dao.UserDao;
import cn.techtutorial.dao.EmailSender;

import cn.techtutorial.model.User;

@WebServlet("/user-registration")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String name = request.getParameter("registration-name");
            String email = request.getParameter("registration-email");
            String password = request.getParameter("registration-password");
            HttpSession session = request.getSession();
            
            
            User newUser = new User();
	        newUser.setName(name);
	        newUser.setEmail(email);
	        newUser.setPassword(password);
	        newUser.setRole("user");
            session.setAttribute("user", newUser);
            try {
                UserDao udao = new UserDao(DbCon.getConnection());
                boolean registrationSuccess = udao.registerUser(newUser);

                if (registrationSuccess) {
                    out.println("Registration successful. You can now login.");
                    response.sendRedirect("login.jsp");
                    // You might also consider redirecting to a login page.
                } else {
                    response.sendRedirect("Fail.html");
                	
                    //out.println("Registration failed. Please try again.");
                }
            } catch (ClassNotFoundException | SQLException e) {
                out.println("An error occurred while processing your request.");
                e.printStackTrace();
            }
        } catch (Exception e) {
          e.printStackTrace();
      }
    }
}
//@WebServlet("/user-registration")
//public class RegisterServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            String name = request.getParameter("registration-name");
//            String email = request.getParameter("registration-email");
//            String password = request.getParameter("registration-password");
//            
////            // Generate OTP
//            String otp = generateOTP();
//
//            // Save OTP in the user's session
//            HttpSession session = request.getSession();
//            session.setAttribute("otp", otp);
//
//            // Save user information in session (for simplicity, you might want to save this in the database later)
//            User newUser = new User();
//	        newUser.setName(name);
//	        newUser.setEmail(email);
//	        newUser.setPassword(password);
//	        newUser.setRole("user");
//            session.setAttribute("user", newUser);
////            try {
////                UserDao udao = new UserDao(DbCon.getConnection());
////                boolean registrationSuccess = udao.registerUser(newUser);
////
////                if (registrationSuccess) {
////                    out.println("Registration successful. You can now login.");
////                    response.sendRedirect("login.jsp");
////                    // You might also consider redirecting to a login page.
////                } else {
////                    response.sendRedirect("Fail.html");
////                	
////                    //out.println("Registration failed. Please try again.");
////                }
////            } catch (ClassNotFoundException | SQLException e) {
////                out.println("An error occurred while processing your request.");
////                e.printStackTrace();
////            }
//            // Send OTP via Email
//            try {
//                EmailSender.sendOTPEmail(email, otp);
//                // Redirect to OTP verification page
//                response.sendRedirect("otp.jsp");
//            } catch (MessagingException e) {
//                e.printStackTrace();
//                out.println("Failed to send OTP email. Please try again.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private String generateOTP() {
//        // Generate a random 6-digit OTP
//        Random random = new Random();
//        int otp = 100000 + random.nextInt(900000);
//        return String.valueOf(otp);
//    }
//}
