/**
 * 
 */
package Cubo;

/**
 * 
 */
public class Coord3d {
	
	/**
	 *
	 */
	private int x;
	
	/**
	 * 
	 */
    private int y;
    
    /**
     * 
     */
    private int z;
    
    /**
     * 
     * @param a
     * @param b
     * @param c
     */
    public Coord3d(int a,int b,int c){
        x = a;
        y = b;
        z = c;
    }
    
    /**
     * 
     * @return
     */
    public Coord3d izquierda(){
        Coord3d c = new Coord3d (x-1,y,z);
        return c;
    }
    
    /**
     * 
     * @return
     */
    public Coord3d derecha(){
        Coord3d c = new Coord3d (x+1,y,z);
        return c;
    }
     
    /**
     *
     * @return
     */
    public Coord3d arriba(){
        Coord3d c = new Coord3d (x,y+1,z);
        return c;
    }   
    
    /**
     * 
     * @return
     */
    public Coord3d abajo(){
        Coord3d c = new Coord3d (x,y-1,z);
        return c;
    }
    
    /**
     * 
     * @return
     */
    public Coord3d delante(){
        Coord3d c = new Coord3d (x,y,z-1);
        return c;
    }    

    /**
     * 
     * @return
     */
    public Coord3d detras(){
        Coord3d c = new Coord3d (x,y,z+1);
        return c;
    }    
    
    /**
     * 
     * @param c
     * @return
     */
    public boolean compara(Coord3d c){
        return ((x==c.x)&&(y==c.y)&&(z==c.z));    
    }
    
    /**
     * 
     * @return
     */
    public int getx(){
        return x;
    }
    
    /**
     * 
     * @return
     */
    public int gety(){
        return y;
    }
        
    /**
     * 
     * @return
     */
    public int getz(){
        return z;
    }
}
