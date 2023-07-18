package com.stayhome.servlets;

import com.stayhome.dao.DbCon;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "AddGasUsage", value = "/AddGasUsage")
public class AddGasUsage  extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the form data
        int flatId = Integer.parseInt(request.getParameter("flat_id"));
        Date usageMonth = Date.valueOf(request.getParameter("usage_month"));
        double unitsConsumed = Double.parseDouble(request.getParameter("units_consumed"));
        int rateId = Integer.parseInt(request.getParameter("rate_id"));
        double amount = calculateAmount(unitsConsumed, rateId);

        try {
            // Insert the gas usage data into the table
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("INSERT INTO gas_usage (flat_id, usage_month, units_consumed, rate_id, amount) VALUES (?, ?, ?, ?, ?)");
            pst.setInt(1, flatId);
            pst.setDate(2, usageMonth);
            pst.setDouble(3, unitsConsumed);
            pst.setInt(4, rateId);
            pst.setDouble(5, amount);
            pst.executeUpdate();
            con.close();

            // Redirect to a success page or display a success message
            response.sendRedirect("index.jsp?message=Gas usage added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            // Redirect to an error page or display an error message
            response.sendRedirect("index.jsp?message=Failed to add gas usage");
        }
    }

    private double calculateAmount(double unitsConsumed, int rateId) {
        // Perform the calculation based on the rate and units consumed
        // You can retrieve the rate from the database using the rateId and apply the calculation logic here
        double rate = getRateFromDatabase(rateId);
        double amount = unitsConsumed * rate;
        return amount;
    }

    private double getRateFromDatabase(int rateId) {
        // Retrieve the rate from the database using the rateId
        // You can implement a DAO method to fetch the rate by its ID
        // Alternatively, you can store the rates in a cache or configuration file for quick retrieval
        // In this example, we assume a hardcoded rate for simplicity
        if (rateId == 1) {
            return 2.5; // Example rate for rateId 1
        } else {
            return 0.0; // Default rate if rateId is not found
        }
    }
}
