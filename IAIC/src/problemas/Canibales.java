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
	}
	
	/**
	 * Constructor principal, inicializa los atributos a los valores indicados.
	 * @param pBarca Posición de la barca.
	 * @param nMisioneros Número de misioneros en el margen izquierdo.
	 * @param nCanibales Número de caníbales en el margen izquierdo.
	 */
	public Canibales(int pBarca, int nMisioneros, int nCanibales){
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
		
        // Comprobaciones para no salirse de rango.
        if ((posBarca < 0) || (numMisionerosIzq < 0) || (numCanibalesIzq < 0)){
        	return false;
        }

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
		// TODO Auto-generated method stub
		return null;
	}

}
