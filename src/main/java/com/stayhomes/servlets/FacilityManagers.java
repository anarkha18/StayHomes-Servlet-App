package com.stayhome.servlets;

import com.stayhome.dao.DbCon;
import com.stayhome.modal.FacilityManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "FacilityManagers", value = "/FacilityManagers")
public class FacilityManagers extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<FacilityManager> managerList = new ArrayList<>();
        try {
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM facility_managers");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String designation = rs.getString("designation");
                String phone = rs.getString("phone");
                String responsibilities = rs.getString("responsibilities");

                // Create a FacilityManager object and add it to the list
                FacilityManager fm = new FacilityManager(id,name,designation,phone,responsibilities);
                managerList.add(fm);
            }

            request.setAttribute("managerList", managerList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("FacilityManager.jsp");
            dispatcher.forward(request, response);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
