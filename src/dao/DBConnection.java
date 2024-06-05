package dao;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/universidad";
    private static final String USER = "root";
    private static final String PASSWORD = "admin123";
    private static Connection conn = getConnection();

    public static Connection getConnection() {
        try {
            conn=DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
        return conn;
    }

    public static void closeConn() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
