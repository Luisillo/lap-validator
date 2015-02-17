package edu.uag.iidis.scec.pruebas;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.extensions.TestSetup;
import junit.textui.TestRunner;

import org.hibernate.cfg.Environment;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import edu.uag.iidis.scec.modelo.*;
import edu.uag.iidis.scec.persistencia.*;
import edu.uag.iidis.scec.persistencia.hibernate.HibernateUtil;

import java.util.*;


public class CiudadDAOTest extends TestCase {

    private static CiudadDAO dao = null;


    protected void setUp() throws Exception {
        dao = new CiudadDAO();
    }

    protected void tearDown() {
        dao = null;
    }

    public void testCrearCiudad() throws Exception {
        Ciudad rol = new Ciudad("rol1","descripcion rol1",Long.parseLong("1"));

        HibernateUtil.beginTransaction();
        try {
            dao.hazPersistente(rol);
            HibernateUtil.commitTransaction();

            assertTrue(rol.getId() != null);
            assertTrue(rol.getNombre().equals("rol1"));
        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
            throw e;
        } finally{
            HibernateUtil.closeSession();
        }
    }

    public static Test suite() {

       //TestSetup suite = new TestSetup(new TestSuite(CiudadDAOTest.class)) {  la modifique por la de abajo
	   TestSetup suite = new TestSetup(new TestSuite(CiudadDAOTest.class)) { 

            protected void setUp(  ) throws Exception {
                // Se ejecuta al inicio de la suite de pruebas

                SchemaExport ddlExport = new SchemaExport(HibernateUtil.getConfiguration());
                ddlExport.create(false, true);

                dao = new CiudadDAO();
            }

            protected void tearDown(  ) throws Exception {
                // se ejecuta al final de la suite
                dao = null;
            }
        };

        return suite;    
		
		}

    public static void main(String[] args) throws Exception {
        TestRunner.run( suite() );
    }

}
