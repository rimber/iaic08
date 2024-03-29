/**
 * Contiene el conjunto de los problemas implementados seg�n el paradigma
 * del espacio de estados.
 */
package problemas;

import aima.search.*;
import java.util.*;

/**
 * Clase que implementa un problema seg�n el paradigma del espacio de estados.
 */
public abstract class Problema implements State,Heuristic{
		
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
	 * Constante que indica el m�ximo n�mero de
	 * nodos que se pueden expandir.
	 */	
	protected static final int maxNodos = 7000;
	
	/**
	 * Contador de nodos expandidos.
	 */
	protected static int nodosExpandidos = 0;
	
	/**
	 * Numero m�ximo de niveles en profundidad iterativa.
	 */	
	public static final int maxNivel = 1000;
	
	/**
	 * Numero de problemas distintos que tenemos.
	 */	
	public static final int numProblemas = 10;
	
	/**
	 * El camino seguido para resolver el problema.
	 */
	private ArrayList<String> camino;
	
	/**
	 * M�todo accesor para el atributo enunciado.
	 * @return Valor del atributo enunciado.
	 */
	public String getEnunciado(){
		return enunciado;
	}
	
	/**
	 * Indica si estamos ante un estado soluci�n.
	 * @return Valor indicando si el estado es soluci�n o no.
	 */
	public abstract boolean isGoal();
	
	/**
	 * Comprueba si el estado del problema es v�lido.
	 * @return Valor indicando la validez del estado.
	 */
	protected abstract boolean isValid();

	/**
	 * Genera todos los posibles estados sucesores del estado actual.
	 * @return Conjunto de estados sucesores.	 
	 */
	public abstract Enumeration<Successor> successors();

	/**
	 * Genera el valor heur�stico del estado.
	 * @return Valor de la he�ristica. 
	 */
	public abstract float h();
	
	/**
	 * Devuele el t�tulo del problema.
	 * @return El nombre del problema.
	 */
	public abstract String dameTitulo();
	
	/**
	 * M�todo que intenta resolver un problema seg�n el n�mero de estrategia
	 * de b�squeda indicada por par�metro:
	 *  0. Primero en profundidad.
	 *  1. Primero en anchura.
	 *  2. Coste uniforme.
	 *  3. Profundidad iterativa.
	 *  4. Escalada.
	 *  5. A*.
	 * @param estrategia N�mero de estrategia de b�squeda.
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
 	 * M�todo que intenta resolver un problema seg�n la estrategia 
 	 * de b�squeda A*. 
 	 * @return Si se ha resuelto o no el problema.
 	 */
	protected boolean resolverA() {
		boolean resuelto = listPath((new AStarSearch(this)).search());
		return resuelto;
	}
	
	/**
 	 * M�todo que intenta resolver un problema seg�n la estrategia 
 	 * de b�squeda de coste uniforme. 
 	 * @return Si se ha resuelto o no el problema.
 	 */
	protected boolean resolverCosteUni() {
		boolean resuelto = listPath((new UniformCostSearch(this)).search());
		return resuelto;
	}
	
	/**
 	 * M�todo que intenta resolver un problema seg�n la estrategia 
 	 * de b�squeda escalada m�xima. 
 	 * @return Si se ha resuelto o no el problema.
 	 */
	protected boolean resolverEscalada() {
		boolean resuelto = listPath((new GreedySearch(this)).search());
		return resuelto;
	}
	
	/**
 	 * M�todo que intenta resolver un problema seg�n la estrategia de b�squeda
 	 * primero en anchura.
 	 * @return Si se ha resuelto o no el problema.
 	 */
	protected boolean resolverPrimAnchura() {
		boolean resuelto = listPath((new BreadthFirstSearch(this)).search());
		return resuelto;
	}
	
	/**
 	 * M�todo que intenta resolver un problema seg�n la estrategia de b�squeda
 	 * en profundidad iterativa.
 	 * @return Si se ha resuelto o no el problema.
 	 */
	protected boolean resolverProfIt() {	
		boolean resuelto=listPath((new IteratedDeepeningSearch(this)).search());
		return resuelto;
	}
	
	/**
 	 * M�todo que intenta resolver un problema seg�n la estrategia de b�squeda 
 	 * primero en profundidad.
 	 * @return Si se ha resuelto o no el problema.
 	 */
	protected boolean resolverProfundidad() {
		boolean resuelto=listPath((new DepthBoundedSearch(this,10)).search());
		return resuelto;
	}
	
	/**
	 * M�todo accesor para el camino seguido en la resoluci�n del problema.
	 * @return El camino seguido.
	 */
	public ArrayList<String> getCamino(){	
		return camino;
	}
	
	/**
	 * M�todo que se usa para saber si el problema se ha resuelto.
	 * @param node Nodo (estado) del problema del que mostrar informaci�n.
	 * @return Devuelve si el problema se ha resuelto.
	 */
    public boolean listPath(SearchNode node) {
        camino = new ArrayList<String>();
 	   if (node == null) {
 		   return false;
 	   }
 	   String linea = "";

 	  camino.add("\n"+" Nodos expandidos: "+nodosExpandidos);
 	   while (node.getParent()!=null) {
 		   linea =  " Operador: " + node.getAppliedOp()+ "\n" +
            			" Profundidad: " + node.getDepth()+ "\n" +
            			" Coste: " + node.getPathCost()+ "\n" +
            			" Estado: " + node.getState() ;
 		   camino.add("\n"+linea);
 		   node = node.getParent();
 	   }	    
  	   camino.add( "\n Estado inicial: " + node.getState()); 
 	   return true;
 	}
    
    /**
     * M�todo accesor para repEstado.
     * @return Representaci�n del estado actual del problema.
     */
    public String toString(){
    	return repEstado;
    }
    
    /**
     * M�todo accesor para el n�mero de nodos expandidos.
	 * @return N�mero de nodos expandidos.
	 */
	public int getNodosExpandidos() {
		return nodosExpandidos;
	}
	
	/**
	 *  M�todo que dado un n�mero de estrategia
	 *  devuelve el nombre de la estrategia aplicada.
	 *  @param estrategia N� que representa la estrategia usada.
	 *  @return El nombre de la estrategia.
	 */	
	public static String estrategiaAplicada(int estrategia){
		String metodo="";
		switch (estrategia){
			//0. Primero en profundidad.
			case 0:
				metodo="Primero en profundidad (M�xima profundidad = 10).";
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
				metodo="Escalada M�xima.";
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
