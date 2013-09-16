/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemPro;

/**
 *
 * @author LANTAI3
 */
public class KomaToString {
    String DataString;
    String DataKoma;
    String KomaStr;
    int DataInt;
    
    
    public String GetString (){
        return DataString;
    }
    public int GetInt(){
        return DataInt;
    }
    public String GetKomaString(){
        return DataKoma;
    }

    
    
    public void SetHapusKoma( String Data ){                
        //Merubah dari ada koma menjadi tidak koma dalam bentuk string untuk menghitung TotPrice dan memperoleh TotPriceDouble
        
        String RubahData= Data.replaceAll(",", "");
        this.DataString = RubahData;
        this.DataInt = Integer.valueOf(RubahData).intValue();

    }
    public void SetKoma ( String Data){
        java.text.DecimalFormat decimalFormat2 = new java.text.DecimalFormat("#,##0");
        String Data2 = String.valueOf(decimalFormat2.format(Data)).toString();
        this.KomaStr = Data2;
        //this.KomaInt = Integer.valueOf(Data2).intValue();
    }
}
