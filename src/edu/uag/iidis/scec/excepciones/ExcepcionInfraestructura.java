package edu.uag.iidis.scec.excepciones;

/**
 * Esta excepcion es usada para atrapar fallas 
 * en la infraestructura del sistema.
 *
 */
public class ExcepcionInfraestructura
        extends RuntimeException {

    public ExcepcionInfraestructura() {
    }

    public ExcepcionInfraestructura(String mensaje) {
        super(mensaje);
    }

    public ExcepcionInfraestructura(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public ExcepcionInfraestructura(Throwable causa) {
        super(causa);
    }
}
