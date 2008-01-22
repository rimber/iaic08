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
	
	/**
	 * Método que intenta resolver un problema según el número de estrategia
	 * de búsqueda indicada por parámetro:
	 *  0. Primero en profundidad.
	 *  1. Primero en anchura.
	 *  2. Coste uniforme.
	 *  3. Profundidad iterativa.
	 *  4. Escalada.
	 *  5. A*.
	 * @param estrategia Número de estrategia de búsqueda.
	 * @return Si se ha resuelto o no el problema con esa estrategia.
	 */
	public boolean resolver(int estrategia){
		
		boolean resuelto = false;
		switch (estrategia){
			//0. Primero en profundidad.
			case 0:
				resuelto=resolverProfundidad();
				break;
			//1. Primero en anchura.
			case 1:
				resuelto=resolverPrimAnchura();
				break;
			//2. Coste uniforme.
			case 2:
				resuelto=resolverCosteUni();
				break;
			//3. Profundidad Iterativa.
			case 3:
				resuelto=resolverProfIt();
				break;
			//4. Escalada.
			case 4:
				resuelto=resolverEscalada();
				break;
			//5. A*
			case 5:
				resuelto=resolverA();	
				break;
			
			default: 
				return false;	
		}				
		return resuelto;
	}

	/**
 	 * Método que intenta resolver un problema según la estrategia de búsqueda 
 	 * primero en profundidad.
 	 * @return Si se ha resuelto o no el problema.
 	 */
	protected abstract boolean resolverProfundidad();
	
	/**
 	 * Método que intenta resolver un problema según la estrategia de búsqueda
 	 * primero en anchura.
 	 * @return Si se ha resuelto o no el problema.
 	 */
	protected abstract boolean resolverPrimAnchura();
	
	/**
 	 * Método que intenta resolver un problema según la estrategia de búsqueda
 	 * en profundidad iterativa.
 	 * @return Si se ha resuelto o no el problema.
 	 */
	protected abstract boolean resolverProfIt();
	
	/**
 	 * Método que intenta resolver un problema según la estrategia 
 	 * de búsqueda escalada. 
 	 * @return Si se ha resuelto o no el problema.
 	 */
	protected abstract boolean resolverEscalada();
	
	/**
 	 * Método que intenta resolver un problema según la estrategia 
 	 * de búsqueda A*. 
 	 * @return Si se ha resuelto o no el problema.
 	 */
	protected abstract boolean resolverA();
	
	/**
 	 * Método que intenta resolver un problema según la estrategia 
 	 * de búsqueda de coste uniforme. 
 	 * @return Si se ha resuelto o no el problema.
 	 */
	protected abstract boolean resolverCosteUni();
	
	/**
	 * 
	 * @param node
	 * @return
	 */
    public boolean listPath(SearchNode node) {
       ArrayList camino = new ArrayList();
	   if (node == null) {
		   return false;
	   }
	   String linea = "";
	   while (node.getParent()!=null) {
		   linea =  "Estado: " + node.getState() +
           				   " Profundidad: " + node.getDepth() +
           				   " Coste: " + node.getPathCost() +
           				   " Operador: " + node.getAppliedOp();
		   camino.add("\n"+linea);
		   node = node.getParent();
	   }
	  
	   linea = ( "\nESTADO INICIAL: " + node.getState());  
	   camino.add(linea);
	   for(int j=camino.size()-1; j>=0;j--){
		   System.out.println((String)camino.get(j));
	   }
	   return true;
	}
	
}
