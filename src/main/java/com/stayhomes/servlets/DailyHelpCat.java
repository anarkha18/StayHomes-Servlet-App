package com.stayhome.servlets;

import com.stayhome.dao.DbCon;
import com.stayhome.modal.DailyHelpCategory;
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

@WebServlet(name = "DailyHelpCat", value = "/DailyHelpCat")
public class DailyHelpCat extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<DailyHelpCategory> DHCList= new ArrayList<>();
        try {
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("select * from daily_help_category");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("category_name");

                // Create a DailyHelpCategory object and add it to the list
                DailyHelpCategory category = new DailyHelpCategory(id, name);
                DHCList.add(category);
            }

            request.setAttribute("DHCList", DHCList);
            RequestDispatcher dis = request.getRequestDispatcher("DailyHelpCategory.jsp");
            dis.forward(request, response);
            con.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}