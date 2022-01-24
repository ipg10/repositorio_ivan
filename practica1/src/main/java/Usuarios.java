/*
clase operacion 
clase Roles
 */

/**
 *
 * @author IVAN
 */
/* atributos
nombre 
contraseña 
funcionalidad
Roles
*/


public class Usuarios {
    private String nombre;
    private Roles rol;
    

 public Usuarios(String nombre, Roles rol){
        this.nombre = nombre;
        this.rol = rol;
 }
 public void setNombre(String nombre){
     this.nombre = nombre;
 }
 
 public String getNombre(){
     return nombre;
 }
 
 public void setRol(Roles rol){
     this.rol= rol;
 }
 
 public Roles getRol(){
     return rol;
 }
 
}


