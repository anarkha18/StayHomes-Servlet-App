package com.stayhome.servlets;

import com.stayhome.dao.DbCon;
import com.stayhome.modal.GasUnitRate;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GasRate", value = "/GasRate")
public class GasRate extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<GasUnitRate> gasRateList = new ArrayList<>();
        try {
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM gas_rate");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                double rate = rs.getDouble("rate");

                // Create a GasRate object and add it to the list
                GasUnitRate gasRate = new GasUnitRate(id, rate);
                gasRateList.add(gasRate);
            }

            request.setAttribute("gasRateList", gasRateList);
            RequestDispatcher dis = request.getRequestDispatcher("GasRate.jsp");
            dis.forward(request, response);
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
