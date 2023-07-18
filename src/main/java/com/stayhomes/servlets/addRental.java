package com.stayhome.servlets;

import com.stayhome.dao.DbCon;
import com.stayhome.modal.flats;
import com.stayhome.modal.userTypes;
import com.stayhome.modal.users;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "addRental", value = "/addRental")
public class addRental extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int flatId = Integer.parseInt(request.getParameter("flatId"));
        int tenantId = Integer.parseInt(request.getParameter("tenantId"));
        try {
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("insert into rental(flat_id,tenant_id) values (?,?);");
            pst.setInt(1,flatId );
            pst.setInt(2,tenantId );
            int i = pst.executeUpdate();

            if (i > 0){
                response.sendRedirect("index.jsp?message=Rental added successfully");
            }
            else {
                response.sendRedirect("index.jsp?message=Failed to add Rental!!!");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}