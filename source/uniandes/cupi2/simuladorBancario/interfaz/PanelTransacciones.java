package uniandes.cupi2.simuladorBancario.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uniandes.cupi2.simuladorBancario.mundo.Transaccion;

@SuppressWarnings("serial")
public class PanelTransacciones extends JPanel implements ListSelectionListener {

	private InterfazSimulador principal;
	
	private JList transacciones;
	
	private JScrollPane scroll;
	
	private JLabel labConsecutivo;
	private JLabel labTipo;
	private JLabel labCuenta;
	private JLabel labValor;
	
	private JTextField txtConsecutivo;
	private JTextField txtTipo;
	private JTextField txtCuenta;
	private JTextField txtValor;
	
	
	public PanelTransacciones(InterfazSimulador pPrincipal)
	{
		principal = pPrincipal;
		setLayout( new BorderLayout( ) );
		
		JPanel panelDetalles = new JPanel();
		panelDetalles.setLayout(new GridLayout( 4, 2 ));
		panelDetalles.setBorder( new EmptyBorder( 10, 10, 10, 10 ) );
		panelDetalles.setBorder(new TitledBorder("Detalles"));

		labConsecutivo = new JLabel("Consecutivo: ");
		panelDetalles.add(labConsecutivo);
		txtConsecutivo = new JTextField();
		txtConsecutivo.setEditable(false);
		panelDetalles.add(txtConsecutivo);
		
		labTipo = new JLabel("Tipo: ");
		panelDetalles.add(labTipo);
		txtTipo = new JTextField();
		txtTipo.setEditable(false);
		panelDetalles.add(txtTipo);
		
		labCuenta = new JLabel("Cuenta: ");
		panelDetalles.add(labCuenta);
		txtCuenta = new JTextField();
		txtCuenta.setEditable(false);
		panelDetalles.add(txtCuenta);
		
		labValor = new JLabel("Valor: ");
		panelDetalles.add(labValor);
		txtValor = new JTextField();
		txtValor.setEditable(false);
		panelDetalles.add(txtValor);
		
        transacciones = new JList( );
        transacciones.addListSelectionListener( this );
        scroll = new JScrollPane( );
        
        setBorder( new TitledBorder( "Historial de transacciones" ) );
        scroll.setViewportView( transacciones );
        scroll.setVerticalScrollBarPolicy( javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scroll.setHorizontalScrollBarPolicy( javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );

		add(scroll, BorderLayout.WEST);
		add(panelDetalles, BorderLayout.CENTER);
	}
	public void refrescar( ArrayList<Transaccion> pLista )
    {
		transacciones.setListData( pLista.toArray( ) );
    }
	
	//@Override
	public void valueChanged(ListSelectionEvent evento) {
		// TODO Auto-generated method stub
		  if( transacciones.getSelectedValue( ) != null )
	        {
	            Transaccion pTransaccion = ( Transaccion )transacciones.getSelectedValue( );
	            NumberFormat formatter = NumberFormat.getNumberInstance( );
	            String consString = formatter.format( pTransaccion.getConsecutivo() );
	            txtConsecutivo.setText( consString );
	            txtCuenta.setText( pTransaccion.getTipoCuenta().toString() );
	            txtTipo.setText( pTransaccion.getTipoTransaccion().toString() );
	            String valueString = formatter.format( pTransaccion.getValor() );
	            txtValor.setText( "$" + valueString );
	            
	        }
	        else
	        {
	        	txtConsecutivo.setText( "" );
	        	txtCuenta.setText( "" );
	        	txtTipo.setText( "" );
	        	txtValor.setText( "" );
	        }
	}
	
	public void cambiarSeleccionado( Transaccion pTransaccion )
	    {
	        transacciones.setSelectedValue( pTransaccion, true );
	    }
}
