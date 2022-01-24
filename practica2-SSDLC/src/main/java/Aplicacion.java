
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/** funcionalidad de seguridad
 * validar seguridad 
 * cosa segura 
 * 
 * verifiar  autorizacion usuario para hacer operacion x 
 * @author IVAN
 */
public class Aplicacion {
    
    public static List<Contacto> listaContactos = new ArrayList<>();
        
    /**
     * se llama por primara vez para crear contactos por defecto
     */
    public static void crearFicheroPrimeraVez(){     
        //se crea los primeros contactos 
        listaContactos.add(new Contacto(1, "Juan", "Perez", "null", false)); 
        listaContactos.add(new Contacto(2, "Juana", "Álvarez", "null", false));
        listaContactos.add(new Contacto(3, "Ivan", "Duque", "null", false));
        //llamamos la funcion para guardarFichero
        guardarFichero(); 
    }
    
    /**
     * Guarda fichero en el dispositivo, este metodo no retorna valor.
     * 
     */
    public static void guardarFichero(){        
        try {
            //escribo el archivo
            FileWriter fichero = new FileWriter("C:/Users/IVAN/Desktop/agenda.txt");
            //recorro la lista
            for(Contacto contacto : listaContactos){ 
                //se crea el hash 
                String textoEncode = MySecurity.buildMD5String(contacto.getId() +" "+ contacto.getNombre() +" "+ contacto.getApellido());
                //guarda la linea y da un salto a la proxima
                fichero.write(textoEncode +" "+ contacto.getId() +" "+ contacto.getNombre() +" "+ contacto.getApellido()+" "+ contacto.getNotaSistema()+"\n");
            }
            //se cierra el fichero
            fichero.close();
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }         
    }
    
    /**
     * metodo validar integridad sirve para saber si un registro esta corruto recibe multiples valores no tiene retorno de ningun tipo
     * @param has
     * @param id
     * @param nombre
     * @param apellido
     * @param nota
     * @param linea 
     */
    public static void validarIntegridad(String has , String id, String nombre, String apellido,String nota, int linea){  
        //se genera el hash nuevamente con los datos recibidos del .txt si el id  nombre  apellido ha sido modificado
        String textoEncode = MySecurity.buildMD5String(id +" "+ nombre +" "+ apellido); 
        //se compara si el hash del .txt es igual las original si esta corrupto cambia el estado el el indice de la lista  
        if(!has.equalsIgnoreCase(textoEncode)){ 
            listaContactos.get(linea).setCorrupto(true);
        }  
    }
    
    /**
     *
     */
    public static void leerFihero(){
         String nombreFichero = "C:/Users/IVAN/Desktop/agenda.txt";
        //Declarar una variable BufferedReader
        BufferedReader br = null;
        try {
           //Crear un objeto BufferedReader al que se le pasa  un objeto FileReader con el nombre del fichero
           br = new BufferedReader(new FileReader(nombreFichero));
           //Leer la primera línea, guardando en un String
           String texto = br.readLine();

           int linea = 0;
           while(texto != null) {                
                  
               //se separa la linea por espacio en blanco y se mete a un array 
               String[] splitStr = texto.trim().split("\\s+");   
               //la linea como es un .txt debe contener 5 palabras si no estara corrupto 
              
               if(splitStr.length != 5){
                   //se cambia el estado de la lista diciendo que el archivo ees corrupto  
                   listaContactos.get(linea).setCorrupto(true); 
               } else {
                   // metodo validar que el archivo este corrupto 
                   //splitStr[0] esto signidica que cogemos  del array del string la posicion 0,1,2...
                   validarIntegridad(splitStr[0],splitStr[1], splitStr[2], splitStr[3],splitStr[4], linea);
               }
               //sumo la linea se supone es como el indice
               linea = linea+1; 
               //Leer la siguiente línea
               texto = br.readLine();
                
           }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Fichero no encontrado"); 
        } catch(Exception e) {
            System.out.println("Error de lectura del fichero"); 
        } finally {
            try {
                if(br != null){
                    br.close();
                }                    
            } catch (Exception e) {
                System.out.println("Error al cerrar el fichero"); 
            }
        }
    } 
    
    
    /**
     * metodo que clona contacto corrupto decibe un indice
     * @param index 
     */
    public void clonar_contacto(int index){ 
         //añadimos el nuevo contacto
     
         listaContactos.add(new Contacto(listaContactos.size()+1, listaContactos.get(index).getNombre(), listaContactos.get(index).getApellido(), "null", false)); 
         //despues de el nuevo contacto,  se regenera y se le establece la  Perdidadeintegridad 
 
         listaContactos.get(index).setNotaSistema("Perdidadeintegridad");   
         
         System.err.println("Su contacto ha sido clonado");
      
    }
    
    /**
     * metodo para modificar contacto corrupto recibe un indice de la lista
     * @param index 
     */
    public void modificar_contacto(int index){
         //se valida si anteriormente perdio la integridad si fue asi no permite modificar  
        //de lo contario modifica de la lista de contacto el corrupto por medio del indice recibido
        if("Perdidadeintegridad".equals(listaContactos.get(index).getNotaSistema())){
            System.err.println("No puede ser modificado por perdida de integridad anteriormente");             
        } else {
            //modifica el contacto crrupto 
            listaContactos.get(index).setCorrupto(false);     
      
            System.err.println("Su contacto ha sido modificado");
        }      
    }
    
    /**
     * metodo para eliminar contacto corrupto recibe un indice de la lista
     * @param index 
     */
    public void eliminar_contacto(int index){
        //se valida si anteriormente perdio la integridad si fue asi no permite eliminar  
        //de lo contario elimina de la lista de contacto el corrupto por medio del indice recibido
        if("Perdidadeintegridad".equals(listaContactos.get(index).getNotaSistema())){
            System.err.println("No puede ser eliminado por perdida de integridad anteriormente");            
        } else {
            //elimina el contacto de la lista 
              listaContactos.remove(index);
        
             System.err.println("Su contacto ha sido eliminado");
        }
         
    }
    
    /**
     * metodo para listar contactos
     */
    public void listar_contacto(){
        for(Contacto contacto : listaContactos){ 
              System.err.println(contacto.getId() +" "+ contacto.getNombre() +" "+ contacto.getApellido()+" "+ contacto.getNotaSistema());
        }
    }
    
    
    public void agregar_usuario(){
        // ¿usuario? --> variable global 
        // if usuario puede hacer operacion (agregar usuario) --> ok
        // else --> error, no autorizado 
        System.err.println("Usted puede agregar usuario");
    }
    
    
}
    
    
