/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

/**
 *
 * @author rakha
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class KoneksiDB {
    private static Connection koneksi;

    private static final String URL  = "jdbc:mysql://localhost:3306/db_gudang_frozen";
    private static final String USER = "root";
    private static final String PASS = "";
    
//    public static Connection getKoneksi() {
//        if (koneksi == null) {
//            try {
//                String url = "jdbc:mysql://localhost:3306/db_gudang_frozen";
//                String user = "root";
//                String pass = "";
//                
//                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//                
//                koneksi = DriverManager.getConnection(url, user, pass);
//                System.out.println("Koneksi Database Berhasil");
//            } catch (SQLException e) {
//                JOptionPane.showMessageDialog(null, "Error Koneksi: " + e.getMessage());
//                System.out.println("Koneksi Database Gagal");
//            }
//        }
//        return koneksi;
//    }
    
    public static Connection getKoneksi() {
        try {
            if (koneksi == null || koneksi.isClosed() || !koneksi.isValid(2)) {
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                koneksi = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("Koneksi Database Berhasil");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Koneksi: " + e.getMessage());
            System.out.println("Koneksi Database Gagal: " + e.getMessage());
        }
        return koneksi;
    }

    public static void closeKoneksi() {
        try {
            if (koneksi != null && !koneksi.isClosed()) {
                koneksi.close();
                koneksi = null;
                System.out.println("Koneksi Database Ditutup");
            }
        } catch (SQLException e) {
            System.out.println("Gagal menutup koneksi: " + e.getMessage());
        }
    }
}