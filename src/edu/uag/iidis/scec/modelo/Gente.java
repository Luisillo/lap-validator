package edu.uag.iidis.scec.modelo;

import java.io.Serializable;
import java.util.*;


/**
 * Esta clase es usada para representar un rol de un
 * usuario. 
 *
 * <p><a href="Gente.java.html"><i>Ver código fuente</i></a></p>
 *
 * @author <a href="mailto:vramos@uag.mx">Victor Ramos</a>
 * @version 1.0
 */
public class Gente extends ClaseBase 
        implements Serializable {

    private Long id;
    private Long idCiudad;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String claveAcceso;

    public Gente() {
    }

    public Gente(Long id){
        this.id = id;
    }

    public Gente(String nombres, String apellidos, String direccion, String telefono, Long idCiudad){
        this.nombres=nombres;
        this.apellidos=apellidos;
        this.idCiudad=idCiudad;
        this.direccion=direccion;
        this.telefono=telefono;
    }

    /**
     * Regresa el id del rol.
     * @return Long
     */
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // ID Ciudad
    public Long getidCiudad() {
        return this.idCiudad;
    }

    public void setidCiudad(Long id) {
        this.idCiudad = id;
    }
    /**
     * Regresa el nombres del rol.
     * @return String
     */
    public String getNombres() {
        return this.nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    
    /**
     * Regresa la descripción del rol.
     * @return String
     */
    public String getApellidos() {
        return this.apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Regresa la descripción del rol.
     * @return String
     */
    public String getDireccion() {
        return this.direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Regresa la descripción del rol.
     * @return String
     */
    public String getTelefono() {
        return this.telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getclaveAcceso() {
        return this.claveAcceso;
    }
    public void setclaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }
}
