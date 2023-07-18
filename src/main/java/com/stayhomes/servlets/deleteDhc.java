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

@WebServlet(name = "deleteDhc", value = "/deleteDhc")
public class deleteDhc extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the user ID parameter from the request
        int catId = Integer.parseInt(request.getParameter("id"));

        try {
            // Delete the user from the database
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("DELETE FROM daily_help_category WHERE id = ?");
            pst.setInt(1, catId);
            pst.executeUpdate();

            response.sendRedirect("index.jsp?message=Daily Help Category deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
