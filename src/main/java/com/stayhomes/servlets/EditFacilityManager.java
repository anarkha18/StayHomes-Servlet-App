package com.stayhome.servlets;

import com.stayhome.dao.DbCon;
import com.stayhome.modal.FacilityManager;

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

@WebServlet(name = "editFacilityManager", value = "/editFacilityManager")
public class EditFacilityManager extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the facility manager ID parameter from the request
        int managerId = Integer.parseInt(request.getParameter("id"));
        FacilityManager manager = new FacilityManager();
        try {
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM facility_managers WHERE id = ?");
            pst.setInt(1, managerId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                manager.setId(rs.getInt("id"));
                manager.setName(rs.getString("name"));
                manager.setDesignation(rs.getString("designation"));
                manager.setPhone(rs.getString("phone"));
                manager.setResponsibilities(rs.getString("responsibilities"));
            }
            request.setAttribute("manager", manager);
            RequestDispatcher dispatcher = request.getRequestDispatcher("editFacilityManager.jsp");
            dispatcher.forward(request, response);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
