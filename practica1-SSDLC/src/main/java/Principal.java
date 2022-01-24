
import java.util.ArrayList;
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
    
      public static Roles retornoderol(String nombredelrol) {
        listaOperaciones = new ArrayList<>();
        Roles rol = null;
        if (nombredelrol.equalsIgnoreCase("Administrador")) {
            listaOperaciones.add(new Operaciones(1, "Agregar Contacto", true));
            listaOperaciones.add(new Operaciones(2, "Modificar Contacto", true));
            listaOperaciones.add(new Operaciones(3, "Eliminar Contacto", true));
            listaOperaciones.add(new Operaciones(4, "Listar Contacto", true));
            listaOperaciones.add(new Operaciones(5, "Agregar Usuario", true));
            rol = new Roles(nombredelrol, listaOperaciones);
            
        } else if (nombredelrol.equalsIgnoreCase("Gestor")) {
            listaOperaciones.add(new Operaciones(1, "Agregar Contacto", true));
            listaOperaciones.add(new Operaciones(2, "Modificar Contacto", true));
            listaOperaciones.add(new Operaciones(3, "Eliminar Contacto", false));
            listaOperaciones.add(new Operaciones(4, "Listar Contacto", true));
            listaOperaciones.add(new Operaciones(5, "Agregar Usuario", false));
            rol = new Roles(nombredelrol, listaOperaciones);
            
        } else {
            if (nombredelrol.equalsIgnoreCase("Asistente")) {
                listaOperaciones.add(new Operaciones(1, "Agregar Contacto", false));
                listaOperaciones.add(new Operaciones(2, "Modificar Contacto", false));
                listaOperaciones.add(new Operaciones(3, "Eliminar Contacto", false));
                listaOperaciones.add(new Operaciones(4, "Listar Contacto", true));
                listaOperaciones.add(new Operaciones(5, "Agregar Usuario", false));
                rol = new Roles(nombredelrol, listaOperaciones);
            }
        }

        return rol;
    }

    public static void permisosOrtogados(Usuarios usuarios, int numero) { 
        boolean status = false;
        for (Operaciones rol : usuarios.getRol().getListaOperaciones()) {
            if (numero == 1 && rol.getIdOperacion() == numero && rol.isPermitido() == true) {
                aplicacion.agregar_contacto();
                status = true;
                break;
            } else if (numero == 2 && rol.getIdOperacion() == numero && rol.isPermitido() == true) {
                aplicacion.modificar_contacto();
                status = true;
                break;
            } else if (numero == 3 && rol.getIdOperacion() == numero && rol.isPermitido() == true) {
                aplicacion.eliminar_contacto();
                status = true;
                break;
            } else if (numero == 4 && rol.getIdOperacion() == numero && rol.isPermitido() == true) {
                aplicacion.listar_contacto();
                status = true;
                break;
            } else if (numero == 5 && rol.getIdOperacion() == numero && rol.isPermitido() == true) {
                aplicacion.agregar_usuario();
                status = true;
                break;
            }  
        }
        if(status == false){
            System.err.println("No posee permisos suficientes para realizar esta operación");
        }
        
    }
    
    public static void main(String args[]){
      
        String entradaTecladousuario = "";
        String entradaTecladorol = "";
        String numeroOperacion = "";
        
         System.out.println("Nombre del Usuario:");            
         Scanner entradaScanner = new Scanner(System.in);
         entradaTecladousuario = entradaScanner.nextLine();
         
         System.out.println("Nombre del Rol:");
         Scanner entradaScanner2 = new Scanner(System.in); 
         entradaTecladorol = entradaScanner2.nextLine();
         
         System.out.println("-----------------------------");
         usuarios = new Usuarios(entradaTecladousuario,retornoderol(entradaTecladorol));
//         System.out.println("Nombre de usuario: " + usuarios.getNombre() + "  Rol:  " + usuarios.getRol().getNombre()); 
         System.out.println("Selecciona el numero de la operación a relizar");
         System.out.println("Nª Operación:1 Operación a realizar:Agregar Contacto");
         System.out.println("Nª Operación:2 Operación a realizar:Modificar Contacto");
         System.out.println("Nª Operación:3 Operación a realizar:Eliminar Contacto");
         System.out.println("Nª Operación:4 Operación a realizar:Listar Contacto");
         System.out.println("Nª Operación:5 Operación a realizar:Agregar Usuario");
         Scanner entradaScanner3 = new Scanner(System.in); 
         numeroOperacion = entradaScanner2.nextLine();
         permisosOrtogados(usuarios, Integer.parseInt(numeroOperacion)); 
         
      
        
     /*{
          System.out.println("No autorizado"); 
      }*/
}
}
