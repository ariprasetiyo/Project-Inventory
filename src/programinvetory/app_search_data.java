package programinvetory;

import SistemPro.KoneksiDatabase;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;


public class app_search_data  {

	private static List<String> list;
        public app_search_data () {
            
        }   
	static {
            
       /*
        * Memanggil agar bisa terkoneksi dengan database
        * Start
        */
            
       KoneksiDatabase cc = new KoneksiDatabase();
       cc.KonekDatabase();
       Connection koneksi = cc.koneksi;
       
       /*
        * End
        */
       
       ResultSet hasilquery = null;
       try {
           Statement stm = koneksi.createStatement();
           hasilquery = stm.executeQuery("SELECT item_name,item_part FROM tbitem");
       }
       catch (Exception ex){System.err.println ("app_search_data.javaError (3)"+ex);
       System.exit(1);
           
       }
       list = new ArrayList<String>();
       String Data_List_Item_Part;
       Vector Vector_Id= new Vector();
       String Data_List_Item_Name;
       try {
           while(hasilquery.next()){
              Data_List_Item_Part = hasilquery.getString("item_part");
              //Vector_Id.addElement(No_Id);
              Data_List_Item_Name = hasilquery.getString("item_name");
               //System.out.println(coba);
              list.add(Data_List_Item_Part + "      --     " + Data_List_Item_Name);
           }
           
       }
       catch (Exception ex){System.err.println ("Error (4)"+ex);
       System.exit(1);
           
       }
		list.add("Abdul Kadir");
		list.add("Abdul Muis");
	}

	public static List<String> getData(){
                //bacadatanegara as = bacadatanegara();
		return list;
	} /*
        public static  List<Vector> getData(){
		return item_dijual;
	} */

    
    public void KonekDatabase() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

  

    
}

