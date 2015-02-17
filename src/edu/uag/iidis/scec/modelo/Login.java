package edu.uag.iidis.scec.modelo;

import java.io.Serializable;
import java.util.*;


/**
 * Esta clase es usada para representar un rol de un
 * usuario. 
 *
 * <p><a href="Login.java.html"><i>Ver código fuente</i></a></p>
 *
 * @author <a href="mailto:vramos@uag.mx">Victor Ramos</a>
 * @version 1.0
 */
public class Login extends ClaseBase 
        implements Serializable {

    private Long id;
    private String nombrePrefijo;
    private String claveAcceso;

    public Login() {
    }

    public Login(Long id){
        this.id = id;
    }

    public Login(String nombrePrefijo, String claveAcceso){
        this.nombrePrefijo=nombrePrefijo;
        this.claveAcceso=claveAcceso;
    }
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Regresa el nombrePrefijo del rol.
     * @return String
     */
    public String getnombrePrefijo() {
        return this.nombrePrefijo;
    }
    public void setnombrePrefijo(String nombrePrefijo) {
        this.nombrePrefijo = nombrePrefijo;
    }

    
    /**
     * Regresa la descripción del rol.
     * @return String
     */
    public String getclaveAcceso() {
        return this.claveAcceso;
    }
    public void setclaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }

}
