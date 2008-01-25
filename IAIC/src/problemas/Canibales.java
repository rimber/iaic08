/**
 * Contiene el conjunto de los problemas implementados según el paradigma
 * del espacio de estados.
 */
package problemas;

import aima.search.*;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Clase que implementa el problema de los misioneros y los caníbales según
 * el paradigma del espacio de estados.
 */
public class Canibales extends Problema{

	/**
	 * Número de misioneros en el margen izquierdo.
	 */
	int numMisionerosIzq;
	
	/**
	 * Número de caníbales en el margen izquierdo.
	 */
	int numCanibalesIzq;
	
	/**
	 * Posición de la barca (Margen del río: 1-izquierda, 0-derecha).
	 */
	int posBarca;
	
	/**
	 * Constructor por defecto: inicializa las componentes a valores por defecto.
	 */
	public Canibales(){
		enunciado = "Tres misioneros y tres caníbales están a la orilla de un río" +
			" que quieren cruzar. Para ello disponen de un bote que tiene como" +
			" capacidad máxima 2 personas. El objetivo consiste en conseguir " +
			"que todos acaben en la otra orilla del río sin que en ningún " +
			"momento los misioneros estén en peligro de ser devorados por los" +
			" caníbales. Se considera que los misioneros están en peligro " +
			"cuando, en un determinado lugar, el número de caníbales supera al" +
			" de misioneros.";
			
		posBarca = 1;
		numMisionerosIzq = 3;
		numCanibalesIzq = 3;
		repEstado = "("+numMisionerosIzq+","+numCanibalesIzq+","+posBarca+")";
		nombreOperador = "";
	}
	
	/**
	 * Constructor principal, inicializa los atributos a los valores indicados.
	 * @param pBarca Posición de la barca.
	 * @param nMisioneros Número de misioneros en el margen izquierdo.
	 * @param nCanibales Número de caníbales en el margen izquierdo.
	 */
	public Canibales(int nMisioneros, int nCanibales, int pBarca){
		enunciado = "Tres misioneros y tres caníbales están a la orilla de un río" +
			" que quieren cruzar. Para ello disponen de un bote que tiene como" +
			" capacidad máxima 2 personas. El objetivo consiste en conseguir " +
			"que todos acaben en la otra orilla del río sin que en ningún " +
			"momento los misioneros estén en peligro de ser devorados por los" +
			" caníbales. Se considera que los misioneros están en peligro " +
			"cuando, en un determinado lugar, el número de caníbales supera al" +
			" de misioneros.";
		posBarca = pBarca;
		numMisionerosIzq = nMisioneros;
		numCanibalesIzq = nCanibales;
		nombreOperador = "";
		repEstado = "("+numMisionerosIzq+","+numCanibalesIzq+","+posBarca+")";
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
		int h = numMisionerosIzq + numCanibalesIzq - posBarca;
		return (float)h;
	}

	/**
	 * Indica si estamos ante un estado solución.
	 * @return Valor indicando si el estado es solución o no.
	 */
	public boolean isGoal() {
		boolean solucion = false;
		// Solución si todos los componentes están a la derecha.
		if ((numMisionerosIzq == 0)&&(numCanibalesIzq == 0)&&(posBarca == 0)){
			solucion = true;
		}
		return solucion;
	}

	/**
	 * Comprueba si el estado del problema es válido.
	 * @return Valor indicando la validez del estado.
	 */
	protected boolean isValid() {
		
        // Si el número de caníbales supera al de misioneros en la izquierda 
        // (siempre que halla alguno).
        if ((numMisionerosIzq > 0) && (numCanibalesIzq > numMisionerosIzq)){
        	return false;
        }

        // Si el número de caníbales supera al de misioneros en la derecha 
        // (siempre que halla alguno).
        if (((3-numMisionerosIzq) > 0) && ((3-numCanibalesIzq) > (3-numMisionerosIzq))){
        	return false;
        }

        return true;
	}

	/**
	 * Genera todos los posibles estados sucesores del estado actual.
	 * @return Conjunto de estados sucesores.	 
	 */
	public Enumeration successors() {
		
		// Tenemos  operadores:
		// Operador 0: Cruza un canibal.
		// Operador 1: Cruzan dos canibales.
		// Operador 2: Cruza un misionero.
		// Operador 3: Cruzan dos misioneros.
		// Operador 4: Cruza un misionero y un canibal.
		
	 	// Operador usado.
	 	int numOperador;
		
	 	// Nuevas posiciones.
		int nnumMisionerosIzq = 0;
	    int nnumCanibalesIzq = 0;
	    int nposBarca = 0;
	    Vector successorVec = new Vector();
	    
	    for (numOperador = 0; numOperador <5; numOperador++){
	    	// Operador 0: Cruza un canibal.
	 		if (numOperador == 0){
	 			// La barca está en la izquierda
	 			if (posBarca == 1){
	 				// Si hay canibales en la izquierda.
	 				if (numCanibalesIzq>0){
	 					nombreOperador = "Cruza un canibal.";
	 				    // Cruza canibal.
	 				    nnumCanibalesIzq = numCanibalesIzq - 1;
	 				    // Cruza la barca.
	 				    nposBarca = 0;
	 				    // Misioneros se quedan igual.
	 				    nnumMisionerosIzq = numMisionerosIzq;
	 				}

	 			}else{// La barca está a la derecha.
	 				// Si hay canibales en la derecha.
	 				if ((3-numCanibalesIzq)>0){
	 					nombreOperador = "Cruza un canibal.";
	 				    // Cruza canibal.
	 				    nnumCanibalesIzq = numCanibalesIzq + 1;
	 				    // Cruza la barca.
	 				    nposBarca = 1;
	 				    // Misioneros se quedan igual.
	 				    nnumMisionerosIzq = numMisionerosIzq;
	 				}
	 			}
	 	 	}
	    
	 		// Operador 1: Cruzan dos canibales.
	 		if (numOperador == 1){

	 			// La barca está en la izquierda
	 			if (posBarca == 1){
	 				// Si hay al menos dos canibales en la izquierda.
	 				if (numCanibalesIzq>1){
	 					nombreOperador = "Cruzan dos canibales.";
	 				    // Cruzan dos canibales.
	 				    nnumCanibalesIzq = numCanibalesIzq - 2;
	 				    // Cruza la barca.
	 				    nposBarca = 0;
	 				    // Misioneros se quedan igual.
	 				    nnumMisionerosIzq = numMisionerosIzq;
	 				}

	 			}else{// La barca está a la derecha.
	 				// Si hay al menos dos canibales en la derecha.
	 				if ((3-numCanibalesIzq)>1){
	 					nombreOperador = "Cruzan dos canibales.";
	 				    // Cruzan dos canibales.
	 				    nnumCanibalesIzq = numCanibalesIzq + 2;
	 				    // Cruza la barca.
	 				    nposBarca = 1;
	 				    // Misioneros se quedan igual.
	 				    nnumMisionerosIzq = numMisionerosIzq;
	 				}
	 			}
	 	 	}
	 		
	    	// Operador 2: Cruza un misionero.
	 		if (numOperador == 2){
	 			// La barca está en la izquierda
	 			if (posBarca == 1){
	 				// Si hay misioneros en la izquierda.
	 				if (numMisionerosIzq>0){
	 					nombreOperador = "Cruza un misionero.";
	 				    // Cruza misionero.
	 				    nnumMisionerosIzq = numMisionerosIzq - 1;
	 				    // Cruza la barca.
	 				    nposBarca = 0;
	 				    // Canibales se quedan igual.
	 				    nnumCanibalesIzq = numCanibalesIzq;
	 				}

	 			}else{// La barca está a la derecha.
	 				// Si hay misioneros en la derecha.
	 				if ((3-numMisionerosIzq)>0){
	 					nombreOperador = "Cruza un misionero.";
	 				    // Cruza canibal.
	 					nnumMisionerosIzq = numMisionerosIzq + 1;
	 				    // Cruza la barca.
	 					nposBarca = 1;
	 				    // Misioneros se quedan igual.
	 				    nnumCanibalesIzq = numCanibalesIzq;
	 				}
	 			}
	 	 	}
	 		
	    	// Operador 3: Cruzan dos misioneros.
	 		if (numOperador == 3){
	 			// La barca está en la izquierda
	 			if (posBarca == 1){
	 				// Si hay al menos dos misioneros en la izquierda.
	 				if (numMisionerosIzq>1){
	 					nombreOperador = "Cruzan dos misioneros.";
	 				    // Cruzan dos misioneros.
	 					nnumMisionerosIzq = numMisionerosIzq - 2;
	 				    // Cruza la barca.
	 					nposBarca = 0;
	 				    // Canibales se quedan igual.
	 				    nnumCanibalesIzq = numCanibalesIzq;
	 				}

	 			}else{// La barca está a la derecha.
	 				// Si hay al menos dos misioneros en la derecha.
	 				if ((3-numMisionerosIzq)>1){
	 					nombreOperador = "Cruzan dos canibales.";
	 				    // Cruzan dos misioneros.
	 					nnumMisionerosIzq = numMisionerosIzq + 2;
	 				    // Cruza la barca.
	 					nposBarca = 1;
	 				    // Canibales se quedan igual.
	 				    nnumCanibalesIzq = numCanibalesIzq;
	 				}
	 			}
	 	 	}
	 		
	    	// Operador 4: Cruza un misionero y un canibal.
	 		if (numOperador == 4){
	 			// La barca está en la izquierda
	 			if (posBarca == 1){
	 				// Si hay al menos hay un misionero y un canibal en la izquierda.
	 				if ((numMisionerosIzq>0)&&(numCanibalesIzq>0)){
	 					nombreOperador = "Cruza un misionero y un canibal.";
	 				    // Cruzan un misionero.
	 					nnumMisionerosIzq = numMisionerosIzq - 1;
	 				    // Cruza un canibal.
	 				    nnumCanibalesIzq = numCanibalesIzq - 1;
	 				    // Cruza la barca.
	 				    nposBarca = 0;
	 				}

	 			}else{// La barca está a la derecha.
	 				// Si hay al menos hay un misionero y un canibal en la derecha.
	 				if (((3-numMisionerosIzq)>0)&&((3-numCanibalesIzq)>0)){
	 					nombreOperador = "Cruza un misionero y un canibal.";
	 				    // Cruzan un misionero.
	 					nnumMisionerosIzq = numMisionerosIzq + 1;
	 				    // Cruza un canibal.
	 				    nnumCanibalesIzq = numCanibalesIzq + 1;
	 				    // Cruza la barca.
	 				    nposBarca = 1;
	 				}
	 			}
	 	 	}
	 		
	 	 	// Creamos el nuevo estado.
	 	 	Canibales nuevoEstado = new Canibales(nnumMisionerosIzq, nnumCanibalesIzq, nposBarca);
	 	 	
	 	 	// Comprobamos si el nuevo estado es válido.
	 	 	if(nuevoEstado.isValid()){	 	 		
	 	 		// Añadimos el estado como sucesor.
	 	 		successorVec.addElement(new Successor(nuevoEstado,nombreOperador,1)); 
	 	 	}
		}

	    return successorVec.elements();
	}

	protected boolean resolverA() {
		boolean resuelto = listPath((new AStarSearch(this)).search());
		return resuelto;
	}

	protected boolean resolverCosteUni() {
		boolean resuelto = listPath((new UniformCostSearch(this)).search());
		return resuelto;
	}

	protected boolean resolverEscalada() {
		boolean resuelto = listPath((new GreedySearch(this)).search());
		return resuelto;
	}

	protected boolean resolverPrimAnchura() {
		boolean resuelto = listPath((new BreadthFirstSearch(this)).search());
		return resuelto;
	}

	protected boolean resolverProfIt() {
		boolean resuelto=listPath((new IteratedDeepeningSearch(this)).search());
		return resuelto;
	}

	protected boolean resolverProfundidad() {
		boolean resuelto=listPath((new DepthBoundedSearch(this,7)).search());
		return resuelto;
	}

    public String toString(){
    	return repEstado;
    }
}
