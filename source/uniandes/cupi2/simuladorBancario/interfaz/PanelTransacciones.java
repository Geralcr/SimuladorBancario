package uniandes.cupi2.simuladorBancario.interfaz;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PanelTransacciones extends JPanel implements ListSelectionListener {

	private InterfazSimulador principal;
	
	private JList transacciones;
	
	private JScrollPane scroll;
	
	public PanelTransacciones(InterfazSimulador pPrincipal)
	{
		principal = pPrincipal;
		
		JPanel panelDetalles = new JPanel();
		panelDetalles.setBorder(new TitledBorder("Detalles"));
		
		setLayout( new BorderLayout( ) );
		
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

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
