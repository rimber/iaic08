/**
 * 
 */
package Cubo;

/**
 * 
 */
public class Coord3d {
	
	/**
	 * desplazamiento con respecto al eje X
	 */
	private int x;
	
	/**
	 * desplazamiento con respecto al eje Y
	 */
    private int y;
    
    /**
     * desplazamiento con respecto al eje Z
     */
    private int z;
    
    /**
     * Crea una coordenada a partir de 3 desplazamientos con respecto a los ejes
     * @param a desplazamiento con respecto al eje X
     * @param b desplazamiento con respecto al eje X
     * @param c desplazamiento con respecto al eje X
     */
    public Coord3d(int a,int b,int c){
        x = a;
        y = b;
        z = c;
    }
    
    /**
     * Método que devuelve la coordenada contigua a la izquierda a la instancia
     * @return  Coordenada contigua a la instacia, pero con el desplazamiento X disminuido
     */
    public Coord3d izquierda(){
        Coord3d c = new Coord3d (x-1,y,z);
        return c;
    }
    
    /**
     * Método que devuelve la coordenada contigua a la derecha a la instancia
     * @return  Coordenada contigua a la instacia, pero con el desplazamiento X aumentado
     */
    public Coord3d derecha(){
        Coord3d c = new Coord3d (x+1,y,z);
        return c;
    }
     
    /**
     * Método que devuelve la coordenada contigua de arriba de la instancia
     * @return  Coordenada contigua a la instacia, pero con el desplazamiento Y aumentado
     */
    public Coord3d arriba(){
        Coord3d c = new Coord3d (x,y+1,z);
        return c;
    }   
    
    /**
     * Método que devuelve la coordenada contigua de debajo de la instancia
     * @return  Coordenada contigua a la instacia, pero con el desplazamiento Y disminuido
     */
    
    public Coord3d abajo(){
        Coord3d c = new Coord3d (x,y-1,z);
        return c;
    }
    
    /**
     * Método que devuelve la coordenada contigua de delante de la instancia
     * @return  Coordenada contigua a la instacia, pero con el desplazamiento Z disminuido
     */
    public Coord3d delante(){
        Coord3d c = new Coord3d (x,y,z-1);
        return c;
    }    

    /**
     * Método que devuelve la coordenada contigua de detrás de la instancia
     * @return  Coordenada contigua a la instacia, pero con el desplazamiento Z aumentado
     */
    public Coord3d detras(){
        Coord3d c = new Coord3d (x,y,z+1);
        return c;
    }    
    
    /**
     * Compara 2 la coordenada con la que se le pasa como parámetro.
     * @param c coordenada a comparar
     * @return si coinciden sus desplazamientos
     */
    public boolean compara(Coord3d c){
        return ((x==c.x)&&(y==c.y)&&(z==c.z));    
    }
    
    /**
     * método accesor al desplazamiento con respecto al eje X
     * @return desplazamiento con respecto al eje X
     */
    public int getx(){
        return x;
    }
    
    /**
     * método accesor al desplazamiento con respecto al eje Y
     * @return desplazamiento con respecto al eje Y
     */
    public int gety(){
        return y;
    }
        
    /**
     * método accesor al desplazamiento con respecto al eje Z
     * @return desplazamiento con respecto al eje Z
     */
    public int getz(){
        return z;
    }
}
