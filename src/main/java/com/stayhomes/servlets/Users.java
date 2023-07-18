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

@WebServlet(name = "Users", value = "/Users")
public class Users extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<users> userList= new ArrayList<>();
        try {
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("select * from users");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                users user = new users();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("user_name"));
                user.setUserEmail(rs.getString("user_email"));
                user.setUserPassword(rs.getString("user_password"));
                user.setUserPhone(rs.getString("user_phone"));
                user.setUserType(rs.getInt("user_type"));
                userList.add(user);
            }
            request.setAttribute("userList", userList);
            RequestDispatcher dis = request.getRequestDispatcher("users.jsp");
            dis.forward(request, response);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        response.setContentType("text/html");


        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1> </h1>");
        out.println("</body></html>");
    }

}