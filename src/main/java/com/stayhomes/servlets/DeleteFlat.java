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

@WebServlet(name = "deleteFlat", value = "/deleteFlat")
public class DeleteFlat extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the user ID parameter from the request
        int flatId = Integer.parseInt(request.getParameter("id"));

        try {
            // Delete the user from the database
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("DELETE FROM flats WHERE id = ?");
            pst.setInt(1, flatId);
            pst.executeUpdate();

            // Set the message parameter
//           request.setAttribute("message", "User deleted successfully");

            // Redirect to the home page with the message parameter
            response.sendRedirect("index.jsp?message=Flat deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
