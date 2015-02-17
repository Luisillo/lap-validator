package edu.uag.iidis.scec.persistencia;

import org.hibernate.*;
import org.hibernate.type.*;
import org.hibernate.criterion.Example;
//import org.hibernate.classic.*;


import edu.uag.iidis.scec.excepciones.ExcepcionInfraestructura;
import edu.uag.iidis.scec.modelo.Gente;
import edu.uag.iidis.scec.persistencia.hibernate.HibernateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Collection;
import java.util.List;


public class GenteDAO {

    private Log log = LogFactory.getLog(GenteDAO.class);

    public GenteDAO() {
    }


    public Gente buscarPorId(Long idGente, boolean bloquear)
            throws ExcepcionInfraestructura {

        Gente gente = null;

        if (log.isDebugEnabled()) {
            log.debug(">buscarPorID(" + idGente + ", " + bloquear + ")");
        }

        try {
            if (bloquear) {
                gente = (Gente)HibernateUtil.getSession()
                                                .load(Gente.class, 
                                                      idGente, 
                                                      LockMode.UPGRADE);
            } else {
                gente = (Gente)HibernateUtil.getSession()
                                                .load(Gente.class,
                                                      idGente);
            }
        } catch (HibernateException ex) {
            if (log.isWarnEnabled()) {
                log.warn("<HibernateException");
            }

            throw new ExcepcionInfraestructura(ex);
        }
        return gente;
    }


    public Collection buscarTodos()
            throws ExcepcionInfraestructura {

        Collection gentes;

        if (log.isDebugEnabled()) {
            log.debug(">buscarTodos()");
        }

        try {
            gentes = HibernateUtil.getSession()
                                    .createCriteria(Gente.class)
                                    .list();
									
			  log.debug(">buscarTodos() ---- list	");									
        } catch (HibernateException e) {
            if (log.isWarnEnabled()) {
                log.warn("<HibernateException");
            }
            throw new ExcepcionInfraestructura(e);
        }
        return gentes;
    }


    public Collection buscarPorEjemplo(Gente gente)
            throws ExcepcionInfraestructura {


        Collection gentes;
 
        if (log.isDebugEnabled()) {
            log.debug(">buscarPorEjemplo()");
        }

        try {
            Criteria criteria = HibernateUtil.getSession()
                                             .createCriteria(Gente.class);
            gentes = criteria.add(Example.create(gente)).list();
        } catch (HibernateException e) {
            if (log.isWarnEnabled()) {
                log.warn("<HibernateException");
            }
            throw new ExcepcionInfraestructura(e);
        }
        return gentes;
    }


    public void hazPersistente(Gente gente)
            throws ExcepcionInfraestructura {

        if (log.isDebugEnabled()) {
            log.debug(">hazPersistente(gente)");
        }

        try {
            HibernateUtil.getSession().saveOrUpdate(gente);
        } catch (HibernateException e) {
            if (log.isWarnEnabled()) {
                log.warn("<HibernateException");
            }
            throw new ExcepcionInfraestructura(e);
        }
    }


    public void hazTransitorio(Gente gente)
            throws ExcepcionInfraestructura {

        if (log.isDebugEnabled()) {
            log.debug(">hazTransitorio(gente)");
        }

        try {
            HibernateUtil.getSession().delete(gente);
        } catch (HibernateException e) {
            if (log.isWarnEnabled()) {
                log.warn("<HibernateException");
            }
            throw new ExcepcionInfraestructura(e);
        }
    }

    public boolean existeGente(String nombreGente)
            throws ExcepcionInfraestructura {

        if (log.isDebugEnabled()) {
            log.debug(">existeRol(nombreRol)");
        }

        try {
			
			
//            String consultaCuentaRoles =
//            "select count(*) from Gente r where r.nombre=?";
//
 //           int resultado =
 //           ((Integer) HibernateUtil.getSession()
 //                          .find(consultaCuentaRoles, 
 //                                nombreRol,
 //                                StringType.INSTANCE)
 //                          .iterator()
 //                          .next()).intValue();
// de acuerdo al nuevo formato
 
			String hql = "select Nombres from Gente where Nombres = :Nombres";
			
			 if (log.isDebugEnabled()) {
           		 log.debug(hql + nombreGente);
        	}
		
			Query query = HibernateUtil.getSession()
										.createQuery(hql);
			if (log.isDebugEnabled()) {
           		 log.debug("<<<<<<<<< create query ok " );
        	}

			query.setParameter("Nombres", nombreGente);
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
    public Collection ordenarPor(String tipo, int tipoInt)
            throws ExcepcionInfraestructura {

        Collection ciudades;

        if (log.isDebugEnabled()) {
            log.debug(">existeRol(nombreRol)");
        }
        String add="";
        if (tipoInt==0) {
            add="ASC";
        } else if(tipoInt==1) {
            add="DESC";
        }
        try {
            String hql="from Gente";
            if(tipo.equals("nombres")) {
                hql = "from Gente order by Nombres "+add;
            } else if(tipo.equals("apellidos")) {
                hql = "from Gente order by Apellidos "+add;
            } else if(tipo.equals("direccion")) {
                hql = "from Gente order by Direccion "+add;
            } else if(tipo.equals("telefono")) {
                hql = "from Gente order by Telefono "+add;
            } else if(tipo.equals("ciudad")) {
                hql = "from Gente order by idCiudad "+add;
            } 
            
             if (log.isDebugEnabled()) {
                 log.debug(hql);
            }
        
            Query query = HibernateUtil.getSession()
                                        .createQuery(hql);
            if (log.isDebugEnabled()) {
                 log.debug("<<<<<<<<< create query ok " );
            }

            if (log.isDebugEnabled()) {
                 log.debug("<<<<<<<<< set Parameter ok antes del query list >>>>>");
            }
            ciudades = query.list();

            return ciudades;

        } catch (HibernateException ex) {
            if (log.isWarnEnabled()) {
                log.warn("<HibernateException *******************");
            }
            throw new ExcepcionInfraestructura(ex);
        }
    }

}
