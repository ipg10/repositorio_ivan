package com.mycompany.auditoria_sistema;

/*
clase operacion 
clase roles
 */

/**
 *
 * @author IVAN
 */
/* atributos
nombre 
contraseña 
funcionalidad
roles
*/


public class Usuario{
    public String nombre;
    public Roles rol;

    /**
     * Constructo usuario
     * @param nombre
     * @param rol 
     */
    public Usuario(String nombre, Roles rol) {
        this.nombre = nombre;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public Roles getRol() {
        return rol;
    }
 
 
}


