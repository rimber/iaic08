/**
 * 
 */
package Cubo;

import java.util.Random;
import problemas.*;

/**
 *
 */
public class Puerta {
    	
	/**
	 * 
	 */
	private Problema prob;
	
	/**
	 * 0. Cerrada. 
	 * 1. Abierta. 
	 * 2. Bloqueada.
	 */
    private int estado;
    
    /**
     * 
     */
    public Puerta() {
        estado = 0;
    }
    
    /**
     * 
     * @param e
     * @param p
     */
    public Puerta(int e, Problema p){
    	estado = e;
    	prob = p;    	
    }
    
    /**
     * 
     * @param e
     */
    public Puerta(int e){
        estado = e;
        if (e==0){
        	
        	// Incluimos un problema aleatoriamente.
            Random rnd = new Random();
            int opc = rnd.nextInt(Problema.numProblemas);
            
            switch (opc){      
            	case 0:
            		prob = new Canibales();
            		break;
            		
            	case 1:
            		prob = new Jarras();
            		break;
            		
            	case 2:
            		prob = new Granjero();
            		break;  		    	  
            }
        }
    }
    
    /**
     * 
     * @return
     */
    public int dameEstado(){
        return estado;
    }
    
    /**
     * 
     * @param e
     */
    public void setEstado(int e){
        estado = e;    
    }
    
    /**
     * 
     * @param est
     * @return
     */
    
    public String dameDescripcion(){
    	
    	return prob.getEnunciado();
    }
    
    public String dameTitulo(){
    	return prob.dameTitulo();    	
    }
    
    
    public boolean abre(int est){
    
	    if (estado==2){
	    	// Si está bloqueada no se puede abrir.
	        return false;
	    }
	    
	    if (estado==1){
	    	// Sin está abierta.
	        return true;
	    }
	       
	    if (estado==0){
	    	// Si está cerrada, trata de resolver.
	    	if (prob.resolver(est)){	
	    		// Abre la puerta.
	    		estado = 1;
	    		return true;
	    	}
	    	else{
	    		// Bloquea la puerta.
	    		estado = 2;
	    		return false;
	    	}
	    }    	    
	    return true;
	}
}
