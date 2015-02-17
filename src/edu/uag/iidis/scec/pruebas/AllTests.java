package edu.uag.iidis.scec.pruebas;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import junit.framework.*;
import junit.textui.TestRunner;

/**
 * Ejecuta todas las pruebas.
 *
 */
public class AllTests extends TestCase {

    public static Test suite() {

        TestSuite suite = new TestSuite();

        suite.addTest(RolDAOTest.suite());
        suite.addTest(EstadoDAOTest.suite());
        suite.addTest(UsuarioDAOTest.suite());
        suite.addTest(CiudadDAOTest.suite());
        suite.addTest(GenteDAOTest.suite());
        suite.addTest(LoginDAOTest.suite());
        return suite;
    }


    public static void main(String args[]) {
        TestRunner.run(suite());
    }
}
