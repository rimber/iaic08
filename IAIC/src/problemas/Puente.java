/**
 * Contiene el conjunto de los problemas implementados según el paradigma
 * del espacio de estados.
 */
package problemas;

import java.util.Enumeration;

/**
 * Clase que implementa el problema de los amigos
 * que quieren cruzar el puente con una linterna
 * según el paradigma del espacio de estados.
 */
public class Puente extends Problema {
	
	/**
	 * Posición de la linterna (Lado del puente: 1-izquierda, 0-derecha).
	 */
	private int posLinterna;
	
	/**
	 * Posición de Alicia (Lado del puente: 1-izquierda, 0-derecha).
	 */
	private int posAlicia;
	
	/**
	 * Posición de Benito (Lado del puente: 1-izquierda, 0-derecha).
	 */
	private int posBenito;
	
	/**
	 * Posición de Carlos (Lado del puente: 1-izquierda, 0-derecha).
	 */
	private int posCarlos;
	
	/**
	 * Posición de David (Lado del puente: 1-izquierda, 0-derecha).
	 */
	private int posDavid;	

	public Puente(){
		enunciado = "Cuatro amigos deben cruzar un frágil puente de madera.\n"+
					"Es de noche y es indispensable usar una linterna para\n"+
					"cruzarlo. El puente sólo puede aguantar el peso de dos\n"+
					"personas como máximo y solo tienen una linterna.\n"+
					"Alicia tarda 8 minutos en cruzarlo, Benito 4 minutos,\n"+
					"Carlos tarda 2 y David 1.";
		posLinterna = 1;		
		posAlicia = 1;		
		posBenito = 1;	
		posCarlos = 1;		
		posDavid = 1;
		repEstado = "("+posLinterna+","+posAlicia+","+posBenito+","+posCarlos+","+posDavid+")";
		nombreOperador = "";
	}
	
	/**
	 * Genera el valor heurístico del estado.
	 * @return Valor de la heúristica. 
	 */
	public float h() {
		// Heurística: Mejor cuanto menor sea h, es decir, cuanto menos componentes
		// estén en el lado izquierdo.
		int h = posLinterna + posAlicia + posBenito + posCarlos + posDavid;
		return (float)h;
	}

	/**
	 * Indica si estamos ante un estado solución.
	 * @return Valor indicando si el estado es solución o no.
	 */
	public boolean isGoal() {
		// Solución si todos los componentes están a la derecha.
		return ((posLinterna == 0)&&(posAlicia == 0)&&(posBenito == 0)&&(posCarlos == 0)&&(posDavid == 0));
	}

	/**
	 * Comprueba si el estado del problema es válido.
	 * @return Valor indicando la validez del estado.
	 */
	protected boolean isValid() {
		// TODO Auto-generated method stub
		return false;
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
