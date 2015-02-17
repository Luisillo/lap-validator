package edu.uag.iidis.scec.modelo;

import java.io.Serializable;

/**
 * @author Victor Ramos
 */
public class Credencial
        implements Serializable {

    private String nombreUsuario;
    private String claveAcceso;

    public Credencial() {}


    public Credencial(String nombreUsuario,
                      String claveAcceso) {
        this.nombreUsuario = nombreUsuario;
        this.claveAcceso = claveAcceso;
    }


    public String getNombreUsuario() {
        return nombreUsuario;
    }


    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }


    public String getClaveAcceso() {
        return claveAcceso;
    }


    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }

}
