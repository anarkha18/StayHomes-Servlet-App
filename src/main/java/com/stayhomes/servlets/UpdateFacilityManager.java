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

@WebServlet(name = "UpdateFacilityManager", value = "/updateFacilityManager")
public class UpdateFacilityManager extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the facility manager data from the request
        int managerId = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String designation = request.getParameter("designation");
        String phone = request.getParameter("phone");
        String responsibilities = request.getParameter("responsibilities");

        try {
            // Update the facility manager data in the database
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("UPDATE facility_managers SET name = ?, designation = ?, phone = ?, responsibilities = ? WHERE id = ?");
            pst.setString(1, name);
            pst.setString(2, designation);
            pst.setString(3, phone);
            pst.setString(4, responsibilities);
            pst.setInt(5, managerId);
            pst.executeUpdate();

            // Redirect to the index.jsp page or any other desired page after successful update
            response.sendRedirect("index.jsp?message=Facility manager updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
