/*
 * Puerta.java
 *
 * Created on 10 de enero de 2008, 15:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Cubo;
import java.util.Random;

import problemas.*;
/**
 *
 * @author usuario_local
 */
public class Puerta {
    
	private static int numProb=3;
	private Problema prob;
    private int estado;//0 estado cerrada, 1 estado abierta 2 estado bloqueada
    /** Creates a new instance of Puerta */
    
    public Puerta() {
        estado=0;
    }
    
    public Puerta(int e){//habra que pasarle un problema tambien
        estado=e;
        if (e==0){
            Random rnd = new Random(4578);
            int opc=rnd.nextInt()%numProb;
            if (opc<0) opc=-opc;
            
            switch (opc){      
            	case 0:prob=new Canibales();break;
            	case 1:prob=new Jarras();break;
            	case 2:prob=new Granjero();break;  		    	  
            }
        }
    }
    
    public Puerta(int e, Problema p){
    	
    	estado=e;
    	prob=p;    	
    }
    
    public int dameEstado(){
        return estado;
    }
    
    public void setEstado(int e){
        estado=e;    
    }
    
    public boolean abre(int est){
    
    if (estado==2){
        return false;
    }/*si está bloqueada no se puede abrir, 
      esto cubre los laterales si el edificio esta bien creado
     */
    
    if (estado==1){
        return true;
    }
        //
    if (estado==0){
    	
    	if (prob.resolver(est)){	
    		estado=1;
    		return true;
    	}
    	else{
    		estado =2;
    		return false;
    	}
    }    	    
    //resolver el problema asociado a la puerta, se actualiza el estado
    return true;//para que no grite con lo de que le falta un return, abra que quitarlol
    }
}
