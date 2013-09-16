/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemPro;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author LANTAI3
 */
public class AmbilHarga {
            /*
            * memanggil database untuk insert price dan insert unit, insert stock
            */
            String Price1;
            String Price2;
            String Unit;
            
            public String GetPrice1 (){
                    return  Price1;
                }
            public void SetPrice1 ( String Data) {
                java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat("#,##0");
                String TotPirceString = String.valueOf(Data).toString();
                double TotPriceDouble = Double.parseDouble(TotPirceString);
                //decimalFormat.format(TotPriceDouble);
                this.Price1 = String.valueOf(decimalFormat.format(TotPriceDouble)).toString();
            }
            
            public String GetUnit (){
                return Unit;
            }
            public void SetUnit (String Unit){
                this.Unit = Unit;
            }
            
            public void AmbilHargaa (String ItemPart){
                
            KoneksiDatabase KoneksiDb = new KoneksiDatabase();
            KoneksiDb.KonekDatabase();
            Connection koneksi = KoneksiDb.koneksi;
            
            ResultSet hasilquery = null;
            try {
                Statement stm = koneksi.createStatement();
                
                    /*
                     * regex untuk item part
                     */
                    String ItemPartSql;
                    String PartRegexSQL = "(\\s)*(--)+(\\s)*(.*)?";
                    ItemPartSql= ItemPart.replaceAll( PartRegexSQL, "");
                hasilquery = stm.executeQuery("SELECT unit,price_1,price_2,do_date_1,do_date_2,item_part FROM tbitem where item_part ='"+ItemPartSql +"'");
            }
            catch (Exception ex){System.err.println ("form_puchaseorder.javaError (3) + error select query "+ex);
            System.exit(1);

            }
            
            String Price1;
            String Price2;
            Date Do_Date1;
            Date Do_Date2;
            String UnitData;
            
            try {
                while(hasilquery.next()){
                   
                   /*
                    * Ambil Unit data
                    */
                   UnitData = hasilquery.getString("unit");
                   SetUnit(UnitData);
                   
                   /*
                    * Ambil data dari database sql
                    */

                   Do_Date1 = hasilquery.getDate("do_date_1");
                   String DataDate = Do_Date1.toString();
                   
                   Do_Date2 = hasilquery.getDate("do_date_2");
                   String DataDate2 = Do_Date2.toString();
                          
                   String DataTahun;
                   String DataTanggal;
                   String DataBulan;
                        
                   /*
                    * Tahun price1
                    */
                   String RegexTahun = "-[0-9][0-9]-[0-9][0-9]";
                   DataTahun = DataDate.replaceAll(RegexTahun, "");
                   int Tahun = Integer.valueOf(DataTahun).intValue();
                   
                   /*
                    * Bulan price1
                    */
                   String RegexBulan1 = "[2-9][0-9][0-9][0-9]-";
                   String RegexBulan2 = "-[0-9][0-9]";
                   
                   String DataBulan1 = DataDate.replaceAll(RegexBulan1, "");
                   DataBulan = DataBulan1.replaceAll(RegexBulan2, "");
                   int Bulan = Integer.valueOf(DataBulan).intValue();
                   
                   /*
                    * Tanggal price1
                    */
                   String RegexTanggal = "[2-9][0-9][0-9][0-9]-[0-9][0-9]-";
                   DataTanggal = DataDate.replaceAll(RegexTanggal, "");
                   int Tanggal = Integer.valueOf(DataTanggal).intValue();
                   
                   
                   //System.out.println( Tahun + " dan " + Bulan + " dan " + Tanggal);
                   
                   /*
                    * Untuk Price2
                    */
                   String DataTahunPrice2;
                   String DataTanggalPrice2;
                   String DataBulanPrice2;
                   
                    /*
                    * Tahun price2
                    */
                   //String RegexTahun = "-[0-9][0-9]-[0-9][0-9]";
                   DataTahunPrice2 = DataDate2.replaceAll(RegexTahun, "");
                   int Tahun2 = Integer.valueOf(DataTahunPrice2).intValue();
                   
                   /*
                    * Bulan price2
                    */
                   //String RegexBulan1 = "[2-9][0-9][0-9][0-9]-";
                   // RegexBulan2 = "-[0-9][0-9]";
                   
                   String DataBulan2 = DataDate2.replaceAll(RegexBulan1, "");
                   DataBulanPrice2 = DataBulan2.replaceAll(RegexBulan2, "");
                   int Bulan2 = Integer.valueOf(DataBulanPrice2).intValue();
                   
                   /*
                    * Tanggal price2
                    */
                   //String RegexTanggal = "[2-9][0-9][0-9][0-9]-[0-9][0-9]-";

                   DataTanggalPrice2 = DataDate2.replaceAll(RegexTanggal, "");
                   int Tanggal2 = Integer.valueOf(DataTanggalPrice2).intValue();

                        /*
                         * Tanggal Bulan dan Waktu Sistem di pagil dari tanggal sis
                         */
                        SistemPro.TanggalSistem Tgl = new SistemPro.TanggalSistem();
                        Tgl.SetTanggalSis();
                        Tgl.SetBulanSis();
                        Tgl.SetTahunSis();
                        
                   try {
                       /*
                        * Compare tanggal sis, tanggal 1 dan tanggal 2
                        */
                       DateFormat DT = new SimpleDateFormat("dd-MM-yyyy");
                       Date DT1 = DT.parse(Tanggal +"-"+Bulan+"-"+Tahun);
                       Date DT2 = DT.parse(Tanggal2 +"-"+Bulan2+"-"+Tahun2);
                       Date DTSis = DT.parse(Tgl.GetTanggalSis() +"-"+Tgl.GetBulanSis()+"-"+Tgl.GetTahunSis());
                        if(DT1.compareTo(DT2)>0){
                            if (DTSis.compareTo(DT1)>=0){
                                Price1 = hasilquery.getString("Price_1");
                                SetPrice1(Price1);
                            }
                            else if (DTSis.compareTo(DT2)>=0){
                                Price2 = hasilquery.getString("Price_2");
                                SetPrice1(Price2);
                            }
                            else if (DTSis.compareTo(DT1)<0){
                                JOptionPane.showMessageDialog(null, "Harga belaku pada tanggal (1) " + DT.format(DT2) );
                                 Price2 = "0";
                                SetPrice1(Price2);
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "Error program: AmbilHarga.java(1)" );
                            }
                        }else if(DT2.compareTo(DT1)>0){
                               if (DTSis.compareTo(DT2)>=0){
                                Price2 = hasilquery.getString("Price_2");
                                SetPrice1(Price2);
                            }
                               else if (DTSis.compareTo(DT1)>=0){
                                Price1 = hasilquery.getString("Price_1");
                                SetPrice1(Price1);
                            }
                               else if (DTSis.compareTo(DT2)<0){
                                JOptionPane.showMessageDialog(null, "Harga belaku pada tanggal (2) " + DT.format(DT1) );
                                Price2 = "0";
                                SetPrice1(Price2);
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "Error program: AmbilHarga.java(2)" );
                            }
                        }else if(DT1.compareTo(DT2)==0){
                                 if (DTSis.compareTo(DT1)==0){
                                Price2 = hasilquery.getString("Price_2");
                                SetPrice1(Price2);
                            }
                                  else {
                                JOptionPane.showMessageDialog(null, "Error program: AmbilHarga.java(3)" );
                            }
                        }
                        else{
                                JOptionPane.showMessageDialog(null,"Harga belum berlaku");
                        }
                       }
                   catch (Exception ex) {
                       
                   }
                }
            }
            catch (Exception ex){System.err.println ("Error (4) : purchase_order.java , query error "+ex);
            System.exit(1);

            }
            }
            
       
            
}
