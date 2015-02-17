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
public final class FormaListadoGentesOrdenar
        extends ValidatorForm {

    private Collection ciudades;
    private Collection gentes;
    private int contador;
    private String ordenar;
    private int tipo;

    public void setCiudades(Collection ciudades) {
        this.ciudades = ciudades;
    }

    public Collection getCiudades() {
        return (this.ciudades);
    }

    public void setGentes(Collection gentes) {
        this.gentes = gentes;
        if (gentes != null) {
          this.contador = gentes.size();
        } else
          this.contador = -1;
    }

    public Collection getGentes() {
        return (this.gentes);
    }

    public void setOrdenar(String ordenar) {
        this.ordenar = ordenar;
    }

    public String getOrdenar() {
        return (this.ordenar);
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return (this.tipo);
    }

    public void reset(ActionMapping mapping,
                      HttpServletRequest request) {
        contador=0;
        ordenar=null;
        tipo=0;
    }


    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {

        // Ejecuta las validaciones proporcionadas por Struts-Validator
        ActionErrors errores = super.validate(mapping, request);

        // Validaciones no cubiertas por Struts-Validator

        return errores;

    }
}
