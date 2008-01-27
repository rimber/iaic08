/**
 * 
 */
package problemas;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Clase que implementa el problema del robot-aspiradora según el paradigma del 
 * espacio de estados.
 */
public class Robot extends Problema {
	
	/**
	 * Indica la posición del robot-aspiradora:
	 * 0. Está en la habitación de la izquierda.
	 * 1. Está en la habitación del centro.
	 * 2. Está en la habitación de la derecha.
	 */
	int posRobot;
	
	/**
	 * Indica si la alfomrbra de la habitación de la izquierda está limpia.
	 */
	boolean limpiaHabIzq;
	
	/**
	 * Indica si la alfomrbra de la habitación del centro está limpia.
	 */
	boolean limpiaHabCen;
	
	/**
	 * Indica si la alfomrbra de la habitación de la derecha está limpia.
	 */
	boolean limpiaHabDer;
	
	/**
	 * Constructor por defecto: inicializa las componentes a valores por defecto.
	 */
	public Robot(){
		enunciado = "Sea un micro-mundo formado por 3 habitaciones y un robot aspirador." +
			" Hay una habitación a la izquierda, otra en el centro y otra a la derecha, " +
			"cuyas alfombras pueden	estar sucias o limpias. El robot puede estar en" +
			" cualquiera de las habitaciones y puede ejecutar dos tipos de operaciones: " +
			"aspirar o moverse. La operación de aspirar requiere que la habitación en la" +
			" que se encuentra el robot esté sucia y su resultado es que dicha habitación" +
			" pasa a estar limpia. La operación mover tiene dos opciones: mover hacia la " +
			"izquierda, que requiere que haya alguna habitación a la izquierda de aquella" +
			" en la que se encuentra el robot, y mover hacia la derecha que requiere la" +
			" existencia de alguna habitación situada a la derecha. En cualquier caso, los" +
			" movimientos serán elementales, es	decir, sólo permitirán el paso a la habitación" +
			" contigua a la actual.";
		
		posRobot = 0;
		limpiaHabIzq = false;
		limpiaHabCen = false;
		limpiaHabDer = false;
		
		repEstado = "("+posRobot+","+limpiaHabIzq+","+limpiaHabCen+","+limpiaHabDer+")";
		nombreOperador = "";
	}
	
	/**
	 * Constructor principal, inicializa los atributos a los valores indicados.
	 * @param pRobot Posición del robot.
	 * @param limHabIzq Si está limpia o no la habitación de la izquierda.
	 * @param limHabCen Si está limpia o no la habitación del centro.
	 * @param limHabDer Si está limpia o no la habitación de la derecha.
	 */
	public Robot(int pRobot, boolean limHabIzq, boolean limHabCen, boolean limHabDer){
		enunciado = "Sea un micro-mundo formado por 3 habitaciones y un robot aspirador." +
				" Hay una habitación a la izquierda, otra en el centro y otra a la derecha, " +
				"cuyas alfombras pueden	estar sucias o limpias. El robot puede estar en" +
				" cualquiera de las habitaciones y puede ejecutar dos tipos de operaciones: " +
				"aspirar o moverse. La operación de aspirar requiere que la habitación en la" +
				" que se encuentra el robot esté sucia y su resultado es que dicha habitación" +
				" pasa a estar limpia. La operación mover tiene dos opciones: mover hacia la " +
				"izquierda, que requiere que haya alguna habitación a la izquierda de aquella" +
				" en la que se encuentra el robot, y mover hacia la derecha que requiere la" +
				" existencia de alguna habitación situada a la derecha. En cualquier caso, los" +
				" movimientos serán elementales, es	decir, sólo permitirán el paso a la habitación" +
				" contigua a la actual.";
		
		posRobot = pRobot;
		limpiaHabIzq = limHabIzq;
		limpiaHabCen = limHabCen;
		limpiaHabDer = limHabDer;
		repEstado = "("+posRobot+","+limpiaHabIzq+","+limpiaHabCen+","+limpiaHabDer+")";
		nombreOperador = "";
	}

	/**
	 * Genera el valor heurístico del estado.
	 * @return Valor de la heúristica. 
	 */
	public float h() {
		// Heurística: Mejor cuanto menor sea h, es decir, cuanto menos componentes
		// alfombras estén sucias.
		int h = 0;
		if (!limpiaHabIzq){
			h++;
		}
		
		if (!limpiaHabCen){
			h++;
		}
		
		if (!limpiaHabDer){
			h++;
		}

		return (float)h;
	}

	/**
	 * Indica si estamos ante un estado solución.
	 * @return Valor indicando si el estado es solución o no.
	 */
	public boolean isGoal() {
		return (limpiaHabIzq && limpiaHabCen && limpiaHabDer);
	}

	/**
	 * Comprueba si el estado del problema es válido.
	 * @return Valor indicando la validez del estado.
	 */
	protected boolean isValid() {
			return true;
	}

	/**
	 * Genera todos los posibles estados sucesores del estado actual.
	 * @return Conjunto de estados sucesores.	 
	 */
	public Enumeration successors(){
	
		// OPERADORES:
		//  - Operador 0: Aspirar habitacion.
		//  - Operador 1: Mover robor a la habitación de la izquierda.
		//  - Operador 2: Mover robot a la habitación de la derecha.
		
	 	// Operador usado.
	 	int numOperador;
	 	
	 	// Nuevas posiciones.
		int pRobot = 0;
		boolean limHabIzq = false;
		boolean limHabCen = false;
		boolean limHabDer = false;
	 	Vector successor = new Vector();
	 	
	 	// Incrementamos el número de nodos expandidos.
	 	nodosExpandidos++;
	}

}
