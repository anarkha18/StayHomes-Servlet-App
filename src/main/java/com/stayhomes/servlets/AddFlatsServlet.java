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

@WebServlet(name = "saveFlat", value = "/saveFlat")
public class AddFlatsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the user data from the request
        String flatNo = request.getParameter("flatNo");
        String flatType = request.getParameter("flatType");

        try {

            // Insert the user data into the database
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM flats WHERE flat_no = ?");
            pst.setString(1, flatNo);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // If the user email already exists, display an error message and redirect to index.jsp
                response.sendRedirect("index.jsp?message=Flat No already exists");
            } else {

                pst = con.prepareStatement("INSERT INTO flats (flat_no, flat_type) VALUES (?, ?)");
                pst.setString(1, flatNo);
                pst.setString(2, flatType);

                pst.executeUpdate();
            }
                // Redirect to the index.jsp page or any other desired page after successful user creation
                response.sendRedirect("index.jsp?message=flat created successfully");
            } catch(Exception e){
                e.printStackTrace();
            }
    }
}
