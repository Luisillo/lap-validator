package edu.uag.iidis.scec.vista;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;


/**
 * Form bean para el registro de una nueva persona.
 *
 * @author Victor Ramos
 */
public final class FormaNuevaPersona
        extends ValidatorForm {

    private String prefijo;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String posfijo;
    private String iniciales;
    private String nombreUsuario;
    private String claveAcceso;
    private String claveAccesoConfirmacion;
	private Collection ciudades;
	private int contador;

    public void setPrefijo(String prefijo) {
        this.prefijo=prefijo;
    }

    public String getPrefijo() {
        return (this.prefijo);
    }


    public void setCiudades(Collection ciudades) {
        this.ciudades = ciudades;
        if (ciudades != null) {
          this.contador = ciudades.size();
        } else
          this.contador = -1;
    }

    public Collection getCiudades() {
        return (this.ciudades);
    }

    public int getContador() {
        return (this.contador);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return (this.nombre);
    }


    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoPaterno() {
        return (this.apellidoPaterno);
    }


    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoMaterno() {
        return (this.apellidoMaterno);
    }


    public void setPostfijo(String posfijo) {
        this.posfijo = posfijo;
    }

    public String getPosfijo() {
        return (this.posfijo);
    }


    public void setIniciales(String iniciales) {
        this.iniciales = iniciales;
    }

    public String getIniciales() {
        return (this.iniciales);
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreUsuario() {
        return (this.nombreUsuario);
    }


    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso=claveAcceso;
    }

    public String getClaveAcceso() {
        return (this.claveAcceso);
    }


    public void setClaveAccesoConfirmacion(String claveAccesoConfirmacion) {
        this.claveAccesoConfirmacion=claveAccesoConfirmacion;
    }

    public String getClaveAccesoConfirmacion() {
        return (this.claveAccesoConfirmacion);
    }


    public void reset(ActionMapping mapping,
                      HttpServletRequest request) {
	prefijo=null;
        nombre=null;
        apellidoPaterno=null;
        apellidoMaterno=null;
        posfijo=null;
        iniciales=null;
        nombreUsuario=null;
        claveAcceso=null;
        claveAccesoConfirmacion=null;
    }


    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {

        // Ejecuta las validaciones proporcionadas por Struts-Validator
        ActionErrors errores = super.validate(mapping, request);

        // Validaciones no cubiertas por Struts-Validator

        return errores;

    }

}
