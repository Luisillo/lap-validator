package edu.uag.iidis.scec.vista;

import java.util.Collection;
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
public final class FormaNuevoGenteCiudades
        extends ValidatorForm {

    private Collection ciudades;
    private Collection estados;
    private int contador;
    private String ciudadBuscar;

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

    public void setEstados(Collection estados) {
        this.estados = estados;
        if (estados != null) {
          this.contador = estados.size();
        } else
          this.contador = -1;
    }

    public Collection getEstados() {
        return (this.estados);
    }

    public void setciudadBuscar(String ciudadBuscar) {
        this.ciudadBuscar = ciudadBuscar;
    }

    public String getciudadBuscar() {
        return (this.ciudadBuscar);
    }


    public void reset(ActionMapping mapping,
                      HttpServletRequest request) {
        ciudadBuscar=null;
    }


    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {

        // Ejecuta las validaciones proporcionadas por Struts-Validator
        ActionErrors errores = super.validate(mapping, request);

        // Validaciones no cubiertas por Struts-Validator

        return errores;

    }

}
