package edu.uag.iidis.scec.persistencia;

import org.hibernate.*;
import org.hibernate.type.*;
import org.hibernate.criterion.Example;
//import org.hibernate.classic.*;


import edu.uag.iidis.scec.excepciones.ExcepcionInfraestructura;
import edu.uag.iidis.scec.modelo.Login;
import edu.uag.iidis.scec.persistencia.hibernate.HibernateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Collection;
import java.util.List;


public class LoginDAO {

    private Log log = LogFactory.getLog(LoginDAO.class);

    public LoginDAO() {
    }


    public Login buscarPorId(Long idLogin, boolean bloquear)
            throws ExcepcionInfraestructura {

        Login Login = null;

        if (log.isDebugEnabled()) {
            log.debug(">buscarPorID(" + idLogin + ", " + bloquear + ")");
        }

        try {
            if (bloquear) {
                Login = (Login)HibernateUtil.getSession()
                                                .load(Login.class, 
                                                      idLogin, 
                                                      LockMode.UPGRADE);
            } else {
                Login = (Login)HibernateUtil.getSession()
                                                .load(Login.class,
                                                      idLogin);
            }
        } catch (HibernateException ex) {
            if (log.isWarnEnabled()) {
                log.warn("<HibernateException");
            }

            throw new ExcepcionInfraestructura(ex);
        }
        return Login;
    }


    public Collection buscarTodos()
            throws ExcepcionInfraestructura {

        Collection Logins;

        if (log.isDebugEnabled()) {
            log.debug(">buscarTodos()");
        }

        try {
            Logins = HibernateUtil.getSession()
                                    .createCriteria(Login.class)
                                    .list();
									
			  log.debug(">buscarTodos() ---- list	");									
        } catch (HibernateException e) {
            if (log.isWarnEnabled()) {
                log.warn("<HibernateException");
            }
            throw new ExcepcionInfraestructura(e);
        }
        return Logins;
    }


    public Collection buscarPorEjemplo(Login Login)
            throws ExcepcionInfraestructura {


        Collection Logins;
 
        if (log.isDebugEnabled()) {
            log.debug(">buscarPorEjemplo()");
        }

        try {
            Criteria criteria = HibernateUtil.getSession()
                                             .createCriteria(Login.class);
            Logins = criteria.add(Example.create(Login)).list();
        } catch (HibernateException e) {
            if (log.isWarnEnabled()) {
                log.warn("<HibernateException");
            }
            throw new ExcepcionInfraestructura(e);
        }
        return Logins;
    }


    public void hazPersistente(Login Login)
            throws ExcepcionInfraestructura {

        if (log.isDebugEnabled()) {
            log.debug(">hazPersistente(Login)");
        }

        try {
            HibernateUtil.getSession().saveOrUpdate(Login);
        } catch (HibernateException e) {
            if (log.isWarnEnabled()) {
                log.warn("<HibernateException");
            }
            throw new ExcepcionInfraestructura(e);
        }
    }


    public void hazTransitorio(Login Login)
            throws ExcepcionInfraestructura {

        if (log.isDebugEnabled()) {
            log.debug(">hazTransitorio(Login)");
        }

        try {
            HibernateUtil.getSession().delete(Login);
        } catch (HibernateException e) {
            if (log.isWarnEnabled()) {
                log.warn("<HibernateException");
            }
            throw new ExcepcionInfraestructura(e);
        }
    }

    public boolean existeLogin(String usuario, String pass)
            throws ExcepcionInfraestructura {

        if (log.isDebugEnabled()) {
            log.debug(">existeRol(nombreRol)");
        }

        try {
			
 
			String hql="from Gente where Nombres = '"+usuario+"' and claveAcceso = '"+pass+"'";
			
			 if (log.isDebugEnabled()) {
           		 log.debug(hql);
        	}
		
			Session paso = HibernateUtil.getSession();

             if (log.isDebugEnabled()) {
                 log.debug("Entra");
            }
			Query query = paso.createQuery(hql);
			if (log.isDebugEnabled()) {
           		 log.debug("<<<<<<<<< create query ok, nombre "+usuario+" clave "+pass );
        	}

			//query.setParameter("nombre", usuario);
            //query.setParameter("clave", pass);
			if (log.isDebugEnabled()) {
           		 log.debug("<<<<<<<<< set Parameter ok antes del query list >>>>>");
        	}
			List results = query.list();
			int resultado = results.size();
			if (log.isDebugEnabled()) {
           		 log.debug("<<<<<<<<< Result size " + resultado);
        	}
            if (resultado == 0) {
               return false;
            }
            
            return true;

        } catch (HibernateException ex) {
            if (log.isWarnEnabled()) {
                log.warn("<HibernateException *******************");
            }
            throw new ExcepcionInfraestructura(ex);
        }
    }

}
