package edu.uag.iidis.scec.servicios;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uag.iidis.scec.modelo.Estado;
import edu.uag.iidis.scec.excepciones.*;
import edu.uag.iidis.scec.persistencia.EstadoDAO;
import edu.uag.iidis.scec.persistencia.hibernate.*;

public class ManejadorEstados {
    private Log log = LogFactory.getLog(ManejadorEstados.class);
    private EstadoDAO dao;

    public ManejadorEstados() {
        dao = new EstadoDAO();
    }


    public Collection listarEstados() {
        Collection resultado;

        if (log.isDebugEnabled()) {
            log.debug(">guardarUsuario(usuario)");
        }

        try {
            HibernateUtil.beginTransaction();
            resultado = dao.buscarTodos();
            HibernateUtil.commitTransaction();
            return resultado;         
        } catch (ExcepcionInfraestructura e) {
            HibernateUtil.rollbackTransaction();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    public void eliminarEstado(Long id) {
        if (log.isDebugEnabled()) {
            log.debug(">eliminarEstado(estado)");
        }
        try {
            HibernateUtil.beginTransaction();           
            Estado estado = dao.buscarPorId(id, true);
            if (estado != null) {
              dao.hazTransitorio(estado);
            }
            HibernateUtil.commitTransaction();
        } catch (ExcepcionInfraestructura e) {
            HibernateUtil.rollbackTransaction();
            if (log.isWarnEnabled()) {
                log.warn("<ExcepcionInfraestructura");
            }
        } finally {
            HibernateUtil.closeSession();
        }

    }

    public int crearEstado(Estado estado) {

        int resultado;

        if (log.isDebugEnabled()) {
            log.debug(">guardarEstado(estado)");
        }

        try {
            HibernateUtil.beginTransaction();           
            
            if (dao.existeEstado(estado.getNombre())) {
               resultado = 1; // Excepción. El nombre de rol ya existe
            } else {

               dao.hazPersistente(estado);

               resultado = 0; // Exito. El rol se creo satisfactoriamente.
            }

            HibernateUtil.commitTransaction();

        } catch (ExcepcionInfraestructura e) {
            HibernateUtil.rollbackTransaction();

            if (log.isWarnEnabled()) {
                log.warn("<ExcepcionInfraestructura");
            }
            resultado = 2;    // Excepción. Falla en la infraestructura
        } finally {
            HibernateUtil.closeSession();
        }
        return resultado;
    }    
}
