package edu.uag.iidis.scec.persistencia;

import org.hibernate.*;
import org.hibernate.type.*;
import org.hibernate.criterion.Example;
//import org.hibernate.classic.*;


import edu.uag.iidis.scec.excepciones.ExcepcionInfraestructura;
import edu.uag.iidis.scec.modelo.Estado;
import edu.uag.iidis.scec.persistencia.hibernate.HibernateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Collection;
import java.util.List;


public class EstadoDAO {

    private Log log = LogFactory.getLog(EstadoDAO.class);

    public EstadoDAO() {
    }


    public Estado buscarPorId(Long idEstado, boolean bloquear)
            throws ExcepcionInfraestructura {

        Estado estado = null;

        if (log.isDebugEnabled()) {
            log.debug(">buscarPorID(" + idEstado + ", " + bloquear + ")");
        }

        try {
            if (bloquear) {
                estado = (Estado)HibernateUtil.getSession()
                                                .load(Estado.class, 
                                                      idEstado, 
                                                      LockMode.UPGRADE);
            } else {
                estado = (Estado)HibernateUtil.getSession()
                                                .load(Estado.class,
                                                      idEstado);
            }
        } catch (HibernateException ex) {
            if (log.isWarnEnabled()) {
                log.warn("<HibernateException");
            }

            throw new ExcepcionInfraestructura(ex);
        }
        return estado;
    }


    public Collection buscarTodos()
            throws ExcepcionInfraestructura {

        Collection estados;

        if (log.isDebugEnabled()) {
            log.debug(">buscarTodos()");
        }

        try {
            estados = HibernateUtil.getSession()
                                    .createCriteria(Estado.class)
                                    .list();
        } catch (HibernateException e) {
            if (log.isWarnEnabled()) {
                log.warn("<HibernateException");
            }
            throw new ExcepcionInfraestructura(e);
        }
        return estados;
    }


    public Collection buscarPorEjemplo(Estado estado)
            throws ExcepcionInfraestructura {


        Collection estados;
 
        if (log.isDebugEnabled()) {
            log.debug(">buscarPorEjemplo()");
        }

        try {
            Criteria criteria = HibernateUtil.getSession()
                                             .createCriteria(Estado.class);
            estados = criteria.add(Example.create(estado)).list();
        } catch (HibernateException e) {
            if (log.isWarnEnabled()) {
                log.warn("<HibernateException");
            }
            throw new ExcepcionInfraestructura(e);
        }
        return estados;
    }


    public void hazPersistente(Estado estado)
            throws ExcepcionInfraestructura {

        if (log.isDebugEnabled()) {
            log.debug(">hazPersistente(estado)");
        }

        try {
            HibernateUtil.getSession().saveOrUpdate(estado);
        } catch (HibernateException e) {
            if (log.isWarnEnabled()) {
                log.warn("<HibernateException");
            }
            throw new ExcepcionInfraestructura(e);
        }
    }


    public void hazTransitorio(Estado estado)
            throws ExcepcionInfraestructura {

        if (log.isDebugEnabled()) {
            log.debug(">hazTransitorio(estado)");
        }

        try {
            HibernateUtil.getSession().delete(estado);
        } catch (HibernateException e) {
            if (log.isWarnEnabled()) {
                log.warn("<HibernateException");
            }
            throw new ExcepcionInfraestructura(e);
        }
    }

    public boolean existeEstado(String nombreEstado)
            throws ExcepcionInfraestructura {

        if (log.isDebugEnabled()) {
            log.debug(">existeEstado(nombreEstado)");
        }

        try {
			
			
//            String consultaCuentaEstados =
//            "select count(*) from Estado r where r.nombre=?";
//
 //           int resultado =
 //           ((Integer) HibernateUtil.getSession()
 //                          .find(consultaCuentaEstados, 
 //                                nombreEstado,
 //                                StringType.INSTANCE)
 //                          .iterator()
 //                          .next()).intValue();
// de acuerdo al nuevo formato
 
			String hql = "select nombre from Estado where nombre = :nombre";
			
			 if (log.isDebugEnabled()) {
           		 log.debug(hql + nombreEstado);
        	}
		
			Query query = HibernateUtil.getSession()
										.createQuery(hql);
			if (log.isDebugEnabled()) {
           		 log.debug("<<<<<<<<< create query ok " );
        	}

			query.setParameter("nombre", nombreEstado);
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
