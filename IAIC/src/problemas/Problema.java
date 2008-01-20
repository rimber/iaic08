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
	
}
