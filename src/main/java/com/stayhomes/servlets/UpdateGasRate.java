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

@WebServlet(name = "UpdateGasRate", value = "/UpdateGasRate")
public class UpdateGasRate extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the gas rate data from the request
        int rateId = Integer.parseInt(request.getParameter("id"));
        double rate = Double.parseDouble(request.getParameter("rate"));

        try {
            // Update the gas rate data in the database
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("UPDATE gas_rate SET rate = ? WHERE id = ?");
            pst.setDouble(1, rate);
            pst.setInt(2, rateId);
            pst.executeUpdate();

            // Redirect to the gas rate list page or any other desired page after successful update
            response.sendRedirect("index.jsp?message=Gas rate updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
