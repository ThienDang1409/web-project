package cn.techtutorial.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.techtutorial.connection.DbCon;
import cn.techtutorial.dao.UserDao;
import cn.techtutorial.model.User;

@WebServlet("/verify-otp")
public class VerifyOTPServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String enteredOTP = request.getParameter("otp");

            // Retrieve the stored OTP from the session
            HttpSession session = request.getSession();
            String storedOTP = (String) session.getAttribute("otp");

            if (enteredOTP.equals(storedOTP)) {
                // OTP verification successful, clear the OTP from the session
                session.removeAttribute("otp");

                // Retrieve user information from the session
                User newUser = (User) session.getAttribute("user");

                // TODO: Save the new user information to the database using UserDao
                UserDao userDao = new UserDao(DbCon.getConnection());
                boolean registrationSuccess = userDao.registerUser(newUser);

                if (registrationSuccess) {
                    out.println("Registration successful!");
                    response.sendRedirect("login.jsp");
                    
                } else {
                    out.println("Failed to register user. Please try again.");
                }
            } else {
                out.println("Invalid OTP. Please try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
