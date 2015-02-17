package edu.uag.iidis.scec.excepciones;

/**
 * Esta excepcion es usada para marcar violaciones.
 * de acceso (autorizacion)
 */
public class ExcepcionServicio
        extends RuntimeException {

    public ExcepcionServicio() {
    }

    public ExcepcionServicio(String mensaje) {
        super(mensaje);
    }

    public ExcepcionServicio(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public ExcepcionServicio(Throwable causa) {
        super(causa);
    }
}
