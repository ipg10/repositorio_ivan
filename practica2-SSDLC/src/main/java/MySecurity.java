/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Arrays;
import java.nio.charset.Charset; 
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException; 

/**
 *
 * @author IVAN
 */
public class MySecurity {
    
    /**
     * devuelve el hash
     * @param cadena
     * @return 
     */
    public static String buildMD5String(String cadena){
       
        byte[] bytes = cadena.getBytes(Charset.defaultCharset());     
        MessageDigest md5;
        try {
         
              md5 = MessageDigest.getInstance ("MD5");
              md5.update(bytes);
                byte resultado[] = md5.digest();
                StringBuilder sb = new StringBuilder();
                for (int i=0;i< resultado.length;i++){
                    sb.append(String.format("%02x", resultado[i]));
                }
              
                return sb.toString();      
        } catch (NoSuchAlgorithmException ex) {
             ex.printStackTrace();
        } 
        return null;                 
    }
    
}
