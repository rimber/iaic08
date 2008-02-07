/**
 * 
 */
package salidaPantalla;

/**
 * 
 */
public class VentanaPedirDato extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private VPrincipal referencia;
	
	/**
	 * 
	 */
    public VentanaPedirDato() {
        initComponents();
    }
    
    /**
     * 
     * @param r
     */
    public VentanaPedirDato(VPrincipal r) {
        referencia = r;
        initComponents();        
        referencia.setEnabled(false); 
    }
    
    /**
     * 
     */   
    // <editor-fold defaultstate="collapsed" desc=" Código Generado ">//GEN-BEGIN:initComponents
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
     * 
     */
    private void initComponents() {
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel = new javax.swing.JLabel();
        jLabel.setText("Introduzca dimensión del edificio");
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
    }// </editor-fold>//GEN-END:initComponents

    /**
     * 
     * @param evt
     */
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
    	ponEleccion();
    	referencia.setEnabled(true);
    	this.setVisible(false);
    	referencia.empiezaJugar();
     	dispose();
    }//GEN-LAST:event_jButton1MouseClicked
    
    
    // Declaración de variables - no modificar//GEN-BEGIN:variables
    /**
     * 
     */
    private javax.swing.JButton jButton1;
    
    /**
     * 
     */
    private javax.swing.JDesktopPane jDesktopPane1; 
    
    /**
     * 
     */
    private javax.swing.JTextField jTextField;
    
    /**
     * 
     */
    private javax.swing.JLabel jLabel;
    // Fin de declaración de variables//GEN-END:variables
}
