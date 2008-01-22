/**
 * Contiene el conjunto de los problemas implementados según el paradigma
 * del espacio de estados.
 */
package problemas;

import aima.search.*;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Clase que implementa el problema del Granjero, el lobo, la cabra y la col según
 * el paradigma del espacio de estados.
 */
public class Granjero extends Problema{

	/**
	 * Posición del granjero (Margen del río: 1-izquierda, 0-derecha).
	 */
	int posGranjero;
	
	/**
	 * Posición del lobo (Margen del río: 1-izquierda, 0-derecha).
	 */
	int posLobo;
	
	/**
	 * Posición de la cabra (Margen del río: 1-izquierda, 0-derecha).
	 */
	int posCabra;
	
	/**
	 * Posición de la col (Margen del río: 1-izquierda, 0-derecha).
	 */
	int posCol;

	/**
	 * Constructor por defecto: inicializa las componentes a valores por defecto.
	 */
	public Granjero() {
		enunciado = "Un granjero se encuentra en la orilla izquierda de un río junto con un lobo, una cabra y"
		+ " una col. Dispone de un bote en el que sólo puede transportar una única cosa cada vez. El"
		+ " granjero pretende transportar al lobo, la cabra y la col al otro lado del río, utilizando el"
		+ " bote. Sin embargo, debe tener cuidado y no dejar solos en una orilla al lobo y a la cabra"
		+ " porque el lobo se comería a la cabra. Tampoco puede dejar solas a la cabra y la col"
		+ " porque la cabra se comería la col. Consigue que el granjero traslade a todos a la"
		+ " margen derecha del río.";
		
		posGranjero = 1;
		posLobo = 1;
		posCabra = 1;
		posCol = 1;
	}
	
	/**
	 * Constructor principal, inicializa los atributos a los valores indicados.
	 * @param pGranjero Posición del granjero.
	 * @param pLobo Posición del lobo.
	 * @param pCabra Posición de la cabra.
	 * @param pCol Posición de la col.
	 */
	public Granjero(int pGranjero, int pLobo, int pCabra, int pCol) {
		enunciado = "Un granjero se encuentra en la orilla izquierda de un río junto con un lobo, una cabra y"
		+ " una col. Dispone de un bote en el que sólo puede transportar una única cosa cada vez. El"
		+ " granjero pretende transportar al lobo, la cabra y la col al otro lado del río, utilizando el"
		+ " bote. Sin embargo, debe tener cuidado y no dejar solos en una orilla al lobo y a la cabra"
		+ " porque el lobo se comería a la cabra. Tampoco puede dejar solas a la cabra y la col"
		+ " porque la cabra se comería la col. Consigue que el granjero traslade a todos a la"
		+ " margen derecha del río.";
		
		posGranjero = pGranjero;
		posLobo = pLobo;
		posCabra = pCabra;
		posCol = pCol;
	}

	/**
	 * Método accesor para el atributo enunciado.
	 * @return Valor del atributo enunciado.
	 */
	public String getEnunciado(){
		return enunciado;
	}
	
	/**
	 * Genera el valor heurístico del estado.
	 * @return Valor de la heúristica. 
	 */
	public float h() {
		// Heurística: Mejor cuanto menor sea h, es decir, cuanto menos componentes
		// estén en el lado izquierdo.
		int h = posGranjero + posLobo + posCabra + posCol;
		return (float)h;
	}

	/**
	 * Indica si estamos ante un estado solución.
	 * @return Valor indicando si el estado es solución o no.
	 */
	public boolean isGoal() {
		boolean solucion = false;
		// Solución si todos los componentes están a la derecha.
		if ((posGranjero == 0)&&(posLobo == 0)&&(posCabra == 0)&&(posCol == 0)){
			solucion = true;
		}
		return solucion;
	}

	/**
	 * Comprueba si el estado del problema es válido.
	 * @return Valor indicando la validez del estado.
	 */
	protected boolean isValid() {
		// Si el lobo está con la cabra en un margen y el granjero está 
		// en la contraria.
		if ((posLobo == posCabra) && (posGranjero!=posLobo)){
			return false;
		}
		
		// Si el cabra está con la col en un margen y el granjero está 
		// en la contraria.
		if ((posCabra == posCol) && (posGranjero!=posCabra)){
		  	return false;
		}
		
		return true;	
	}

	/**
	 * Genera todos los posibles estados sucesores del estado actual.
	 * @return Conjunto de estados sucesores.	 
	 */
	public Enumeration successors() {
		
		// Tenemos 4 operadores:
		// Operador 0: Cruza el lobo (con el granjero).
		// Operador 1: Cruza la cabra (con el granjero).
		// Operador 2: Cruza la col (con el granjero).
		// Operador 3: Cruza el granjero solo.
		
	 	// Operador usado.
	 	int numOperador;
	 	String nombreOperador = "";
	 	
	 	// Nuevas posiciones.
	 	int nposLobo = 1;
	 	int nposCabra = 1;
	 	int nposCol = 0;
	 	int nposGranjero  = 0;
	 	Vector successor = new Vector();
	 	 
	 	for (numOperador = 0; numOperador <4; numOperador++){
	 		// Operador 0: Cruza el lobo (con el granjero).
	 		if (numOperador == 0){
	 			// El lobo y el granjero deben estar en la misma orilla
	 			// para poder cruzar.
	 			if (posGranjero == posLobo){
	 				// Nombre del operador.
		 	 		nombreOperador = "cruzaLobo";
	 				// Cruza el granjero.
		 	 		nposGranjero = 1 - posGranjero;
		 	 		// Cruza el lobo.
		 	 		nposLobo = 1 - posLobo;
		 	 		// Los demás se quedan donde están.
		 	 		nposCabra = posCabra;
		 	 		nposCol = posCol;
	 			}
	 	 	}
	 		
	 		// Operador 1: Cruza la cabra (con el granjero).
	 	 	if (numOperador == 1){
	 	 		if (posGranjero == posCabra){
	 	 			// Nombre del operador.
	 	 			nombreOperador = "cruzaCabra";
	 				// Cruza el granjero.
		 	 		nposGranjero = 1 - posGranjero;
		 	 		// Cruza la cabra.
		 	 		nposCabra = 1 - posCabra;
		 	 		// Los demás se quedan donde están.
		 	 		nposLobo = posLobo;
		 	 		nposCol = posCol;
	 	 		}
	 	 	}
	 	 	
	 	    // Operador 2: Cruza la col (con el granjero).
	 	 	if (numOperador == 2){
	 	 		if (posGranjero == posCol){
	 	 			// Nombre del operador.
	 	 			nombreOperador = "cruzaCol";
	 				// Cruza el granjero.
		 	 		nposGranjero = 1 - posGranjero;
		 	 		// Cruza la cabra.
		 	 		nposCol = 1 - posCol;
		 	 		// Los demás se quedan donde están.
		 	 		nposLobo = posLobo;
		 	 		nposCabra = posCabra;
	 	 		}
	 	 	}
	 	 	
	 	 	// Operador 3: Cruza el granjero solo.
	 	 	if(numOperador == 3){
 	 			// Nombre del operador.
 	 			nombreOperador = "cruzaGranjero";
 				// Cruza el granjero.
	 	 		nposGranjero = 1 - posGranjero;
	 	 		// Los demás se quedan donde están.
	 	 		nposCol = posCol;
	 	 		nposLobo = posLobo;
	 	 		nposCabra = posCabra;
	 	 	}
	 	 	
	 	 	// Creamos el nuevo estado.
	 	 	Granjero nuevoEstado = new Granjero(nposGranjero,nposLobo,nposCabra,nposCol);
	 	 		
	 	 	// Comprobamos si el nuevo estado es válido.
	 	 	if(nuevoEstado.isValid()){
	 	 		// Añadimos el estado como sucesor.
	 	 		successor.addElement(new Successor(nuevoEstado,nombreOperador,1)); 
	 	 	}
	 	 }
	 	 
	 	 return successor.elements();
	}

	protected boolean resolverA() {
		// TODO Auto-generated method stub
		return false;
	}

	protected boolean resolverCosteUni() {
		// TODO Auto-generated method stub
		return false;
	}

	protected boolean resolverEscalada() {
		// TODO Auto-generated method stub
		return false;
	}

	protected boolean resolverPrimAnchura() {
		// TODO Auto-generated method stub
		return false;
	}

	protected boolean resolverProfIt() {
		// TODO Auto-generated method stub
		return false;
	}

	protected boolean resolverProfundidad() {
		// TODO Auto-generated method stub
		return false;
	}

}
