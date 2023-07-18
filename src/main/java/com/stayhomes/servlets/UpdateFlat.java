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

@WebServlet(name = "updateFlat", value = "/updateFlat")
public class UpdateFlat extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the user data from the request
        int flatId = Integer.parseInt(request.getParameter("id"));
        String flatNo = request.getParameter("flatNo");
        String flatType = request.getParameter("flatType");

        try {
            // Update the user data in the database
            Connection con = DbCon.getConn();
            // Check if the user email already exists for other users
            PreparedStatement pst = con.prepareStatement("SELECT * FROM flats WHERE flat_no = ? AND id != ?");
            pst.setString(1, flatNo);
            pst.setInt(2, flatId);
            ResultSet rs = pst.executeQuery();


            if (rs.next()) {
                // If the user email already exists, display an error message and redirect to index.jsp
                response.sendRedirect("index.jsp?message=Flat No already exists");
            } else {
                pst = con.prepareStatement("UPDATE flats SET flat_no = ?, flat_type = ? WHERE id = ?");
                pst.setString(1, flatNo);
                pst.setString(2, flatType);
                pst.setInt(3, flatId);
                pst.executeUpdate();

                // Redirect to the users.jsp page or any other desired page after successful update
                response.sendRedirect("index.jsp?message=Flat updated successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
