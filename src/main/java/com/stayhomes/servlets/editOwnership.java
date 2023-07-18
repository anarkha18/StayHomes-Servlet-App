package com.stayhome.servlets;

import com.stayhome.dao.DbCon;
import com.stayhome.modal.flats;
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
import java.util.List;


@WebServlet(name = "editOwnership", value = "/editOwnership")
public class editOwnership extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int id = Integer.parseInt(request.getParameter("id"));
        flats flat = new flats();
        try{
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("SELECT flats.*, users.* FROM flats LEFT JOIN users ON flats.flat_owner = users.id WHERE flats.id=?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                flat.setId(rs.getInt("id"));
                flat.setFlatNo(rs.getString("flat_no"));

                users owner = new users();
                owner.setId(rs.getInt("flat_owner"));
                owner.setUserName(rs.getString("user_name"));

                flat.setFlatOwner(owner);
            }
            request.setAttribute("myFlat", flat);
            List<users> owners = ownership.getAllOwners(con);
            request.setAttribute("owners", owners);
            RequestDispatcher dis = request.getRequestDispatcher("editOwnership.jsp");
            dis.forward(request, response);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
