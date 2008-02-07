/**
 * Contiene el conjunto de clases que implementan el micromundo dle edificio cúbico.
 */
package Cubo;

import java.util.ArrayList;

/**
 * Clase que implementa la representación de una habitación en el micromundo.
 */
public class Habitacion {
    
	/**
	 * Vector de puertas de la habitación.
	 */
    private Puerta puertas[]; 
    
    /**
     * Última puerta que hemos intentado abrir en la habitación.
     */
    private int ultimaPuerta;
    
    /**
     * Constructor principal de Habitación.
     */
    public Habitacion() {
        puertas = new Puerta[6];  
        ultimaPuerta = 0;
    }
    
    /**
     * Método que intenta abrir una puerta.
     * @param direccion Indica la puerta concreta que queremos abrir.
     * @param estrategia Indica la estrategia que vamos a seguir para resolver el problema de la puerta.
     * @return Si se ha conseguido abrir la puerta que querríamos.
     */
    public boolean abrePuerta(int direccion, int estrategia){
    	ultimaPuerta++;
    	if ((direccion>=0) && (direccion<6)){        
    		return puertas[direccion].abre(estrategia);                              
    	}
    	return false;
    }
    
    /**
     * Metodo accesor de la última dirección por la que se ha intentado continuar en el problema.
     * @return Devuelve la dirección de la última puerta que se ha intentado abrir.
     */
    public int dameUltimaPuertaProbada(){
        return ultimaPuerta;
    }
 
    /**
     * Metodo accesor a la puerta correspondiente a una dirección.
     * @param dir Dirección de la puerta que queremos que se devuelva.
     * @return Puerta buscada.
     */
    public Puerta damePuerta(int dir){
        if ((dir>=0) && (dir<6)){      
            return puertas[dir];    
        }
        return null;
    }
    
    /**
     * Metodo accesor al título del problema de la puerta asociada a la dirección pasada.
     * @param dir Direccion de la puerta de la que se quiere saber el título del problema.
     * @return Título  del problema de la puerta.
     * */
    public String dameTitulo(int dir){
    	return puertas[dir].dameTitulo();
    	
    }
    
    /**
     * Método accesor a la solucón al problema encontrada.
     * @param dir Dirección de la puerta de la que se quiere saber la solución de su problema asociado.
     * @return Solución al problema encontrado.
     * */
    public ArrayList<String> dameSolucion(int dir){
    	
    	return puertas[dir].dameSolucion();
    }
    
    
    /**
     * Metodo accesor a la descripción del problema de la puerta asociada a la dirección pasada.
     * @param dir Dirección de la puerta de la que se quiere saber la descripción del problema.
     * @return Breve descripción del problema de la puerta.
     * */
    public String dameDescripcion(int dir){
    	return puertas[dir].dameDescripcion();
    }
    
    /**
     * Coloca la puerta deseada en la dirección pasada por parametro.
     * @param p Puerta que se va a poner.
     * @param pos Dirección de la puerta que se va a poner.
     * @return Si la dirección dada era válida para poner la puerta.
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
