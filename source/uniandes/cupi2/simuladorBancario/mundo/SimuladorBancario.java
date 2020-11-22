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

import java.util.ArrayList;


/**
 * Clase que representa el simulador bancario para las tres cuentas de un cliente.
 */
public class SimuladorBancario
{
	
	public static final double INVERSION_MAXIMO = 100000000;
	
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

	//TODO: 1 Crear atributo
    private double interesGenerado;
	
    /**
     * Cédula del cliente.
     */
    private String cedula;

    /**
     * Nombre del cliente.
     */
    private String nombre;

    /**
     * Mes actual.
     */
    private int mesActual;
    /*
     * consecutivo
     */
    private int consecutivoActual;

    /**
     * Cuenta corriente del cliente.
     */
    private CuentaCorriente corriente;

    /**
     * Cuenta de ahorros del cliente.
     */
    private CuentaAhorros ahorros;

    /**
     * CDT del cliente.
     */
    private CDT inversion;
    
    /*
     * Array de transacciones
     */
    private ArrayList<Transaccion> transacciones;

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Inicializa el simulador con la información del cliente. <br>
     * <b>post: </b> El mes fue inicializado en 1, y las tres cuentas (CDT, corriente y de ahorros) fueron inicializadas como vacías. <br>
     * @param pCedula Cédula del nuevo cliente. pCedula != null && pCedula != "".
     * @param pNombre Nombre del nuevo cliente. pNombre != null && pNombre != "".
     */
    public SimuladorBancario( String pCedula, String pNombre )
    {
        // Inicializa los atributos personales del cliente
        nombre = pNombre;
        cedula = pCedula;
        // Inicializa el mes en el 1
        mesActual = 1;
        consecutivoActual = 1;
        // Inicializa las tres cuentas en vacío
        corriente = new CuentaCorriente( );
        ahorros = new CuentaAhorros( );
        inversion = new CDT( );
        transacciones = new ArrayList<Transaccion>();
    }

    /**
     * Retorna el nombre del cliente.
     * @return Nombre del cliente.
     */
    public String darNombre( )
    {
        return nombre;
    }
    
    public double darInteresGenerado() {
    	return interesGenerado + ahorros.darInteresGenerado();
    }

    /**
     * Retorna la cédula del cliente.
     * @return Cédula del cliente.
     */
    public String darCedula( )
    {
        return cedula;
    }

    /**
     * Retorna la cuenta corriente del cliente.
     * @return Cuenta corriente del cliente.
     */
    public CuentaCorriente darCuentaCorriente( )
    {
        return corriente;
    }

    /**
     * Retorna el CDT del cliente.
     * @return CDT del cliente.
     */
    public CDT darCDT( )
    {
        return inversion;
    }

    /**
	 * Retorna la cuenta de ahorros del cliente.
	 * @return Cuenta de ahorros del cliente.
	 */
	public CuentaAhorros darCuentaAhorros( )
	{
	    return ahorros;
	}

	/**
     * Retorna el mes en el que se encuentra la simulación.
     * @return Mes actual.
     */
    public int darMesActual( )
    {
        return mesActual;
    }
    
    /*
     * Retorna transacciones
     */
    public ArrayList<Transaccion> darTransacciones()
    {
    	return transacciones;
    }
    
    /**
     * Calcula el saldo total de las cuentas del cliente.
     * @return Saldo total de las cuentas del cliente.
     */
    public double calcularSaldoTotal( )
    {
    	verificarInvariante();
        return corriente.darSaldo( ) + ahorros.darSaldo( ) + inversion.calcularValorPresente( mesActual );
    }

    /**
     * Invierte un monto de dinero en un CDT. <br>
     * <b>post: </b> Invirtió un monto de dinero en un CDT.
     * @param pMonto Monto de dinero a invertir en un CDT. pMonto > 0.
     * @param pInteresMensual Interés del CDT elegido por el cliente.
     */
    public void invertirCDT( double pMonto, String pInteresMensual ) throws Exception
    {
    	verificarInvariante();
		double pInteres = Double.parseDouble(pInteresMensual) / 100.0;
		inversion.invertir( pMonto, pInteres, mesActual );
		addTransaccion(	new Transaccion(consecutivoActual, pMonto, Transaccion.TipoTransaccion.ENTRADA , Transaccion.TipoCuenta.CDT ));
    }

    /**
     * Consigna un monto de dinero en la cuenta corriente. <br>
     * <b>post: </b> Consignó un monto de dinero en la cuenta corriente.
     * @param pMonto Monto de dinero a consignar en la cuenta. pMonto > 0.
     */
    public void consignarCuentaCorriente( double pMonto )
    {
        corriente.consignarMonto( pMonto );
		addTransaccion(	new Transaccion(consecutivoActual, pMonto, Transaccion.TipoTransaccion.ENTRADA , Transaccion.TipoCuenta.CORRIENTE ));
    }

    /**
     * Consigna un monto de dinero en la cuenta de ahorros. <br>
     * * <b>post: </b> Consignó un monto de dinero en la cuenta de ahorros.
     * @param pMonto Monto de dinero a consignar en la cuenta. pMonto > 0.
     */
    public void consignarCuentaAhorros( double pMonto )
    {
    	verificarInvariante();
        ahorros.consignarMonto( pMonto );
		addTransaccion(	new Transaccion(consecutivoActual, pMonto, Transaccion.TipoTransaccion.ENTRADA , Transaccion.TipoCuenta.AHORROS ));
    }

    /**
     * Retira un monto de dinero de la cuenta corriente. <br>
     * <b>pre: </b> La cuenta corriente ha sido inicializada
     * <b>post: </b> Si hay saldo suficiente, entonces este se redujo en el monto especificado.
     * @param pMonto Monto de dinero a retirar de la cuenta. pMonto > 0.
     */
    public void retirarCuentaCorriente( double pMonto )
    {
    	verificarInvariante();
        corriente.retirarMonto( pMonto );
		addTransaccion(	new Transaccion(consecutivoActual, pMonto, Transaccion.TipoTransaccion.SALIDA , Transaccion.TipoCuenta.CORRIENTE ));
    }

    /**
     * Retira un monto de dinero de la cuenta de ahorros. <br>
     * <b>post: </b> Se redujo el saldo de la cuenta en el monto especificado.
     * @param pMonto Monto de dinero a retirar de la cuenta. pMonto > 0.
     */
    public void retirarCuentaAhorros( double pMonto )
    {
    	verificarInvariante();
        ahorros.retirarMonto( pMonto );
		addTransaccion(	new Transaccion(consecutivoActual, pMonto, Transaccion.TipoTransaccion.SALIDA , Transaccion.TipoCuenta.AHORROS ));
    }
    

    /**
     * Avanza en un mes la simulación. <br>
     * <b>post: </b> Se avanzó el mes de la simulación en 1. Se actualizó el saldo de la cuenta de ahorros.
     */
    public void avanzarMesSimulacion( )
    {
    	double valorCDT = inversion.darValorInvertido() * inversion.darInteresMensual();
    	double valorAhorros = ahorros.darSaldo() * ahorros.darInteresMensual() ;
        mesActual += 1;
        ahorros.actualizarSaldoPorPasoMes( );
        verificarInvariante();
        
        if(valorAhorros != 0)
        {
        	addTransaccion(	new Transaccion(consecutivoActual, valorAhorros , Transaccion.TipoTransaccion.ENTRADA , Transaccion.TipoCuenta.AHORROS ));
        }
        
		if (valorCDT != 0)
		{
			addTransaccion(	new Transaccion(consecutivoActual, valorCDT , Transaccion.TipoTransaccion.ENTRADA , Transaccion.TipoCuenta.CDT ));
		}
    }
    
    /**
     * Avanza la simulción un numero de meses dado por parámetro.
     * @param pMeses numero de meses a avanzar
     * <b>post: </b> Se avanzaron los meses de la simulación. Se actualizaron los saldos.
     */
    public void metodo1( int pMeses )
    {
    	for(int i = 1 ; i <= pMeses ; i++)
    	{
    		avanzarMesSimulacion();
    		System.out.print("mes: " + i);
    	}
    	verificarInvariante();
    }
    
    /**
     * Cierra el CDT, pasando el saldo a la cuenta corriente. <br>
     * <b>pre: </b> La cuenta corriente y el CDT han sido inicializados. <br>
     * <b>post: </b> El CDT quedó cerrado y con valores en 0, y la cuenta corriente aumentó su saldo en el valor del cierre del CDT.
     */
    public void cerrarCDT( )
    {
    	//TODO: 8 agregar el interes generado por el cdt al total de la simulacion
    	verificarInvariante();
        interesGenerado += inversion.darInteresGenerado(mesActual);
        double valorCierreCDT = inversion.cerrar( mesActual );
        corriente.consignarMonto( valorCierreCDT );
        /*
         * Añadir al array transacciones
         */
		addTransaccion(	new Transaccion(consecutivoActual, valorCierreCDT, Transaccion.TipoTransaccion.SALIDA , Transaccion.TipoCuenta.CDT ));
		addTransaccion(	new Transaccion(consecutivoActual, valorCierreCDT , Transaccion.TipoTransaccion.ENTRADA , Transaccion.TipoCuenta.CORRIENTE ));

    }
    
    /**
     * Retrira el saldo total la cuenta de ahorros, pasandolo a la cuenta corriente. <br>
     * <b>pre: </b> La cuenta corriente y el la cuenta de ahorros han sido inicializados. <br>
     * <b>post: </b> La cuenta de ahorros queda vacia ( con valores en 0 ), y la cuenta corriente aumentó su saldo en el valor del saldo total que tenia la cuenta de ahorros.
     */
    public void pasarAhorrosToCorriente()
    {
    	double cantidad = ahorros.darSaldo();
    	ahorros.cerrarCuenta(); 
    	corriente.consignarMonto(cantidad);
    	
    	addTransaccion(	new Transaccion(consecutivoActual, cantidad, Transaccion.TipoTransaccion.SALIDA , Transaccion.TipoCuenta.AHORROS ));
		addTransaccion(	new Transaccion(consecutivoActual, cantidad , Transaccion.TipoTransaccion.ENTRADA , Transaccion.TipoCuenta.CORRIENTE ));
    }


    /**
     * Reinicia la simulación.
     * @return interes total generado por la simulación.
     */
    public double metodo2( )//reinicia
    {	
    	cerrarCDT();
    	corriente.cerrarCuenta();
    	double respuesta = interesGenerado + ahorros.darInteresGenerado();
    	ahorros.cerrarCuenta();
    	interesGenerado = 0;
    	mesActual = 1;
    	transacciones.clear();
        return respuesta;
    }
    
    /*
     * Reto Final
     */
	public int metodo3(int pTipo, int pCuenta) 
	{
		int mayorConsecutivo = 0;
		double mayorValor = 0.0;

        for( Transaccion actual : transacciones )
        {
        	if(actual.getTipoTransaccion().ordinal() == pTipo && actual.getTipoCuenta().ordinal() == pCuenta) 
        	{
    			if(actual.getValor() > mayorValor)
    			{
    				mayorValor = actual.getValor();
    				mayorConsecutivo = actual.getConsecutivo();
        		}
        	}
        }
		return mayorConsecutivo;
	}
	
	public void addTransaccion(Transaccion nuevaTransaccion)
	{
		transacciones.add(nuevaTransaccion);
		consecutivoActual ++;
		System.out.println(Integer.toString(nuevaTransaccion.getConsecutivo()) + " " + nuevaTransaccion.getTipoTransaccion() +" " + nuevaTransaccion.getTipoCuenta() + " " + Double.toString(nuevaTransaccion.getValor() ) );
	}
	
	/*
	 * Metodos para validar
	 */
	private boolean validarExistenciaObjetos() 
	{
		return (corriente != null && ahorros != null && inversion != null);
	} 
	
	private boolean validarExistenAtributos()
	{
		boolean atributosInt = mesActual >= 1 && interesGenerado >= 0 ;
		boolean atributoCedula =  cedula != null && cedula != "" ;
		boolean atributoNombre =  nombre != null && nombre != "" ;
		return (atributosInt && atributoCedula && atributoNombre);
	}
	
	private boolean validarSaldoAInteres() 
	{
		return (ahorros.darSaldo( ) + inversion.calcularValorPresente( mesActual )) < INVERSION_MAXIMO;
	}
	
	/*
	 * Metodo invariante
	 */
	private void verificarInvariante()
	{
		 assert validarExistenciaObjetos() : "Los obejtos sin inicializar" ;
		 assert validarExistenAtributos() : "Los atributos deben ser positivos";
		 assert validarSaldoAInteres() : "ERROR: SE SUPERÓ EL MONTO MÁXIMO DE INVERSIÓN";
	}
}