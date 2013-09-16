/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package programinvetory;

import SistemPro.KoneksiDatabase;
import SistemPro.RenderingKanan;
import SistemPro.RenderingTengah;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author LANTAI3
 */
public class form_purchaseorderView extends javax.swing.JDialog {
    private int TanggalTanpaMinusx ;
    private String TanggalDenganMinusx;
    /*
     * Rata tengah atau kiri table
     */
    private TableCellRenderer kanan = new RenderingKanan();
    private TableCellRenderer tengah = new RenderingTengah();
    DefaultTableModel TabelModelOrder;
    
    

    /*
     * Membuat calendar
     */
    /*
    public int GetTanggalTanpaMinus (){
        return TanggalTanpaMinusx;
    }
    public String GetTanggalDenganMinus (){
        return TanggalDenganMinusx;
    }
   
    private int TahunX;
    private int Tahun (){
        Calendar Cal = Calendar.getInstance();
        return TahunX = Cal.get(Calendar.YEAR);
    }
    private int BulanX;
    public int Bulan (){
        Calendar Cal = Calendar.getInstance();
        return TahunX = Cal.get(Calendar.MONTH);
    }
    */

    /**
     * Creates new form form_purchaseorderView
     */
     /*
    public static  List<String> ListData = new ArrayList<>();
    public void SetDataViewPo(List Data){
    form_purchaseorderView.ListData = Data;
    }
    public   List<String> GetDataListViewPo (){
        //ListDataKe1 = Data1;
        //String[] sl = (String[]) list.toArray(new String[0]);
        //System.out.println("Array of String has length " + sl.length);
        return ListData;
    }
    public JButton GetButtonSend (){
        return JBottomGetPo;
    }
     public JButton GetButtomViewPo (){
        return JBottomGetPo;
    }
    
    public String GetViewTransNo (){
        int numRows     = TabelOrderViewPo.getSelectedRows().length;
        int AmbilRow    = TabelOrderViewPo.getSelectedRow() ;
        String AmbilDataTransNo = "cobaa";
                
                for(int i=0; i<numRows ; i++ ) {
                    /*
                     * Ambil Data
                     
                    AmbilDataTransNo = (String) TabelModelOrder.getValueAt(AmbilRow, 1);
                }
         return AmbilDataTransNo;    
    }
   
    */
    private form_purchaseorderObject DataObject;
     
    public form_purchaseorderView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
       
        
        /*
         * Tombol Get headerPO
         */
        /*
        JBottomGetPo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int numRows     = TabelOrderViewPo.getSelectedRows().length;
                int AmbilRow    = TabelOrderViewPo.getSelectedRow() ;
                
                for(int i=0; i<numRows ; i++ ) {
                    
                    /*
                     * Ambil Data
                     */
        /*
                    List<String> DataYangAkanDIlist1 = new ArrayList<String>();
                    String AmbilDataTransNo = (String) TabelModelOrder.getValueAt(AmbilRow, 1);
                    ///////////
                    DataObject.SetTransNoViewPo(AmbilDataTransNo);
                    JOptionPane.showMessageDialog(null, AmbilDataTransNo);
                    /*
                    form_purchaseorder cvb = new form_purchaseorder();
                    cvb.SetViewTransNo( AmbilDataTransNo);
                    cvb.GetViewTransNo2 ();
                    
                    DataYangAkanDIlist1.add(AmbilDataTransNo);
                    JOptionPane.showMessageDialog(null, AmbilDataTransNo + " " + DataYangAkanDIlist1);
                    SetDataViewPo(DataYangAkanDIlist1);
                    GetDataListViewPo ();
                    
                            form_purchaseorder xv = new form_purchaseorder();
                            xv.JTextTransNoPo.setText(AmbilDataTransNo+ " xxxxxxxxxx ");
                            SwingUtilities.updateComponentTreeUI(xv);
        xv.invalidate();
        xv.validate();
        xv.repaint();
/*
                }
                
            }
        });
        /*
         * Membuat tabel dan menampilkan datanya untuk title
         */
         
         String header[] = {"No", "No Po","Date","Suplier", "Suplier Address","To Departements","Departmenets Address","Tot Price","User"};
         TabelModelOrder = new DefaultTableModel(null, header) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                                //if(colIndex == 4) {return EditKolomQty ;} //  nilai false agar tidak bisa di edit
                                //if(colIndex == 8) {return EditKolomQty ;}
                                //if(colIndex == 9) {return EditKolomQty ;}
                                return false;   //Disallow the editing of any cell
                        }
        };
         
        /*
         * Ambil data dari database ke Jtabel
         */
        KoneksiDatabase KD = new KoneksiDatabase();
        KD.KonekDatabase();
        Connection K = KD.koneksi;
        //DefaultTableModel model = null;
        int baris;
       
        ResultSet HQ = null;

           try {
               Statement stm = K.createStatement();

                //insert  into menginlut ke database
                //  stm.executeUpdate("in kotak_pesan (no_id, no, Pengirim,Subject,Pesan,Hostname) VALUES"
                //        + "('9','9','"+ database +"','coba','coba','ari')");
               
              HQ = stm.executeQuery("SELECT key_no, trans_no, supplier, supplier_address,depaterment,delivery_address, date_po, grand_total, user from tbheaderpo");
               
                //stm.close();
               //hasilqueryambilno  = stm2.executeQuery("SELECT no ROM kotak_pesan "
                //       + "ORDER BY no_id DESC LIMIT 1");
                // String noo = hasilqueryambilno.getString("no");
                //   System.out.print(noo + " ini");
               
               //hasilquery.refreshRow();
               baris = HQ.getRow();
               //hasilquery.refreshRow();
               while(HQ.next()  ){
                   String No            = HQ.getString("key_no");
                   String TransNo       = HQ.getString("trans_no");
                   String Date          = HQ.getString("date_po");
                   String Supplier      = HQ.getString("supplier");
                   String SA            = HQ.getString("supplier_address");
                   String Departement   = HQ.getString("depaterment");
                   String DA            = HQ.getString("delivery_address");
                   String TotalPrice    = HQ.getString("grand_total");
                   String User          = HQ.getString("user");
                   
                   String[] add         = {No,TransNo,Date,Supplier,SA,Departement,DA, TotalPrice, User};
                   TabelModelOrder.addRow(add);      
                    //model.fireTableDataChanged();
                   //negara.addElement(nama_negara);
               }
           }
           catch (Exception ex){
            JOptionPane.showMessageDialog (null, "Error (4)"+ ex, "Error" , JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
           }
           
         TabelOrderViewPo.setModel(TabelModelOrder);
         
         /*
          * Membuat sort pada tabel
          */
      
         final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(TabelModelOrder);
         TabelOrderViewPo.setRowSorter(sorter);
         JTextSearchPo.addKeyListener(new KeyListener(){
             @Override
            public void keyReleased(KeyEvent e) {
               String text = JTextSearchPo.getText();
               if (text.length() == 0) {
                 sorter.setRowFilter(null);
               } else {
                 try {
                   sorter.setRowFilter(
                       RowFilter.regexFilter("(?i)"+text));
                   //System.out.println(sorter.getViewRowCount());
                 } catch (PatternSyntaxException pse) {
                   System.err.println("Bad regex pattern");
                 }
               }
            }     
             @Override
            public void keyTyped(KeyEvent e) {
            }
             @Override
            public void keyPressed(KeyEvent e) {
            }
         });
         /*
          * End
          */
        /*
         * Rata tengah atau kiri table
         */
        TabelOrderViewPo.getColumnModel().getColumn(0).setCellRenderer( tengah );
        TabelOrderViewPo.getColumnModel().getColumn(1).setCellRenderer( tengah );
        TabelOrderViewPo.getColumnModel().getColumn(2).setCellRenderer( tengah );
        TabelOrderViewPo.getColumnModel().getColumn(5).setCellRenderer( tengah );
        TabelOrderViewPo.getColumnModel().getColumn(7).setCellRenderer( kanan );
        TabelOrderViewPo.getColumnModel().getColumn(8).setCellRenderer( tengah );
      
        /*
         * Ukura table TabelOrderBarang
         */
        
        int jarak_colom[] = {30,100,100,120,250,120,250,120,70};
        colom_table ukuran_colom = new colom_table();
        ukuran_colom.ukuran_colom(TabelOrderViewPo, jarak_colom);
        
    }
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        dateChooserDialog1 = new datechooser.beans.DateChooserDialog();
        dateChooserDialog2 = new datechooser.beans.DateChooserDialog();
        dateChooserDialog3 = new datechooser.beans.DateChooserDialog();
        dateChooserDialog4 = new datechooser.beans.DateChooserDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        JTextSearchPo = new javax.swing.JTextField();
        JButtonRefreshPo = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        DateChooserViewPoSatu = new datechooser.beans.DateChooserCombo();
        DateChooserViewPoDua = new datechooser.beans.DateChooserCombo();
        JBottomGetPo = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelOrderViewPo = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("View Po Data");

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Search");

        jLabel4.setText(":");

        JTextSearchPo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTextSearchPoKeyReleased(evt);
            }
        });

        JButtonRefreshPo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/add_item.png"))); // NOI18N
        JButtonRefreshPo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonRefreshPoActionPerformed(evt);
            }
        });

        jLabel5.setText("Refresh");

        jLabel6.setText("Period");

        jLabel7.setText(" To ");

        DateChooserViewPoSatu.setCurrentView(new datechooser.view.appearance.AppearancesList("Grey",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));

    DateChooserViewPoDua.setCurrentView(new datechooser.view.appearance.AppearancesList("Grey",
        new datechooser.view.appearance.ViewAppearance("custom",
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                true,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 255),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(128, 128, 128),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(255, 0, 0),
                false,
                false,
                new datechooser.view.appearance.swing.ButtonPainter()),
            (datechooser.view.BackRenderer)null,
            false,
            true)));

JBottomGetPo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Gambar/get.png"))); // NOI18N
JBottomGetPo.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        JBottomGetPoActionPerformed(evt);
    }
    });

    jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel8.setText("Get");

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addGap(0, 0, 0)
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, 0)
            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, 0)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(JTextSearchPo, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jLabel5)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(DateChooserViewPoSatu, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel7)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(DateChooserViewPoDua, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(JButtonRefreshPo, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JBottomGetPo, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGap(0, 0, Short.MAX_VALUE))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTextSearchPo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(DateChooserViewPoSatu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(DateChooserViewPoDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(JButtonRefreshPo))
                .addComponent(JBottomGetPo))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel5)
                .addComponent(jLabel8))
            .addGap(0, 0, Short.MAX_VALUE))
    );

    JBottomGetPo.getAccessibleContext().setAccessibleParent(this);

    TabelOrderViewPo.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {

        }
    ));
    TabelOrderViewPo.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
    TabelOrderViewPo.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            TabelOrderViewPoMouseClicked(evt);
        }
    });
    jScrollPane1.setViewportView(TabelOrderViewPo);

    jLabel1.setForeground(new java.awt.Color(0, 0, 204));
    jLabel1.setText("*) Double Klik untuk mengambil data atau");

    jLabel3.setForeground(new java.awt.Color(0, 0, 204));
    jLabel3.setText("*) Pilih data dan klik tombol Get");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane1)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1)
                .addComponent(jLabel3))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 500, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel3))))
    );

    java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    setBounds((screenSize.width-1217)/2, (screenSize.height-499)/2, 1217, 499);
    }// </editor-fold>//GEN-END:initComponents

    private void JButtonRefreshPoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonRefreshPoActionPerformed
        String FilterDate1 = DateChooserViewPoSatu.getText();
        String FilterDate2 = DateChooserViewPoDua.getText();
        
        SistemPro.TransDate FilterTgl = new SistemPro.TransDate();
        FilterTgl.SetTransDate(FilterDate1);
        int FilterDateIntSatu = FilterTgl.GetTransDate();
        FilterTgl.SetTransDate(FilterDate2);
        int FilterDateIntDua  = FilterTgl.GetTransDate();
        
        /*
         * Hapus Data Tabel Dulu
         */
        DefaultTableModel dtm = (DefaultTableModel)  TabelOrderViewPo.getModel();
        dtm.setRowCount(0);
        /*
         * Filter Data Header Po dari periode
         */
        /*
         * Ambil data dari database ke Jtabel
         */
        KoneksiDatabase KD = new KoneksiDatabase();
        KD.KonekDatabase();
        Connection K = KD.koneksi;
        //DefaultTableModel model = null;
        int baris;
       
        ResultSet HQ = null;

           try {
               Statement stm = K.createStatement();

                //insert  into menginlut ke database
                //  stm.executeUpdate("in kotak_pesan (no_id, no, Pengirim,Subject,Pesan,Hostname) VALUES"
                //        + "('9','9','"+ database +"','coba','coba','ari')");
               
              HQ = stm.executeQuery("SELECT key_no, trans_no, supplier, supplier_address,depaterment,delivery_address, "
                      + "date_po, grand_total, user, trans_date "
                      + "from tbheaderpo where trans_date between '"+FilterDateIntSatu+"' and '" + FilterDateIntDua +"'");
               
                //stm.close();
               //hasilqueryambilno  = stm2.executeQuery("SELECT no ROM kotak_pesan "
                //       + "ORDER BY no_id DESC LIMIT 1");
                // String noo = hasilqueryambilno.getString("no");
                //   System.out.print(noo + " ini");
               
               //hasilquery.refreshRow();
               baris = HQ.getRow();
               //hasilquery.refreshRow();
               while(HQ.next()  ){
                   String No            = HQ.getString("key_no");
                   String TransNo       = HQ.getString("trans_no");
                   String Date          = HQ.getString("date_po");
                   String Supplier      = HQ.getString("supplier");
                   String SA            = HQ.getString("supplier_address");
                   String Departement   = HQ.getString("depaterment");
                   String DA            = HQ.getString("delivery_address");
                   String TotalPrice    = HQ.getString("grand_total");
                   String User          = HQ.getString("user");
                   
                   String[] add         = {No,TransNo,Date,Supplier,SA,Departement,DA, TotalPrice, User};
                   TabelModelOrder.addRow(add);      
                    //model.fireTableDataChanged();
                   //negara.addElement(nama_negara);
               }
           }
           catch (Exception ex){
            JOptionPane.showMessageDialog (null, "Error (4)"+ ex, "Error" , JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
           }
        
        
    }//GEN-LAST:event_JButtonRefreshPoActionPerformed
    
    /*
     * action diaman mau ambil data dari open view po 2
     */
     public form_purchaseorderObject GetTableData(){
        return DataObject;
    }
     private void PangilDataUntukDikirimKeForm_purchaseorder(){
        DataObject = new form_purchaseorderObject();
        int numRows     = TabelOrderViewPo.getSelectedRows().length;
        int AmbilRow    = TabelOrderViewPo.getSelectedRow() ;
                
                for(int i=0; i<numRows ; i++ ) {
                    
                    /*
                     * Ambil Data
                     */
        
                    List<String> DataYangAkanDIlist1 = new ArrayList<String>();
                    String AmbilDataTransNo     = (String) TabelModelOrder.getValueAt(AmbilRow, 1);
                    String AmbilDataDate        = (String) TabelModelOrder.getValueAt(AmbilRow, 2);
                    String AmbilDataSuplier     = (String) TabelModelOrder.getValueAt(AmbilRow, 3);
                    String AmbilDtaSuplierAD    = (String) TabelModelOrder.getValueAt(AmbilRow, 4);
                    String AmbilDataDM          = (String) TabelModelOrder.getValueAt(AmbilRow, 5);
                    String AmbilDataDMAlmt      = (String) TabelModelOrder.getValueAt(AmbilRow, 6);
                    String AmbilDataGrandTo     = (String) TabelModelOrder.getValueAt(AmbilRow, 7);
                    
                    ///////////
                    DataObject.SetVPTransNo(AmbilDataTransNo);
                    DataObject.SetVPDate(AmbilDataDate);
                    DataObject.SetVPSuplier(AmbilDataSuplier);
                    DataObject.SetVPSuplierAddress(AmbilDtaSuplierAD);
                    DataObject.SetVPDepartement(AmbilDataDM);
                    DataObject.SetVPDepartementsAddress(AmbilDataDMAlmt);
                    DataObject.SetVPGrandTot(AmbilDataGrandTo);
                    
                    /*
                    if ( AmbilDataTransNo != null ){
                        /*
                         * Panggil konfigurasi koneksi database
                         */
                    /*
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
                                   if (HQ.last() == true){
                                       DataObject.SetVPJumlahRow(HQ.getRow());
                                   }                              
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
                                   DataObject.SetVPDataTabel(add);
                               }
                           }
                           catch (Exception ex){
                            JOptionPane.showMessageDialog (null, "Error (4)"+ ex, "Error" , JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                           }

                       }
                    */
                    this.setVisible(false);
                }
     }
    private void JBottomGetPoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBottomGetPoActionPerformed
             PangilDataUntukDikirimKeForm_purchaseorder();
    }//GEN-LAST:event_JBottomGetPoActionPerformed

    private void TabelOrderViewPoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelOrderViewPoMouseClicked
         if(evt.getClickCount()==2){
             PangilDataUntukDikirimKeForm_purchaseorder();
        }
    }//GEN-LAST:event_TabelOrderViewPoMouseClicked

    private void JTextSearchPoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTextSearchPoKeyReleased

    }//GEN-LAST:event_JTextSearchPoKeyReleased

    /**
     * @param args the command line arguments
     */
    /*
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(form_purchaseorderView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_purchaseorderView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_purchaseorderView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_purchaseorderView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        /*
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                form_purchaseorderView dialog = new form_purchaseorderView(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    } */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo DateChooserViewPoDua;
    private datechooser.beans.DateChooserCombo DateChooserViewPoSatu;
    public javax.swing.JButton JBottomGetPo;
    public javax.swing.JButton JButtonRefreshPo;
    private javax.swing.JTextField JTextSearchPo;
    private javax.swing.JTable TabelOrderViewPo;
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private datechooser.beans.DateChooserDialog dateChooserDialog1;
    private datechooser.beans.DateChooserDialog dateChooserDialog2;
    private datechooser.beans.DateChooserDialog dateChooserDialog3;
    private datechooser.beans.DateChooserDialog dateChooserDialog4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
