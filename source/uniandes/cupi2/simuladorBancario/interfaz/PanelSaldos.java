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
package uniandes.cupi2.simuladorBancario.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * Panel con la información bancaria del cliente.
 */
@SuppressWarnings("serial")
public class PanelSaldos extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para avanzar un mes de la simulación.
     */
    private final static String AVANZAR_MES = "AVANZAR MES";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación.
     */
    private InterfazSimulador principal;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta de saldo total.
     */
    private JLabel etiquetaSaldo;

    /**
     * Etiqeta mes.
     */
    private JLabel etiquetaMes;

    /**
     * Campo donde se visualiza el saldo total.
     */
    private JTextField txtSaldo;

    /**
     * Campo para visualizar el mes actual de la simulación.
     */
    private JTextField txtMes;

    /**
     * Botón para avanzar de mes en la simulación.
     */
    private JButton botonAvanzarMes;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea el nuevo panel Central, e inicializa todos sus componentes. <br>
     * <b>post: </b> Se inicializó el panel.
     * @param pPrincipal Ventana principal. pPrincipal != null.
     */
    public PanelSaldos( InterfazSimulador pPrincipal )
    {

        // Inicializa los elementos del panel
        principal = pPrincipal;
        etiquetaSaldo = new JLabel( "Total: " );
        etiquetaSaldo.setHorizontalAlignment( JLabel.RIGHT );
        etiquetaMes = new JLabel( "Mes: " );
        txtSaldo = new JTextField( );
        txtSaldo.setPreferredSize( new Dimension( 215, 0 ) );
        txtMes = new JTextField( 2 );
        botonAvanzarMes = new JButton( );
        botonAvanzarMes.setText( "Avanzar mes" );
        botonAvanzarMes.setActionCommand( AVANZAR_MES );
        botonAvanzarMes.addActionListener( this );
        botonAvanzarMes.setPreferredSize( new Dimension( 215, 0 ) );

        txtMes.setEditable( false );
        txtSaldo.setEditable( false );

        JPanel panelInfo = new JPanel( );
        panelInfo.setLayout( new BorderLayout( ) );
        panelInfo.setBorder( new EmptyBorder( 0, 5, 5, 5 ) );

        JPanel panelMes = new JPanel( );
        panelMes.setLayout( new GridLayout( 1, 2 ) );
        panelMes.setBorder( new EmptyBorder( 0, 0, 0, 5 ) );

        JPanel panelTotales = new JPanel( );
        panelTotales.setLayout( new BorderLayout( ) );
        panelTotales.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );

        panelMes.add( etiquetaMes );
        panelMes.add( txtMes );
        panelInfo.add( panelMes, BorderLayout.CENTER );
        panelInfo.add( botonAvanzarMes, BorderLayout.EAST );
        panelTotales.add( etiquetaSaldo, BorderLayout.CENTER );
        panelTotales.add( txtSaldo, BorderLayout.EAST );
        setLayout( new GridLayout( 2, 1 ) );

        add( panelInfo );
        add( panelTotales );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza en la interfaz el mes actual de la simulación. <br>
     * <b>post: </b> Se mostró el nuevo mes actual de la simulación.
     * @param pMes Mes actual. pMes > 0.
     */
    public void actualizarMes( int pMes )
    {
        txtMes.setText( "" + pMes );
    }

    /**
     * Actualiza el saldo total del cliente en la interfaz. <br>
     * <b>post: </b> Se actualizó la información con el saldo actual.
     * @param pSaldo Saldo total del cliente. pSaldo != null.
     */
    public void actualizarSaldoTotal( String pSaldo )
    {
        txtSaldo.setText( pSaldo );
    }

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Evento de click sobre un botón. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        try
        {
            String command = pEvento.getActionCommand( );
            if( command.equals( AVANZAR_MES ) )
            {
                principal.avanzarMesSimulacion( );
            }

        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, "Información inválida: intente de nuevo..." );
        }
    }

}
