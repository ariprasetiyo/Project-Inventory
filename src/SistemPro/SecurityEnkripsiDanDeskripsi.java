/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemPro;

import java.io.IOException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 *  Cara penggunaannya
 *  SecretKey key = KeyGenerator.getInstance("DES").generateKey();
    DesEncrypter encrypter = new DesEncrypter(key);
    String encrypted = encrypter.encrypt("Don't tell anybody!!");
    String decrypted = encrypter.decrypt(encrypted);
    System.out.println(encrypted  + " dan " + decrypted);
 */

/**
 * Penggunaan deskripsi saja
 * SecretKey key = KeyGenerator.getInstance("DES").generateKey();
   SistemPro.SecurityEnkripsiDanDeskripsi Deskripsi = new SistemPro.SecurityEnkripsiDanDeskripsi(key);
   String decrypted =Deskripsi.decrypt(Pass);
 */

public class SecurityEnkripsiDanDeskripsi {


  public String encrypt(String str) throws Exception {
      /*
       * Index di mulai dari nol
       * Total karakter 67, variabel JmlhChar di hitungan dari index 0
       * Setingan awal geser adalah 1
       */
       char[] kr ={ '0','1','2','3','4','5','6','7','8','9',' ','.','□',+
                    'a','b','c','d','e','f','g','h','i','j','k','l','m',+
                    'n','o','p','q','r','s','t','u','v','w','x','y','z', +
                   '\'','!','?','A','B','C','D','E','F','G','H','I','J', +
                    'K','L','M','N','O','P','Q','R','S','T','U','V','W',+
                    'X','Y','Z',',',';','-','=','/'
                   };
        String Hasil1 = "";
        char[] cArray1 =str.toCharArray();
        int JmlhChar = kr.length - 1;
        int Geser = 1;
        for (char c1 : cArray1){
            for(int i=0; i<=JmlhChar; i++){
                if(c1 == kr[i]){
                   i = i+(Geser);
                   if(i>=(JmlhChar + 1)){
                       i = i-(JmlhChar + 1);
                    }
                    c1 = kr[i];
                    Hasil1 = Hasil1 + c1;
                 }
            }
        }
        
    /*
     * Encode base64
     */
    BASE64Encoder encoder = new BASE64Encoder();
    String encodedBytes = encoder.encodeBuffer(Hasil1.getBytes());
    return encodedBytes;
  }

  public String decrypt(String str) throws Exception {
      /*
       * Decode base64
       */
      BASE64Decoder decoder = new BASE64Decoder();
      byte[] decodedBytes = decoder.decodeBuffer(str);
      String Hasil2 = new String(decodedBytes);
   
    //System.out.println(Hasil2 + " xxx ");
 
      /*
       * Index di mulai dari nol
       * Total karakter 66, variabel JmlhChar
       * Setingan awal geser adalah 1
       */
    
        char[] kr ={'0','1','2','3','4','5','6','7','8','9',' ','.','□',+
                    'a','b','c','d','e','f','g','h','i','j','k','l','m',+
                    'n','o','p','q','r','s','t','u','v','w','x','y','z', +
                   '\'','!','?','A','B','C','D','E','F','G','H','I','J', +
                    'K','L','M','N','O','P','Q','R','S','T','U','V','W',+
                    'X','Y','Z',',',';','-','=','/'
        };
        String bantu2 = "";
        
         //enkripsi
        char[] cArray1 =Hasil2.toCharArray();
        int JmlhChar = kr.length - 1;
        for (char c1 : cArray1){
            for(int i=0; i<=(JmlhChar - 1); i++){
                if(c1 == kr[i]){
                   i = i-(1);
                   if(i<=-1){
                       i = i+JmlhChar;
                    }
                    c1 = kr[i];
                    bantu2 = bantu2 + c1;
                 }
            }
        }
    return bantu2;
  }
}
