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

@WebServlet(name = "deleteRental", value = "/deleteRental")
public class deleteRental extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the user data from the request
        int rentalId = Integer.parseInt(request.getParameter("id"));

        try {
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("DELETE FROM rental WHERE id = ?");
            pst.setInt(1, rentalId);
            pst.executeUpdate();

            // Redirect to the users.jsp page or any other desired page after successful update
            response.sendRedirect("index.jsp?message=Rental removed successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
