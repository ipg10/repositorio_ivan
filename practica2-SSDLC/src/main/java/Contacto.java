/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fenix
 */
public class Contacto {
    
    private int id;
    private String nombre;
    private String apellido;
    private String notaSistema;
    private boolean corrupto;

    /**
     * Constructor
     * @param id
     * @param nombre
     * @param apellido
     * @param notaSistema 
     * @param corrupto 
     */
    public Contacto(int id, String nombre, String apellido, String notaSistema,boolean corrupto) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.notaSistema = notaSistema;
        this.corrupto = corrupto;
    }

    
     
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNotaSistema() {
        return notaSistema;
    }

    public void setNotaSistema(String notaSistema) {
        this.notaSistema = notaSistema;
    }

    public boolean isCorrupto() {
        return corrupto;
    }

    public void setCorrupto(boolean corrupto) {
        this.corrupto = corrupto;
    }

    @Override
    public String toString() {
        return "Contacto{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", notaSistema=" + notaSistema + ", corrupto=" + corrupto + '}';
    }
    
    
    
    
}
