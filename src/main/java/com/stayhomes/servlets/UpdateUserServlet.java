package com.stayhome.servlets;

import com.stayhome.dao.DbCon;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "updateUser", value = "/updateUser")
public class UpdateUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the user data from the request
        int userId = Integer.parseInt(request.getParameter("id"));
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String userPassword = request.getParameter("userPassword");
        int userType = Integer.parseInt(request.getParameter("userType"));
        String userPhone = request.getParameter("userPhone");


        try {
            // Update the user data in the database
            Connection con = DbCon.getConn();
            // Check if the user email already exists for other users
            PreparedStatement pst = con.prepareStatement("SELECT * FROM users WHERE user_email = ? AND id != ?");
            pst.setString(1, userEmail);
            pst.setInt(2, userId);
            ResultSet rs = pst.executeQuery();


            if (rs.next()) {
                // If the user email already exists, display an error message and redirect to index.jsp
                response.sendRedirect("index.jsp?message=User email already exists");
            } else {
                // If the user email doesn't exist, insert the user data into the database
                pst = con.prepareStatement("UPDATE users SET user_name = ?, user_email = ?, user_password = ?, user_type = ?, user_phone = ? WHERE id = ?");
                pst.setString(1, userName);
                pst.setString(2, userEmail);
                pst.setString(3, userPassword);
                pst.setInt(4, userType);
                pst.setString(5, userPhone);
                pst.setInt(6, userId);
                pst.executeUpdate();

                // Redirect to the users.jsp page or any other desired page after successful update
                response.sendRedirect("index.jsp?message=User updated successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
