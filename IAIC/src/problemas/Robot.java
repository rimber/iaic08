/**
 * 
 */
package problemas;

import java.util.Enumeration;
import java.util.Random;
import java.util.Vector;

import aima.search.Successor;

/**
 * Clase que implementa el problema del robot-aspirador según el paradigma del 
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
		
		Random generador = new Random();
		posRobot = generador.nextInt(3);

		if ((generador.nextInt(2))==0){
			limpiaHabIzq = false;
		}else{
			limpiaHabIzq = true;
		}
		
		if ((generador.nextInt(2))==0){
			limpiaHabCen = false;
		}else{
			limpiaHabCen = true;
		}
		
		if ((generador.nextInt(2))==0){
			limpiaHabDer = false;
		}else{
			limpiaHabDer = true;
		}
		
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
	public Enumeration<Successor> successors(){
	
		// OPERADORES:
		//  - Operador 0: Aspirar habitación.
		//  - Operador 1: Mover robot a la habitación de la izquierda.
		//  - Operador 2: Mover robot a la habitación de la derecha.
		
	 	// Operador usado.
	 	int numOperador;
	 	
	 	// Nuevas posiciones.
		int pRobot = 0;
		boolean limHabIzq = false;
		boolean limHabCen = false;
		boolean limHabDer = false;
	 	Vector<Successor> successor = new Vector<Successor>();
	 	
	 	// Incrementamos el número de nodos expandidos.
	 	nodosExpandidos++;
	 	
	 	for (numOperador = 0; numOperador <3; numOperador++){
	 		boolean operadorAplicado = false;
	 		
	 		// Operador 0: Aspirar habitación.
	 		if (numOperador == 0){
	 			// Comprobamos como está la alfombra de la habitación
	 			// en la que está el aspirador.
	 			boolean limpiaHab = false;
	 			switch (posRobot){
	 				case 0: 
	 					limpiaHab = limpiaHabIzq;
	 					break;
	 				case 1:
	 					limpiaHab = limpiaHabCen;
	 					break;
	 				case 2:
	 					limpiaHab = limpiaHabDer;
	 					break;
	 			}
	 			
	 			// Si no está limpia, aplicamos operador.
	 			if (!limpiaHab){
	 				operadorAplicado = true;
	 				nombreOperador = "aspirar";
	 				
	 				// Limpiamos la habitación.
		 			switch (posRobot){
		 				case 0: 
		 					limHabIzq = true;
		 					limHabCen = limpiaHabCen;
		 					limHabDer = limpiaHabDer;
		 					break;
		 				case 1:
		 					limHabCen = true;
		 					limHabIzq = limpiaHabIzq;
		 					limHabDer = limpiaHabDer;
		 					break;
		 				case 2:
		 					limHabDer = true;
		 					limHabCen = limpiaHabCen;
		 					limHabIzq = limpiaHabIzq;
		 					break;
		 			}
		 			pRobot = posRobot;
	 			}
	 	 	}
	 		
	 		// Operador 1: Mover robot a la habitación de la izquierda.
	 		if (numOperador == 1){
	 			// Si el robot no está en la habitación de la izquierda.
	 			if (posRobot!=0){
	 				// Aplicamos operador.
	 				operadorAplicado = true;
	 				nombreOperador = "moverIzquierda";
	 				// Movemos al robot.
	 				pRobot = posRobot-1;
	 				// El resto se queda igual.
 					limHabCen = limpiaHabCen;
 					limHabDer = limpiaHabDer;
 					limHabIzq = limpiaHabIzq;
	 			}
	 		}
	 		
	 		// Operador 1: Mover robot a la habitación de la derecha.
	 		if (numOperador == 2){
	 			// Si el robot no está en la habitación de la derecha.
	 			if (posRobot!=2){
	 				// Aplicamos operador.
	 				operadorAplicado = true;
	 				nombreOperador = "moverDerecha";
	 				// Movemos al robot.
	 				pRobot = posRobot + 1;
	 				// El resto se queda igual.
 					limHabCen = limpiaHabCen;
 					limHabDer = limpiaHabDer;
 					limHabIzq = limpiaHabIzq;
	 			}
	 		}
	 		
	 		if (operadorAplicado){
	 			// Creamos el nuevo estado.
		 		Robot nuevoEstado = new Robot(pRobot,limHabIzq,limHabCen,limHabDer);
	 	 		
	 	 		// Comprobamos si el nuevo estado es válido.
	 	 		if(nuevoEstado.isValid()){	 	 		
	 	 			// Añadimos el estado como sucesor.
	 	 			successor.addElement(new Successor(nuevoEstado,nombreOperador,1)); 
	 	 		}
	 		}
 	 	}
 	 
 	 	return successor.elements();
	}
	public String dameTitulo(){
		return "Robot";
		
	}
	
}
