package com.mycompany.auditoria_sistema;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author IVAN
 */
public class Principal {

    private static ArrayList<Operaciones> listaOperaciones;
    private static Usuario usuario; 
    private static List<Contacto> listaContacto = new ArrayList<>();
    private static Contacto primerContacto = new Contacto(1, "Juan", "Rojas");
    private static Auditar auditar = new Auditar();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            
            /*
            hacemos las declaraciones y especiamente inicializamos la clase Auditar para llamar a los metodos de 
            creacion, escritura y cierre del archivo  .txt con el resultado de la auditoria
            */
            
            //se crea el archivo en blanco en la ruta especificada
            auditar.iniciarFichero();
            //se crea la linea cabecera con los titulos de la pregunta
            auditar.guardarTitulosFichero();
            //se crea una linea con los parametros necesarios este metodo se llama cada vez que se guarda 
            //una linea con los datos de la auditoria
            auditar.guardarLineaFichero(null, "Inicio del sistema", "Ejecutado", "Iniciando sistema"); 
             
            String entradaTeclado = "";
            String entradaTecladorol = "";

            System.out.println("Nombre del Usuario:");
            Scanner entradaEscaner = new Scanner(System.in); 
            entradaTeclado = entradaEscaner.nextLine(); 
            System.out.println("Nombre del Rol:");
            Scanner entradaEscaner2 = new Scanner(System.in); 
            entradaTecladorol = entradaEscaner2.nextLine(); 
            System.err.println("-----------------------------------------------");
            usuario = new Usuario(entradaTeclado, retornoderol(entradaTecladorol));
             //creamos el usuario y guardamos la auditoria
             //se crea una linea con los parametros de este metodo que se llama cada vez que se guarda 
            //una linea con los datos de la auditoria
            auditar.guardarLineaFichero(usuario, "Creacion de usuario", "Ejecutado", usuario.getRol().getNombre());

            
       
            System.err.println("Nombre de usuario: " + usuario.getNombre() + "  Rol " + usuario.getRol().getNombre());
            System.out.println("Tipee el numero de la operacion a realizar");
            //se recorre la lista
            for (Operaciones op : listaOperaciones) {
                System.out.println("Nª de operacion:" + op.getIdOperacion() + " Tipo de Operacion:" + op.getDescOperacion());
            }
            
            //se lee  por teclado la repuesta del usuario
            Scanner entradaEscaner3 = new Scanner(System.in); 
            int numOperacion = Integer.valueOf(entradaEscaner3.nextLine());    
          
            // metodo para saber si el usuario puede o no realizar la accion
            permisosOrtogados(usuario, numOperacion);
            
            //se guarda el usuario en sesion que esta saliendo del sistema SALIDA DEL SISTEMA
            auditar.guardarLineaFichero(usuario, "Salir del sistema", "Ejecutado", usuario.getRol().getNombre());
            //se cierra el fichero que estaba abierto 
            auditar.cerrarFichero();

        } catch (NullPointerException e) { 
            //ocurrio un error se guarda la linea correspondiente y se cierra el fichero abierto
            System.err.println("No autorizado"); 
            auditar.guardarLineaFichero(null, "No autorizado", "No autorizado", usuario.getRol().getNombre());
            auditar.cerrarFichero();
        }

    }

    public static void agregar_contacto() {
         
        //creamos un nuevo contacto 
        Contacto nuevo = new Contacto(2, "Maria", "Lopez");
        //se guarda la linea correspondiente pasando los parametros necesarios
        auditar.guardarLineaFichero(nuevo, "Agregar Contacto", "Ejecutado", usuario.getRol().getNombre());
        
        System.out.println("Agregar contacto");
    }

    //modificar contacto
    public static void modificar_contacto() {          
        //a un contacto creado se modifica para agregarlo luego al archivo de la auditoria
        primerContacto.setNombre("Rosa");
        //se guarda la linea correspondiente pasando los parametros necesarios
        auditar.guardarLineaFichero(primerContacto, "Modificar Contacto", "Ejecutado", usuario.getRol().getNombre());
    
        System.out.println("Modificar contacto");
    }

    
    //eliminar contacto
    public static void eliminar_contacto() {          
        //se guarda la linea correspondiente pasando los parametros necesarios elegimos guardar el que se supone se esta eliminado 
        //pero tambien podrias mandar el usuario que lo esta borrando me refiero a los objetos
        auditar.guardarLineaFichero(primerContacto, "Eliminar Contacto", "Ejecutado", usuario.getRol().getNombre());
          //se lanza por consola un mensaje
        System.out.println("Eliminar contacto"); 
    }

    //listar contacto
    public static void listar_contacto() {          
        listaContacto.add(primerContacto);
        //se guarda la linea correspondiente pasando los parametros necesarios 
        //en este caso pasamos el array de la contacto como objecto
        auditar.guardarLineaFichero(listaContacto, "Listar Contacto", "Ejecutado", usuario.getRol().getNombre());
          
        System.out.println("Listar contacto");
    }


    public static void agregar_usuario() {          
        //creamos el nuevo usuario para luego guardarlo en la linea de auditoria
        Usuario usuario2 = new Usuario("Raul Lopez", retornoderol("Administrador"));
        //se guarda la linea correspondiente pasando los parametros necesarios, agregamos el objecto de usuario nuevo
        auditar.guardarLineaFichero(usuario2, "Agregar Usuario", "Ejecutado", usuario.getRol().getNombre());
          
        System.out.println("Agregar usuario");
    }

    /**
     * Administrador : agregar contacto modificar contacto Eliminar contacto
     * Listar contacto Agregar Usuario
     *
     * Gestor : agregar contacto modificar contacto Listar contacto
     *
     * Asistente: Listar contacto
     *
     */
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
        
        //se agrega la linea a la auditoria se manda el objeto rol que sera asigando al usuario
        auditar.guardarLineaFichero(rol, "Asignar    rol ", "Ejecutado", "Usuario");
        return rol;
    }

    
    /**
     * recibe parametros que es un usuario (el actual ) y el id que es la accion a hacer (crear , modificar, eliminar etc)
     * @param usuario
     * @param rol_id //es la operacion a realizar 
     */
        public static void permisosOrtogados(Usuario usuario , int rol_id) {
        //iteramos la lista de operaciones    
        for (Operaciones rol : usuario.getRol().getListaOperaciones()) { 
            //verificamos que el id de la operacion sea igual a la del rol y el rol sea igual a 1 es agreagar
            if ((rol.getIdOperacion() == rol_id) && (rol_id == 1)) {
                //preguntamos si tiene permiso
                if(rol.isPermitido() == true){
                    //se ejecuta si tiene permiso
                    agregar_contacto();
                } else {
                    //si no tiene permiso se ejecuta este mensaje y se guarda la linea de auditoria
                    System.err.println("No posee permiso");
                    //se guarda la linea  y un mensaje diciendo que no tiene persimo por lo cual fallo
                    auditar.guardarLineaFichero(usuario, "Agregar contacto", "Error, No posee permiso", usuario.getRol().getNombre());
                }     
                //se detiene el for
                break;
                //se repite lo mismo de arriba pero se pregunta si es igual a la operacion 2 y asi susecivamente recordar que dos es modificar contacto
            } else if ((rol.getIdOperacion() == rol_id) && (rol_id == 2)) {
                if(rol.isPermitido() == true){
                     modificar_contacto();
                } else {
                    System.err.println("No posee permiso");
                    auditar.guardarLineaFichero(usuario, "Modificar contacto", "Error, No posee permiso", usuario.getRol().getNombre());
                }                 
                break;
            } else if ((rol.getIdOperacion() == rol_id) && ((rol_id == 3))) {
                if(rol.isPermitido() == true){
                     eliminar_contacto();
                } else {
                    System.err.println("No posee permiso");
                    auditar.guardarLineaFichero(usuario, "Eliminar contacto", "Error, No posee permiso", usuario.getRol().getNombre());
                }                  
                break;
            } else if ((rol.getIdOperacion() == rol_id) && ((rol_id == 4))) {
                if(rol.isPermitido() == true){
                    listar_contacto();
                } else {
                    System.err.println("No posee permiso");
                    auditar.guardarLineaFichero(usuario, "Listar contacto", "Error, No posee permiso", usuario.getRol().getNombre());
                }                     
                break;
            } else if ((rol.getIdOperacion() == rol_id) && ((rol_id == 5))) {
                 if(rol.isPermitido() == true){
                    agregar_usuario();
                } else {
                    System.err.println("No posee permiso");
                    auditar.guardarLineaFichero(usuario, "Agregar Usuario", "Error, No posee permiso", usuario.getRol().getNombre());
                }   
                break;
            }
        }
    }
        
        
//    public static void permisosOrtogados(Usuario usuario) {
//        for (Operaciones rol : usuario.getRol().getListaOperaciones()) {
//            if (rol.getIdOperacion() == 1 && rol.isPermitido() == true) {
//                agregar_contacto();
//            } else if (rol.getIdOperacion() == 2 && rol.isPermitido() == true) {
//                modificar_contacto();
//            } else if (rol.getIdOperacion() == 3 && rol.isPermitido() == true) {
//                eliminar_contacto();
//            } else if (rol.getIdOperacion() == 4 && rol.isPermitido() == true) {
//                listar_contacto();
//            } else if (rol.getIdOperacion() == 5 && rol.isPermitido() == true) {
//                agregar_usuario();
//            }
//        }
//    }

}
