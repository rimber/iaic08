package salidaPorPantalla;


import java.awt.BorderLayout;
import java.awt.GraphicsConfigTemplate;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.ToolTipManager;
import javax.swing.WindowConstants;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;


/**
 * This example displays a Swing based application with a JMenuBar that allows
 * you to switch between displaying a Cube and a Sphere. You can also perform a
 * simple screen capture and start/stop rotation using menu commands.
 */
public class SwingTest extends JPanel implements ActionListener {
  /**
   * the is root of the scenegraph which contains the cube and sphere objects
   */
  private BranchGroup sceneBranchGroup = null;

  /**
   * a rotation interpolator that automatically rotates the cube and sphere
   */
  private RotationInterpolator rotator = null;

  /**
   * Constructor. Set the layout algorithm for the panel and initialize the
   * Java 3D rendering system and view side scenegraph.
   */
  public SwingTest() {
	  setLayout(new BorderLayout());
       iniciar();
  }

  /**
   * Initialize the Java 3D rendering system and view side scenegraph.
   */
  protected void iniciar() {

    BranchGroup grupoHijo = createSceneBranchGroup();
    GraphicsConfiguration config=SimpleUniverse.getPreferredConfiguration();       
    Canvas3D canvas=new Canvas3D(config);
    SimpleUniverse universo=new SimpleUniverse(canvas);
    universo.addBranchGraph(grupoHijo);
    universo.getViewingPlatform().setNominalViewingTransform();
  }

  /**
   * Callback to allow the Canvas3D to be added to a Panel.
   */
  protected void addCanvas3D(Canvas3D c3d) {
    add(c3d, BorderLayout.CENTER);
  }

  /**
   * Create a Java 3D View and attach it to a ViewPlatform
   */
  

  /**
   * Create a Background for the Canvas3D.
   */
  protected Background createBackground() {
	  //colores del fondo
    Background back = new Background(new Color3f(0.9f, 0.9f, 0.9f));
    back.setApplicationBounds(createApplicationBounds());
    return back;
  }

  /**
   * Create a Bounds object for the scene.
   */
  protected Bounds createApplicationBounds() {
    return new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
  }

  /**
   * Create a Canvas3D.
   * 
   * @param offscreen
   *            true to specify an offscreen canvas
   */
  protected Canvas3D createCanvas3D(boolean offscreen) {
    GraphicsConfigTemplate3D gc3D = new GraphicsConfigTemplate3D();
    gc3D.setSceneAntialiasing(GraphicsConfigTemplate.PREFERRED);
    GraphicsDevice gd[] = GraphicsEnvironment.getLocalGraphicsEnvironment()
        .getScreenDevices();

    Canvas3D c3d = new Canvas3D(gd[0].getBestConfiguration(gc3D), offscreen);
    c3d.setSize(500, 500);

    return c3d;
  }


  /**
   * Callback to get the scale factor for the View side of the scenegraph
   */
  protected double getScale() {
    return 3;
  }

  /**
   * Get the TransformGroup for the View side of the scenegraph
   */
  public TransformGroup[] getViewTransformGroupArray() {
    TransformGroup[] tgArray = new TransformGroup[1];
    tgArray[0] = new TransformGroup();

    // move the camera BACK a little...
    // note that we have to invert the matrix as
    // we are moving the viewer
    Transform3D t3d = new Transform3D();
    t3d.setScale(getScale());
    t3d.setTranslation(new Vector3d(0.0, 3.0, -20.0));
    t3d.invert();
    tgArray[0].setTransform(t3d);

    return tgArray;
  }

  /**
   * Adds the View side of the scenegraph to the Locale
   */
  protected void addViewBranchGroup(Locale locale, BranchGroup bg) {
    locale.addBranchGraph(bg);
    
  }

  /**
   * Create the scene side of the scenegraph
   */
  protected BranchGroup createSceneBranchGroup() {
    // create the root of the scene side scenegraph
    BranchGroup objRoot = new BranchGroup();

    // create a TransformGroup to rotate the objects in the scene
    // set the capability bits on the TransformGroup so that it
    // can be modified at runtime
    TransformGroup objTrans = new TransformGroup();
    objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

    // create a spherical bounding volume
    BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
        100.0);

    // create a 4x4 transformation matrix
    Transform3D yAxis = new Transform3D();

    // create an Alpha interpolator to automatically generate
    // modifications to the rotation component of the transformation matrix
    Alpha rotationAlpha = new Alpha(-1, Alpha.INCREASING_ENABLE, 0, 0,
        8000, 0, 0, 0, 0, 0);

    // create a RotationInterpolator behavior to effect the TransformGroup
    rotator = new RotationInterpolator(rotationAlpha, objTrans, yAxis,
        0.0f, (float) Math.PI * 2.0f);

    // set the scheduling bounds on the behavior
    rotator.setSchedulingBounds(bounds);

    // add the behavior to the scenegraph
    objTrans.addChild(rotator);
    
    // create the BranchGroup which contains the objects
    // we add/remove to and from the scenegraph
    sceneBranchGroup = new BranchGroup();

    // allow the BranchGroup to have children added at runtime
    sceneBranchGroup.setCapability(Group.ALLOW_CHILDREN_EXTEND);
    sceneBranchGroup.setCapability(Group.ALLOW_CHILDREN_READ);
    sceneBranchGroup.setCapability(Group.ALLOW_CHILDREN_WRITE);

    // add both the cube and the sphere to the scenegraph
    sceneBranchGroup.addChild(createCube());
    //sceneBranchGroup.addChild(createSphere());

    // create the colors for the lights
    Color3f lColor1 = new Color3f(0.7f, 0.7f, 0.7f);
    Vector3f lDir1 = new Vector3f(-1.0f, -1.0f, -1.0f);
    Color3f alColor = new Color3f(0.2f, 0.2f, 0.2f);

    // create the ambient light
    AmbientLight aLgt = new AmbientLight(alColor);
    aLgt.setInfluencingBounds(bounds);

    // create the directional light
    DirectionalLight lgt1 = new DirectionalLight(lColor1, lDir1);
    lgt1.setInfluencingBounds(bounds);

    // add the lights to the scenegraph
    objRoot.addChild(aLgt);
    objRoot.addChild(lgt1);

    // wire the scenegraph together
    objTrans.addChild(sceneBranchGroup);
    objRoot.addChild(objTrans);

    // return the root of the scene side of the scenegraph
    return objRoot;
  }

  /**
   * Create a BranchGroup that contains a Cube. The user data for the
   * BranchGroup is set so the BranchGroup can be identified.
   */
  protected BranchGroup createCube() {
    BranchGroup bg = new BranchGroup();
    bg.setCapability(BranchGroup.ALLOW_DETACH);
    //bg.addChild(new com.sun.j3d.utils.geometry.ColorCube());
    bg.addChild(new com.sun.j3d.utils.geometry.Box());
    Bounds b =bg.getBounds();
    
    bg.setUserData("Cube");
    return bg;
  }

  
  /**
   * Removes a BranchGroup from the scene based on user data
   * 
   * @param name
   *            the user data to look for
   */
  //no deberiamos usar esto, es para sacar dibujos, pero eso no lo queremos
 

  /**
   * Returns the View Platform Activation Radius
   */
  protected float getViewPlatformActivationRadius() {
    return 100;
  }

  /**
   * Creates the View Platform for the View
   */
  protected ViewPlatform createViewPlatform() {
    ViewPlatform vp = new ViewPlatform();
    vp.setViewAttachPolicy(View.RELATIVE_TO_FIELD_OF_VIEW);
    vp.setActivationRadius(getViewPlatformActivationRadius());

    return vp;
  }

  /**
   * Returns the distance to the rear clipping plane.
   */
  protected double getBackClipDistance() {
    return 100.0;
  }

  /**
   * Returns the distance to the near clipping plane.
   */
  protected double getFrontClipDistance() {
    return 1.0;
  }

  /**
   * Creates the View side BranchGroup. The ViewPlatform is wired in beneath
   * the TransformGroups.
   */
  protected BranchGroup createViewBranchGroup(TransformGroup[] tgArray,
      ViewPlatform vp) {
    BranchGroup vpBranchGroup = new BranchGroup();

    if (tgArray != null && tgArray.length > 0) {
      Group parentGroup = vpBranchGroup;
      TransformGroup curTg = null;

      for (int n = 0; n < tgArray.length; n++) {
        curTg = tgArray[n];
        parentGroup.addChild(curTg);
        parentGroup = curTg;
      }

      tgArray[tgArray.length - 1].addChild(vp);
    } else
      vpBranchGroup.addChild(vp);

    return vpBranchGroup;
  }

  /**
   * Called to render the scene into the offscreen Canvas3D and save the image
   * (as a JPEG) to disk.
   */
 
  /**
   * AWT callback to indicate that an items has been selected from a menu.
   */
  public void actionPerformed(ActionEvent ae) {
    System.out.println("Action Performed: " + ae.getActionCommand());

    java.util.StringTokenizer toker = new java.util.StringTokenizer(ae
        .getActionCommand(), "|");

    String menu = toker.nextToken();
    String command = toker.nextToken();

    if (menu.equals("Acciones")) {
      if (command.equals("Salir")) {
        System.exit(0);
      } else if (command.equals("Parar de Ejecutar")) {
  
      }
    } else if (menu.equals("Metodo Resolucion")) {
      if (command.equals("A*")) {
        //resolver con A*
        
      } else if (command.equals("Escalada")) {
        //resolver con escalada
        
      } else if (command.equals("Primero en Anchura")) {
    	  //resolver 
      } else if (command.equals("Profundidad Iterativa")) {
    	  //resolver 
      } else if (command.equals("Profundidad")) {
    	  //resolver 
      }
    }
  }

  /**
   * Helper method to creates a Swing JMenuItem.
   */
  private JMenuItem createMenuItem(String menuText, String buttonText,
      ActionListener listener) {
    JMenuItem menuItem = new JMenuItem(buttonText);
    menuItem.addActionListener(listener);
    menuItem.setActionCommand(menuText + "|" + buttonText);
    return menuItem;
  }

  /*
   * Registers a window listener to handle ALT+F4 window closing.
   * 
   * @param frame the JFrame for which we want to intercept close messages.
   */
  static protected void registerWindowListener(JFrame frame) {
    // disable automatic close support for Swing frame.
    frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

    // adds the window listener
    frame.addWindowListener(new WindowAdapter() {
      // handles the system exit window message
      public void windowClosing(WindowEvent e) {
        System.exit(1);
      }
    });
  }

  public JMenuBar createMenuBar() {
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = null;

    menu = new JMenu("Acciones");
    menu.add(createMenuItem("Acciones", "Parar de ejecutar", this));
    menu.add(createMenuItem("Acciones", "Salir", this));
    menuBar.add(menu);

    menu = new JMenu("Solucionar");
    menu.add(createMenuItem("Solucionar", "A*", this));
    menu.add(createMenuItem("Solucionar", "Escalada", this));
    menu.add(createMenuItem("Solucionar", "Primero en Anchura", this));
    menu.add(createMenuItem("Solucionar", "Profundidad Iterativa", this));
    menu.add(createMenuItem("Solucionar", "Profundidad", this));
    menu.add(createMenuItem("Solucionar", "Cargar desde Archivo", this));    
    menuBar.add(menu);
/*
    menu = new JMenu("Rotate");
    menu.add(createMenuItem("Rotate", "On", this));
    menu.add(createMenuItem("Rotate", "Off", this));
    menuBar.add(menu);
*/
    return menuBar;
  }

  /**
   * main entry point for the application. Creates the parent JFrame, the
   * JMenuBar and creates the JPanel which is the application itself.
   */
  public static void main(String[] args) {
    

   JPopupMenu.setDefaultLightWeightPopupEnabled(false);

    ToolTipManager ttm = ToolTipManager.sharedInstance();
    ttm.setLightWeightPopupEnabled(false);

    JFrame frame = new JFrame();

    SwingTest swingTest = new SwingTest();
    
    frame.setJMenuBar(swingTest.createMenuBar());

    frame.getContentPane().add(swingTest);
    frame.setSize(700, 700);
    registerWindowListener(frame);

    frame.setVisible(true);
  }
}