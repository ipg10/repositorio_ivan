package com.mycompany.auditoria_sistema;


import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author IVAN
 */
public class Auditar {
    
      
    //se declara una variable
    public static FileWriter  fichero;
    
    /**
     * este metodo que se encarga de crear el fichero en la ruta especificada
     */
    public   void iniciarFichero(){       
        try {
            fichero = new FileWriter("C:/Users/IVAN/Desktop/auditar.txt");
        } catch (IOException ex) {
            Logger.getLogger(Auditar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * este metodo permite crear o escribir una linea en el fichero abierto hasta que se cierre puede ser llamado este metodo
     * Guardar lineas
     * @param object
     * @param accion
     * @param resultado_operación
     * @param actor_accion 
     */
    public   void guardarLineaFichero(Object object,String accion,String resultado_operación,String actor_accion ){        
        try {
            //guarda la linea y da un salto a la proxima
            fichero.write(getFechaActual()+" | "+object+" | "+accion+" | "+resultado_operación+" | "+actor_accion+"\n");
        } catch (IOException ex) {
            Logger.getLogger(Auditar.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    /**
     * Guardar CABECERA  este metodo es igual al anterior pero lo en este caso creamos una lineaespecial 
     * para la cabecera del documento para que sepan que contiene cada columna
     */
    public   void guardarTitulosFichero(){        
        try {
            //guarda la linea y da un salto a la proxima
            fichero.write("FECHA Y HORA         |                     OBJECTO                     |       ACCION      | EJECUCION | ACTOR DE LA ACCION \n");
        } catch (IOException ex) {
            Logger.getLogger(Auditar.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    /**
     * metodo que no devuelve valor y es usado para cerrar el archivo abierto. 
     */
   public   void cerrarFichero(){     
        try {
            fichero.close();
        } catch (IOException ex) {
            Logger.getLogger(Auditar.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   
   /**
    * metodo que devuelve una cadena la cual es la fecha y la hora en ese momento que es llamado
    * @return 
    */
   public String getFechaActual(){

            //Instanciamos el objeto Calendar
            //en fecha obtenemos la fecha y hora del sistema
            Calendar fecha = new GregorianCalendar();
            //Obtenemos el valor del año, mes, día,
            //hora, minuto y segundo del sistema
            //usando el método get y el parámetro correspondiente
            int año = fecha.get(Calendar.YEAR);
            int mes = fecha.get(Calendar.MONTH);
            int dia = fecha.get(Calendar.DAY_OF_MONTH);
            int hora = fecha.get(Calendar.HOUR_OF_DAY);
            int minuto = fecha.get(Calendar.MINUTE);
            int segundo = fecha.get(Calendar.SECOND);
            String ff = ""+dia+"/"+(mes+1)+"/"+año+" "+hora+":"+minuto+":"+segundo;

            /*retornamos la fecha  */
            return ff;
    }
   
} 
