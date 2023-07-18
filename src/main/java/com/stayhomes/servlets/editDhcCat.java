package com.stayhome.servlets;

import com.stayhome.dao.DbCon;
import com.stayhome.modal.DailyHelpCategory;
import com.stayhome.modal.flats;
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

@WebServlet(name = "editDhcCat ", value = "/editDhcCat")
public class editDhcCat extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the user ID parameter from the request
        int catId = Integer.parseInt(request.getParameter("id"));
        DailyHelpCategory dhc = new DailyHelpCategory();
        try {
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("select  * from daily_help_category where id = ?");
            pst.setInt(1, catId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                dhc.setId(rs.getInt("id"));
                dhc.setName(rs.getString("category_name"));
            }
            request.setAttribute("dhc", dhc);
            RequestDispatcher dis = request.getRequestDispatcher("editDhcCat.jsp");
            dis.forward(request, response);
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
