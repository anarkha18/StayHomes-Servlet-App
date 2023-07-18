package com.stayhome.servlets;

import com.stayhome.dao.DbCon;
import com.stayhome.modal.flats;
import com.stayhome.modal.rental;
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

@WebServlet(name = "rentals ", value = "/rentals")
public class rentals extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<rental> rentalList = new ArrayList<>();

        try {
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("SELECT r.*, f.*, u.* " +
                    "FROM rental r " +
                    "JOIN flats f ON r.flat_id = f.id " +
                    "JOIN users u ON r.tenant_id = u.id");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                rental rental = new rental();
                rental.setId(rs.getInt("id"));

                flats flat = new flats();
                flat.setId(rs.getInt("flat_id"));
                flat.setFlatNo(rs.getString("flat_no"));
                flat.setFlatType(rs.getString("flat_type"));

                rental.setFlat(flat);
                users tenant = new users();
                tenant.setId(rs.getInt("tenant_id"));
                tenant.setUserName(rs.getString("user_name"));
                tenant.setUserEmail(rs.getString("user_email"));
                tenant.setUserPhone(rs.getString("user_phone"));
                // Set other owner properties as needed
                rental.setTenant(tenant);
                rentalList.add(rental);
            }

            request.setAttribute("rentalList", rentalList);

            // Retrieve flats with no rental
            List<flats> flatsWithNoRental = getFlatsWithNoRenatl(con);
            request.setAttribute("flatsWithNoRental", flatsWithNoRental);

            // Retrieve all tenants with no flats
            List<users> tenantsWithNoFlats = getTenantsWithNoFlats(con);
            request.setAttribute("tenantsWithNoFlats", tenantsWithNoFlats);

            RequestDispatcher dis = request.getRequestDispatcher("rentals.jsp");
            dis.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<flats> getFlatsWithNoRenatl(Connection con) throws SQLException {
        List<flats> flatsWithNoRenatl = new ArrayList<>();

        PreparedStatement pst = con.prepareStatement("SELECT * FROM flats WHERE id NOT IN (SELECT flat_id FROM rental)");
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            flats flat = new flats();
            flat.setId(rs.getInt("id"));
            flat.setFlatNo(rs.getString("flat_no"));
            flat.setFlatType(rs.getString("flat_type"));

            flatsWithNoRenatl.add(flat);
        }

        return flatsWithNoRenatl;
    }

    public static List<users> getTenantsWithNoFlats(Connection con) throws SQLException {
        List<users> tenantsWithNoFlats = new ArrayList<>();

        PreparedStatement pst = con.prepareStatement("SELECT * FROM users WHERE user_type = 2 AND id NOT IN (SELECT tenant_id FROM rental)");
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            users tenant = new users();
            tenant.setId(rs.getInt("id"));
            tenant.setUserName(rs.getString("user_name"));
            tenant.setUserEmail(rs.getString("user_email"));
            tenant.setUserPhone(rs.getString("user_phone"));
            // Set other owner properties as needed

            tenantsWithNoFlats.add(tenant);
        }

        return tenantsWithNoFlats;
    }

}