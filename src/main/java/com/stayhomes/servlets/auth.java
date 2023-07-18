package com.stayhome.servlets;

import com.stayhome.dao.DbCon;
import com.stayhome.modal.users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "auth", value = "/auth")
public class auth extends HttpServlet{

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        String email = request.getParameter("userEmail");
        String pass = request.getParameter("userPassword");

        try{
            Connection con = DbCon.getConn();
            System.out.println();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM users WHERE user_email = ? AND user_password = ?");
            pst.setString(1, email);
            pst.setString(2, pass);
            ResultSet rs = pst.executeQuery();
            if (rs.next()){
                HttpSession session1 = request.getSession();
                session1.setAttribute("userId", rs.getInt(1));
                session1.setAttribute("userName", rs.getString(2));
                session1.setAttribute("userEmail", rs.getString(3));
                session1.setAttribute("userPassword", rs.getString(4));
                session1.setAttribute("userType", rs.getInt(5));
                session1.setAttribute("userPhone", rs.getInt(6));

                // Redirect to the appropriate page based on user type
                int userType = rs.getInt(5);
                String redirectPage;
                if (userType == 1) {
                    redirectPage = "ownerHome.jsp";
                } else if (userType == 2) {
                    redirectPage = "tenantHome.jsp";
                } else if (userType == 3) {
                    redirectPage = "accountantHome.jsp";
                } else if (userType == 4){
                    redirectPage = "index.jsp";
                }
                else{
                    redirectPage = "login.jsp?message=Error in Login credentials, please try again!!!";
                }
                response.sendRedirect(redirectPage);
            }

         else {
                response.sendRedirect("login.jsp?message=Error in Login credentials, please try again!!!");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
