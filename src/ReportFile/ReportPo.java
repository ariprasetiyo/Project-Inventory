/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import programinvetory.form_purchaseorder;
import programinvetory.form_purchaseorderObject;
import programinvetory.form_purchaseorderView;

/**
 *081392021766
 * @author BSP
 */

public class ReportPo extends form_purchaseorder
{
    private String No;
    private String TransNo;
    private String ItemPart;
    private String ItemName;
    private String Qty;
    private String Price;
    private String Unit;
    private String Pic;
    
    private String TotalPrice;
    private String TglButuhkan;
    private String Keterangan;
    private String Tanggal;
    private String Departement;
    private String Bagian;
    private String NoPermohonan;
    private String JenisPembelian;
    private String JenisBuget;
    private String GrandTotR;
    private String TatalJmlhPesan;
     
     /* 
      * Get data tabel PO : format nama harus benar
      */
     public String getNo(){
         return No;
     }
     public String getTransNo(){
         return TransNo;
     }
     public String getItemPart(){
         return ItemPart;
     }
     public String getItemName(){
         return ItemName;
     }
    public String getQty(){
         return Qty;
     }
    public String getPrice(){
         return Price;
     }
    public String getUnit(){
         return Unit;
     }
     public String getPIC(){
        return Pic;
    }
/*
 * 
 */
    public String getTotalPrice(){
        return TotalPrice;
    }
    public String getTglButuhkan(){
        return TglButuhkan;
    }
    public String getKeterangan(){
        return Keterangan;
    }
    public String getTanggal(){
        return Tanggal;
    }
    public String getDepartement(){
        return Departement;
    }
    public String getBagian(){
        return Bagian;
    }
    public String NoPermohonan(){
        return NoPermohonan;
    }
    public String JenisPembelian(){
        return JenisPembelian;
    }
    public String JenisBuget(){
        return JenisBuget;
    }
    public String getGrandTot (){
        return GrandTotR;
    }
    public String TatalJmlhPesan (){
        return TatalJmlhPesan;
    }
   
    
    public  void ReportPoo (String No, String TransNo,String ItemPart,String ItemName, String qty
            ,String Price, String TotPrice, String TanggalPo, String TanggalBth, String Departements, String Bagian
            ,String Unit, String Keterangan, String PIC, String GrandTot ){ 
        this.No = No;
        
        this.TransNo = TransNo;
        //this.ItemPart = ItemPart;
        this.ItemName = ItemName;
        this.Qty = qty;
        this.Price = Price;
        this.Unit = Unit;
        this.TotalPrice = TotPrice;
        this.Tanggal = TanggalPo;
        //tanggalbutuh
        this.Departement = Departements;
        this.Bagian = Bagian;
        this.Keterangan = Keterangan;
        this.Pic = PIC;
        this.GrandTotR = GrandTot;
    }

    /*
     * End
     */
    
    /*
     * Jika cuma bukan tabel yang memakai array
     */
   
    public static   Collection DataSourceTabelPO  () {

    List Data = new ArrayList();
   // Vector Data;
    ReportPo as ;

    /*
     * Data di ambil dari form_puchaseorder.java
     */
    String[] Data0 = (String[]) form_purchaseorder.ListDataKe0.toArray(new String[0]);// no
    //String[] Data1 = (String[]) form_purchaseorder.ListDataKe1.toArray(new String[0]); // trans no
    //String[] Data2 = (String[]) form_purchaseorder.ListDataKe2.toArray(new String[0]); // item part
    String[] Data3 = (String[]) form_purchaseorder.ListDataKe3.toArray(new String[0]); // item name
    String[] Data4 = (String[]) form_purchaseorder.ListDataKe4.toArray(new String[0]); // qty
    String[] Data5 = (String[]) form_purchaseorder.ListDataKe5.toArray(new String[0]); // price
    String[] Data6 = (String[]) form_purchaseorder.ListDataKe6.toArray(new String[0]);// tot price
    String[] Data7 = (String[]) form_purchaseorder.ListDataKe7.toArray(new String[0]); // unit
    String[] Data8 = (String[]) form_purchaseorder.ListDataKe8.toArray(new String[0]); //tgl dibutuhkan
    String[] Data9 = (String[]) form_purchaseorder.ListDataKe9.toArray(new String[0]); // keterangan
    String[] Data10= (String[]) form_purchaseorder.ListDataKe10.toArray(new String[0]); // keterangan
    String DataTgl      = RTglPo;
    String DataBagian   = RBagian;
    String DataPIC      = RPIC;
    String DataTransNoPo= RTransNoPo;
    String DataDepar    = RDepartements;
    String DataGrandTot = RGrandTot;
    //JOptionPane.showMessageDialog(null," dan " + Data10[1]);
     try
        {
           // Data.add(as. ReportPoo(2,"2","2","2",2,"2","2"));
           
            for (int av = 0; av <= Data3.length - 1; av++){
                as = new ReportPo();
                //JOptionPane.showMessageDialog(null,"x" + " " + Data1[av]  + " " + Data7[av]  + " "+ Data3[av] + " " + Data4[av] + " " + Data5[av]  + " "+Data6[av]  
                //        + " " + Data8[av] + " " + Data9[av] + " " + PIC + " " + TglPoo);
                /*String No, String TransNo,String ItemPart,String ItemName, String qty
            ,String Price, String TotPrice, String TanggalPo, String TanggalBth, String Departements, String Bagian
            ,String Unit, String Keterangan, String PIC */
                //JOptionPane.showMessageDialog(null, Data0[av]);
                as.ReportPoo( Data0[av],DataTransNoPo,"ItemPart", Data3[av], Data4[av], Data5[av], Data6[av],DataTgl
                       ,Data8[av],DataDepar, DataBagian,Data7[av],Data9[av],DataPIC , DataGrandTot);
                //as.ReportPoo("1");
                Data.add(as);      
                     }
        }
        catch(Exception ex)
        {
            System.out.println(ex + " Error memanggil report ");
            ex.printStackTrace();
        }
 
    return Data;
 }   
    public static Map getReportParameter() {
    Map parameters = new HashMap();
 
    parameters.put("printer", "1010111");
 
    return parameters;
}
    /*
    public ReportPo(){
         JasperDesign jasperDesign = null;
        // JasperDesign jasperDesign;
        JasperPrint jasperPrint = null ;
        JasperReport jasperReport;
        HashMap hashMap = new HashMap();

       System.out.print("oc");

        //System.out.print(a + " dan " +  b);
        hashMap.put("PMT_TRANSNO", "2010");
        try {
                            //get report file and then load into jasperDesign
                            String x = System.getProperties().getProperty("java.class.path").split(";")[System.getProperties().getProperty("java.class.path").split(";").length - 1] + "\\Report\\ReportPoIT.jrxml";
                            jasperDesign = JRXmlLoader.load(x);
                            //compile the jasperDesign
                            jasperReport = JasperCompileManager.compileReport(jasperDesign);
                            //fill the ready report with data and parameter                 
                            //for (int aa = 0; aa <= 0 /* a *//*; aa++) {
                            jasperPrint = JasperFillManager.fillReport(jasperReport, hashMap, new JRBeanCollectionDataSource(
                                    //String TglPo, String GrandTot, String PIC, String Depar, String Bagian
                            
                            ReportPo.DataSourceTabelPO()));
                         //   }
                    
                            //view the report using JasperViewer
                            JasperViewer.viewReport(jasperPrint);
                           
                        } catch (JRException e) {
                            e.printStackTrace();
                        }
    }
    /*
    public static void main(String[] str) throws JRException
    {


        
        JasperDesign jasperDesign = null;
        // JasperDesign jasperDesign;
        JasperPrint jasperPrint = null ;
        JasperReport jasperReport;
        HashMap hashMap = new HashMap();



        //System.out.print(a + " dan " +  b);
        hashMap.put("PMT_TRANSNO", "2010");
        try {
                            //get report file and then load into jasperDesign
                            String x = System.getProperties().getProperty("java.class.path").split(";")[System.getProperties().getProperty("java.class.path").split(";").length - 1] + "\\Report\\ReportPoIT.jrxml";
                            jasperDesign = JRXmlLoader.load(x);
                            //compile the jasperDesign
                            jasperReport = JasperCompileManager.compileReport(jasperDesign);
                            //fill the ready report with data and parameter  
                            //for (int aa = 0; aa <= 0 /* a */
    /*; aa++) {
                            jasperPrint = JasperFillManager.fillReport(jasperReport, hashMap, new JRBeanCollectionDataSource(
                                    //String TglPo, String GrandTot, String PIC, String Depar, String Bagian
                            
                            ReportPo.DataSourceTabelPO()));
                         //   }
                    
                            //view the report using JasperViewer
                            JasperViewer.viewReport(jasperPrint);
                           
                        } catch (JRException e) {
                            e.printStackTrace();
                        }
                  
    }
       /* try
        {
            JasperReport jasperReport = null;
            JasperPrint jasperPrint = null;
            JasperDesign jasperDesign = null;
            Map parameters = new HashMap();
           // System.out.print(System.getProperties().getProperty("java.class.path").split(";")[System.getProperties().getProperty("java.class.path").split(";").length - 1] + "\\report4\\report1.jrxml");
            jasperDesign = JRXmlLoader.load(System.getProperties().getProperty("java.class.path").split(";")[System.getProperties().getProperty("java.class.path").split(";").length - 1] + "\\report4\\report1.jrxml");
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jasperPrint  = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(report4.Student.getStudentList()));
            JasperExportManager.exportReportToPdfFile(jasperPrint,"StudentInfo.pdf");
            JasperViewer.viewReport(jasperPrint);
        }
        catch(Exception ex)
        {
            System.out.println("EXCEPTION: "+ex);
        } 
        
        /////////*
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource( getStudentList());
 
    // Compile JRXML menjadi Jasper
    JasperReport jasperReport = JasperCompileManager.compileReport(System.getProperties().getProperty("java.class.path").split(";")[System.getProperties().getProperty("java.class.path").split(";").length - 1] + "\\report4\\report1.jrxml");
 
    // Fill report dengan datasource
    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
            new HashMap(), dataSource);
 
    // Export report
    JasperExportManager.exportReportToPdfFile(jasperPrint, System.getProperties().getProperty("java.class.path").split(";")[System.getProperties().getProperty("java.class.path").split(";").length - 1] + "\\report4\\report1.jrxml");
 */
        
   // }
    
}

