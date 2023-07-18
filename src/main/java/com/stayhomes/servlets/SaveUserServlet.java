package com.stayhome.servlets;

import com.stayhome.dao.DbCon;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "saveUser", value = "/saveUser")
public class SaveUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the user data from the request
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String userPassword = request.getParameter("userPassword");
        int userType = Integer.parseInt(request.getParameter("userType"));
        String userPhone = request.getParameter("userPhone");

        try {
            // Check if the user email already exists in the database
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM users WHERE user_email = ?");
            pst.setString(1, userEmail);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // If the user email already exists, display an error message and redirect to index.jsp
                response.sendRedirect("index.jsp?message=User email already exists");
            } else {
                // If the user email doesn't exist, insert the user data into the database
                pst = con.prepareStatement("INSERT INTO users (user_name, user_email, user_password, user_type, user_phone) VALUES (?, ?, ?, ?, ?)");
                pst.setString(1, userName);
                pst.setString(2, userEmail);
                pst.setString(3, userPassword);
                pst.setInt(4, userType);
                pst.setString(5, userPhone);
                pst.executeUpdate();

                response.sendRedirect("index.jsp?message=User updated successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
