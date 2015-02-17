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


public class UsuarioDAOTest extends TestCase {

    private static UsuarioDAO dao = null;
    private static RolDAO rolDAO = null;
    private static EstadoDAO estadoDAO = null;

    protected void setUp() throws Exception {
        // se ejecuta al inicio de cada método de prueba
    }

    protected void tearDown() {
        // se ejecuta al final de cada método de prueba
    }

    public void testCrearUsuario() throws Exception {
        Usuario usuario = new Usuario(
                    new NombrePersona("Ing.",
                                      "Gonzalo",
                                      "Osuna", "Millán", 
                                      "", "gom"),
                    new Credencial("gosuna","123"));
        try {
            HibernateUtil.beginTransaction();
            dao.hazPersistente(usuario);
            HibernateUtil.commitTransaction();

            assertTrue(usuario.getId() != null);
            assertTrue(usuario.getNombre()
                              .getApellidoPaterno()
                              .equals("Osuna"));
            assertTrue(usuario.getCredencial()
                              .getNombreUsuario()
                              .equals("gosuna"));
        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
            throw e;
        } finally{
            HibernateUtil.closeSession();
        }
    }

    public void testCrearUsuarioInvalido() throws Exception {
        Usuario usuario = new Usuario(
                    new NombrePersona("Ing.",
                                      "Gonzalo",
                                      "Osuna", "Millán", 
                                      "", "gom"),
                    new Credencial("gosuna","123"));

        try {
            HibernateUtil.beginTransaction();
            dao.hazPersistente(usuario);
            HibernateUtil.commitTransaction();
            fail("Falló");
        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
        } finally{
            HibernateUtil.closeSession();
        }
    }


    public void testBuscarUsuarioPorId() throws Exception {
        Usuario usuario;
        try {
            usuario = dao.buscarPorId(new Long(1), false);
            assertTrue(usuario.getId() != null);
            assertTrue(usuario.getNombre()
                              .getApellidoPaterno()
                              .equals("Osuna"));
        } catch (Exception e) {
            throw e;
        } finally{
            HibernateUtil.closeSession();
        }
    }


    public void testBuscarPorEjemplo() throws Exception {
        Usuario usuario = new Usuario();
        Collection usuarios;

        usuario.setCredencial(new Credencial("gosuna", null));
        try {
            usuarios = dao.buscarPorEjemplo(usuario);
            assertTrue(usuarios.size() == 1);
        } catch (Exception e) {
            throw e;
        } finally{
            HibernateUtil.closeSession();
        }
    }


    public void testBuscarTodos() throws Exception {
        Collection usuarios;

        try {
            usuarios = dao.buscarTodos();
            assertTrue(usuarios.size() == 1);
        } catch (Exception e) {
            throw e;
        } finally{
            HibernateUtil.closeSession();
        }
    }


    public void testAgregarRol() throws Exception {
        Usuario usuario;
        Rol rol1 = new Rol("rol1","descripcion rol1");
        Rol rol2 = new Rol("rol2","descripcion rol2");
        try {
            HibernateUtil.beginTransaction();
            usuario = dao.buscarPorId(new Long(1), false);
            usuario.addRol(new Rol("rol1","descripcion rol1"));
            usuario.addRol(new Rol("rol2","descripcion rol2"));
            dao.hazPersistente(usuario);
            HibernateUtil.commitTransaction();
            assertTrue(usuario.getRoles().size() == 2);
        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
            throw e;
        } finally{
            HibernateUtil.closeSession();
        }
    }


    public void testVerificarRol() throws Exception {
        Usuario usuario;
        try {
            usuario = dao.buscarPorId(new Long(1), false);
            assertTrue(usuario.getRoles().size() == 2);
        } catch (Exception e) {
            throw e;
        } finally{
            HibernateUtil.closeSession();
        }
    }

    public void testEliminarRol() throws Exception {
        Usuario usuario;
        Rol rol;
        try {
            HibernateUtil.beginTransaction();
            rol = rolDAO.buscarPorId(new Long(2), false);
            usuario = dao.buscarPorId(new Long(1), false);
            usuario.removeRol(rol);
            dao.hazPersistente(usuario);
            HibernateUtil.commitTransaction();
            assertTrue(usuario.getRoles().size() == 1);
        } catch (Exception e) {
            throw e;
        } finally{
            HibernateUtil.closeSession();
        }
    }

    public void testAgregarEstado() throws Exception {
        Usuario usuario;
        Estado estado1 = new Estado("estado1","descripcion estado1");
        Estado estado2 = new Estado("estado2","descripcion estado2");
        try {
            HibernateUtil.beginTransaction();
            usuario = dao.buscarPorId(new Long(1), false);
            usuario.addEstado(new Estado("estado1","descripcion estado1"));
            usuario.addEstado(new Estado("estado2","descripcion estado2"));
            dao.hazPersistente(usuario);
            HibernateUtil.commitTransaction();
            assertTrue(usuario.getEstados().size() == 2);
        } catch (Exception e) {
            HibernateUtil.rollbackTransaction();
            throw e;
        } finally{
            HibernateUtil.closeSession();
        }
    }


    public void testVerificarEstado() throws Exception {
        Usuario usuario;
        try {
            usuario = dao.buscarPorId(new Long(1), false);
            assertTrue(usuario.getEstados().size() == 2);
        } catch (Exception e) {
            throw e;
        } finally{
            HibernateUtil.closeSession();
        }
    }

    public void testEliminarEstado() throws Exception {
        Usuario usuario;
        Estado estado;
        try {
            HibernateUtil.beginTransaction();
            estado = estadoDAO.buscarPorId(new Long(2), false);
            usuario = dao.buscarPorId(new Long(1), false);
            usuario.removeEstado(estado);
            dao.hazPersistente(usuario);
            HibernateUtil.commitTransaction();
            assertTrue(usuario.getEstados().size() == 1);
        } catch (Exception e) {
            throw e;
        } finally{
            HibernateUtil.closeSession();
        }
    }
    public static Test suite() {

       TestSetup suite = new TestSetup(new TestSuite(UsuarioDAOTest.class)) {

            protected void setUp(  ) throws Exception {
                // Se ejecuta al inicio de la suite

                SchemaExport ddlExport = new SchemaExport(HibernateUtil.getConfiguration());
                ddlExport.create(false, true);

                dao = new UsuarioDAO();
                rolDAO = new RolDAO();
                estadoDAO = new EstadoDAO();
            }

            protected void tearDown(  ) throws Exception {
                // se ejecuta al final de la suite
                dao = null;
                rolDAO = null;
                estadoDAO = null;
            }
        };

        return suite;
    }

    public static void main(String[] args) throws Exception {
        TestRunner.run( suite() );
    }

}
