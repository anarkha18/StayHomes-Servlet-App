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

@WebServlet(name = "updateOwnership", value = "/updateOwnership")
public class updateOwnership extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the user data from the request
        String flatNo = request.getParameter("flatNo");
        int flatOwner = Integer.parseInt(request.getParameter("flatOwner"));

        try {
            Connection con = DbCon.getConn();
            // Check if the user email already exists for other users
            PreparedStatement pst = con.prepareStatement("UPDATE flats SET flat_owner = ? WHERE flat_no = ?");
                pst.setInt(1, flatOwner);
                pst.setString(2, flatNo);
                pst.executeUpdate();

                // Redirect to the users.jsp page or any other desired page after successful update
                response.sendRedirect("index.jsp?message=Flat updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
