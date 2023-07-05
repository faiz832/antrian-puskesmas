/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program;

/**
 *
 * @author bintangkusuma
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import UserDefinedException.InputKosongException;
import UserDefinedException.dalamAntrianException;
import UserDefinedException.TidakAdaBarisDipilih;

public class Pasien {
    private static Connection c;
    private static Statement s;
    private static ResultSet rs;
    
    private static void openDB() throws ClassNotFoundException, SQLException{
        String URL = "jdbc:mysql://localhost:3306/puskesmas";
        String Username = "root";
        String Password = "";

        Class.forName("com.mysql.cj.jdbc.Driver");
        c = DriverManager.getConnection(URL,Username,Password);
        s = c.createStatement();
    }
    
    private static void closeDB() {
        try{
            rs.close();
            s.close();
            c.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    private String IDPasien;
    private String namaPasien;
    private String umur;
    private String gender;

    public Pasien(String IDPasien, String namaPasien, String umur, String gender) {
        this.IDPasien = IDPasien;
        this.namaPasien = namaPasien;
        this.umur = umur;
        this.gender = gender;
    }
    
    public Pasien(String IDPasien) {
        this.IDPasien = IDPasien;
    }
    
    public String getIdPasien(){
        return IDPasien;
    }
    
    public int cekAdaPasienUmum(String IDPasien){
        int cekAdaPasien = 0;
        try {
            openDB();
            rs = s.executeQuery("SELECT COUNT(*) AS total FROM AntrianRuangUmum WHERE IDPasien = '" + IDPasien + "';");
            rs.next();
            cekAdaPasien = Integer.parseInt(rs.getString("total"));

        }catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
        return cekAdaPasien;
    }

    public int cekAdaPasienKhusus(String IDPasien){
        int cekAdaPasien = 0;
        try {
            openDB();
            rs = s.executeQuery("SELECT COUNT(*) AS total FROM AntrianRuangKhusus WHERE IDPasien = '" + IDPasien + "';");
            rs.next();
            cekAdaPasien = Integer.parseInt(rs.getString("total"));

        }catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
        return cekAdaPasien;

    }
    
    public int cekDuplikatPasien(String IDPasien){
        int cekAdaPasien = 0;
        try {
            openDB();
            rs = s.executeQuery("SELECT COUNT(*) AS total FROM Pasien WHERE IDPasien = '" + IDPasien + "';");
            rs.next();
            cekAdaPasien = Integer.parseInt(rs.getString("total"));

        }catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
        return cekAdaPasien;
    }
    
    public void inputDatabasePasien(String IDPasien, String namaPasien, String umur, String gender){
        try {
            openDB();
            s.executeUpdate(
                "INSERT INTO Pasien VALUES ('" + IDPasien + "','" + namaPasien + "','" + umur + "','" + gender + "')"
            );
        }catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }
    
    public void updateDatabasePasien(String IDPasien, String namaPasien, String umur, String gender){
        try {
            openDB();
            s.executeUpdate(
                "UPDATE Pasien SET namaPasien = '" + namaPasien + "', umur = '" + umur + "', jenisKelamin = '" + gender + "' WHERE IDPasien = '" + IDPasien + "';"
            );
        }catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }
    
    public void deleteDatabasePasien(String IDPasien){
        try {
            openDB();
            s.executeUpdate(
                "DELETE FROM Pasien WHERE IDPasien = '" + IDPasien + "';"
            );
        }catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }
    
    
    


}