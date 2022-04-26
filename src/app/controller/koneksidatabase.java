package app.controller;


import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER
 */
    public class koneksidatabase {
    private static Connection koneksi;
    
    
    public static Connection koneksi() {
        String driver = "com.mysql.jdbc.Driver";
        String host = "jdbc:mysql://localhost/gudangalami";
        String user = "root";
        String pass = "";
        try {
            Class.forName(driver);
            Connection conn = (Connection) DriverManager.getConnection(host, user, pass);
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            Alert peringatan = new Alert(Alert.AlertType.ERROR);
            peringatan.setTitle("Peringatan SQL Connection");
            peringatan.setHeaderText(null);
            peringatan.setContentText("" + e);
            peringatan.showAndWait();
        }
        return null;
    }
    
}
