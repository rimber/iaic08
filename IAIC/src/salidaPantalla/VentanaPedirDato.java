/**
 * Contiene el conjunto de clases que implementan el interface del micromundo 
 * del edificio c�bico.
 */
package salidaPantalla;

/**
 * Implementa la interfaz auxiliar de la ventana de petici�n de datos al usuario.
 */
public class VentanaPedirDato extends javax.swing.JFrame {

    /**
	 * Indica la version del JFrame.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Referencia al formulario que ha abierto esta ventana.
	 */
	private VPrincipal referencia;
	
    /**
     * Bot�n aceptar.
     */
    private javax.swing.JButton jButton1;
    
    /**
     * Panel sobre el que se a�aden todos los elementos del interface.
     */
    private javax.swing.JDesktopPane jDesktopPane1; 
    
    /**
     * Campo de texto sobre el que va a escribir el usuario.
     */
    private javax.swing.JTextField jTextField;
    
    /**
     * Etiqueta informativa para el usuario.
     */
    private javax.swing.JLabel jLabel;
	
	/**
	 * Crea una nueva ventana
	 */
    public VentanaPedirDato() {
        initComponents();
    }
    
    /**
     * Constructor que crea una instancia nueva de Ventana a partir de una
     * referencia a otra ventana.
     * @param r Referencia al formulario llamante.
     */
    public VentanaPedirDato(VPrincipal r) {
        referencia = r;
        initComponents();        
        referencia.setEnabled(false); 
    }
    
    /**
     * Informa al formulario llamante de la elecci�n del usuario.
     */   
    private void ponEleccion(){
    	int dim=1;
    	String dato =jTextField.getText();
    	try{    		
    		dim=Integer.valueOf(dato);
    		referencia.setDimension(dim);    
    	}catch(Exception e){
    		VentanaInfo ven=new VentanaInfo(referencia,"NO HAS INTRODUCIDO UN NUMERO");
        	ven.setTitle("Error");
        	ven.setVisible(true); 
    	}    	    	
    }
    
    /**
     * Inicializa los componentes de la ventana.
     */
    private void initComponents() {
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel = new javax.swing.JLabel();
        jLabel.setText("Introduzca dimensi�n del edificio");
        jTextField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        jDesktopPane1.setBackground(new java.awt.Color(204, 255, 204));
        jTextField.setText(""); 
        
        jTextField.setBounds(120, 70,40,20);
        jTextField.setVisible(true);
        jDesktopPane1.add(jTextField, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jButton1.setText("Aceptar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jLabel.setFont(new java.awt.Font("Batang", 1, 14));      
        jLabel.setBounds(30, 40, 350, 20);
        jDesktopPane1.add(jLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jButton1.setBounds(100, 110, 80, 20);
        jDesktopPane1.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jDesktopPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDesktopPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
        );
        pack();
        setSize(300, 200);
        setLocation(100, 100);
        setResizable(false);
    }

    /**
     * M�todo que implementa las acciones que se realizan al pulsar el boton aceptar,
     * informa de la elecci�n al formulario principal, activa el empezar a jugar, y se cierra.
     * @param evt Evento asociado.
     */
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
    	ponEleccion();
    	referencia.setEnabled(true);
    	this.setVisible(false);
    	referencia.empiezaJugar();
     	dispose();
    }    
    
}
