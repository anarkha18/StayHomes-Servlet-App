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

@WebServlet(name = "addDhcCat", value = "/addDhcCat")
public class addDhcCat extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the user data from the request

        String cname = request.getParameter("cname");

        try {
            // Insert the user data into the database
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM daily_help_category WHERE category_name = ?");
            pst.setString(1, cname);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // If the user email already exists, display an error message and redirect to index.jsp
                response.sendRedirect("index.jsp?message=category already exists");
            } else {

                pst = con.prepareStatement("INSERT INTO daily_help_category (category_name) VALUES (?)");
                pst.setString(1, cname);

                pst.executeUpdate();
            }
            // Redirect to the index.jsp page or any other desired page after successful user creation
            response.sendRedirect("index.jsp?message=category created successfully");
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
