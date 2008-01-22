/*
 * Habitacion.java
 *
 * Created on 10 de enero de 2008, 15:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Cubo;



/**
 *
 * @author usuario_local
 */
public class Habitacion {
    

    private Puerta puertas[]; 
    private int ultimaPuerta;
    /** Creates a new instance of Habitacion */
    public Habitacion() {
        puertas=new Puerta[6];  
        ultimaPuerta=0;
    }
    
    public boolean abrePuerta(int direccion, int estrategia){
    ultimaPuerta++;
     if ((direccion>=0) && (direccion<6)){        
        return puertas[direccion].abre(estrategia);//quiza abria que pasarle el método
                                         //con el que vas a resolver 
     }
     return false;
    
    }
    
    public int dameUltimaPuertaProbada(){
        return ultimaPuerta;
    }
    /*          2    
                |
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
  
    public Puerta damePuerta(int dir){
        if ((dir>=0) && (dir<6)){      
            return puertas[dir];    
        }
        return null;
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
