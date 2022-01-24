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

    public Operaciones(int IdOperacion, String descOperacion, boolean permitido) {
        this.IdOperacion = IdOperacion;
        this.descOperacion = descOperacion;
        this.permitido = permitido;
    }


    
    
    /**
     * @return the IdOperacion
     */
    public int getIdOperacion() {
        return IdOperacion;
    }

    /**
     * @param IdOperacion the IdOperacion to set
     */
    public void setIdOperacion(int IdOperacion) {
        this.IdOperacion = IdOperacion;
    }

    /**
     * @return the descOperacion
     */
    public String getDescOperacion() {
        return descOperacion;
    }

    /**
     * @param descOperacion the descOperacion to set
     */
    public void setDescOperacion(String descOperacion) {
        this.descOperacion = descOperacion;
    }

    /**
     * @return the permitido
     */
    public boolean isPermitido() {
        return permitido;
    }

    /**
     * @param permitido the permitido to set
     */
    public void setPermitido(boolean permitido) {
        this.permitido = permitido;
    }
    
}
