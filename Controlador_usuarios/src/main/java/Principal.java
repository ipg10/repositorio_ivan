
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/** message diggest  implementacion de md5 permite generar funcion hash a parrir datos de entrada
 * tenemos que proprcionar o validar que el dato que tenemos no se ha modificado fuera de nuestro entrono 
 * , tenemos quie genrear el fichero de integridad cada registro 
 *
 * @author IVAN
 */
public class Principal 
{
    private static ArrayList<Operaciones> listaOperaciones = new ArrayList<>();
    private static Usuarios usuarios;
    private static Aplicacion aplicacion = new Aplicacion(); 
    
    /**
     * Roles este metodo agregar las operaciones de los roles segun sea el permiso dado
     * @param nombredelrol
     * @return Rol(permisos) segun sea su rol
     */
      public static Roles retornoderol(String nombredelrol) {
        listaOperaciones = new ArrayList<>();
        Roles rol = null;
        if (nombredelrol.equalsIgnoreCase("Administrador")) {
            listaOperaciones.add(new Operaciones(1, "Clonar Contacto", true));
            listaOperaciones.add(new Operaciones(2, "Modificar Contacto", true));
            listaOperaciones.add(new Operaciones(3, "Eliminar Contacto", true));
            listaOperaciones.add(new Operaciones(4, "Listar Contacto", true));
//            listaOperaciones.add(new Operaciones(5, "Agregar Usuario", true));
            rol = new Roles(nombredelrol, listaOperaciones);
            
        } else if (nombredelrol.equalsIgnoreCase("Gestor")) {
            listaOperaciones.add(new Operaciones(1, "Clonar Contacto", true));
            listaOperaciones.add(new Operaciones(2, "Modificar Contacto", true));
            listaOperaciones.add(new Operaciones(3, "Eliminar Contacto", false));
            listaOperaciones.add(new Operaciones(4, "Listar Contacto", true));
//            listaOperaciones.add(new Operaciones(5, "Agregar Usuario", false));
            rol = new Roles(nombredelrol, listaOperaciones);
            
        } else {
            if (nombredelrol.equalsIgnoreCase("Asistente")) {
                listaOperaciones.add(new Operaciones(1, "Clonar Contacto", true));
                listaOperaciones.add(new Operaciones(2, "Modificar Contacto", false));
                listaOperaciones.add(new Operaciones(3, "Eliminar Contacto", false));
                listaOperaciones.add(new Operaciones(4, "Listar Contacto", true));
//                listaOperaciones.add(new Operaciones(5, "Agregar Usuario", false));
                rol = new Roles(nombredelrol, listaOperaciones);
            }
        }

        return rol;
    }

    /**
     * Este metodo no regresa nada
     * @param usuarios
     * @param numero
     * @param index 
     */  
    public static void permisosOrtogados(Usuarios usuarios, int numero, int index) { 
        boolean status = false;
        //itera la lista
        for (Operaciones rol : usuarios.getRol().getListaOperaciones()) {
            //valida la operacion a realizar y si esta esta permitida para el rol
            //se repite la operacion por cada permiso  
            
            if (numero == 1 && rol.getIdOperacion() == numero && rol.isPermitido() == true) {
                //si esta permitido ejecuta el codigo y sea hace un break para que no siga preguntando 
                aplicacion.clonar_contacto(index);
                status = true;
                break;
            } else if (numero == 2 && rol.getIdOperacion() == numero && rol.isPermitido() == true) {
                aplicacion.modificar_contacto(index);
                status = true;
                break;
            } else if (numero == 3 && rol.getIdOperacion() == numero && rol.isPermitido() == true) {
                aplicacion.eliminar_contacto(index);
                status = true;
                break;
            } else if (numero == 4 && rol.getIdOperacion() == numero && rol.isPermitido() == true) {
                aplicacion.listar_contacto();
                status = true;
                break;
            } 
            //else if (numero == 5 && rol.getIdOperacion() == numero && rol.isPermitido() == true) {
//                aplicacion.agregar_usuario();
//                status = true;
//                break;
//            }  
        }
        //si no tiene el permiso dado el status es false y le informa al usuario que no puede sejecutar esa operacion 
        if(status == false){
            System.err.println("No posee permisos suficientes para realizar esta operación");
        }
        
    }
    
    /**
     * metodo que no retorna nada y tiene como parametro un index para saber la posicion de la lista de contacto 
     * que se le aplicara una accion 
     * @param index 
     */
    public static void preguntarQueHacer(int index){
         //sumo uno al index ya que los array enpiezan en la posision 0  
         int num = index+1; 
         //pregunta la operacion a realizar 
         System.out.println("La linea "+ num + " esta corrupto que desea hacer:");           
         System.out.println("Nª Operación:1 Operación a realizar:Clonar Contacto");
         System.out.println("Nª Operación:2 Operación a realizar:Modificar Contacto");
         System.out.println("Nª Operación:3 Operación a realizar:Eliminar Contacto");
//       System.out.println("Nª Operación:4 Operación a realizar:Listar Contacto");
//       System.out.println("Nª Operación:5 Operación a realizar:Agregar Usuario");
         //lee el valor pulsado por teclado
         Scanner entradaScanner3 = new Scanner(System.in); 
         String numeroOperacion = entradaScanner3.nextLine();
         //llama al metodo permisosOrtogados
         permisosOrtogados(usuarios, Integer.parseInt(numeroOperacion), index); 
    }
     
    public static void preguntarQueHacerCasoFaltaIntegridad(int index){ 
        //pregunta la operacion a realizar 
         System.out.println("Tipee el numero de la operación a relizar");
         System.out.println("Nª Operación:2 Operación a realizar:Modificar Contacto");
         System.out.println("Nª Operación:3 Operación a realizar:Eliminar Contacto");
         System.out.println("Nª Operación:4 Operación a realizar:Listar Contacto");
//         System.out.println("Nª Operación:5 Operación a realizar:Agregar Usuario");
        //lee el valor pulsado por teclado
         Scanner entradaScanner3 = new Scanner(System.in); 
         String numeroOperacion = entradaScanner3.nextLine();
         //llama al metodo permisosOrtogados pasando los parametros que este pide
         permisosOrtogados(usuarios, Integer.parseInt(numeroOperacion), index); 
    }
    
    /**
     * se usa para la verificacion de la intengridad 
     */
    public static void verificarIntegridadRun(){
        //  lista de numero "listaNumero" para insertar los indices de los archivos que son corruptos(lista negra)
        List<ClaseNumero> listaNumero = new ArrayList<>();
        //itero la lista de contacto que esta creada en la clase Aplicacion
         for(int i = 0; i < Aplicacion.listaContactos.size(); i++){
             //se pregunta si es corrupto 
             //no se verifica la integridad de los archivos que ya han tenido perdida 

             if(Aplicacion.listaContactos.get(i).isCorrupto()  == true && Aplicacion.listaContactos.get(i).getNotaSistema().equals("null")){
                 //se añade a la lista de numero el indice del archivo corrupto
                  listaNumero.add(new ClaseNumero(i));
             }
         }
         
         //itero la lista de numero listaNumero para saber si hay indice de archivos corrupto
         for(int i = 0; i < listaNumero.size(); i++){
             //se llama el metodo y se le pasa el indice corrupto
             preguntarQueHacer(listaNumero.get(i).getNumero());
         }
         //al tener la lista recuperada se vuelve a crear el archivo, eliminando el corrupto, llamamos el siguiente metodo: Aplicacion.guardarFichero()
         Aplicacion.guardarFichero();
    }
    
    public static void main(String args[]){
        
    
        //llamamos el metodo crearFicheroPrimeraVez que esta en la clase Aplicacion de manera publica el cual genera el fichero .txt con data al inciair el programa
        Aplicacion.crearFicheroPrimeraVez();          
         
        //Lo siguiente pide por teclado informacion
         System.out.println("Nombre del Usuario:");            
         Scanner entradaScanner = new Scanner(System.in);
         String entradaTecladousuario = entradaScanner.nextLine();      
         //Lo siguiente pide por teclado informacion
         System.out.println("Nombre del Rol:");
         Scanner entradaScanner2 = new Scanner(System.in); 
         String entradaTecladorol = entradaScanner2.nextLine();
         
         System.out.println("-----------------------------");
         //se cra el nuevo usuario asignandole el rol y sus privilegios 
         usuarios = new Usuarios(entradaTecladousuario,retornoderol(entradaTecladorol));
//         System.out.println("Nombre de usuario: " + usuarios.getNombre() + "  Rol:  " + usuarios.getRol().getNombre()); 
         
         //se lee el fichero creado por primera vez  
         // para que se demueste que fue alterado 
         Aplicacion.leerFihero();
         
         //se llama el metodo veficar integridad 
         verificarIntegridadRun();
          
         /**
          *  se pide por teclado que realize una operacion
          * si le da a salir del sistema se verifica la integridad una vez mas 
          * si no, hago un archivo con la nota Perdidadeintegridad para que  no pueda ser modificado ni eliminado
          */
         System.out.println("-------------------------------");
         System.out.println("Selecione el número de la operación");
         System.out.println("Nª Operación:1 Operación a realizar:Salir del sistema");
         System.out.println("Nª Operación:2 Operación a realizar:Seguir en el sistema");
         Scanner entradaScanner4 = new Scanner(System.in); 
         String numeroOperacions = entradaScanner4.nextLine();
         
         Aplicacion.leerFihero();
         if(Integer.parseInt(numeroOperacions) == 1){ 
             verificarIntegridadRun(); 
              //se termina la ejecucion del programa
         } else {
             // recorremos la lista que se usa para comprobar que si hubo una Perdidadeintegridad anteiormente 
             //este archivo no pueda ser eliminado ni modificado
             for(int i = 0; i < Aplicacion.listaContactos.size(); i++){
                 //valida que no haya tenido Perdidadeintegridad anteriormente
                if(!"null".equalsIgnoreCase(Aplicacion.listaContactos.get(i).getNotaSistema())){
                    //llama al metodo preguntarQueHacerCasoFaltaIntegridad
                  preguntarQueHacerCasoFaltaIntegridad(i);
                }
            } 
            //se termina la ejecucion del programa
            
         }
             
        
     /*{
          System.out.println("No autorizado"); 
      }*/
}
}