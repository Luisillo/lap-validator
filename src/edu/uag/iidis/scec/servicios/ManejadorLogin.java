package edu.uag.iidis.scec.servicios;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uag.iidis.scec.modelo.Login;
import edu.uag.iidis.scec.excepciones.*;
import edu.uag.iidis.scec.persistencia.LoginDAO;
import edu.uag.iidis.scec.persistencia.hibernate.*;

public class ManejadorLogin {
    private Log log = LogFactory.getLog(ManejadorRoles.class);
    private LoginDAO dao;

    public ManejadorLogin() {
        dao = new LoginDAO();
    }


    public Collection listarLogin() {
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


    public void eliminarLogin(Long id) {
        if (log.isDebugEnabled()) {
            log.debug(">eliminarLogin(gente)");
        }
        try {
            HibernateUtil.beginTransaction();           
            Login gente = dao.buscarPorId(id, true);
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

    public int crearLogin(Login gente) {

        int resultado;

        if (log.isDebugEnabled()) {
            log.debug(">guardarLogin(gente)");
        }

        try {
            System.out.println("Pasa varibles "+gente.getnombrePrefijo()+" y "+gente.getclaveAcceso());
            HibernateUtil.beginTransaction();           
            
            if (!dao.existeLogin(gente.getnombrePrefijo(), gente.getclaveAcceso())) {
               resultado = 1; // Excepción. El nombre de gente ya existe
            } else {
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
