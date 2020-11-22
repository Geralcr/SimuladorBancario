/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n1_simuladorBancario
 * Autor: Equipo Cupi2 2017
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cupi2.simuladorBancario.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uniandes.cupi2.simuladorBancario.mundo.CDT;

/**
 * Clase usada para verificar que los métodos de la clase CDT estén correctamente implementados.
 */
public class CDTTest
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * CDT usada para las pruebas.
     */
    private CDT cdt;

    // -----------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------

    /**
     * Escenario 1: Crea un nuevo CDT e inicializa sus atributos.
     */
    private void setupEscenario1( )
    {
        cdt = new CDT( );
    }

    /**
     * Prueba 1: Método que se encarga de verificar el método inicializar.<br>
     * <b> Métodos a probar: </b> <br>
     * inicializar<br>
     * darInteresMensual<br>
     * calcularValorPresente<br>
     * <b> Casos de prueba: </b> <br>
     * 1. El CDT fue incializado correctamente.
     */
    @Test
    public void testCDT( )
    {
        setupEscenario1( );
        assertEquals( "El interes mensual no es el esperado", 0.0, cdt.darInteresMensual( ), 0.00001 );
        assertEquals( "El valor presente no es el esperado", 0, cdt.calcularValorPresente( 1 ), 0.00001 );
    }

    /**
     * Prueba 2: Método que se encarga de verificar el método invertir.<br>
     * <b> Métodos a probar: </b> <br>
     * invertir<br>
     * calcularValorPresente<br>
     * <b> Casos de prueba: </b> <br>
     * 1. El CDT no tiene dinero invertido.
     */
    @Test
    public void testInvertir( )
    {
        setupEscenario1( );
        try {
			cdt.invertir( 100000, 0.05, 1 );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage() ); ;
		}
        assertEquals( "El valor presente del CDT no es el esperado", 100000, cdt.calcularValorPresente( 1 ), 2 );

    }

    /**
     * Prueba 3: Método que se encarga de verificar el método calcularValorPresente.<br>
     * <b> Métodos a probar: </b> <br>
     * invertir<br>
     * calcularValorPresente<br>
     * <b> Casos de prueba: </b> <br>
     * 1. El CDT tiene dinero invertido.
     */
    @Test
    public void testCalcularValorPresesente( )
    {
        setupEscenario1( );
        try {
			cdt.invertir( 100000, 0.05, 1 );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage() ); ;
		}
        assertEquals( "El valor presente del CDT no es el esperado", 100000 * ( 1 + 0.05 ), cdt.calcularValorPresente( 2 ), 0.01 );

    }

    /**
     * Prueba 4: Método que se encarga de verificar el método cerrar.<br>
     * <b> Métodos a probar: </b> <br>
     * invertir<br>
     * calcularValorPresente<br>
     * <b> Casos de prueba: </b> <br>
     * 1. El CDT tiene dinero invertido.
     */
    @Test
    public void testCerrar( )
    {
        setupEscenario1( );
        try {
			cdt.invertir( 100000, 0.05, 1 );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage() ); 
		}
        assertEquals( "El valor presente del CDT no es el esperado", 100000 * ( 1 + 0.05 ), cdt.cerrar( 2 ), 0.01 );
        assertEquals( "El interes mensual no es el esperado", 0.0, cdt.darInteresMensual( ), 0.00001 );
        assertEquals( "El valor presente no es el esperado", 0, cdt.calcularValorPresente( 1 ), 0.00001 );

    }

}
