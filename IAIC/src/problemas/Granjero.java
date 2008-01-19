/**
 * 
 */
package problemas;

import java.util.Enumeration;

/**
 *
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
	 * 
	 * @return 	 
	 */
	public float h() {
		
		return 0;
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
	 * 
	 * @return 	 
	 */
	public Enumeration successors() {
		
		return null;
	}

}
