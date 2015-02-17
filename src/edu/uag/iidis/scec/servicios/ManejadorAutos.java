package edu.uag.iidis.scec.servicios;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uag.iidis.scec.modelo.Auto;
import edu.uag.iidis.scec.excepciones.*;
import edu.uag.iidis.scec.persistencia.AutoDAO;
import edu.uag.iidis.scec.persistencia.hibernate.*;

public class ManejadorAutos {
    private Log log = LogFactory.getLog(ManejadorAutos.class);
    private AutoDAO dao;

    public ManejadorAutos() {
        dao = new AutoDAO();
    }


    public Collection listarAutos() {
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

    public void eliminarAuto(Long id) {
        if (log.isDebugEnabled()) {
            log.debug(">eliminarAuto(auto)");
        }
        try {
            HibernateUtil.beginTransaction();           
            Auto auto = dao.buscarPorId(id, true);
            if (auto != null) {
              dao.hazTransitorio(auto);
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

    public int crearAuto(Auto auto) {

        int resultado;

        if (log.isDebugEnabled()) {
            log.debug(">guardarEstado(auto)");
        }

        try {
            HibernateUtil.beginTransaction();           
            
            if (dao.existeAuto(auto.getPlacas())) {
               resultado = 1; // Excepción. El nombre de rol ya existe
            } else {

               dao.hazPersistente(auto);

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
