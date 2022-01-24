
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Administrador : agregar contacto
 *                 modificar contacto
 *                 Eliminar contacto 
 *                 Listar contacto 
 *                 Agregar Usuario
 * 
 * Gestor :       agregar contacto
 *                modificar contacto
 *                Listar contacto
 * 
 * Asistente:     Listar contacto 
 * 
 * 
 * @author IVAN
 */
public class Roles {
    private String nombre;
    private List<Operaciones> listaOperaciones;
    
   public Roles (String nombre, List<Operaciones> listaOperaciones) {
        this.nombre = nombre;
        this.listaOperaciones = listaOperaciones;
    }
   
  //getters y setters

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the listaOperaciones
     */
    public List<Operaciones> getListaOperaciones() {
        return listaOperaciones;
    }

    /**
     * @param listaOperaciones the listaOperaciones to set
     */
    public void setListaOperaciones(List<Operaciones> listaOperaciones) {
        this.listaOperaciones = listaOperaciones;
    }
   
   
    
}

 
