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
public final class FormaNuevoRol
        extends ValidatorForm {

    private String marca;
    private String color;
	private String placas;
	private String propietario;

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMarca() {
        return (this.marca);
    }


    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return (this.color);
    }
	
	public void setPlacas(String placas){
	
		this.placas = placas;
	}
	
	public String getPlacas(){
	
		return placas;
	}
	
	public void setPropietario(String propietario){
	
		this.propietario = propietario;
	}
	
	public String getPropietario(){
	
		return propietario;
	}


    public void reset(ActionMapping mapping,
                      HttpServletRequest request) {
        marca=null;
        color=null;
		placas=null;
		propietario=null;
    }


    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {

        // Ejecuta las validaciones proporcionadas por Struts-Validator
        ActionErrors errores = super.validate(mapping, request);

        // Validaciones no cubiertas por Struts-Validator

        return errores;

    }

}
