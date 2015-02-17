package edu.uag.iidis.scec.servicios;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uag.iidis.scec.modelo.Gente;
import edu.uag.iidis.scec.excepciones.*;
import edu.uag.iidis.scec.persistencia.GenteDAO;
import edu.uag.iidis.scec.persistencia.hibernate.*;

public class ManejadorGentes {
    private Log log = LogFactory.getLog(ManejadorRoles.class);
    private GenteDAO dao;

    public ManejadorGentes() {
        dao = new GenteDAO();
    }


    public Collection listarGentes() {
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

    public Collection listarGentesOrd(String tipo, int tipoInt) {

        Collection resultado;

        if (log.isDebugEnabled()) {
            log.debug(">guardarUsuario(usuario)");
        }

        try {
            HibernateUtil.beginTransaction();
            resultado = dao.ordenarPor(tipo, tipoInt);
            HibernateUtil.commitTransaction();
            return resultado;         
        } catch (ExcepcionInfraestructura e) {
            HibernateUtil.rollbackTransaction();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    public void eliminarGente(Long id) {
        if (log.isDebugEnabled()) {
            log.debug(">eliminarGente(gente)");
        }
        try {
            HibernateUtil.beginTransaction();           
            Gente gente = dao.buscarPorId(id, true);
            if (gente != null) {
              dao.hazTransitorio(gente);
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

    public int crearGente(Gente gente) {

        int resultado;

        if (log.isDebugEnabled()) {
            log.debug(">guardarGente(gente)");
        }

        try {
            HibernateUtil.beginTransaction();           
            
            if (dao.existeGente(gente.getNombres())) {
               resultado = 1; // Excepción. El nombre de gente ya existe
            } else {

               dao.hazPersistente(gente);

               resultado = 0; // Exito. El gente se creo satisfactoriamente.
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
