package edu.uag.iidis.scec.excepciones;

/**
 * Esta excepcion es usada para atrapar fallas 
 * en las reglas de negocio.
 *
 */
public class ExcepcionNegocio
        extends RuntimeException {

    public ExcepcionNegocio() {
    }

    public ExcepcionNegocio(String mensaje) {
        super(mensaje);
    }

    public ExcepcionNegocio(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public ExcepcionNegocio(Throwable causa) {
        super(causa);
    }
}
