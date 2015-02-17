package edu.uag.iidis.scec.modelo;

import java.io.Serializable;

/**
 * @author Victor Ramos
 */
public class NombrePersona
        implements Serializable {

    private String prefijo;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String posfijo;
    private String iniciales;

    public NombrePersona() {}


    public NombrePersona(String prefijo,
                         String nombre,
                         String apellidoPaterno,
                         String apellidoMaterno,
                         String posfijo,
                         String iniciales) {
        this.prefijo = prefijo;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.posfijo = posfijo;
        this.iniciales = iniciales;
    }


    public String getPrefijo() {
        return prefijo;
    }


    public void setPrefijo(String prefijo) {
        this.prefijo = prefijo;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getApellidoPaterno() {
        return apellidoPaterno;
    }


    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }


    public String getApellidoMaterno() {
        return apellidoMaterno;
    }


    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }


    public String getIniciales() {
        return iniciales;
    }


    public void setIniciales(String iniciales) {
        this.iniciales = iniciales;
    }


    public String getPosfijo() {
        return posfijo;
    }


    public void setPosfijo(String posfijo) {
        this.posfijo = posfijo;
    }


    public String getNombreCompleto() {
        
        return (nombre + " " + 
               apellidoPaterno + " " + 
               apellidoMaterno);
    }

}
