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

@WebServlet(name = "deleteOwnership", value = "/deleteOwnership")
public class deleteOwnership extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the user data from the request
        int flatId = Integer.parseInt(request.getParameter("id"));

        try {
            Connection con = DbCon.getConn();
            // Check if the user email already exists for other users
            PreparedStatement pst = con.prepareStatement("UPDATE flats SET flat_owner = NULL WHERE id = ?");
            pst.setInt(1, flatId);
            pst.executeUpdate();

            // Redirect to the users.jsp page or any other desired page after successful update
            response.sendRedirect("index.jsp?message=Flat ownership removed successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
