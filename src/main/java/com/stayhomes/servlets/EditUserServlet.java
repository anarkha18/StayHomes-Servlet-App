package com.stayhome.servlets;

import com.stayhome.dao.DbCon;
import com.stayhome.modal.userTypes;
import com.stayhome.modal.users;

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

@WebServlet(name = "EditUserServlet ", value = "/editUser")
public class EditUserServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the user ID parameter from the request
        int userId = Integer.parseInt(request.getParameter("id"));
        users user = new users();
        try {
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("select  * from users where id = ?");
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                 user.setUserName(rs.getString("user_name"));
                user.setUserEmail(rs.getString("user_email"));
                user.setUserPassword(rs.getString("user_password"));
                user.setUserPhone(rs.getString("user_phone"));
                user.setUserType(rs.getInt("user_type"));
            }
            request.setAttribute("myUser", user);
            RequestDispatcher dis = request.getRequestDispatcher("editUser.jsp");
            dis.forward(request, response);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
