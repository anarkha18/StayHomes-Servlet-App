package com.stayhomes.servlets;

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

@WebServlet(name = "deleteUser", value = "/deleteUser")
public class DeleteUserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the user ID parameter from the request
        int userId = Integer.parseInt(request.getParameter("id"));

        try {
            // Check if the user's usertype is 1 (owner)
            Connection con = DbCon.getConn();
            PreparedStatement checkPst = con.prepareStatement("SELECT user_type FROM users WHERE id = ?");
            checkPst.setInt(1, userId);
            ResultSet rs = checkPst.executeQuery();

            if (rs.next()) {
                int userType = rs.getInt("user_type");

                if (userType == 1) {
                    // Update the flat_owner ID to NULL in flats table
                    PreparedStatement updatePst = con.prepareStatement("UPDATE flats SET flat_owner = NULL WHERE flat_owner = ?");
                    updatePst.setInt(1, userId);
                    updatePst.executeUpdate();
                }

                // Delete the user from the database
                PreparedStatement deletePst = con.prepareStatement("DELETE FROM users WHERE id = ?");
                deletePst.setInt(1, userId);
                deletePst.executeUpdate();

                // Redirect to the home page with the message parameter
                response.sendRedirect("index.jsp?message=User deleted successfully");
            } else {
                // User not found, display appropriate message or redirect to an error page
                response.sendRedirect("index.jsp?message=User not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
