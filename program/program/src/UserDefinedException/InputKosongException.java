/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserDefinedException;

import javax.swing.JOptionPane;

/**
 *
 * @author bintangkusuma
 */
public class InputKosongException extends Exception{
    public void tampilpesan(){
        JOptionPane.showMessageDialog(null, "Input tidak boleh kosong !");
    }
}
