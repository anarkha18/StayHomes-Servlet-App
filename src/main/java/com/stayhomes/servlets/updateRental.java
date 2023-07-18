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

@WebServlet(name = "updateRental", value = "/updateRental")
public class updateRental extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the user data from the request
        int rentalId = Integer.parseInt(request.getParameter("rentalId"));
        int tenantId = Integer.parseInt(request.getParameter("tenantId"));

        try {
            Connection con = DbCon.getConn();
            // Check if the user email already exists for other users
            PreparedStatement pst = con.prepareStatement("UPDATE rental SET tenant_id = ? WHERE id = ?");
            pst.setInt(1, tenantId);
            pst.setInt(2, rentalId);
            pst.executeUpdate();

            // Redirect to the users.jsp page or any other desired page after successful update
            response.sendRedirect("index.jsp?message=Rental updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
