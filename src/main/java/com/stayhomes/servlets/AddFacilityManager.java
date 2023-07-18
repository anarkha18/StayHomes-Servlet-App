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

@WebServlet(name = "AddFacilityManager", value = "/AddFacilityManager")
public class AddFacilityManager extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the facility manager data from the request
        String name = request.getParameter("name");
        String designation = request.getParameter("designation");
        String phone = request.getParameter("phone");
        String responsibilities = request.getParameter("responsibilities");

        try {
            // Insert the facility manager data into the database
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("INSERT INTO facility_managers (name, designation, phone, responsibilities) VALUES (?, ?, ?, ?)");
            pst.setString(1, name);
            pst.setString(2, designation);
            pst.setString(3, phone);
            pst.setString(4, responsibilities);

            pst.executeUpdate();

            // Redirect to the index.jsp page or any other desired page after successful facility manager creation
            response.sendRedirect("index.jsp?message=Facility manager created successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
