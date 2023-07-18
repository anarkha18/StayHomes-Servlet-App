package com.stayhome.servlets;

import com.stayhome.dao.DbCon;
import com.stayhome.modal.GasUnitRate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "EditGasRate", value = "/EditGasRate")
public class EditGasRate extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the gas rate ID parameter from the request
        int rateId = Integer.parseInt(request.getParameter("id"));

        try {
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM gas_rate WHERE id = ?");
            pst.setInt(1, rateId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                double rate = rs.getDouble("rate");

                // Create a GasRate object
                GasUnitRate gasRate = new GasUnitRate(id, rate);

                request.setAttribute("gasRate", gasRate);
                request.getRequestDispatcher("EditGasRate.jsp").forward(request, response);
            } else {
                // Gas rate not found, handle the case accordingly
                response.sendRedirect("index.jsp?message=Gas rate not found");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
