package uniandes.cupi2.simuladorBancario.mundo;

public class Transaccion {
	
	public enum TipoTransaccion
	{
	  ENTRADA,
	  SALIDA
	  
	}
	public enum TipoCuenta
	{
		AHORROS,
		CORRIENTE,
		CDT
	}
	 
	private TipoTransaccion tipoTransaccion;
	private TipoCuenta tipoCuenta;
	
	private int consecutivo;
	private double valor;
	private String descripcion;
	
	public Transaccion(int pconsecutivo, double pValor,TipoTransaccion pTTransaccion, TipoCuenta pTCuenta, String pdescripcion) 
	{
		// TODO Auto-generated constructor stub
		consecutivo = pconsecutivo;
		valor = pValor;
		tipoTransaccion = pTTransaccion;
		tipoCuenta = pTCuenta;
		descripcion = pconsecutivo + " " + pdescripcion;
	}
	
	public int getConsecutivo()
	{
		return consecutivo;
	}
	
	public double getValor()
	{
		return valor;
	}
	
	public TipoTransaccion getTipoTransaccion()
	{
		return tipoTransaccion;
	}
	
	public TipoCuenta getTipoCuenta()
	{
		return tipoCuenta;
	}
	
	public String toString()
    {
        //String representacion = consecutivo + " " + tipoTransaccion +" " + tipoCuenta ;
        return descripcion;
    }
	
}
