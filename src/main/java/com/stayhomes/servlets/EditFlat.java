package com.stayhome.servlets;

import com.stayhome.dao.DbCon;
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

@WebServlet(name = "editFlat ", value = "/editFlat")
public class EditFlat extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the user ID parameter from the request
        int flatId = Integer.parseInt(request.getParameter("id"));
        flats flat = new flats();
        try {
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("select  * from flats where id = ?");
            pst.setInt(1, flatId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                flat.setId(rs.getInt("id"));
                flat.setFlatNo(rs.getString("flat_no"));
                flat.setFlatType(rs.getString("flat_type"));
            }
            request.setAttribute("myFlat", flat);
            RequestDispatcher dis = request.getRequestDispatcher("editFlats.jsp");
            dis.forward(request, response);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
