package uniandes.cupi2.simuladorBancario.mundo;

public class Transaccion {
	
	public enum TipoTransaccion
	{
	  SALIDA,
	  ENTRADA
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
	
	public Transaccion(int pconsecutivo, double pValor,TipoTransaccion pTTransaccion, TipoCuenta pTCuenta) 
	{
		// TODO Auto-generated constructor stub
		consecutivo = pconsecutivo;
		valor = pValor;
		tipoTransaccion = pTTransaccion;
		tipoCuenta = pTCuenta;
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
	
	public String toString(String Valor )
    {
        String representacion = Valor + tipoCuenta.toString() ;
        return representacion;
    }
	
}
