package com.stayhome.servlets;

import com.stayhome.dao.DbCon;
import com.stayhome.modal.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "MonthlyGasUsage", value = "/MonthlyGasUsage")
public class MonthlyGasUsage extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<GasUsage> monthlyGasUsages = new ArrayList<>();
        try {
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("select * from gas_usage  join flats on flats.id = flat_id join gas_rate on gas_rate.id = rate_id;  ");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                GasUsage gasUsage = new GasUsage();
                gasUsage.setId(rs.getInt("id"));


                flats flat = new flats();
                flat.setId(rs.getInt("flat_id"));
                flat.setFlatNo(rs.getString("flat_no"));
                flat.setFlatType(rs.getString("flat_type"));

                gasUsage.setFlatId(flat);
                gasUsage.setUsageMonth(rs.getDate("usage_month"));
                gasUsage.setUnitsConsumed(rs.getDouble("units_consumed"));


                GasUnitRate rate = new GasUnitRate();
                rate.setId(rs.getInt("rate_id"));
                rate.setRate(rs.getDouble("rate"));

                gasUsage.setRateId(rate);
                gasUsage.setAmount(rs.getDouble("amount"));
                monthlyGasUsages.add(gasUsage);
            }

            request.setAttribute("monthlyGasUsages", monthlyGasUsages);
            List<flats> myFlats =MonthlyGasUsage.getFlats(con);
            request.setAttribute("myFlats", myFlats);

            GasUnitRate myGas = MonthlyGasUsage.getGasRateById(1);
            request.setAttribute("myGas", myGas);
            RequestDispatcher dis = request.getRequestDispatcher("MonthlyGasUsage.jsp");
            dis.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static List<flats> getFlats(Connection con) throws SQLException {
        List<flats> getFlats = new ArrayList<>();

        PreparedStatement pst = con.prepareStatement("SELECT * FROM flats WHERE flat_owner IS NOT NULL");
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            flats flat = new flats();
            flat.setId(rs.getInt("id"));
            flat.setFlatNo(rs.getString("flat_no"));
            flat.setFlatType(rs.getString("flat_type"));

            getFlats.add(flat);
        }

        return getFlats;
    }
    public static GasUnitRate getGasRateById(int id) {
        GasUnitRate gasRate = null;
        try {
            Connection con = DbCon.getConn();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM gas_rate WHERE id = ?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                double rate = rs.getDouble("rate");
                gasRate = new GasUnitRate(id, rate);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gasRate;
    }
}
