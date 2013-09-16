/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemPro;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author LANTAI3
 */
public class KeyNo {
    String no;
    int noint;
    public String GetKeyNoString (){
        return no;
    }
    public int GetKeyNoInt (){
        return noint;
    }
    public void SetKeyNo ( String Tabel) {
        KoneksiDatabase K = new KoneksiDatabase();
        K.KonekDatabase();
        Connection CK = K.koneksi;
        ResultSet Hq = null;
        try {
            Statement stm = CK.createStatement();
            Hq = stm.executeQuery("select key_no from "+Tabel+ " order by key_no desc limit 0,1" );
            String Hasil;
            int Hasil3 = 0;
            while (Hq.next()){
                Hasil = Hq.getString("key_no");
                Hasil3 = Integer.valueOf(Hasil).intValue(); 
                //JOptionPane.showMessageDialog(null,Hasil3 + " Key No");
            }
            Hasil3 = Hasil3 + 1;
            this.noint = Hasil3;
            this.no = String.valueOf(Hasil3).toString();
                    
        }
        catch (Exception X) {
            JOptionPane.showMessageDialog(null,  "key_no.java : error : 1225 database :"  +X, " Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
