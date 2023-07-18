package com.stayhome.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbCon {

    public static final String driverName = "com.mysql.cj.jdbc.Driver";
    public static final String url = "jdbc:mysql://localhost:3306/stayhomes";
    public static final String uname = "root";
    public static final String pass = "admin";

    public static Connection getConn(){
        Connection con= null;
        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(url,uname,pass);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return con;

    }
}
