package com.stayhome.servlets;

import com.stayhome.dao.DbCon;
import com.stayhome.modal.flats;
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

@WebServlet(name = "Flats", value = "/Flats")
public class Flats extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<flats> flatsList = new ArrayList<>();

        try {
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("SELECT flats.*, users.* FROM flats LEFT JOIN users ON flats.flat_owner = users.id");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                flats flat = new flats();
                flat.setId(rs.getInt("id"));
                flat.setFlatNo(rs.getString("flat_no"));
                flat.setFlatType(rs.getString("flat_type"));

                users owner = new users();
                owner.setId(rs.getInt("flat_owner"));
                owner.setUserName(rs.getString("user_name"));
                owner.setUserEmail(rs.getString("user_email"));
                // Set other owner properties as needed

                flat.setFlatOwner(owner);

                flatsList.add(flat);
            }

            request.setAttribute("flatsList", flatsList);
            RequestDispatcher dis = request.getRequestDispatcher("flats.jsp");
            dis.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}