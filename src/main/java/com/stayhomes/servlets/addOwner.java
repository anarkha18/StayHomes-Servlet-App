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
import java.sql.ResultSet;


@WebServlet(name = "addOwner", value = "/addOwner")
public class addOwner extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String flatNo = request.getParameter("flatNo");
        String flatOwner = request.getParameter("flatOwner");

        try{
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM flats WHERE flat_no = ?");
            pst.setString(1,flatNo);
            ResultSet rs = pst.executeQuery();

            while (rs.next()){
                pst = con.prepareStatement("UPDATE flats SET flat_owner = ? WHERE flat_no = ?");
                pst.setString(1, flatOwner);
                pst.setString(2, flatNo);
                pst.executeUpdate();


                response.sendRedirect("index.jsp?message=flat updated successfully");
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
