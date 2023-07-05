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

public class AntrianRuangUmum extends Antrian{
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
        
    private String nomorAntrian;
    private Pasien pasien;
    private Dokter dokter;
    
    public AntrianRuangUmum(String nomorAntrian, Pasien pasien, Dokter dokter) {
        this.nomorAntrian = nomorAntrian;
        this.pasien = pasien;
        this.dokter = dokter;
    }
    
    public AntrianRuangUmum(String nomorAntrian) {
        this.nomorAntrian = nomorAntrian;

    }
    
    @Override
    public String getIdPasienDatabase(String nomorAntrian){
        String IDPasien = "";
        try {
            openDB();
            rs = s.executeQuery("SELECT IDPasien FROM AntrianRuangUmum WHERE IDPasien = '" + nomorAntrian + "';");
            rs.next();
            IDPasien = rs.getString("IDPasien");

        }catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
        return IDPasien;
    }
    
    @Override
    public int cekDataPasien(Pasien pasien){
        int cekAdaPasien = 0;
        try {
            openDB();
            rs = s.executeQuery("SELECT COUNT(*) AS total FROM Pasien WHERE IDPasien = '" + pasien.getIdPasien() + "';");
            rs.next();
            cekAdaPasien = Integer.parseInt(rs.getString("total"));

        }catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
        return cekAdaPasien;
    }

    
    @Override
    public int cekDataDokter(Dokter dokter){
        int cekAdaDokter = 0;
        try {
            openDB();
            rs = s.executeQuery("SELECT COUNT(*) AS total FROM Dokter WHERE IDDokter = '" + dokter.getIdDokter() + "';");
            rs.next();
            cekAdaDokter = Integer.parseInt(rs.getString("total"));
            
        }catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
        return cekAdaDokter;
    }
    
    @Override
    public void inputDatabaseAntrian(String nomorAntrian, Pasien pasien, Dokter dokter){
        try {
            openDB();
            String IDPasien = pasien.getIdPasien();
            String IDDokter = dokter.getIdDokter();
            s.executeUpdate(
                "INSERT INTO AntrianRuangUmum VALUES ('" + nomorAntrian + "','" + IDPasien + "','" + IDDokter + "')"
            );
        }catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }
    
    @Override
    public void updateDatabaseAntrian(String nomorAntrian, Pasien pasien, Dokter dokter){
        try {
            openDB();
            String IDPasien = pasien.getIdPasien();
            String IDDokter = dokter.getIdDokter();
            s.executeUpdate(
                "UPDATE AntrianRuangUmum SET IDPasien = '" + IDPasien + "', IDDokter = '" + IDDokter + "' WHERE nomorAntrian = '" + nomorAntrian + "';"
            );
        }catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }
    
    @Override
    public void deleteDatabaseAntrian(String nomorAntrian){
        try {
            openDB();
            s.executeUpdate(
                "DELETE FROM AntrianRuangUmum WHERE nomorAntrian = '" + nomorAntrian + "';"
            );
        }catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }
    
}
