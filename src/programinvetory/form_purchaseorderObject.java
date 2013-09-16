/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package programinvetory;

/**
 *
 * @author LANTAI3
 */
public class form_purchaseorderObject {
    private String TransNo;
    private String Tgl;
    private String Suplier;
    private String SuplierAddress;
    private String Departements;
    private String DepartementsAddress;
    private String GrandTotal;

    public form_purchaseorderObject (){
        
    }
    public String GetVPTransNo(){
        return TransNo;
    }
    public void SetVPTransNo (String Data){
        this.TransNo = Data;
    }
    
    public String GetVPDate(){
        return Tgl;
    }
    public void SetVPDate ( String Data){
        this.Tgl = Data;
    }
    public String GetVPSuplier(){
        return Suplier;
    }
    public void SetVPSuplier ( String Data){
        this.Suplier = Data;
    }
    
    public String GetVPSuplierAddress(){
        return SuplierAddress;
    }
    public void SetVPSuplierAddress ( String Data){
        this.SuplierAddress = Data;
    }
    
    public String GetVPDepartements(){
        return Departements ;
    }
    public void SetVPDepartement ( String Data){
        this.Departements = Data;
    }
    
    public String GetVPDepartementsAddress(){
        return DepartementsAddress ;
    }
    public void SetVPDepartementsAddress ( String Data){
        this.DepartementsAddress = Data;
    }
    
    public String GetVPGrandTot(){
        return GrandTotal ;
    }
    public void SetVPGrandTot ( String Data){
        this.GrandTotal = Data;
    }
    /*
     * Bagaian DetailViewPO                                               
     */
    /*
     * Detail Trans
     */
    private String Period;       
    private String TransNoSet;     
    private String ItemPart;    
    private String ItemName ;  
    private String Qty ;  
    private String Price;
    private String PriceTot;   
    private String Unit ;       
    private String TanggalBth ;  
    private String Ket ;         
    private String TransDate ;   
    private String Updated ;  
    
    public String GetVPeriod(){
        return Period ;
    }
    public void SetVPPeriod ( String Data){
        this.Period = Data;
    }
     public String GetVPTransNo2(){
        return TransNoSet ;
    }
    public void SetVPTransNo2 ( String Data){
        this.TransNoSet = Data;
    }
    public String GetVPItemPart(){
        return ItemPart ;
    }
    public void SetVPItemPart ( String Data){
        this.ItemPart = Data;
    }
     public String GetVPItemName(){
        return ItemName ;
    }
    public void SetVPItemName ( String Data){
        this.ItemName = Data;
    }
     public String GetVPQty(){
        return Qty ;
    }
    public void SetVPQty ( String Data){
        this.Qty = Data;
    }
    public String GetVPPrice(){
        return Price;
    }
    public void SetVPPrice (String Data){
        this.Price = Data;
    }
     public String GetVPPriceTot(){
        return PriceTot  ;
    }
    public void SetVPPriceTot ( String Data){
        this.PriceTot = Data;
    }
     public String GetVPUnit(){
        return Unit  ;
    }
    public void SetVPUnit ( String Data){
        this.Unit = Data;
    }
     public String GetVPTanggalButuh(){
        return TanggalBth ;
    }
    public void SetVPTanggalButuh ( String Data){
        this.TanggalBth = Data;
    }
     public String GetVPKeterangan(){
        return Ket ;
    }
    public void SetVKeterangan ( String Data){
        this.Ket = Data;
    }
     public String GetVPTransDate(){
        return TransDate ;
    }
    public void SetVPTransDate ( String Data){
        this.TransDate = Data;
    }
     public String GetVPUpdated(){
        return Updated  ;
    }
    public void SetVPUpdated ( String Data){
        this.Updated = Data;
    }
    /*
     * Data Array untuk tabel yang akan di view di form_purcehaseorder
     */
    String[] DataTabel;
    public String[] GetVPDataTabel ( ){
        return DataTabel;
    }
    public void SetVPDataTabel (String[] Data){
        this.DataTabel = Data;
    }
    private int JumlahRow;
    public int GetVPJumlahRow(){
        return JumlahRow;
    }
    public void SetVPJumlahRow ( int Data){
        this.JumlahRow = Data;
    }
    
    /*
     * Data object buat add item di tabel
     */
    String AddItemTabel;
    public String GetAddItemTabel(){
        return AddItemTabel;
    }
    public void SetAddItemTabel(String Data){
        this.AddItemTabel = Data;
    }
}

