package edu.uag.iidis.scec.modelo;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;


/**
 * Clase base para los objetos que forman parte
 * del modelo del dominio.  
 * Esto es para los métodos <code>toString</code>, 
 * <code>equals</code> y <code>hashCode</code>.
 *
 * <p>
 * <a href="BaseObject.java.html"><i>Ver código fuente</i></a>
 * </p>
 *
 * @author <a href="mailto:vramos@uag.mx">Victor Ramos</a>
 * @version 1.0
 */
public class ClaseBase implements Serializable {

    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }


    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }


    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
