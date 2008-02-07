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
	 * Vector de puertas de la habitación
	 */
    private Puerta puertas[]; 
    
    /**
     * Última puerta que hemos intentado abrir en la habitación
     */
    private int ultimaPuerta;
    
    /**
     * Crea una instancia de habitacion por defecto
     */
    public Habitacion() {
        puertas = new Puerta[6];  
        ultimaPuerta = 0;
    }
    
    /**
     * Método que intenta abrir una puerta
     * @param direccion Indica la puerta concreta que queremos abrir
     * @param estrategia Indica la estrategia que vamos a seguir para resolver el problema de la puerta
     * @return Si se ha conseguido abrir la puerta que querríamos
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
     * metodo accesor de la última dirección por la que se ha intentado continuar en el problema
     * segçun el siguiente diagrama
     *           2    
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
     * @return devuelve la dirección de la última puerta que se ha intentado abrir
     */
    public int dameUltimaPuertaProbada(){
        return ultimaPuerta;
    }
    
 
    /**
     * Metodo accesor a la puerta correspondiente a una dirección
     * @param dir dirección de la puerta que queremos que se devuelva
     * @return la puerta buscada
     */
    public Puerta damePuerta(int dir){
        if ((dir>=0) && (dir<6)){      
            return puertas[dir];    
        }
        return null;
    }
    
    /**
     * Metodo accesor al título del problema de la puerta asociada a la dirección pasada
     * @param direccion de la puerta de la que se quiere saber el título del problema
     * @return título  del problema de la puerta
     * */
    
    public String dameTitulo(int dir){
    	return puertas[dir].dameTitulo();
    	
    }
    /**
     * Método accesor al a la solucón al problema encontrada
     * @param dirección del a puera de la que se quiere saber la solución de su problema asociado
     * @return solución al problema encontrado
     * */
    public ArrayList<String> dameSolucion(int dir){
    	
    	return puertas[dir].dameSolucion();
    }
    
    
    /**
     * Metodo accesor a la descripción del problema de la puerta asociada a la dirección pasada
     * @param dirección de la puerta de la que se quiere saber la descripción del problema
     * @return breve descripción del problema de la puerta
     * */
    public String dameDescripcion(int dir){
    	return puertas[dir].dameDescripcion();
    }
    
    /**
     * Coloca la puerta deseada en la dirección pasada por parametro
     * @param p Puerta que se va a poner
     * @param pos dirección de la puerta que se va a poner
     * @return si la dirección dada era válida para poner la puerta
     */
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
