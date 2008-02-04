/**
 * 
 */
package Cubo;

import java.util.ArrayList;

/**
 *
 */
public class Habitacion {
    
	/**
	 * 
	 */
    private Puerta puertas[]; 
    
    /**
     * 
     */
    private int ultimaPuerta;
    
    /**
     * 
     */
    public Habitacion() {
        puertas = new Puerta[6];  
        ultimaPuerta = 0;
    }
    
    /**
     * 
     * @param direccion
     * @param estrategia
     * @return
     */
    public boolean abrePuerta(int direccion, int estrategia){
    	ultimaPuerta++;
    	if ((direccion>=0) && (direccion<6)){        
    		return puertas[direccion].abre(estrategia);                              
    	}
    	return false;
    }
    
    /**
     * 
     * @return
     */
    public int dameUltimaPuertaProbada(){
        return ultimaPuerta;
    }
    
    /*          2    
                |
                |
           _____+____________ 
          /|               /| 
         /_|______________/ |
        |  |             | 5|
  3====>|  |             |  |<====1
        |  |_____________|__|
        | /              | /
        |/_4_____________|/
                ^
                |
                |
                0    
     */
  
    /**
     * 
     */
    public Puerta damePuerta(int dir){
        if ((dir>=0) && (dir<6)){      
            return puertas[dir];    
        }
        return null;
    }
    
    /**
     * 
     * @param p
     * @param pos
     * @return
     */
    
    public String dameTitulo(int dir){
    	return puertas[dir].dameTitulo();
    	
    }
    
    public ArrayList<String> dameSolucion(int dir){
    	
    	return puertas[dir].dameSolucion();
    }
    
    public String dameDescripcion(int dir){
    	return puertas[dir].dameDescripcion();
    }
    public boolean setPuerta(Puerta p,int pos){
        
        if ((pos<0) || (pos >5)){
            return false;
        }
        else{
            puertas[pos]=p;
            return true;
        }   
    }
}
