package edu.uag.iidis.scec.vista;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;


/**
 * Form bean para el registro de una nueva persona.
 *
 * @author Victor Ramos
 */
public final class FormaNuevoLogin
        extends ValidatorForm {

    private Long id;
    private String nombrePrefijo;
    private String claveAcceso;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setnombrePrefijo(String nombrePrefijo) {
        this.nombrePrefijo=nombrePrefijo;
    }

    public String getnombrePrefijo() {
        return (this.nombrePrefijo);
    }


    public void setclaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }

    public String getclaveAcceso() {
        return (this.claveAcceso);
    }


    public void reset(ActionMapping mapping,
                      HttpServletRequest request) {
	    nombrePrefijo=null;
        claveAcceso=null;
    }


    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {

        // Ejecuta las validaciones proporcionadas por Struts-Validator
        ActionErrors errores = super.validate(mapping, request);

        // Validaciones no cubiertas por Struts-Validator

        return errores;

    }

}
