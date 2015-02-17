package edu.uag.iidis.scec.excepciones;

/**
 * Esta excepcion es usada para marcar violaciones.
 * de acceso (autorizacion)
 */
public class ExcepcionSeguridad
        extends RuntimeException {

    public ExcepcionSeguridad() {
    }

    public ExcepcionSeguridad(String mensaje) {
        super(mensaje);
    }

    public ExcepcionSeguridad(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public ExcepcionSeguridad(Throwable causa) {
        super(causa);
    }
}
