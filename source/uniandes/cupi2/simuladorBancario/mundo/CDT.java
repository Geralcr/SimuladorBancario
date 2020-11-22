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
package uniandes.cupi2.simuladorBancario.mundo;

/**
 * Clase que representa un CDT.
 */
public class CDT
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Valor inicial del CDT.
     */
    private double valorInvertido;

    /**
     * Interés mensual que del CDT
     */
    private double interesMensual;

    /**
     * Mes de apertura del CDT.
     */
    private int mesApertura;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicializa el CDT. <br>
     * <b>post: </b> El valor invertido, el interés mensual y la fecha se inicializaron en 0.
     */
    public CDT( )
    {
        valorInvertido = 0;
        interesMensual = 0;
        mesApertura = 0;
    }

    /**
     * Retorna el interés que paga el banco mensualmente por este CDT.
     * @return Interés mensual del CDT.
     */
    public double darInteresMensual( )
    {
        return interesMensual;
    }
    
    public double darValorInvertido()
    {
    	return valorInvertido;
    }
    
    public int darMesApertura()
    {
    	return mesApertura;
    }
    /**
     * Inicia una inversión en un CDT .<br>
     * <b>post: </b> Se cambian los valores del CDT, con los valores recibidos. <br>
     * @param pMontoInvertido Monto de dinero que se va a invertir en el CDT. pMontoInvertido > 0.
     * @param pInteresMensual Interés mensual que va a ganar el CDT. pInteresMensual > 0.
     * @param pMes Mes de apertura del CDT. pMes > 0.
     */
    public void invertir( double pMontoInvertido, double pInteresMensual, int pMes )throws Exception
    {
    	if ( darValorInvertido() == 0 && darInteresMensual() == 0 && darMesApertura() == 0 ) {
	        
    		valorInvertido = pMontoInvertido;
	        interesMensual = pInteresMensual;
	        mesApertura = pMes;
	        verificarInvariante();
    	} 
    	else 
    	{
    		throw new Exception ("ERROR: SOLO SE PUEDE TENER UN CDT ACTIVO A LA VEZ");
    	}
    	
    }

    /**
     * Calcula el valor presente de la inversión teniendo en cuenta el interés de la cuenta. <br>
     * @param pMesActual Mes actual del simulador. pMesActual > 0.
     * @return Valor presente del dinero invertido en CDT.
     */
    public double calcularValorPresente( int pMesActual )
    {
        int mesesTranscurridos = pMesActual - mesApertura;
        return ( double ) ( valorInvertido + ( mesesTranscurridos * interesMensual * valorInvertido ) );
    }

    /**
     * Cierra el CDT y retorna el valor invertido más los intereses. <br>
     * <b>post: </b> Se retornó el rendimiento del CDT, y se reinició sus atributos a 0.
     * @param pMesActual Mes de cierre para calcular el rendimiento del CDT.
     * @return Valor de cierre del CDT.
     */
    public double cerrar( int pMesActual )
    {
        double valorCierre = calcularValorPresente( pMesActual );
        valorInvertido = 0;
        interesMensual = 0;
        mesApertura = 0;
        verificarInvariante();
        return valorCierre;
    }
    
    //TODO: 7 Calcular los intereses del cdt
    public double darInteresGenerado(int pMesActual) {
    	int mesesTranscurridos = pMesActual - mesApertura;
    	return (double) ( mesesTranscurridos * interesMensual * valorInvertido );
    }
    
    private boolean validarAtributos() 
    {
    	return valorInvertido >= 0 && interesMensual >= 0 && mesApertura >= 0;
    }
    /**
     * Verifica invariante
     */
    private void verificarInvariante()
    {
    	assert validarAtributos() : "Los atributos deben ser positivos";
    }
}