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

public class Dokter {
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
    
    private String IDDokter;
    private String namaDokter;
    private String spesialisasi;

    public Dokter(String IDDokter, String namaDokter, String spesialisasi) {
        this.IDDokter = IDDokter;
        this.namaDokter = namaDokter;
        this.spesialisasi = spesialisasi;
    }
    
    public Dokter(String IDDokter) {
        this.IDDokter = IDDokter;
    }
    
    public String getIdDokter(){
        return IDDokter;
    }
    
    public int cekDuplikatDokter(String IDDokter){
        int cekAdaDokter = 0;
        try {
            openDB();
            rs = s.executeQuery("SELECT COUNT(*) AS total FROM Dokter WHERE IDDokter = '" + IDDokter + "';");
            rs.next();
            cekAdaDokter = Integer.parseInt(rs.getString("total"));
            
        }catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
        return cekAdaDokter;
    }

    public void inputDatabaseDokter(String IDDokter, String namaDokter, String spesialis){
        try {
            openDB();
            s.executeUpdate(
                "INSERT INTO Dokter VALUES ('" + IDDokter + "','" + namaDokter + "','" + spesialis + "')"
            );
        }catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }
    
    public void updateDatabaseDokter(String IDDokter, String namaDokter, String spesialis){
        try {
            openDB();
            s.executeUpdate(
                "UPDATE Dokter SET namaDokter = '" + namaDokter + "', spesialisasi = '" + spesialis + "' WHERE IDDokter = '" + IDDokter + "';"
            );
        }catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }
    
    public void deleteDatabaseDokter(String IDDokter){
        try {
            openDB();
            s.executeUpdate(
                "DELETE FROM Dokter WHERE IDDokter = '" + IDDokter + "';"
            );
        }catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }
}
