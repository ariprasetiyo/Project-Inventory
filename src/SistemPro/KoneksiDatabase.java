/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemPro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author LANTAI3
 */
public class KoneksiDatabase {
    
        String IpServer = "localhost";
        String Database = "itbsp";
        String User = "root";
        String Password = "";
        public Connection koneksi = null;
       
        /*
       public static void main(String args[]) {
     
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProgramInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProgramInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProgramInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProgramInventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               KoneksiDatabase a = new KoneksiDatabase();
               a.KonekDatabase();
                
            }
        });
    } */
        
       public void KonekDatabase () {
           // n.
        try {
                    Class.forName("com.mysql.jdbc.Driver");//.newInstance();
                    //Class.forName ("com.mysql.jdbc.Driver");
            }
            catch ( Exception ex ) {
                    JOptionPane.showMessageDialog (null, "Error (1)"+ ex, "Error", JOptionPane.ERROR_MESSAGE);
                    //System.err.println("Error (1): " + ex);
                    //System.exit(1);
            } 
            //koneksi database
           koneksi = null;
           //System.out.print(database);
            try {
                    //Class.forName("com.mysql.jdbc.Driver");//"/media/ILMU/PROGRAMING/JAVA/belajar_java/koneksi_sql/paket_and_error/mysql-connector-java-3.1.14/mysql-connector-java-3.1.14-bin.jar");
                    
                    koneksi = DriverManager.getConnection ( "jdbc:mysql://"+IpServer+"/"+Database,User,Password );
                    System.out.println("berhasi koneksi !!!!!!" + Database);
                    //System.out.print(DariDatabaseFlat);
            }
            catch ( Exception ex ) {
                    String DapatkanNama = new String();
                    DapatkanNama = this.getClass().getName();
                    JOptionPane.showMessageDialog (null, "Error (2) :"
                            + " tidak ada koneksi ke database HO jakarta. Hub IT HO BSP di no 02189641773" ); 
                   //+ ex, "Error", JOptionPane.ERROR_MESSAGE);
                   // System.err.println(DapatkanNama + "error (2): " + ex);
                   // System.exit(1);
            }
    }
}
