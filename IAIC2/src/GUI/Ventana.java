/**
 * 
 */
package GUI;

/**
 *
 * @author  
 */
public class Ventana extends javax.swing.JFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
    private javax.swing.JCheckBox jCheckBox1;
	
    /**
	 * 
	 */
    private javax.swing.JCheckBox jCheckBox2;
    
	/**
	 * 
	 */
    private javax.swing.JCheckBox jCheckBox3;
    
	/**
	 * 
	 */
    private javax.swing.JCheckBox jCheckBox4;
    
	/**
	 * 
	 */
    private javax.swing.JCheckBox jCheckBox5;
    
	/**
	 * 
	 */
    private javax.swing.JCheckBox jCheckBox6;
    
	/**
	 * 
	 */
    private javax.swing.JCheckBox jCheckBox7;
    
	/**
	 * 
	 */
    private javax.swing.JCheckBox jCheckBox8;
    
	/**
	 * 
	 */
    private javax.swing.JCheckBox jCheckBox9;
    
	/**
	 * 
	 */
    private javax.swing.JComboBox jComboBox1;
    
	/**
	 * 
	 */
    private javax.swing.JComboBox jComboBox2;
    
	/**
	 * 
	 */
    private javax.swing.JComboBox jComboBox3;
    
	/**
	 * 
	 */
    private javax.swing.JDesktopPane jDesktopPane1;
    
	/**
	 * 
	 */
    private javax.swing.JLabel jLabel1;
    
	/**
	 * 
	 */
    private javax.swing.JLabel jLabel2;
    
	/**
	 * 
	 */
    private javax.swing.JLabel jLabel3;
    
	/**
	 * 
	 */
    private javax.swing.JLabel jLabel4;
    
	/**
	 * 
	 */
    private javax.swing.JLabel jLabel5;
    
	/**
	 * 
	 */
    private javax.swing.JLabel jLabel6;
    
	/**
	 * 
	 */
    private javax.swing.JToggleButton jToggleButton1;
	
	/**
	 * 
	 */
    public Ventana() {
        initComponents();
    }
    
    /**
     * 
     */
    private void initComponents() {
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jToggleButton1 = new javax.swing.JToggleButton();
        jCheckBox5 = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jDesktopPane1.setBackground(new java.awt.Color(255, 255, 102));
        jLabel1.setFont(new java.awt.Font("Batang", 1, 14));
        jLabel1.setText("Introduzca sus Preferencias");
        jLabel1.setBounds(120, 20, 210, 30);
        jDesktopPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setFont(new java.awt.Font("Batang", 1, 12));
        jLabel2.setText("Precio:              De:");
        jLabel2.setBounds(20, 90, 150, 20);
        jDesktopPane1.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel3.setFont(new java.awt.Font("Batang", 1, 11));
        jLabel3.setText("Hasta:");
        jLabel3.setBounds(280, 90, 50, 14);
        jDesktopPane1.add(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Indiferente", "50", "100", "200" }));
        jComboBox1.setBounds(180, 90, 90, 20);
        jDesktopPane1.add(jComboBox1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Indiferente", "50", "100", "200" }));
        jComboBox2.setDoubleBuffered(true);
        jComboBox2.setBounds(340, 90, 90, 20);
        jDesktopPane1.add(jComboBox2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel4.setFont(new java.awt.Font("Batang", 1, 12));
        jLabel4.setText("Tama\u00f1o:");
        jLabel4.setBounds(20, 140, 60, 15);
        jDesktopPane1.add(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jCheckBox1.setBackground(new java.awt.Color(255, 204, 0));
        jCheckBox1.setText("Peque\u00f1o");
        jCheckBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox1.setBounds(140, 140, 70, 15);
        jDesktopPane1.add(jCheckBox1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jCheckBox2.setBackground(new java.awt.Color(255, 204, 51));
        jCheckBox2.setText("Mediano");
        jCheckBox2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox2.setBounds(240, 140, 70, 15);
        jDesktopPane1.add(jCheckBox2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jCheckBox3.setBackground(new java.awt.Color(255, 204, 51));
        jCheckBox3.setText("Grande");
        jCheckBox3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox3.setBounds(340, 140, 80, 15);
        jDesktopPane1.add(jCheckBox3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jCheckBox4.setBackground(new java.awt.Color(204, 204, 255));
        jCheckBox4.setText("Manos Libres");
        jCheckBox4.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox4.setBounds(30, 190, 100, 15);
        jDesktopPane1.add(jCheckBox4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jToggleButton1.setText("Mostrar Modelos");
        jToggleButton1.setBounds(160, 330, 160, 20);
        jDesktopPane1.add(jToggleButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jCheckBox5.setBackground(new java.awt.Color(204, 204, 255));
        jCheckBox5.setText("MP3");
        jCheckBox5.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox5.setBounds(190, 190, 60, 15);
        jDesktopPane1.add(jCheckBox5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel5.setFont(new java.awt.Font("Batang", 1, 12));
        jLabel5.setText("Frecuencia de Uso:");
        jLabel5.setBounds(10, 240, 140, 15);
        jDesktopPane1.add(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jCheckBox6.setBackground(new java.awt.Color(102, 255, 204));
        jCheckBox6.setFont(new java.awt.Font("Batang", 1, 12));
        jCheckBox6.setText("Poco");
        jCheckBox6.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox6.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox6.setBounds(180, 240, 51, 15);
        jDesktopPane1.add(jCheckBox6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jCheckBox7.setBackground(new java.awt.Color(102, 255, 204));
        jCheckBox7.setFont(new java.awt.Font("Batang", 1, 12));
        jCheckBox7.setText("Moderado");
        jCheckBox7.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox7.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox7.setBounds(260, 240, 87, 15);
        jDesktopPane1.add(jCheckBox7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jCheckBox8.setBackground(new java.awt.Color(102, 255, 204));
        jCheckBox8.setFont(new java.awt.Font("Batang", 1, 12));
        jCheckBox8.setText("Mucho");
        jCheckBox8.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox8.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox8.setBounds(370, 240, 73, 15);
        jDesktopPane1.add(jCheckBox8, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jCheckBox9.setBackground(new java.awt.Color(204, 204, 255));
        jCheckBox9.setText("Viaja al Extranjero");
        jCheckBox9.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox9.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox9.setBounds(310, 190, 120, 15);
        jDesktopPane1.add(jCheckBox9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel6.setBackground(new java.awt.Color(0, 204, 102));
        jLabel6.setFont(new java.awt.Font("Batang", 1, 12));
        jLabel6.setText("\u00bfSuele usar la c\u00e1mara de fotos?");
        jLabel6.setBounds(10, 290, 220, 15);
        jDesktopPane1.add(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No", "Casi nunca", "A veces", "Mucho" }));
        jComboBox3.setBounds(300, 290, 120, 22);
        jDesktopPane1.add(jComboBox3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jDesktopPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jDesktopPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
        );
        pack();
    }
    
    /**
     * 
     * @param args
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }
   
}
