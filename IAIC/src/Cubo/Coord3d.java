/*
 * Coord3d.java
 *
 * Created on 10 de enero de 2008, 15:18
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Cubo;

/**
 *
 * @author usuario_local
 */

public class Coord3d {
    private int x;
    private int y;
    private int z;
    /** Creates a new instance of Coord3d */
    public Coord3d() {
    }
    
    public Coord3d(int a,int b,int c){
        x=a;
        y=b;
        z=c;
    }
    
    public Coord3d izquierda(){
        Coord3d c=new Coord3d (x-1,y,z);
        return c;
    }
    
    public Coord3d derecha(){
        Coord3d c=new Coord3d (x+1,y,z);
        return c;
    }
     
    public Coord3d arriba(){
        Coord3d c=new Coord3d (x,y+1,z);
        return c;
    }   
    
    public Coord3d abajo(){
        Coord3d c=new Coord3d (x,y-1,z);
        return c;
    }
    
    public Coord3d delante(){
        Coord3d c=new Coord3d (x,y,z-1);
        return c;
    }    

    public Coord3d detras(){
        Coord3d c=new Coord3d (x,y,z+1);
        return c;
    }    
    
    public boolean compara(Coord3d c){
        return ((x==c.x)&&(y==c.y)&&(z==c.z));    
    }
    
    public int getx(){
        return x;
    }
    
    public int gety(){
        return y;
    }
        
    public int getz(){
        return z;
    }
}
