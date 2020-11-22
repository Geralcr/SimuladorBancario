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
public class PanelCDT extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para abrir un CDT.
     */
    private final static String ABRIR_CDT = "ABRIR CDT";

    /**
     * Constante para cerrar un CDT.
     */
    private final static String CERRAR_CDT = "CERRAR CDT";

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
     * Etiqueta del saldo del CDT.
     */
    private JLabel etiquetaSaldoCdt;

    /**
     * Campo donde se visualiza el saldo del CDT.
     */
    private JTextField txtSaldoCdt;

    /**
     * Boton para abrir un nuevo CDT.
     */
    private JButton btnAbrirCDT;

    /**
     * Boton para cancelar un CDT.
     */
    private JButton btnCerrarCDT;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo panel e inicializa sus elementos. <br>
     * <b>post: </b> Se inicializó el panel.
     * @param pPrincipal Ventana principal de la aplicación. pPrincipal != null.
     */
    public PanelCDT( InterfazSimulador pPrincipal )
    {

        // Inicializa los elementos del panel
        principal = pPrincipal;

        etiquetaSaldoCdt = new JLabel( "Saldo CDT: " );
        txtSaldoCdt = new JTextField( 14 );
        txtSaldoCdt.setEditable( false );

        btnAbrirCDT = new JButton( "Abrir" );
        btnAbrirCDT.setActionCommand( ABRIR_CDT );
        btnAbrirCDT.addActionListener( this );
        btnAbrirCDT.setPreferredSize( new Dimension( 100, 0 ) );

        btnCerrarCDT = new JButton( "Cerrar" );
        btnCerrarCDT.setActionCommand( CERRAR_CDT );
        btnCerrarCDT.addActionListener( this );
        btnCerrarCDT.setPreferredSize( new Dimension( 100, 0 ) );

        JPanel panelInfo = new JPanel( );
        JPanel panelBotones = new JPanel( );
        panelInfo.setLayout( new GridLayout( 1, 2 ) );
        panelInfo.setBorder( new EmptyBorder( 0, 0, 5, 0 ) );
        panelBotones.setLayout( new BorderLayout( ) );
        panelBotones.setBorder( new EmptyBorder( 0, 5, 5, 5 ) );
        panelBotones.setPreferredSize( new Dimension( 215, 0 ) );

        // Ubica los elementos en el panel
        panelInfo.add( etiquetaSaldoCdt );
        panelInfo.add( txtSaldoCdt );
        panelBotones.add( btnAbrirCDT, BorderLayout.WEST );
        panelBotones.add( new JLabel( " " ), BorderLayout.CENTER );
        panelBotones.add( btnCerrarCDT, BorderLayout.EAST );

        setLayout( new BorderLayout( ) );
        add( panelInfo, BorderLayout.CENTER );
        add( panelBotones, BorderLayout.EAST );
        setBorder( new CompoundBorder( new EmptyBorder( 0, 3, 5, 3 ), new TitledBorder( "CDT" ) ) );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza el saldo del CDT del cliente en la interfaz. <br>
     * <b>post: </b> Se actualizó la información con el saldo en CDT.
     * @param pSaldo Saldo en el CDT del cliente. pSaldo != null.
     */
    public void actualizarSaldoCDT( String pSaldo )
    {
        txtSaldoCdt.setText( pSaldo );
    }

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Evento de click sobre un botón. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String command = pEvento.getActionCommand( );
        if( command.equals( ABRIR_CDT ) )
        {
            String strValor = JOptionPane.showInputDialog( principal, "Introduzca el monto de dinero de la inversión", "Abrir CDT", JOptionPane.QUESTION_MESSAGE );
            if( strValor != null )
            {
                String strInteres = JOptionPane.showInputDialog( principal, "Introduzca el interés mensual en porcentaje", "Abrir CDT", JOptionPane.QUESTION_MESSAGE );
                if( strInteres != null )
                {
                    principal.invertirCDT( strValor, strInteres );
                }
            }
        }
        else if( command.equals( CERRAR_CDT ) )
        {
            principal.cerrarCDT( );
        }


    }

}
