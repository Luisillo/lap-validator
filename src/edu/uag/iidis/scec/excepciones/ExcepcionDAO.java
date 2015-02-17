package edu.uag.iidis.scec.excepciones;

/**
 * Esta excepcion es usada para marcar violaciones.
 * de acceso (autorizacion)
 */
public class ExcepcionDAO
        extends RuntimeException {

    public ExcepcionDAO() {
    }

    public ExcepcionDAO(String mensaje) {
        super(mensaje);
    }

    public ExcepcionDAO(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public ExcepcionDAO(Throwable causa) {
        super(causa);
    }
}
