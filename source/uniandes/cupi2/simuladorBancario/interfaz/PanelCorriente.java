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
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * Panel con las opciones de la aplicación.
 */
@SuppressWarnings("serial")
public class PanelCorriente extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para consignar en la cuenta corriente.
     */
    private final static String CONSIGNAR_CUENTA_CORRIENTE = "CONSIGNAR CUENTA CORRIENTE";

    /**
     * Constante para retirar dinero en la cuenta corriente.
     */
    private final static String RETIRAR_CUENTA_CORRIENTE = "RETIRAR CUENTA CORRIENTE";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal del simulador.
     */
    private InterfazSimulador principal;

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta del saldo de cuenta corriente.
     */
    private JLabel etiquetaSaldoCorriente;

    /**
     * Campo donde se visualiza el saldo el saldo de la cuenta corriente.
     */
    private JTextField txtSaldoCorriente;

    /**
     * Boton para consignar en la cuenta corriente.
     */
    private JButton btnConsignarCuentaCorriente;

    /**
     * Boton para hacer retiros de la cuenta corriente.
     */
    private JButton btnRetirarCuentaCorriente;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo panel e inicializa sus elementos. <br>
     * <b>post: </b> Se inicializó el panel.
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal != null.
     */
    public PanelCorriente( InterfazSimulador pPrincipal )
    {

        // Inicializa los elementos del panel
        principal = pPrincipal;

        etiquetaSaldoCorriente = new JLabel( "Saldo corriente: " );
        txtSaldoCorriente = new JTextField( 14 );
        txtSaldoCorriente.setEditable( false );

        btnConsignarCuentaCorriente = new JButton( "Consignar" );
        btnConsignarCuentaCorriente.setActionCommand( CONSIGNAR_CUENTA_CORRIENTE );
        btnConsignarCuentaCorriente.addActionListener( this );
        btnConsignarCuentaCorriente.setPreferredSize( new Dimension( 100, 0 ) );

        btnRetirarCuentaCorriente = new JButton( "Retirar" );
        btnRetirarCuentaCorriente.setActionCommand( RETIRAR_CUENTA_CORRIENTE );
        btnRetirarCuentaCorriente.addActionListener( this );
        btnRetirarCuentaCorriente.setPreferredSize( new Dimension( 100, 0 ) );

        JPanel panelInfo = new JPanel( );
        JPanel panelBotones = new JPanel( );
        panelInfo.setLayout( new GridLayout( 1, 2 ) );
        panelInfo.setBorder( new EmptyBorder( 0, 0, 5, 0 ) );
        panelBotones.setLayout( new BorderLayout( ) );
        panelBotones.setBorder( new EmptyBorder( 0, 5, 5, 5 ) );
        panelBotones.setPreferredSize( new Dimension( 215, 0 ) );

        // Ubica los elementos en el panel
        panelInfo.add( etiquetaSaldoCorriente );
        panelInfo.add( txtSaldoCorriente );
        panelBotones.add( btnConsignarCuentaCorriente, BorderLayout.WEST );
        panelBotones.add( new JLabel( " " ), BorderLayout.CENTER );
        panelBotones.add( btnRetirarCuentaCorriente, BorderLayout.EAST );

        setLayout( new BorderLayout( ) );
        add( panelInfo, BorderLayout.CENTER );
        add( panelBotones, BorderLayout.EAST );
        setBorder( new CompoundBorder( new EmptyBorder( 3, 3, 2, 3 ), new TitledBorder( "Cuenta corriente" ) ) );
    }

    // -----------------------------------------------------------------
    // Metodos
    // -----------------------------------------------------------------

    /**
     * Actualiza el saldo de la cuenta corriente del cliente en la interfaz. <br>
     * <b>post: </b> Se actualizó la información con el saldo de la cuenta de corriente.
     * @param pSaldo Saldo en la cuenta de corriente del cliente. pSaldo != null.
     */
    public void actualizarSaldoCorriente( String pSaldo )
    {
        txtSaldoCorriente.setText( pSaldo );
    }

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Evento de click sobre un botón. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String command = pEvento.getActionCommand( );
        if( command.equals( CONSIGNAR_CUENTA_CORRIENTE ) )
        {
            String strValor = JOptionPane.showInputDialog( principal, "Introduzca el monto de dinero a consignar", "Consignar", JOptionPane.QUESTION_MESSAGE );
            if( strValor != null )
                principal.consignarCorriente( strValor );
        }
        else if( command.equals( RETIRAR_CUENTA_CORRIENTE ) )
        {
            String strValor = JOptionPane.showInputDialog( principal, "Introduzca el monto de dinero a retirar", "Retirar", JOptionPane.QUESTION_MESSAGE );
            if( strValor != null )
                principal.retirarCorriente( strValor );
        }


    }

}
