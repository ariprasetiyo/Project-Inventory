/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package programinvetory;


import ReportFile.ReportPo;
import SistemPro.AmbilHarga;
import SistemPro.KoneksiDatabase;
import SistemPro.NoUrut;
import SistemPro.RenderingKanan;
import SistemPro.RenderingTengah;
import SistemPro.TransNo;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author LANTAI3
 */
public class form_purchaseorder extends javax.swing.JInternalFrame {
    

    /**
     * Creates new form form_purchaseorder
     */
 
    /*
     * Rata tengah atau kiri table
     */
    public TableCellRenderer kanan = new RenderingKanan();
    public TableCellRenderer tengah = new RenderingTengah();
    
    public DefaultTableModel TabelModelOrder;
    Object obj[] = new Object[10];
    /*
     * NoUrut
     */
    NoUrut NoUrutAmbil = new NoUrut();
    boolean Disable;
    boolean EditKolomQty = false;
    
    /*
    public void SetBooleanOpen ( String Data){
        form_purchaseorder Bk, 
        * = new form_purchaseorder();
        Bk.BooleanOpen.setText(Data);
        JOptionPane.showMessageDialog(null, Data);
        
    }
    */
    public void SetViewTransNo( String Data){
        form_purchaseorder vb = new form_purchaseorder();
        vb.JTextTransNoPo.setText(Data);
    }
    public JTextField GetViewTransNo2 (){
        return JTextTransNoPo;
    }
    
    /*
     * memangil file form_purchaseorder
     */  
    private form_purchaseorderObject DataObject;
    public form_purchaseorder() {

        initComponents();
        /*
        form_purchaseorderObject Dt = DapatTransNo.GetTableData();
        JTextTransNoPo.setText(Dt.GetTransNoViewPo());
        /*DapatTransNo.GetButtonSend().addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,  "form_purchase.java : error : 1227 : " , " Error delete", JOptionPane.ERROR_MESSAGE);
                JTextTransNoPo.setText(DapatTransNo.GetViewTransNo());
                
            }
        });
        
        
        //JComboBoxItem.addKeyListener(new app_search1(JComboBoxItem));
       // form_purchaseorderView cx = new form_purchaseorderView();
       // cx.JBottomGetPo.addActionListener(new ActionListener(){
         /*   String[] Data1 = (String[])form_purchaseorderView.ListData.toArray(new String[0]);
            @Override
            public void actionPerformed(ActionEvent e) {
                  System.out.print(Data1.length+ " di coba    ");
        try {
        for (int av = 0; av <= Data1.length ; av++){
                System.out.print(Data1[av] + " xxxxxxx "); 
                     }
        }
        catch (Exception x){
            System.out.print("Error"); 
        }
                
            }
        });
      
        
        /*
         * Action saat pilih Item
         */
        JComboBoxItem.setSelectedIndex(-1);
        final JTextField JTextFieldItem = (JTextField)JComboBoxItem.getEditor().getEditorComponent();
        JTextFieldItem.setText("");
        JTextFieldItem.addKeyListener(new app_search1(JComboBoxItem));
        //JComboBoxItem.addActionListener(new ActionListener (){
         //   @Override
         //     public void actionPerformed (ActionEvent e) {
                //  JComboBox cb = (JComboBox)e.getSource();
                  //String XC = (String) cb.getSelectedItem();
                  //JComboBoxItem.getSelectedItem();
                  //String CVB = (String) JComboBoxItem.getSelectedItem();
                  //JTextAddItem.setVisible(false);
                  //JTextAddItem.setText(CVB);
                  //JTextFieldItem.setText("");
           //   } 
    //    });
        /*
         * open data view Po
         */
        /*
        JButtonOpenPo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                
                form_purchaseorderView View = new form_purchaseorderView(new javax.swing.JFrame(), true);
                View.setResizable(false);
                View.setVisible(true);
                     }
        });
       */
        
        /*
         * edit Po
         */
        JBottomEditPo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed ( ActionEvent e){
                EditKolomQty = true;
                //JOptionPane.showMessageDialog(null, JTextGrandTotPo.getText());
            }
        });
        /*
         * hapus row
         */
        JBottomDeleteItemPo.addActionListener(new ActionListener(){
            @Override
             public void actionPerformed (ActionEvent e) {
                
                int numRows = TabelOrderBarang.getSelectedRows().length;
                int AmbilRow = TabelOrderBarang.getSelectedRow() ;
                
                for(int i=0; i<numRows ; i++ ) {
                    /*
                     * Perhitungan pengurangan harga jika di hapus
                     */
                    SistemPro.KomaToString KtoS = new SistemPro.KomaToString();
                    KtoS.SetHapusKoma((String) TabelModelOrder.getValueAt(AmbilRow, 6));
                    int PenguranganTot = KtoS.GetInt();
                    
                    
                    KtoS.SetHapusKoma(JTextGrandTotPo.getText());             
                    int KurangTot = KtoS.GetInt();
                    KurangTot = KurangTot - PenguranganTot;
                    JTextGrandTotPo.setText(String.valueOf(KurangTot).toString());
                    
                    TabelModelOrder.removeRow(TabelOrderBarang.getSelectedRow());
                }
             }
        });
        /*
         * Delete PO
         */
        
        JBottomDeletePo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                 String Hapus = JTextTransNoPo.getText();
                 int Pilih = JOptionPane.showConfirmDialog(null, "Apakah anda yakin untuk menghapus po ini : " + Hapus + " ?", " Delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                 if (Pilih == JOptionPane.YES_OPTION){
                        SistemPro.KoneksiDatabase KD = new SistemPro.KoneksiDatabase();
                        KD.KonekDatabase();
                        Connection K = KD.koneksi;
                        try {
                            Statement S = K.createStatement();
                            S.executeUpdate("DELETE FROM `tbheaderpo` WHERE `trans_no` = '"+ Hapus +"'");
                        }
                        catch (Exception X){
                            JOptionPane.showMessageDialog(null,  "form_purchase.java : error : 1227 : "  +X, " Error delete", JOptionPane.ERROR_MESSAGE);
                            X.printStackTrace();
                        } 
                 }
                 else if ( Pilih == JOptionPane.NO_OPTION){
                     
                 }
                 
            }
        });
        /*
         * Save Po
         */
        
        JBottomSavePo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed (ActionEvent e) {
                /*
                 * Ambil tanggal dari kzao kalender
                 */
                 String date_po = TanggalPo.getKazaoCalendar().getShortDate();
                 SistemPro.TanggalSistem N = new SistemPro.TanggalSistem();
                 
                 N.SetKazaoToTglIndo(date_po);
                 N.SetKazaoToBlnIndo(date_po);
                 N.SetKazaoToThnIndo(date_po);
                 
                 String DatePo = N.GetThnIndoStKazao()+"-"+N.GetBlnIndoStrKazao()+"-"+N.GetTglIndoStrKazao();
                 String per    = N.GetThnIndoStKazao()+N.GetBlnIndoStrKazao();
                 int period = Integer.valueOf(per).intValue();
                 
                 TransNo TN = new TransNo();
                 SistemPro.TanggalSistem Tgl = new SistemPro.TanggalSistem();
                 
                 /*
                  * Panggil set tanggal dari Sistempro/tanggalsistem
                  */
                 Tgl.SetTanggalSis(); 
                 Tgl.SetBulanSis();
                 Tgl.SetTahunSis();
        
                 /*
                  * code membuat trans_no
                  */
                 TN.SetTransNoPo("PO", "trans_no", "date_po", "tbheaderpo", DatePo);              
                 String NoTrans = TN.GetTransNoPo();
                 //JOptionPane.showMessageDialog(null,NoTrans);
                 JTextTransNoPo.setText(NoTrans);
                 Disable = false;
                 
                 /*
                  * Membuat Trans Date
                  */
                 String TransDate1 = N.GetThnIndoStKazao()+ N.GetBlnIndoStrKazao()+N.GetTglIndoStrKazao();
                 int TransDate = Integer.valueOf(TransDate1).intValue();
                 JOptionPane.showMessageDialog(null, TransDate);
                 
                 /*
                  * Ambil data yang akan di save
                  */
                 String trans_no,goods_status, departement,supplier,supplier_address, delivery_to,delivery_address,sub_total,ppn,diskon,grand_total,amount,user,created,update;
                 
                 trans_no = NoTrans;
                 goods_status = "1";
                 departement = "IT";
                 
 
                 supplier = JTextSupplierPo.getText();
                 supplier_address = JTextAddressPo.getText()+" "+ JTextAdressPo2.getText();
                 delivery_to = JTextDeliveryToPo.getText()+" "+JTextDeliveryToPo2.getText();
                 sub_total = JTextSubTotalPo.getText();
                 ppn = JTextPpnPo.getText();
                 diskon = JTextDiscPo.getText();
                 grand_total = JTextGrandTotPo.getText();
                 //JOptionPane.showMessageDialog(null, grand_total);
                 amount = (String) JComboAmountPo.getSelectedItem();
                 user = "x";
                 created = "x";
                 update = "x";

                 /*
                  * Key No tbheaderpo
                  */
                 SistemPro.KeyNo KN = new SistemPro.KeyNo();
                 KN.SetKeyNo("tbheaderpo");
                 int KeyNo = KN.GetKeyNoInt();
                 //JOptionPane.showMessageDialog(null,  KeyNo);
                 
                 /*
                  * Save headerpo ke database MYSQL
                  *
                  * */
                 SistemPro.KoneksiDatabase KD = new SistemPro.KoneksiDatabase();
                 KD.KonekDatabase();
                 Connection K = KD.koneksi;
                 JOptionPane.showMessageDialog(null,  JTextGrandTotPo.getText() + " dan " + supplier + " dan " + supplier_address);
                 //ResultSet HQ =null;
                 try {
                    Statement Stm = K.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        Stm.executeUpdate("INSERT INTO tbheaderpo (key_no,period,trans_no,goods_status,depaterment,date_po,supplier,supplier_address," +
   "                     delivery_address,sub_total,ppn, diskon,grand_total,amount,user,created,updated, trans_date) VALUES('"
                                + KeyNo + "','"
                                + period + "','" + trans_no + "','"+ goods_status +"','"+ departement 
                                + "','"+ DatePo + "','"+ supplier +"','"+ supplier_address
                                + "','"+ delivery_to +"','"+ sub_total 
                                + "','"+ ppn +"','"+ diskon+"','"+ grand_total
                                + "','"+ amount + "','"+ user +"','"+ created + "','"+ update + "','" + TransDate
                                + "')");     
                        
                  /*
                   * Save DetailPo to database Mysql
                   */
                 try {
                     int a = TabelOrderBarang.getRowCount() ;
                     Statement stm = K.createStatement();

                     // dbStatement=con.createStatement();

                     for(int i=0;i< a;i++){
                         /*
                         * Key No tbdetailtrans
                         */
                        KN.SetKeyNo("tbdetailtrans");
                        int KeyNoDT = KN.GetKeyNoInt();

                         int no           =Integer.valueOf(TabelOrderBarang.getValueAt(i, 0).toString()).intValue();
                         String transno   =TabelOrderBarang.getValueAt(i, 1).toString();
                         String itempart  =TabelOrderBarang.getValueAt(i, 2).toString();
                         String itemname  =TabelOrderBarang.getValueAt(i, 3).toString();
                         String qty       =TabelOrderBarang.getValueAt(i, 4).toString();
                         String price     =TabelOrderBarang.getValueAt(i, 5).toString();
                         String totprice  =TabelOrderBarang.getValueAt(i, 6).toString();
                         String unit      =TabelOrderBarang.getValueAt(i, 7).toString();
                         String bth       =TabelOrderBarang.getValueAt(i, 8).toString();
                         String ket       =TabelOrderBarang.getValueAt(i, 9).toString();
                         //String TransDate ="xxx";
                         String userDT    ="xxxx";
                         String Updated   ="xxxx";
                         /*
                         JOptionPane.showMessageDialog(null, period+"','"+KeyNoDT+"','"+no+"','"+trans_no+"','"+itempart+"','"+itemname+"','"+qty+"','"
                                 +"','"+price+"','"+totprice+"','"+unit+"','"+bth+"','"+ket+"','"+TransDate+"','"+userDT+"','"+Updated);
                          */
                         stm.executeUpdate("INSERT INTO tbdetailtrans (period, key_no, no, trans_no, item_part, item_name, qty, price,price_tot,"+
                                 "unit, tanggal_bth, ket, trans_date, user, updated) VALUES ('"
                                 +period+"','"+KeyNoDT+"','"+no+"','"+trans_no+"','"+itempart+"','"+itemname+"','"+qty+"','"
                                 +price+"','"+totprice+"','"+unit+"','"+bth+"','"+ket+"','"+TransDate+"','"+userDT+"','"+Updated+"')");
                         }
                 }
                 catch (Exception X){
                      JOptionPane.showMessageDialog(null,  "form_purchase.java : error : 1226 : "  +X, " Error", JOptionPane.ERROR_MESSAGE);
                    }           
                 }
                 catch (Exception Ex){
                JOptionPane.showMessageDialog(null,  "form_purchase.java : error : 1224 : "  +Ex, " Error", JOptionPane.ERROR_MESSAGE);
            }  
                  AturanMenu(Disable);
 
    }});
        
    JButtomAddPo.addActionListener(new ActionListener(){
         public void actionPerformed (ActionEvent e) {

                Disable = true;
                AturanMenu(Disable);

                JTextSupplierPo.setText("");
                JTextAddressPo.setText("");
                JTextBagian.setText("");
                JTextDeliveryToPo.setText("");
                JTextDeliveryToPo2.setText("");
                JTextKeterangan.setText("");
                JTextQtyPO.setText("");
                JTextSupplierPo.setText("");
                JTextDiscPo.setText("0");
                JTextPpnPo.setText("0");
                JTextSubTotalPo.setText("0");
                JComboBoxItem.setSelectedItem("");
                JTextGrandTotPo.setText("0");
                JTextAdressPo2.setText("");
                JTextSupplierPo.setText("");

                /*
                 * Logika hapus semua data di jtable
                 */
                //TabelModelOrder.getDataVector().removeAllElements();
                //TabelModelOrder.fireTableDataChanged();

                DefaultTableModel dtm = (DefaultTableModel) TabelOrderBarang.getModel();
                dtm.setRowCount(0);         
              }
            
        });
        JBottomPrintPo.addActionListener(new ActionListener(){
             public void actionPerformed (ActionEvent e) {
                       // JOptionPane.showMessageDialog (null,"xxx"  , "Error", JOptionPane.ERROR_MESSAGE);
                      PangilDataReportPo();
                  
              }
        });
        /*
         * Button add di klik untuk add item di table
         */
        JBottomQtyPo.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e) {
                  String s = e.getActionCommand();
                  if (s.equals("Add")) {
                      AddItemDiTable ();
                  }
              }
        });
        /*
         * Button add untuk add PO baru 
         */
        
        /*
         * Action jika Qty di Enter
         */
        JTextQtyPO.addKeyListener(new KeyListener (){
            public void keyPressed(KeyEvent e) {
                    String s = KeyEvent.getKeyText(e.getKeyCode());
                    if (s.equals("Enter")){
                        AddItemDiTable ();
                    }
            }
            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        
        /*
         * Untuk Tabel
         */
        String header[] = {"No", "Trans No","Item Part","Item Name", "Qty", "Price", "Tot Price", "Unit", "Tang Bth", "Ket"};
        TabelModelOrder = new DefaultTableModel(null,header) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                                if(colIndex == 4) {return EditKolomQty ;} //  nilai false agar tidak bisa di edit
                                if(colIndex == 8) {return EditKolomQty ;}
                                if(colIndex == 9) {return EditKolomQty ;}
                                return false;   //Disallow the editing of any cell
                        }
        };
       
        TabelOrderBarang.setModel(TabelModelOrder);
       
        /*
         * Rata tengah atau kiri table
         */
        TabelOrderBarang.getColumnModel().getColumn(5).setCellRenderer( kanan );
        TabelOrderBarang.getColumnModel().getColumn(6).setCellRenderer( kanan );
        
        TabelOrderBarang.getColumnModel().getColumn(4).setCellRenderer( tengah );
        TabelOrderBarang.getColumnModel().getColumn(7).setCellRenderer( tengah );
        
        /*
         * Ukura table TabelOrderBarang
         */
        
        int jarak_colom[] = {40,160,200,300,40,100,100,100,100,300};
        colom_table ukuran_colom = new colom_table();
        ukuran_colom.ukuran_colom(TabelOrderBarang, jarak_colom);
        
        /*
         * Full frame layar
         */
        //Dimension Dim = Toolkit.getDefaultToolkit().getScreenSize();
        //this.setSize(Dim.width, Dim.height);

    }

    public void AturanOpenPo (boolean Disable){
//       ("Not Resizable", false, true, true, true);
       setClosable(Disable);
       //setEnabled(Disable);
       AturanMenu(false);
    }
    public void AturanMenu (boolean Disable){
            JTextTransNoPo.setText("");
            JTextSupplierPo.setText("");
            JTextAddressPo.setText("");
            JTextBagian.setText("");
            JTextDeliveryToPo.setText("");
            JTextDeliveryToPo2.setText("");
            JTextKeterangan.setText("");
            JTextQtyPO.setText("");
            JTextSupplierPo.setText("");
            JTextDiscPo.setText("0");
            JTextPpnPo.setText("0");
            JTextSubTotalPo.setText("0");
            JComboBoxItem.setSelectedItem("");
            JTextGrandTotPo.setText("0");
            JTextAdressPo2.setText("");
            JTextSupplierPo.setText("");
           
            //JTextSupplierPo.setEditable(Disable);
            JTextSupplierPo.setEnabled(Disable);

            //JTextAddressPo.setEditable(Disable);
            JTextAddressPo.setEnabled(Disable);

            //JTextBranchPo.setEditable(Disable);
            //JTextDeliveryToPo.setEditable(Disable);
            //JTextDeliveryToPo2.setEditable(Disable);
           // JTextKeterangan.setEditable(Disable);
            //JTextQtyPO.setEditable(Disable);
            //JTextSupplierPo.setEditable(Disable);
            //JComboBoxItem.setEditable(Disable);
            //JTextAdressPo2.setEditable(Disable);
            //TanggalPo.setEditable(true);
            //TanggalBthPO.setEditable(true);
            //JTextSupplierPo.setEditable(Disable);

            JTextBagian.setEnabled(Disable);
            JTextDeliveryToPo.setEnabled(Disable);
            JTextDeliveryToPo2.setEnabled(Disable);
            JTextKeterangan.setEnabled(Disable);
            JTextQtyPO.setEnabled(Disable);
            JTextSupplierPo.setEnabled(Disable);
            JTextDiscPo.setEnabled(Disable);
            JTextPpnPo.setEnabled(Disable);
            JTextSubTotalPo.setEnabled(Disable);
            JComboBoxItem.setEnabled(Disable);
            JTextGrandTotPo.setEnabled(Disable);
            JTextAdressPo2.setEnabled(Disable);

            TanggalPo.setEnabled(Disable);
            TanggalBthPO.setEnabled(Disable);


            JTextSupplierPo.setEnabled(Disable);
            JBottomEditPo.setEnabled(Disable);
            JBottomDeletePo.setEnabled(Disable);
            JBottomSavePo.setEnabled(Disable);
            JBottomPrintPo.setEnabled(Disable);
            JBottomQtyPo.setEnabled(Disable);
            JBottomDeleteItemPo.setEnabled(Disable);
            EditKolomQty = false;
    }


    /*
     * Logik untuk mengambil data dari table kemudian di kirim ke file atau klas lain
     */
    public static List<String> ListDataKe0 = new ArrayList<>();
    public static List<String> ListDataKe1 = new ArrayList<>();
    public static List<String> ListDataKe2 = new ArrayList<>();
    public static List<String> ListDataKe3 = new ArrayList<>();
    public static List<String> ListDataKe4 = new ArrayList<>();
    public static List<String> ListDataKe5 = new ArrayList<>();
    public static List<String> ListDataKe6 = new ArrayList<>();
    public static List<String> ListDataKe7 = new ArrayList<>();
    public static List<String> ListDataKe8 = new ArrayList<>();
    public static List<String> ListDataKe9 = new ArrayList<>();
    public static List<String> ListDataKe10 = new ArrayList<>();
    
    int Row;
    int Colum;
    public int RowGlobal (){
        Row = TabelOrderBarang.getRowCount() ;
        return Row;
    }
    public int ColumGlobal(){
        Colum = TabelOrderBarang.getColumnCount();
        return Colum;
    }
    
    public void SetDataColumKe0(List Data){
    form_purchaseorder.ListDataKe0 = Data;
    }
    public  List<String> GetDataListPoKeDataSource_ColumKe0 (List Data1){
        ListDataKe0 = Data1;
        return ListDataKe0;
    }
    
    public void SetDataColumKe1(List Data){
    form_purchaseorder.ListDataKe1 = Data;
    }
    public  List<String> GetDataListPoKeDataSource_ColumKe1 (){
        //ListDataKe1 = Data1;
        //String[] sl = (String[]) list.toArray(new String[0]);
        //System.out.println("Array of String has length " + sl.length);
        return ListDataKe1;
    }
    
    public void SetDataColumKe2(List Data) {
    form_purchaseorder.ListDataKe2 = Data;
    }
    public  List<String> GetDataListPoKeDataSource_ColumKe2 (List Data1){
        ListDataKe2 = Data1;
        return ListDataKe2;
    }
    
    public void SetDataColumKe3(List Data){
    form_purchaseorder.ListDataKe3 = Data;
    }
    public  List<String> GetDataListPoKeDataSource_ColumKe3 (List Data1){
        ListDataKe3 = Data1;
        return ListDataKe3;
    }
    
    public void SetDataColumKe4(List Data){
    form_purchaseorder.ListDataKe4 = Data;
    }
    public  List<String> GetDataListPoKeDataSource_ColumKe4 (List Data1){
        ListDataKe4 = Data1;
        return ListDataKe4;
    }
    
    public void SetDataColumKe5(List Data){
    form_purchaseorder.ListDataKe5 = Data;
    }
   public  List<String> GetDataListPoKeDataSource_ColumKe5 (List Data1){
        ListDataKe5 = Data1;
        return ListDataKe5;
    }
   
   public void SetDataColumKe6(List Data){
    form_purchaseorder.ListDataKe6 = Data;
    }
   public  List<String> GetDataListPoKeDataSource_ColumKe6 (List Data1){
        ListDataKe6 = Data1;
        return ListDataKe6;
    }
   
   public void SetDataColumKe7 (List Data){
    form_purchaseorder.ListDataKe7 = Data;
    }
   public  List<String> GetDataListPoKeDataSource_ColumKe7 (List Data1){
        ListDataKe7 = Data1;
        return ListDataKe7;
    }
   
   public void SetDataColumKe8 (List Data){
    form_purchaseorder.ListDataKe8 = Data;
    }
   public  List<String> GetDataListPoKeDataSource_ColumKe8 (List Data1){
        ListDataKe8 = Data1;
        return ListDataKe8;
    }
   public void SetDataColumKe9 (List Data){
    form_purchaseorder.ListDataKe9 = Data;
    }
   public  List<String> GetDataListPoKeDataSource_ColumKe9 (List Data1){
        ListDataKe9 = Data1;
        return ListDataKe9;
    }
   
   public void SetDataColumKe10 (List Data){
    form_purchaseorder.ListDataKe9 = Data;
    }
   public  List<String> GetDataListPoKeDataSource_ColumKe10 (List Data1){
        ListDataKe10 = Data1;
        return ListDataKe10;
    }
   
   /*
    * Logika di atas akan meng Setdatacolum dalam file form_puchaseorder
    * Kemudian nimbun data pada List<string> getDataList yang akan di kirim ke ReportPO.java
    * yang dikirim ke reportPo adalah ListData dari 1 - 7
    * Logika end
    * end
    */
    
    /*
     * passing data dengan pewaris etendexts 
     * yag di lempar ke report po
     */
    
    protected static String RTglPo;
    protected void SetReportTgl (String Data){
        form_purchaseorder.RTglPo = Data;
    }
    protected static String RGrandTot;
    protected void SetReportGrandTot (String Data){
        form_purchaseorder.RGrandTot = Data;
    }
    protected static String RBagian;
    protected void SetReportBagian (String Data){
        form_purchaseorder.RBagian = Data;
    }
    protected static String RPIC;
    protected void SetReportPIC (String Data){
        form_purchaseorder.RPIC = Data;
    }
    protected static String RDepartements;
    protected void SetReportDepar (String Data){
        form_purchaseorder.RDepartements = Data;
    }
    protected static String RTransNoPo;
    protected void SetReportTransNoPo (String Data){
        form_purchaseorder.RTransNoPo = Data;
    }
    
    public void PangilDataReportPo () {
        //SetReportTgl(10);
        /*
         * Logika program untuk mengirim data singel ke reportpo.java
         */
        String ReportTgl    = TanggalPo.getKazaoCalendar().getShortDate();
        String ReportGrand  = JTextGrandTotPo.getText();
        String ReportBagian = JTextBagian.getText();
        String ReportPIC    = "Ari Prasetiyo";
        String ReportDepar  = "IT";
        String ReportTrans  = JTextTransNoPo.getText();
        
        SetReportTgl(ReportTgl);
        SetReportGrandTot(ReportGrand);
        SetReportBagian(ReportBagian);
        SetReportPIC(ReportPIC);
        SetReportDepar(ReportDepar);
        SetReportTransNoPo(ReportTrans);
        /*
         * end
         */
        
        /*
         * Logika program untuk mengirim data array tabel ke reportpo.java
         */
        int a = TabelOrderBarang.getRowCount() ;
        int b = TabelOrderBarang.getColumnCount();
        String[][] DataTabelArrayDimensional;
        DataTabelArrayDimensional = new String[a][b];
        
        JasperDesign jasperDesign = null;
        // JasperDesign jasperDesign;
        JasperPrint jasperPrint = null ;
        JasperReport jasperReport;
        HashMap hashMap = new HashMap();

        String coba = TabelOrderBarang.getValueAt(0, 3) + "data dari Report Po";

        
        //hashMap.put("PMT_TRANSNO", "2010");
        //for (int aa = 0; aa <= a; aa++){
               // for ( int bb= 0; bb <= b; bb++) {      

                //String ListItemBarang2 = app_search_data.getData().toString();
                 
                 /*
                  * Data tabel di ambil dari int row ke aa colom ke dua
                  * Data di insert di clas datasourcePo agar bisa tampil di report
                  */
        
                  //DataTabelArrayDimensional[aa][1]= (String) TabelOrderBarang.getValueAt(aa, 1);
 
        /*
         * Logika untuk mengambil data dari tabel
         */
                  List<String> DataYangAkanDIlist0 = new ArrayList<String>();
                  //List<String> DataYangAkanDIlist1 = new ArrayList<String>();
                  //List<String> DataYangAkanDIlist2 = new ArrayList<String>();
                  List<String> DataYangAkanDIlist3 = new ArrayList<String>();
                  List<String> DataYangAkanDIlist4 = new ArrayList<String>();
                  List<String> DataYangAkanDIlist5 = new ArrayList<String>();
                  List<String> DataYangAkanDIlist6 = new ArrayList<String>();
                  List<String> DataYangAkanDIlist7 = new ArrayList<String>();
                  List<String> DataYangAkanDIlist8 = new ArrayList<String>();
                  List<String> DataYangAkanDIlist9 = new ArrayList<String>();
                  
                  for (int aa =0; aa < a /* a */;aa++){

                 //for ( int bb= 0; bb < b; bb++) {
                 // DataTabelArrayDimensional[aa][bb]= (String) TabelOrderBarang.getValueAt(aa, bb);
                      
                  /*
                   * Jumlah berdasarkan colum tabel PO
                   */
                  DataYangAkanDIlist0.add((String) TabelOrderBarang.getValueAt(aa, 0));
                  //DataYangAkanDIlist1.add((String) TabelOrderBarang.getValueAt(aa, 1));
                  //DataYangAkanDIlist2.add((String) TabelOrderBarang.getValueAt(aa, 2));
                  DataYangAkanDIlist3.add((String) TabelOrderBarang.getValueAt(aa, 3));
                  DataYangAkanDIlist4.add((String) TabelOrderBarang.getValueAt(aa, 4));
                  DataYangAkanDIlist5.add((String) TabelOrderBarang.getValueAt(aa, 5));
                  DataYangAkanDIlist6.add((String) TabelOrderBarang.getValueAt(aa, 6));
                  DataYangAkanDIlist7.add((String) TabelOrderBarang.getValueAt(aa, 7));
                  DataYangAkanDIlist8.add((String) TabelOrderBarang.getValueAt(aa, 8));
                  DataYangAkanDIlist9.add((String) TabelOrderBarang.getValueAt(aa, 9));
                  
                  }
                   SetDataColumKe0( DataYangAkanDIlist0); // ambil no
                   //SetDataColumKe1( DataYangAkanDIlist1); // ambil transno
                   //SetDataColumKe2( DataYangAkanDIlist2); // item part
                   SetDataColumKe3( DataYangAkanDIlist3); // item name
                   SetDataColumKe4( DataYangAkanDIlist4); // qty
                   SetDataColumKe5( DataYangAkanDIlist5); //price
                   SetDataColumKe6( DataYangAkanDIlist6); // tot price
                   SetDataColumKe7( DataYangAkanDIlist7); // unit
                   SetDataColumKe8( DataYangAkanDIlist8); // Tanggal Butuh
                   SetDataColumKe9( DataYangAkanDIlist9); // Keterangan
                   
                  // ReportFile.ReportPo cvb = new ReportFile.ReportPo();
                   //cvb.setVisible(true);
                   
                   //JOptionPane.showMessageDialog(null, DataYangAkanDIlist1);
                   //GetDataListPoKeDataSource_ColumKe7( DataYangAkanDIlist7);

                   /*
                    * End ******************( )*************************
                    */
                   
                  //for (int aa = 0; aa < a; aa++) {
                   
                    try {
                            //get report file and then load into jasperDesign
                            String x = System.getProperties().getProperty("java.class.path").split(";")[System.getProperties().getProperty("java.class.path").split(";").length - 1] + "\\Report\\ReportPoIT.jrxml";
                            jasperDesign = JRXmlLoader.load(x);
                            //compile the jasperDesign
                            jasperReport = JasperCompileManager.compileReport(jasperDesign);
                            //fill the ready report with data and parameter
                            
                            //for (int aa = 0; aa <= 0 /* a *///; aa++) {
                            jasperPrint = JasperFillManager.fillReport(jasperReport, hashMap, new JRBeanCollectionDataSource(
                                    //String TglPo, String GrandTot, String PIC, String Depar, String Bagian
                            
                            ReportPo.DataSourceTabelPO()));
                         //   }
                    
                            //view the report using JasperViewer
                           JasperViewer.viewReport(jasperPrint, false);
                           
                        } catch (JRException e) {
                          e.printStackTrace();
                       }
                  
                //  }
                //System.out.print( DataTabelArrayDimensional[aa][bb]= (String) TabelOrderBarang.getValueAt(aa, bb) + " xx ");
                
               
                //}
                //System.out.println("");
            //}
       
    }
    
    /*
     * Filter dan Logika untuk item name dan item code yang akan di add di JTabel
     */
    String HasilFilterItem;
    void SetFilterItem(String Data ){
        
        String RegrexA  = "[\\[\\]]";
        String A        = Data.replaceAll(RegrexA, ",");
        String RegrexB  = "[a-zA-Z0-9]+(-)+[a-zA-Z0-9]+(-)+[a-zA-Z0-9]+(-)+[a-zA-Z0-9]+";
        String B        = A.replaceAll(RegrexB, "");
        String RegexC   = "(\\s)*(--)+(\\s)*";
        String C  = B.replaceAll(RegexC, "");
        this.HasilFilterItem = C;
    }
    String GetFilterItem (){
        return HasilFilterItem;
    }
    public void AddItemDiTable (){
        String ItemName = (String) JComboBoxItem.getSelectedItem();
        //System.out.println(ItemName);
        String ItemQty = JTextQtyPO.getText();
        String ItemPart =  ItemName;
        String ItemKet = JTextKeterangan.getText();
        String ItemBth = TanggalBthPO.getKazaoCalendar().getShortDate();
        
        /*
         * Set data no urut
         */
        int ax = TabelOrderBarang.getRowCount() ;
        //JOptionPane.showMessageDialog(null, ax);
        if (ax == 0 ){
            NoUrutAmbil.SetNoUrut(String.valueOf(ax).toString());
        }
        else if ( ax > 0) {
            ax = ax - 1;
            String ab = (String) TabelOrderBarang.getValueAt(ax, 0);
            NoUrutAmbil.SetNoUrut(ab);
        }

        /*
         * List item untuk mengecek item ada di system atau tidak\
         * Data dari all database
         */
        String ListItemBarang = app_search_data.getData().toString(); 
        SetFilterItem(ListItemBarang);
        String CekItemNameDatabase  = GetFilterItem();
        
        /*
         * untuk filter item yang akan di add di tabel
         */
        SetFilterItem(ItemName);
        String CekItemTambah = "(.*)?,(" + GetFilterItem() +")+,(.*)?";  
        String ItemNameRegex = "(.*)?("+ItemName+")+(.*)?";

            /*
             * Membuat dan mengambil data dari Jtabel dengan looping dimensional
             * yang di pecah menjadi data tiap array string
             */
            
            int a = TabelOrderBarang.getRowCount() -1 ;
            int b = TabelOrderBarang.getColumnCount()-1;
            String[][] DataTabelArrayDimensional;
            DataTabelArrayDimensional = new String[a+1][b+1];
          
            boolean BenarTidakAdaITem2 = true;

            /*
             * Looping untuk mencari apakah ada item atau tidak di tabel dari data yang di input
             */
            for (int aa = 0; aa <= a; aa++){
                
                /*
                 * Set No Urut selanjutnya
                 */
                for ( int bb= 0; bb <= b; bb++) {
                    
                /*
                 * Ambil semua
                 * DataTabelArrayDimensional[aa][bb]= (String) TabelOrderBarang.getValueAt(aa, bb);
                 */
                 String PartRegex = "(\\s)*(--)+(\\s)*(.*)?";
                 ItemPart   = ItemPart.replaceAll( PartRegex, "");
            
                //String ListItemBarang2 = app_search_data.getData().toString();
                 
                 /*
                  * Data tabel di ambil dari int row ke aa colom ke dua
                  */
                DataTabelArrayDimensional[aa][2]= (String) TabelOrderBarang.getValueAt(aa, 2);          
                //System.out.print( DataTabelArrayDimensional[aa][bb]= (String) TabelOrderBarang.getValueAt(aa, bb) + " xx ");
                if (ItemPart.matches( DataTabelArrayDimensional[aa][2]) ){
                     BenarTidakAdaITem2 = false;
                    }
                }
            }
            
            //JOptionPane.showMessageDialog (null,BenarTidakAdaItem  , "Error", JOptionPane.ERROR_MESSAGE);
            
        /*
         * Menambahkan item ke Tabel dinamis sekaligus mengecek
         */
        if (CekItemNameDatabase.matches(CekItemTambah )){
            if ( ListItemBarang.matches(ItemNameRegex) ){
                if (BenarTidakAdaITem2 == true) {

               /*
                * memanggil database untuk insert price dan insert unit, insert stock
                */ 
                JTextQtyPO.setText("");
                JComboBoxItem.setSelectedItem("");
                JComboBoxItem.requestFocusInWindow();

                /*
                 * regex untuk Item Name
                 */
                String NameRegex = "(.*)?(\\s)*(--)+(\\s)*";

                ItemName= ItemName.replaceAll( NameRegex, "");
                int JumlahKarakter = ItemName.length();
                    if (JumlahKarakter > 7) {

                        /*
                         * memanggil file java ambilharga.java di folder Sistemprog
                         */
                        AmbilHarga Ambil = new AmbilHarga();
                        Ambil.AmbilHargaa(ItemPart);

                        /*
                         * regex untuk item part
                         */
                        String PartRegex = "(\\s)*(--)+(\\s)*(.*)?";
                        ItemPart= ItemPart.replaceAll( PartRegex, "");

                        /*
                         * Menambah di JTabel 
                         * Menghitung Total  dan merubah format
                         */
                        int TotPrice = 0;
                        String TotPriceDouble;

                        java.text.DecimalFormat decimalFormat2 = new java.text.DecimalFormat("#,##0");

                        //Merubah dari ada koma menjadi tidak koma dalam bentuk string untuk menghitung TotPrice dan memperoleh TotPriceDouble
                        String RegexGetPrice = ",";

                        SistemPro.KomaToString KSI = new SistemPro.KomaToString();
                        KSI.SetHapusKoma(Ambil.GetPrice1());
                        String RubahPrice = KSI.GetString();

                        TotPrice = Integer.valueOf(ItemQty).intValue() * Integer.valueOf(RubahPrice).intValue();
                        TotPriceDouble = String.valueOf(decimalFormat2.format(TotPrice)).toString();

                        //Merubah dari ada koma menjadi tidak koma dalam bentuk string untuk menghitung TotGrand dan memperoleh TotGrand2
                        String TotGrand = JTextGrandTotPo.getText();
                        TotGrand = TotGrand.replace(",", "");
                        int TotGrand2 = Integer.valueOf(TotGrand).intValue() + TotPrice ;
                        TotGrand =  String.valueOf(decimalFormat2.format(TotGrand2)).toString();
                        JTextGrandTotPo.setText(TotGrand);

                        /*
                         * End
                         */

                        obj[0] = NoUrutAmbil.GetNoUrut();
                        obj[1] = "PO12345";
                        obj[2] = ItemPart;
                        obj[3] = ItemName;
                        obj[4] = ItemQty;
                        obj[5] = Ambil.GetPrice1();
                        obj[6] = TotPriceDouble;
                        obj[7] = Ambil.GetUnit();
                        obj[8] = ItemBth.toString();
                        obj[9] = ItemKet;


                        /*
                         * Menambahkan dari object obj ke dalam tabel berdasarkan obj array
                         */
                        TabelModelOrder.addRow(obj);

                        JTextKeterangan.setText("");
                        //setLebarKolom();
                    }
                    else {
                        JOptionPane.showMessageDialog (null, "Masukan data dengan benar", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showMessageDialog (null, "Barang sudah ada di tabel", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else {
                JOptionPane.showMessageDialog (null, "Barang tidak ada di database 1", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog (null, "Barang tidak ada di database 2", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /*
     * JReport generate report JTabel
     */
    private void generateReports(String name, Map param)
{
        try
        {
                String source = "C:/sabonay/jasperreports/" + name + ".jrxml";
                if (new File(source).exists() == false)
                {
                 //       xputils.showMessage("Please go to setting and Choose report Source");
                        return;
                }
/*
 *  Sementara disable
 */
                /*
                JasperReport jasperReport = JasperCompileManager.compileReport(source);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, new JRTableModelDataSource(TabelOrderBarang.getModel()));

                JasperViewer.viewReport(jasperPrint, false);
                */

        }
        catch (Exception e)
        {
                e.printStackTrace();
                System.out.println("reports Error  " + e.toString());

        }
}
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jDialog1 = new javax.swing.JDialog();
        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jFrame3 = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelOrderBarang = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        JComboAmountPo = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        JTextDiscPo = new javax.swing.JTextField();
        JTextSubTotalPo = new javax.swing.JTextField();
        JTextGrandTotPo = new javax.swing.JTextField();
        JTextPpnPo = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        JTextTransNoPo = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        JTextBagian = new javax.swing.JTextField();
        JTextSupplierPo = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        JTextAddressPo = new javax.swing.JTextField();
        JTextAdressPo2 = new javax.swing.JTextField();
        JTextDeliveryToPo = new javax.swing.JTextField();
        JTextDeliveryToPo2 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox();
        TanggalPo = new org.kazao.calendar.KazaoCalendarDate();
        jPanel10 = new javax.swing.JPanel();
        JTextKeterangan = new javax.swing.JTextField();
        JComboBoxItem = new javax.swing.JComboBox();
        JTextQtyPO = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        JBottomQtyPo = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        TanggalBthPO = new org.kazao.calendar.KazaoCalendarDate();
        JBottomDeleteItemPo = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        JBottomDeletePo = new javax.swing.JButton();
        JBottomEditPo = new javax.swing.JButton();
        JBottomPrintPo = new javax.swing.JButton();
        JButtonOpenPo = new javax.swing.JButton();
        JBottomSavePo = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        JButtomAddPo = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame3Layout = new javax.swing.GroupLayout(jFrame3.getContentPane());
        jFrame3.getContentPane().setLayout(jFrame3Layout);
        jFrame3Layout.setHorizontalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame3Layout.setVerticalGroup(
            jFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setIconifiable(true);
        setResizable(true);
        setTitle("Order Barang");
        setFocusable(false);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/iconIT.png"))); // NOI18N
        setRequestFocusEnabled(false);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${maximizable}"), this, org.jdesktop.beansbinding.BeanProperty.create("maximizable"));
        bindingGroup.addBinding(binding);

        TabelOrderBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TabelOrderBarang.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(TabelOrderBarang);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentHidden(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Amount");

        JComboAmountPo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JComboAmountPo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        JComboAmountPo.setEnabled(false);

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Sub Total");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Disc");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("PPN");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Grand Total");

        jTextField4.setEditable(false);
        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextField5.setEditable(false);
        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        JTextDiscPo.setEditable(false);
        JTextDiscPo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JTextDiscPo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        JTextSubTotalPo.setEditable(false);
        JTextSubTotalPo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JTextSubTotalPo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        JTextGrandTotPo.setEditable(false);
        JTextGrandTotPo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JTextGrandTotPo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        JTextGrandTotPo.setText("0");
        JTextGrandTotPo.setToolTipText("");

        JTextPpnPo.setEditable(false);
        JTextPpnPo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JTextPpnPo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField4)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JTextSubTotalPo)
                            .addComponent(JTextDiscPo)
                            .addComponent(JTextPpnPo)
                            .addComponent(JTextGrandTotPo, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JTextSubTotalPo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField4)
                    .addComponent(JTextDiscPo))
                .addGap(2, 2, 2)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField5)
                    .addComponent(JTextPpnPo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JTextGrandTotPo))
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JComboAmountPo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JComboAmountPo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Trans No");

        JTextTransNoPo.setEditable(false);
        JTextTransNoPo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JTextTransNoPo.setDisabledTextColor(new java.awt.Color(51, 0, 204));
        JTextTransNoPo.setEnabled(false);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Bagian");

        JTextBagian.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        JTextBagian.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        JTextBagian.setEnabled(false);

        JTextSupplierPo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Supplier");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Address");

        JTextAddressPo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        JTextAdressPo2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        JTextDeliveryToPo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        JTextDeliveryToPo2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setText("Delivery To");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("Date");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("DTM");

        jComboBox4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox4.setEnabled(false);

        TanggalPo.setDoubleBuffered(false);
        TanggalPo.setEditable(false);
        TanggalPo.setEnabled(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 25, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JTextAdressPo2)
                    .addComponent(JTextAddressPo)
                    .addComponent(JTextDeliveryToPo2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JTextDeliveryToPo)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JTextBagian, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(JTextTransNoPo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(18, 18, 18)
                                .addComponent(TanggalPo, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 50, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(JTextSupplierPo, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(17, 17, 17))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JTextTransNoPo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TanggalPo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JTextBagian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JTextSupplierPo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JTextAddressPo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTextAdressPo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTextDeliveryToPo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTextDeliveryToPo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        JTextKeterangan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        JComboBoxItem.setEditable(true);
        JComboBoxItem.setModel(new javax.swing.DefaultComboBoxModel(app_search_data.getData().toArray()));

        JTextQtyPO.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JTextQtyPO.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Qty");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setText("Add Item");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("Keterangan");

        JBottomQtyPo.setText("Add");
        JBottomQtyPo.setEnabled(false);
        JBottomQtyPo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBottomQtyPoActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setText("Tanggal Bth");

        TanggalBthPO.setDoubleBuffered(false);
        TanggalBthPO.setEditable(false);
        TanggalBthPO.setEnabled(false);

        JBottomDeleteItemPo.setText("Delete");
        JBottomDeleteItemPo.setEnabled(false);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JTextKeterangan)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(JComboBoxItem, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addGap(10, 10, 10)
                        .addComponent(JTextQtyPO, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(TanggalBthPO, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JBottomQtyPo, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBottomDeleteItemPo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JComboBoxItem, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTextQtyPO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTextKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(JBottomDeleteItemPo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JBottomQtyPo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(22, 22, 22))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(TanggalBthPO, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        JBottomDeletePo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/delete_icon.png"))); // NOI18N
        JBottomDeletePo.setEnabled(false);

        JBottomEditPo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/edit_icon.png"))); // NOI18N
        JBottomEditPo.setEnabled(false);

        JBottomPrintPo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/print_icon.png"))); // NOI18N
        JBottomPrintPo.setEnabled(false);

        JButtonOpenPo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/open_icon.png"))); // NOI18N
        JButtonOpenPo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonOpenPoActionPerformed(evt);
            }
        });

        JBottomSavePo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/save_icon.png"))); // NOI18N
        JBottomSavePo.setEnabled(false);

        jLabel2.setText("    Delete");

        jLabel3.setText("      Edit");

        jLabel4.setText("      Print");

        jLabel5.setText("      Open");

        jLabel6.setText("    Save");

        JButtomAddPo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/add_item.png"))); // NOI18N

        jLabel7.setText("Add");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JBottomDeletePo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JButtomAddPo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JButtonOpenPo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JBottomSavePo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(JBottomPrintPo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(JBottomEditPo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(JButtomAddPo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JBottomSavePo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JButtonOpenPo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JBottomPrintPo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JBottomEditPo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JBottomDeletePo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1ComponentHidden

    private void JBottomQtyPoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBottomQtyPoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JBottomQtyPoActionPerformed

    /*
     * action diaman mau ambil data dari open view po 1
     */
    private void JButtonOpenPoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonOpenPoActionPerformed
        try {
            
           /*
            * aturan membuka menu tombol pada saat klik tombol po
            */
            JBottomPrintPo.setEnabled(true);
            JBottomSavePo.setEnabled(false);
            JBottomQtyPo.setEnabled(false);
            JBottomEditPo.setEnabled(false);
            JBottomDeletePo.setEnabled(false);
            /*
            * end
            */
            form_purchaseorderView  DapatTransNo = new form_purchaseorderView(new javax.swing.JFrame(), true);
            DapatTransNo.setVisible(true);
            form_purchaseorderObject Dt = DapatTransNo.GetTableData();
            JTextTransNoPo.setText(Dt.GetVPTransNo());
            JTextSupplierPo.setText(Dt.GetVPSuplier());
            JTextAddressPo.setText(Dt.GetVPSuplierAddress());
            JTextDeliveryToPo.setText(Dt.GetVPDepartementsAddress());
            JTextGrandTotPo.setText(Dt.GetVPGrandTot());
            /*
            * Hapus Data Tabel Dulu
            */
            DefaultTableModel dtm = (DefaultTableModel) TabelOrderBarang.getModel();
            dtm.setRowCount(0);

            /*
            * Memangil database untuk di view di tabel po
            */
            String AmbilDataTransNo = Dt.GetVPTransNo();
            if ( AmbilDataTransNo != null ){
                /*
                * Panggil konfigurasi koneksi database
                */
                KoneksiDatabase KD = new KoneksiDatabase();
                KD.KonekDatabase();
                Connection K = KD.koneksi;
                int baris;

                ResultSet HQ = null;
                try {
                    Statement stm = K.createStatement();
                    HQ = stm.executeQuery("SELECT period, no, trans_no, item_part, item_name, qty, price, price_tot, unit, "+
                        "tanggal_bth, ket, trans_date, updated from  `tbdetailtrans` WHERE `trans_no` = '" +AmbilDataTransNo+"'" );
                    //JOptionPane.showMessageDialog(null, baris);
                    while(HQ.next()  ){

                        String Period        = HQ.getString("period");
                        String NoData        = HQ.getString("no");
                        String TransNoZ      = HQ.getString("trans_no");
                        String ItemPart      = HQ.getString("item_part");
                        String ItemName      = HQ.getString("item_name");
                        String QtyPo         = HQ.getString("qty");
                        String Price         = HQ.getString("price");
                        String PriceTot      = HQ.getString("price_tot");
                        String Unit          = HQ.getString("unit");
                        String TanggalBth    = HQ.getString("tanggal_bth");
                        String Ket           = HQ.getString("ket");
                        String TransDate     = HQ.getString("trans_date");
                        String Updated       = HQ.getString("updated");

                        String[] add         = {NoData,TransNoZ,ItemPart,ItemName,QtyPo,Price,PriceTot, Unit,TanggalBth,Ket};
                        TabelModelOrder.addRow(add);
                    }
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog (null, "Error (6)"+ ex, "Error" , JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }

            }
        }
        catch ( Exception ex){
            //JOptionPane.showMessageDialog (null, "Error (5)"+ ex, "Error" , JOptionPane.ERROR_MESSAGE);
            //ex.printStackTrace();
        }
        /*
        for ( int a = 1;  a <= Dt.GetVPJumlahRow(); a ++){
            String[] XC = Dt.GetVPDataTabel();
            TabelModelOrder.addRow(XC);
        }
        * */
        //TabelModelOrder.addRow(Dt.GetVPDataTabel());
        //        TanggalPo.setCalendar(null);
    }//GEN-LAST:event_JButtonOpenPoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBottomDeleteItemPo;
    private javax.swing.JButton JBottomDeletePo;
    private javax.swing.JButton JBottomEditPo;
    private javax.swing.JButton JBottomPrintPo;
    private javax.swing.JButton JBottomQtyPo;
    private javax.swing.JButton JBottomSavePo;
    private javax.swing.JButton JButtomAddPo;
    private javax.swing.JButton JButtonOpenPo;
    private javax.swing.JComboBox JComboAmountPo;
    private javax.swing.JComboBox JComboBoxItem;
    private javax.swing.JTextField JTextAddressPo;
    private javax.swing.JTextField JTextAdressPo2;
    private javax.swing.JTextField JTextBagian;
    private javax.swing.JTextField JTextDeliveryToPo;
    private javax.swing.JTextField JTextDeliveryToPo2;
    private javax.swing.JTextField JTextDiscPo;
    private javax.swing.JTextField JTextGrandTotPo;
    private javax.swing.JTextField JTextKeterangan;
    private javax.swing.JTextField JTextPpnPo;
    private javax.swing.JTextField JTextQtyPO;
    private javax.swing.JTextField JTextSubTotalPo;
    private javax.swing.JTextField JTextSupplierPo;
    public javax.swing.JTextField JTextTransNoPo;
    public javax.swing.JTable TabelOrderBarang;
    public org.kazao.calendar.KazaoCalendarDate TanggalBthPO;
    private org.kazao.calendar.KazaoCalendarDate TanggalPo;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JFrame jFrame3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
