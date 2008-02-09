/**
 * 
 */
package salidaPantalla;

/**
 * Clase que implementa un Frame que usamos de manera
 * auxiliar en el micromundo.
 */
public class VentanaInfo extends javax.swing.JFrame {
    
	/**
	 * Indica la version de JFrame.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Es una referencia a la ventana principal
	 */
	private VPrincipal referencia;
	
	/**
	 * Es el botón Aceptar.
	 */
    private javax.swing.JButton jButton1;
    
    /**
     * Contenedor que se usa para crear el interfaz
     */
    private javax.swing.JDesktopPane jDesktopPane1;
    
    /**
     * Un panel con barras de desplazamiento
	 * para el área de texto.
     */
    private javax.swing.JScrollPane jScrollPane1;
    
    /**
     * Es el área de texto que se utiliza para mostrar
     * información. 
     */
    private javax.swing.JTextArea jTextArea1;
	
    /**
     * Constructor por defecto
     */
    public VentanaInfo() {
        initComponents();
    }
    
	/**
	 * 
	 * @param r es el objeto de la clase VPrincipal 
	 * que llama al objeto de la clase VentanaInfo.
	 * @param mensaje es el texto que hay que mostrar
	 * en el área de texto.
	 */
    public VentanaInfo(VPrincipal r,String mensaje) {
        referencia = r;
        initComponents();
        jTextArea1.setText(mensaje);
        referencia.setEnabled(false);
        jTextArea1.setEditable(false);        
    }
    
    /**
     * Método que cambia el tamaño del objeto VentanaInfo
     * a un tamaño predefinido por nosotros.
     */
    public void cambiaTamanio(){
    	
    	this.setBounds(100, 100, 600, 400);
    	jScrollPane1.setBounds(2, 2, 590, 300);
    	jButton1.setBounds(250, 330, 80, 20);
    	jDesktopPane1.setBackground(new java.awt.Color(215, 115,15 ));
    }
    
    /**
     * Método privado que inicializa todos los atributos
	 * del entorno gráfico de la clase VentanaInfo.
     */
    private void initComponents() {
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        jDesktopPane1.setBackground(new java.awt.Color(102, 204, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jScrollPane1.setBounds(2, 2, 296, 223);
        jDesktopPane1.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton1.setText("Aceptar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton1.setBounds(110, 250, 80, 20);
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
        setSize(307, 320);
        setLocation(100, 100);
        setResizable(false);
    }

    /**
     * Método que elimina el objeto de la clase VentanaInfo
     * y devuelve el control al objeto de la clase VPrincipal
     * que le invocó. 
     * @param evt Evento asociado a pulsar con el ratón 
     * o bien al darle espacio sobre el botón Aceptar.
     */
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
    	referencia.setEnabled(true);
     	dispose();
    }

}
