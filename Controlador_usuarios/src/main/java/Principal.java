
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

    public static void permisosOrtogados(Usuarios usuarios) {
        for (Operaciones rol : usuarios.getRol().getListaOperaciones()) {
            if (rol.getIdOperacion() == 1 && rol.isPermitido() == true) {
                aplicacion.agregar_contacto();
            } else if (rol.getIdOperacion() == 2 && rol.isPermitido() == true) {
                aplicacion.modificar_contacto();
            } else if (rol.getIdOperacion() == 3 && rol.isPermitido() == true) {
                aplicacion.eliminar_contacto();
            } else if (rol.getIdOperacion() == 4 && rol.isPermitido() == true) {
                aplicacion.listar_contacto();
            } else if (rol.getIdOperacion() == 5 && rol.isPermitido() == true) {
                aplicacion.agregar_usuario();
            }
        }
    }
    
    public static void main(String args[]){
      
        String entradaTecladousuario = "";
        String entradaTecladorol = "";
        
         System.out.println("Nombre del Usuario:");            
         Scanner entradaScanner = new Scanner(System.in);
         entradaTecladousuario = entradaScanner.nextLine();
         
         System.out.println("Nombre del Rol:");
         Scanner entradaScanner2 = new Scanner(System.in); 
         entradaTecladorol = entradaScanner2.nextLine();
         
         System.out.println("-----------------------------");
         usuarios = new Usuarios(entradaTecladousuario,retornoderol(entradaTecladorol));
         System.out.println("Nombre de usuario: " + usuarios.getNombre() + "  Rol:  " + usuarios.getRol().getNombre());
            permisosOrtogados(usuarios);
         
      
        
     /*{
          System.out.println("No autorizado"); 
      }*/
}
}
