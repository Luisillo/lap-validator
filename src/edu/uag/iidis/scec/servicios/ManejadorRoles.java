package edu.uag.iidis.scec.servicios;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uag.iidis.scec.modelo.Rol;
import edu.uag.iidis.scec.excepciones.*;
import edu.uag.iidis.scec.persistencia.RolDAO;
import edu.uag.iidis.scec.persistencia.hibernate.*;

public class ManejadorRoles {
    private Log log = LogFactory.getLog(ManejadorRoles.class);
    private RolDAO dao;

    public ManejadorRoles() {
        dao = new RolDAO();
    }


    public Collection listarRoles() {
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

    public void eliminarRol(Long id) {
        if (log.isDebugEnabled()) {
            log.debug(">eliminarRol(rol)");
        }
        try {
            HibernateUtil.beginTransaction();           
            Rol rol = dao.buscarPorId(id, true);
            if (rol != null) {
              dao.hazTransitorio(rol);
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

    public int crearRol(Rol rol) {

        int resultado;

        if (log.isDebugEnabled()) {
            log.debug(">guardarRol(rol)");
        }

        try {
            HibernateUtil.beginTransaction();           
            
            if (dao.existeRol(rol.getNombre())) {
               resultado = 1; // Excepción. El nombre de rol ya existe
            } else {

               dao.hazPersistente(rol);

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
