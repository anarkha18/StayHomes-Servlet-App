package com.stayhome.servlets;

import com.stayhome.dao.DbCon;
import com.stayhome.modal.flats;
import com.stayhome.modal.rental;
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


@WebServlet(name = "editRental", value = "/editRental")
public class editRental extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int rentalId = Integer.parseInt(request.getParameter("id"));
        rental myRental = new rental();
        try {
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("SELECT rental.id, flats.flat_no, rental.flat_id,rental.tenant_id, users.user_name " +
                    "FROM rental " +
                    "INNER JOIN flats ON rental.flat_id = flats.id " +
                    "INNER JOIN users ON rental.tenant_id = users.id " +
                    "WHERE rental.id = ?");

            pst.setInt(1, rentalId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                myRental.setId(rs.getInt("id"));

                flats myFlat = new flats();
                myFlat.setId(rs.getInt("flat_id"));
                myFlat.setFlatNo(rs.getString("flat_no"));

                users myUser = new users();
                myUser.setId(rs.getInt("tenant_id"));
                myUser.setUserName(rs.getString("user_name"));

                myRental.setFlat(myFlat);
                myRental.setTenant(myUser);
            }
            request.setAttribute("myRental", myRental);
            List<users> tenants = rentals.getTenantsWithNoFlats(con);
            request.setAttribute("tenants", tenants);
            RequestDispatcher dis = request.getRequestDispatcher("editRental.jsp");
            dis.forward(request, response);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}

