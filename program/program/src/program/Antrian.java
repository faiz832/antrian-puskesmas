/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package program;

/**
 *
 * @author bintangkusuma
 */


abstract class Antrian {
    public int cekDataPasien(Pasien pasien){return 0;};
    public int cekDataDokter(Dokter dokter){return 0;};
    public String getIdPasienDatabase(String nomorAntrian){return "";};
    public void inputDatabaseAntrian(String nomorAntrian, Pasien pasien, Dokter dokter){};
    public void updateDatabaseAntrian(String nomorAntrian, Pasien pasien, Dokter dokter){};
    public void deleteDatabaseAntrian(String nomorAntrian){};
    
}
