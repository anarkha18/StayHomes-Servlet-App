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

@WebServlet(name = "DeleteFacilityManager", value = "/DeleteFacilityManager")
public class DeleteFacilityManager extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the facility manager ID parameter from the request
        int managerId = Integer.parseInt(request.getParameter("id"));

        try {
            // Delete the facility manager from the database
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("DELETE FROM facility_managers WHERE id = ?");
            pst.setInt(1, managerId);
            pst.executeUpdate();

            response.sendRedirect("index.jsp?message=Facility manager deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
