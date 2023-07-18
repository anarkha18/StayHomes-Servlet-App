package com.stayhome.servlets;

import com.stayhome.dao.DbCon;
import com.stayhome.modal.userTypes;
import com.stayhome.modal.users;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "tenants", value = "/tenants")
public class tenants extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<users> tenantsList= new ArrayList<>();
        try {
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("select * from users where user_type = 2");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                users user = new users();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("user_name"));
                user.setUserEmail(rs.getString("user_email"));
                user.setUserPhone(rs.getString("user_phone"));
                tenantsList.add(user);

            }

            request.setAttribute("tenantsList", tenantsList);
            RequestDispatcher dis = request.getRequestDispatcher("tenants.jsp");
            dis.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}