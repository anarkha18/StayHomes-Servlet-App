package com.stayhome.servlets;

import com.stayhome.dao.DbCon;
import com.stayhome.modal.flats;
import com.stayhome.modal.userTypes;
import com.stayhome.modal.users;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "ownership", value = "/ownership")
public class ownership extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<flats> flatsWithOwners = new ArrayList<>();

        try {
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("SELECT flats.*, users.* FROM flats LEFT JOIN users ON flats.flat_owner = users.id where flat_owner IS NOT NULL");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                flats flat = new flats();
                flat.setId(rs.getInt("id"));
                flat.setFlatNo(rs.getString("flat_no"));
                flat.setFlatType(rs.getString("flat_type"));

                users owner = new users();
                owner.setId(rs.getInt("flat_owner"));
                owner.setUserName(rs.getString("user_name"));
                owner.setUserPhone(rs.getString("user_phone"));
                // Set other owner properties as needed

                flat.setFlatOwner(owner);

                flatsWithOwners.add(flat);
            }

            request.setAttribute("flatsWithOwners", flatsWithOwners);

            // Retrieve flats with no owners
            List<flats> flatsWithNoOwners = getFlatsWithNoOwners(con);
            request.setAttribute("flatsWithNoOwners", flatsWithNoOwners);

            // Retrieve all owners
            List<users> allOwners = getAllOwners(con);
            request.setAttribute("allOwners", allOwners);

            RequestDispatcher dis = request.getRequestDispatcher("ownership.jsp");
            dis.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<flats> getFlatsWithNoOwners(Connection con) throws SQLException {
        List<flats> flatsWithNoOwners = new ArrayList<>();

        PreparedStatement pst = con.prepareStatement("SELECT * FROM flats WHERE flat_owner IS NULL");
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            flats flat = new flats();
            flat.setId(rs.getInt("id"));
            flat.setFlatNo(rs.getString("flat_no"));
            flat.setFlatType(rs.getString("flat_type"));

            flatsWithNoOwners.add(flat);
        }

        return flatsWithNoOwners;
    }

    public static List<users> getAllOwners(Connection con) throws SQLException {
        List<users> allOwners = new ArrayList<>();

        PreparedStatement pst = con.prepareStatement("SELECT * FROM users WHERE user_type = 1");
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            users owner = new users();
            owner.setId(rs.getInt("id"));
            owner.setUserName(rs.getString("user_name"));
            owner.setUserEmail(rs.getString("user_email"));
            // Set other owner properties as needed

            allOwners.add(owner);
        }

        return allOwners;
    }

}