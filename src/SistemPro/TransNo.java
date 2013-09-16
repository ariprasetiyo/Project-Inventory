/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemPro;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author LANTAI3
 */
public class TransNo {
    public String TransNo;
    int Nourut = 0;
    public int GetNoUrut (){
        return Nourut;
    }
    public String GetTransNoPo (){
        return TransNo;
    }
    public void SetTransNoPo ( String JnsData, String SelectKolom, String SelectPeriod, String FromTabel, String WhereSyarat){

            KoneksiDatabase KoneksiDb = new KoneksiDatabase();
            KoneksiDb.KonekDatabase();
            Connection koneksi = KoneksiDb.koneksi;

            ResultSet hasilquery = null;

            //JOptionPane.showMessageDialog(null,WhereSyarat+ " where syarat ");
            TanggalSistem ConvertTgl = new TanggalSistem();
            ConvertTgl.SetDate(WhereSyarat);
            String  Tahun   = ConvertTgl.GetTahunStrDate();
            String Tanggal  = String.valueOf(ConvertTgl.GetTanggalIntDate()).toString();
            String Bulan    = String.valueOf(ConvertTgl.GetBulanIntDate()).toString();
            String RegexTransNo2 = JnsData+Tahun+Tanggal+Bulan;
            //String RegexTransNo = JnsData+ConvertTgl.GetThnIndoStKazao()+String.valueOf(ConvertTgl.GetTglIndoIntKazao()).toString()+ConvertTgl.GetBlnIndoStrKazao();
            //JOptionPane.showMessageDialog(null,RegexTransNo2+ " RegexTransNo ");
            try {
                Statement stm = koneksi.createStatement();
                //
                //SELECT * FROM  tbheaderpo where date_po = '2013-06-12'  order by trans_no desc limit 0,1
                //select * from tbheaderpo where period = 'asas'  order by period desc LIMIT 0,1
                hasilquery = stm.executeQuery("SELECT "+SelectKolom+" , "+SelectPeriod+" FROM "+ FromTabel
                        +" where "+ SelectPeriod +" = '"+ WhereSyarat +"' order by "
                        + " key_no desc limit 0,1");
                //TN.SetTransNoPo("PO", "trans_no", "date_po", "tbheaderpo", GabTgl);
               // hasilquery = stm.executeQuery("SELECT * FROM  tbheaderpo where date_po = '2013-06-13'  order by trans_no desc limit 0,1");
                String HasilData = "0";
                int Tambah = 0;
                
                /*
                 * Convert tanggal sistem dari wheresyarat = kazaocalender
                 * format PO tahun-tanggal-bulan
                 */
                while(hasilquery.next()){

                    HasilData = hasilquery.getString(SelectKolom);
                    //JOptionPane.showMessageDialog(null,HasilData + " hasil data 1x ");
                    // ambil number dari trans_no filter
                    HasilData = HasilData.replaceAll(RegexTransNo2, "");
                    //JOptionPane.showMessageDialog(null,HasilData + " hasil data 2 ");
                    
                    Tambah = Integer.valueOf(HasilData).intValue();
                    
                }
                Tambah = Tambah + 1;
                
                this.Nourut = Tambah;
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, " TransNo.java Error : 3111 : "+ ex, "Error ", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
      String Gab = JnsData+Tahun+Tanggal+Bulan+ String.valueOf(GetNoUrut()).toString();  
      this.TransNo = Gab;
    }
}
