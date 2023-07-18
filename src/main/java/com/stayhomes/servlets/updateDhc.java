package com.stayhome.servlets;

import com.stayhome.dao.DbCon;

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

@WebServlet(name = "updateDhc", value = "/updateDhc")
public class updateDhc extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the user data from the request
        int catId = Integer.parseInt(request.getParameter("id"));
        String cname = request.getParameter("cname");

        try {
            // Update the user data in the database
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("UPDATE daily_help_category SET category_name = ? WHERE id = ?");
                pst.setString(1, cname);
                pst.setInt(2, catId);
                pst.executeUpdate();

                // Redirect to the users.jsp page or any other desired page after successful update
                response.sendRedirect("index.jsp?message=DailyHelp Category updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
