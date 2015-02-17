package edu.uag.iidis.scec.servicios;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uag.iidis.scec.modelo.Usuario;
import edu.uag.iidis.scec.excepciones.*;
import edu.uag.iidis.scec.persistencia.UsuarioDAO;
import edu.uag.iidis.scec.persistencia.hibernate.*;

public class ManejadorUsuarios {
    private Log log = LogFactory.getLog(ManejadorUsuarios.class);
    private UsuarioDAO dao;

    public ManejadorUsuarios() {
        dao = new UsuarioDAO();
    }


    public Usuario obtenerUsuario(String nombreUsuario) 
            throws ExcepcionServicio {

        if (log.isDebugEnabled()) {
            log.debug(">obtenerUsuario(" + nombreUsuario + ")");
        }

        try {
            return dao.buscarPorNombreUsuario(nombreUsuario);
        } catch (ExcepcionInfraestructura e) {
            log.error("<ExcepcionInfraestructura");
            throw new ExcepcionServicio(e.getMessage(), e);
        }
    }


    public Collection obtenerUsuarios(Usuario usuario) {

        if (log.isDebugEnabled()) {
            log.debug(">obtenerUsuarios(usuario)");
        }

        return dao.buscarTodos();
    }


    public int crearUsuario(Usuario usuario) {

        int resultado;

        if (log.isDebugEnabled()) {
            log.debug(">guardarUsuario(usuario)");
        }

        try {
            HibernateUtil.beginTransaction();           
            
            if (dao.existeUsuario(usuario.getCredencial()
                                         .getNombreUsuario())) {
               resultado = 1; // Excepción. El nombre de usuario ya existe
            } else {

               dao.hazPersistente(usuario);

               resultado = 0; // Exito. El usuario se creo satisfactoriamente.
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


/*
    public void eliminarUsuario(String nombreUsuario) 
            throws ExcepcionServicio {

        if (log.isDebugEnabled()) {
            log.debug(">eliminarUsuario(" + nombreUsuario + ")");
        }

        try {
            dao.hazTransitorio(nombreUsuario);
        } catch (ExcepcionInfraestructura e) {
            if (log.isWarnEnabled()) {
                log.warn("<ExcepcionInfraestructura");
            }
            throw new ExcepcionServicio(e.getMessage(), e);
        }
    }
*/
    
}
