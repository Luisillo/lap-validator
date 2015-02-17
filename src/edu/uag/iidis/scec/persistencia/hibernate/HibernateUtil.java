package edu.uag.iidis.scec.persistencia.hibernate;
//*   import org.hibernate.*;  
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.*;
import org.hibernate.service.*;


import org.apache.commons.logging.*;
import edu.uag.iidis.scec.excepciones.ExcepcionInfraestructura;

import javax.naming.*;


public class HibernateUtil {

    private static Log log = LogFactory.getLog(HibernateUtil.class);

    private static Configuration configuration;
    private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;

    private static final ThreadLocal threadSession = new ThreadLocal();
    private static final ThreadLocal threadTransaction = new ThreadLocal();
    private static final ThreadLocal threadInterceptor = new ThreadLocal();


    // Create the initial SessionFactory from the 
    // default configuration files
	
	
	////  Hibernae 4  utiliza diferente el SessionFactory porque buildSessionFactory deprecated este es el codigo de hibernate 4
/*
		private static SessionFactory sessionFactory;
		private static ServiceRegistry serviceRegistry;

		private static SessionFactory configureSessionFactory() throws HibernateException {
	    Configuration configuration = new Configuration();
	    configuration.configure();
	    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	    return sessionFactory;
}
	
	
	Este es el de hibernate 2.16
	
    static {
        if (log.isDebugEnabled()) {
            log.debug(">creando sessionFactory");
        }

        try {
            configuration = new Configuration();
            sessionFactory = configuration.configure().buildSessionFactory();
        } catch (Throwable ex) {
            log.error("Building SessionFactory failed.", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

	
	
*/

	
    static {
        if (log.isDebugEnabled()) {
            log.debug(">creando sessionFactory");
        }

        try {
            configuration = new Configuration();
		    configuration.configure();
		    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
		    sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            
        } catch (Throwable ex) {
            log.error("Building SessionFactory failed.", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Regresa el SessionFactory usado por esta clase estática.
     *
     * @return SessionFactory
     */
    public static SessionFactory getSessionFactory() {
      /* El siguiente codigo se utiliza para JNDI
        SessionFactory sessionFactory = null;
        try {
            Context ctx = new InitialContext();
            String jndiName = "java:hibernate/HibernateFactory";
            sessions = (SessionFactory)ctx.lookup(jndiName);
        } catch (NamingException ex) {
            throw new ExcepcionInfraestructura(ex);
        }
        return sessions;
      */
        return sessionFactory;
    }

    /**
     * Returns the original Hibernate configuration.
     *
     * @return Configuration
     */
    public static Configuration getConfiguration() {
        return configuration;
    }

    /**
     * Reconstruye SessionFactory con la misma configuración
     *
     */
     public static void rebuildSessionFactory()
            throws ExcepcionInfraestructura {

        synchronized(sessionFactory) {
            try {
                sessionFactory = getConfiguration().buildSessionFactory();
            } catch (Exception ex) {
                throw new ExcepcionInfraestructura(ex);
            }
        }
     }

    /**
     * Rebuild the SessionFactory with the given Hibernate Configuration.
     *
     * @param cfg
     */
     public static void rebuildSessionFactory(Configuration cfg)
            throws ExcepcionInfraestructura {

        synchronized(sessionFactory) {
            try {
                sessionFactory = cfg.buildSessionFactory();
                configuration = cfg;
            } catch (Exception ex) {
                throw new ExcepcionInfraestructura(ex);
            }
        }
     }

    /**
     * Recupera la sesión actual local al thread.
     * <p/>
     * Si la sesión no está abierta, abre una nueva sesión 
     * para el thread que se ejecuta.
     *
     * @return Session
     */
    public static Session getSession()
            throws ExcepcionInfraestructura {

        if (log.isDebugEnabled()) {
            log.debug(">getSession()");
        }

        Session s = (Session) threadSession.get();
        try {
            if (s == null) {
                if (log.isDebugEnabled()) {
                    log.debug("-Abriendo una nueva sesión para el thread.");
                }

                if (getInterceptor() != null) {
                    if (log.isDebugEnabled()) {
                        log.debug("-Usando el interceptor: " + 
                                  getInterceptor().getClass());
                    } ///// de acuerdo a la nueva forma de los interceptors
					//Session session = sf.withOptions()
                    //.interceptor(new AuditInterceptor())
                    //.openSession(); 
					// s = getSessionFactory().openSession(getInterceptor());
				   s = getSessionFactory()
				   		.withOptions()
                    	.interceptor(getInterceptor())
                    	.openSession();
				   
                } else {
                    s = getSessionFactory().openSession();
                }
                threadSession.set(s);
            }
        } catch (HibernateException ex) {
            log.error("<HibernateException");
            throw new ExcepcionInfraestructura(ex);
        }

        return s;
    }

    /**
     * Cierra la sesión local thread.
     */
    public static void closeSession()
            throws ExcepcionInfraestructura {

        if (log.isDebugEnabled()) {
            log.debug(">closeSession()");
        }

        try {
            Session s = (Session) threadSession.get();
            threadSession.set(null);
            if (s != null && s.isOpen()) {
                if (log.isDebugEnabled()) {
                    log.debug("-Cerrando la sesión local al thread.");
                }
                s.close();
            }
        } catch (HibernateException ex) {
            log.error("<HibernateException");
            throw new ExcepcionInfraestructura(ex);
        }
    }


    /**
     * Inicia una nueva transacción de base de datos
     */
    public static void beginTransaction()
            throws ExcepcionInfraestructura {

        if (log.isDebugEnabled()) {
            log.debug(">beginTransaction()");
        }

        Transaction tx = (Transaction) threadTransaction.get();
        try {
            if (tx == null) {
                if (log.isDebugEnabled()) {
                    log.debug("-Iniciando una nueva transacción de base de datos en este thread.");
                }
                tx = getSession().beginTransaction();
                threadTransaction.set(tx);
            }
        } catch (HibernateException ex) {
            log.error("<HibernateException");
            throw new ExcepcionInfraestructura(ex);
        }
    }


    /**
     * Compromete la transacción de base de datos.
     */
    public static void commitTransaction()
            throws ExcepcionInfraestructura {

        if (log.isDebugEnabled()) {
            log.debug(">commitTransaction()");
        }

        Transaction tx = (Transaction) threadTransaction.get();
        try {
            if ( tx != null && !tx.wasCommitted()
                            && !tx.wasRolledBack() ) {
                if (log.isDebugEnabled()) {
                    log.debug("-Comprometiendo la transacción de este thread.");
                }
                tx.commit();
            }
            threadTransaction.set(null);
        } catch (HibernateException ex) {
            if (log.isDebugEnabled()) {
                log.debug("-Deshaciendo la transacción de este thread.");
            }
            rollbackTransaction();
            log.error("<HibernateException");
            throw new ExcepcionInfraestructura(ex);
        }
    }


    /**
     * Deshace la transacción de base de datos
     */
    public static void rollbackTransaction()
            throws ExcepcionInfraestructura {

        if (log.isDebugEnabled()) {
            log.debug(">rollbackTransaction()");
        }

        Transaction tx = (Transaction) threadTransaction.get();
        try {
            threadTransaction.set(null);
            if ( tx != null && !tx.wasCommitted() && !tx.wasRolledBack() ) {
                if (log.isDebugEnabled()) {
                    log.debug("-Intentando deshacer la transacción para este thread.");
                }
                tx.rollback();
            }
        } catch (HibernateException ex) {
            log.error("<HibernateException");
            throw new ExcepcionInfraestructura(ex);
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("-Intentando cerrar la sesión.");
            }
            closeSession();
        }
    }

    /**
     * Reconnects a Hibernate Session to the current Thread.
     *
     * @param session The Hibernate Session to be reconnected.
     */
    public static void reconnect(Session session)
       throws ExcepcionInfraestructura {
 //       try {
  //          session.reconnect();
 //           threadSession.set(session);
  //      } catch (HibernateException ex) {
  //          throw new ExcepcionInfraestructura(ex);
  //      }
    }

    /**
     * Disconnect and return Session from current Thread.
     *
     * @return Session the disconnected Session
     */
    public static Session disconnectSession()
        throws ExcepcionInfraestructura {

        Session session = getSession();
        try {
            threadSession.set(null);
            if (session.isConnected() && session.isOpen())
                session.disconnect();
        } catch (HibernateException ex) {
            throw new ExcepcionInfraestructura(ex);
        }
        return session;
    }

    /**
     * Register a Hibernate interceptor with the current thread.
     * <p>
     * Every Session opened is opened with this interceptor after
     * registration. Has no effect if the current Session of the
     * thread is already open, effective on next close()/getSession().
     */
    public static void registerInterceptor(Interceptor interceptor) {
        threadInterceptor.set(interceptor);
    }

    private static Interceptor getInterceptor() {
        Interceptor interceptor =
            (Interceptor) threadInterceptor.get();
        return interceptor;
    }

}

