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
	 * Reloj para controlar el tiempo máximo
	 * en el que puede hacerse la búsqueda.
	 */
	protected Reloj reloj;
	
	/**
	 * Enunciado del problema.
	 */
	protected String enunciado;
	
	/**
	 * Forma de representar el estado de cada problema.
	 */
	protected String repEstado;
	
	/**
	 * Nombre del operador aplicado para pasar 
	 * de un estado a otro.
	 */
	protected String nombreOperador;
	
	/**
	 * Constante que indica el máximo número de
	 * nodos que se pueden expandir.
	 */	
	protected static final int maxNodos = 7000;
	
	/**
	 * Contador de nodos expandidos.
	 */
	protected static int nodosExpandidos = 0;
	
	/**
	 * Numero de problemas distintos que tenemos
	 */	
	public static final int numProblemas = 10;
	
	/**
	 * Método accesor para el atributo enunciado.
	 * @return Valor del atributo enunciado.
	 */
	public String getEnunciado(){
		return enunciado;
	}
	
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
	public abstract Enumeration<Successor> successors();

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
				nodosExpandidos = 0;
				resuelto=resolverProfundidad();
				break;
			//1. Primero en anchura.
			case 1:
				nodosExpandidos = 0;
				resuelto=resolverPrimAnchura();
				break;
			//2. Coste uniforme.
			case 2:
				nodosExpandidos = 0;
				resuelto=resolverCosteUni();
				break;
			//3. Profundidad Iterativa.
			case 3:
				nodosExpandidos = 0;
				resuelto=resolverProfIt();
				break;
			//4. Escalada.
			case 4:
				nodosExpandidos = 0;
				resuelto=resolverEscalada();
				break;
			//5. A*.
			case 5:
				nodosExpandidos = 0;
				resuelto=resolverA();	
				break;
			default: 
				return false;
		}				
		return resuelto;
	}

	/**
 	 * Método que intenta resolver un problema según la estrategia 
 	 * de búsqueda A*. 
 	 * @return Si se ha resuelto o no el problema.
 	 */
	private boolean resolverA() {
		boolean resuelto = listPath((new AStarSearch(this)).search());
		return resuelto;
	}
	
	/**
 	 * Método que intenta resolver un problema según la estrategia 
 	 * de búsqueda de coste uniforme. 
 	 * @return Si se ha resuelto o no el problema.
 	 */
	private boolean resolverCosteUni() {
		boolean resuelto = listPath((new UniformCostSearch(this)).search());
		return resuelto;
	}
	
	/**
 	 * Método que intenta resolver un problema según la estrategia 
 	 * de búsqueda escalada máxima. 
 	 * @return Si se ha resuelto o no el problema.
 	 */
	private boolean resolverEscalada() {
		boolean resuelto = listPath((new GreedySearch(this)).search());
		return resuelto;
	}
	
	/**
 	 * Método que intenta resolver un problema según la estrategia de búsqueda
 	 * primero en anchura.
 	 * @return Si se ha resuelto o no el problema.
 	 */
	private boolean resolverPrimAnchura() {
		boolean resuelto = listPath((new BreadthFirstSearch(this)).search());
		return resuelto;
	}
	
	/**
 	 * Método que intenta resolver un problema según la estrategia de búsqueda
 	 * en profundidad iterativa.
 	 * @return Si se ha resuelto o no el problema.
 	 */
	private boolean resolverProfIt() {
		boolean resuelto=listPath((new IteratedDeepeningSearch(this)).search());
		return resuelto;
	}
	
	/**
 	 * Método que intenta resolver un problema según la estrategia de búsqueda 
 	 * primero en profundidad.
 	 * @return Si se ha resuelto o no el problema.
 	 */
	private boolean resolverProfundidad() {
		boolean resuelto=listPath((new DepthBoundedSearch(this,10)).search());
		return resuelto;
	}
	
	/**
	 * Método para mostar los resultados de la resolución de un problema.
	 * @param node Nodo (estado) del problema del que mostrar información.
	 * @return Devuelve si el problema se ha resuelto.
	 */
    public boolean listPath(SearchNode node) {
       ArrayList<String> camino = new ArrayList<String>();
	   if (node == null) {
		   return false;
	   }
	   String linea = "";
	   while (node.getParent()!=null) {
		   linea =  " Operador: " + node.getAppliedOp()+ 
           			" Profundidad: " + node.getDepth() +
           			" Coste: " + node.getPathCost() +
           			" Estado: " + node.getState() ;
		   camino.add("\n"+linea);
		   node = node.getParent();
	   }	  
	   linea = ( "\n Estado inicial: " + node.getState());  
	   camino.add(linea);
	   for(int j=camino.size()-1; j>=0;j--){
		   System.out.println((String)camino.get(j));
	   }
	   System.out.println();
	   return true;
	}
    
    /**
     * Método accesor para repEstado.
     * @return Representación del estado actual del problema.
     */
    public String toString(){
    	return repEstado;
    }
    
    /**
     * Método accesor para el número de nodos expandidos.
	 * @return Número de nodos expandidos.
	 */
	public int getNodosExpandidos() {
		return nodosExpandidos;
	}
	
	/**
	 *  Método que dado un número de estrategia
	 *  devuelve el nombre de la estrategia aplicada.
	 *  @param estrategia nº que representa la estrategia usada.
	 *  @retun El nombre de la estrategia.
	 */	
	public String estrategiaAplicada(int estrategia){
		String metodo="";
		switch (estrategia){
			//0. Primero en profundidad.
			case 0:
				metodo="Primero en profundidad (Máxima profundidad = 10).";
				break;
			//1. Primero en anchura.
			case 1:
				metodo="Primero en anchura.";
				break;
			//2. Coste uniforme.
			case 2:
				metodo="Coste uniforme.";
				break;
			//3. Profundidad Iterativa.
			case 3:
				metodo="Profundidad iterativa.";
				break;
			//4. Escalada.
			case 4:
				metodo="Escalada Máxima.";
				break;
			//5. A*
			case 5:
				metodo="A*.";	
				break;
			default: 
				return null;	
		}				
		return metodo;
	}
	
}
