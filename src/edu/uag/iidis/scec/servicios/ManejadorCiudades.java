package edu.uag.iidis.scec.servicios;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uag.iidis.scec.modelo.Ciudad;
import edu.uag.iidis.scec.excepciones.*;
import edu.uag.iidis.scec.persistencia.CiudadDAO;
import edu.uag.iidis.scec.persistencia.hibernate.*;

public class ManejadorCiudades {
    private Log log = LogFactory.getLog(ManejadorRoles.class);
    private CiudadDAO dao;

    public ManejadorCiudades() {
        dao = new CiudadDAO();
    }


    public Collection listarCiudades() {
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

    public Collection buscarCiudades(String ciudadBuscar) {
        Collection resultado;

        if (log.isDebugEnabled()) {
            log.debug(">guardarUsuario(usuario)");
        }

        try {
            HibernateUtil.beginTransaction();
            resultado = dao.buscarPorNombre(ciudadBuscar);
            HibernateUtil.commitTransaction();
            return resultado;         
        } catch (ExcepcionInfraestructura e) {
            HibernateUtil.rollbackTransaction();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    public void eliminarCiudad(Long id) {
        if (log.isDebugEnabled()) {
            log.debug(">eliminarCiudad(ciudad)");
        }
        try {
            HibernateUtil.beginTransaction();           
            Ciudad ciudad = dao.buscarPorId(id, true);
            if (ciudad != null) {
              dao.hazTransitorio(ciudad);
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

    public int crearCiudad(Ciudad ciudad) {

        int resultado;

        if (log.isDebugEnabled()) {
            log.debug(">guardarCiudad(ciudad)");
        }

        try {
            HibernateUtil.beginTransaction();           
            
            if (dao.existeCiudad(ciudad.getNombre())) {
               resultado = 1; // Excepción. El nombre de ciudad ya existe
            } else {

               dao.hazPersistente(ciudad);

               resultado = 0; // Exito. El ciudad se creo satisfactoriamente.
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
