/**
 * Contiene el conjunto de los problemas implementados según el paradigma
 * del espacio de estados.
 */
package problemas;

import aima.search.*;
import java.util.*;

/**
 * Clase que implementa un problema según el paradigma del espacio de estados.
 */
public abstract class Problema implements State,Heuristic{

	/**
	 * Enunciado del problema.
	 */
	public String enunciado;
	
	/**
	 * Indica si estamos ante un estado solución.
	 * @return Valor indicando si el estado es solución o no.
	 */
		
	public abstract boolean isGoal();
	
	/**
	 * Comprueba si el estado del problema es válido.
	 * @return Valor indicando la validez del estado.
	 */
	protected abstract boolean isValid();

	/**
	 * Genera todos los posibles estados sucesores del estado actual.
	 * @return Conjunto de estados sucesores.	 
	 */
	public abstract Enumeration successors();

	/**
	 * Genera el valor heurístico del estado.
	 * @return Valor de la heúristica. 
	 */
	public abstract float h();
	
	
	public boolean resolver(int estrategia){
		
		boolean resuelto=false;
		switch (estrategia){
			//0= Primero en profundidad
			case 0:resuelto=resolverProfundidad();break;
			//1= Primero en anchura
			case 1:resuelto=resolverPrimAnchura();break;

			//2= Coste uniforme
			case 2:resuelto=resolverCosteUni();break;
			
			//3=Profundidad Iterativa
			case 3:resuelto=resolverProfIt();break;
			
			//4=Escalada 
			case 4:resuelto=resolverEscalada();break;
			
			//5=A*
			case 5:resuelto=resolverA();	break;
			
			default: return false;	
		}				
		return resuelto;
	}
	
	protected abstract boolean resolverProfundidad();
	
	protected abstract boolean resolverPrimAnchura();
	
	protected abstract boolean resolverProfIt();
	
	protected abstract boolean resolverEscalada();
	
	protected abstract boolean resolverA();
	
	protected abstract boolean resolverCosteUni();
	
	
}
