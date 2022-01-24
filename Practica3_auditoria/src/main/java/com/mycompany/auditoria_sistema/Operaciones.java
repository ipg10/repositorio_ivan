package com.mycompany.auditoria_sistema;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Agregar un contacto : administrador
 *                       gestor
 * Modificar un contacto : administrador
 *                         gestor
 * Eliminar un contacto : Administrador
 * 
 * Listar los contactos : administrador
 *                        gestor
 *                        Asistente
 * Agregar usuarios y asociacion de un rol a un usuario : administrador
 * 
 */
public class Operaciones {
    private int IdOperacion;
    private String descOperacion;
    private boolean permitido;

    /**
     * Constructor de operaciones con todos los campos
     * @param IdOperacion
     * @param descOperacion
     * @param permitido 
     */
    public Operaciones(int IdOperacion, String descOperacion, boolean permitido) {
        this.IdOperacion = IdOperacion;
        this.descOperacion = descOperacion;
        this.permitido = permitido;
    }

    
    /**
     * getters and setters 
     */
    public int getIdOperacion() {
        return IdOperacion;
    }

    public void setIdOperacion(int IdOperacion) {
        this.IdOperacion = IdOperacion;
    }

    public String getDescOperacion() {
        return descOperacion;
    }

    public void setDescOperacion(String descOperacion) {
        this.descOperacion = descOperacion;
    }

    public boolean isPermitido() {
        return permitido;
    }

    public void setPermitido(boolean permitido) {
        this.permitido = permitido;
    }

   
    
  
    
}
