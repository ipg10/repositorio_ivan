

/** funcionalidad de seguridad
 * validar seguridad 
 * cosa segura 
 * 
 * verifiar  autorizacion usuario para hacer operacion x 
 * @author IVAN
 */
public class Aplicacion {
    //usuario
    public void agregar_contacto(){
        //??Usuario --> Tiene permisos para ejecutar eso??
        //asistente.
        System.err.println("Usted puede agregar contacto");
    }
    public void modificar_contacto(){
        System.err.println("Usted puede modificar contacto");
    }
    public void eliminar_contacto(){
        System.err.println("Usted puede eliminar contacto");
    }
    public void listar_contacto(){
        System.err.println("Usted puede listar contacto");
    }
    public void agregar_usuario(){
        // ¿usuario? --> variable global 
        // if usuario puede hacer operacion (agregar usuario) --> ok
        // else --> error, no autorizado 
        System.err.println("Usted puede agregar usuario");
    }
    
    
}
    
    
