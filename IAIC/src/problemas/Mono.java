/**
 * Contiene el conjunto de los problemas implementados según el paradigma
 * del espacio de estados.
 */
package problemas;

import java.util.Enumeration;
import java.util.Vector;
import aima.search.*;

/**
 * Clase que implementa el problema de la habitación, el mono y el plátano según
 * el paradigma del espacio de estados.
 */
public class Mono extends Problema{

	/**
	 * Posición del mono:
	 * 0. Al lado de la puerta (izquierda).
	 * 1. Al lado de la ventana (derecha).
	 * 2. En el centro de la habitación.
	 */
	private int posMono;
	
	/**
	 * Posición de la caja:
	 * 0. Al lado de la puerta (izquierda).
	 * 1. Al lado de la ventana (derecha).
	 * 2. En el centro de la habitación.
	 */
	private int posCaja;
	
	/**
	 * Indica si el mono está subido o no en la caja.
	 */
	private boolean subidoCaja;
	
	/**
	 * Indica si el mono tiene o no el platano.
	 */
	private boolean tienePlatano;
	
	/**
	 * Constructor por defecto: inicializa las componentes a valores por defecto.
	 */
	public Mono(){
		enunciado = "Hay un mono en la puerta de una habitación. En el centro de la"
				+ " habitación hay un plátano colgando del techo. El mono está"
				+ " hambriento y quiere conseguir el plátano pero no alcanza porque"
				+ " está muy alto. En la habitación también hay una ventana y"
				+ " debajo de ella hay una caja que le permitiría alcanzar el "
				+ "plátano si se subiera a ella. El mono puede realizar las "
				+ "siguientes acciones: andar por el suelo, subirse a la caja, "
				+ "empujar la caja (si el mono está en la misma posición que la "
				+ "caja) y coger el plátano (si está subido encima de la caja y la"
				+ " caja está justo debajo del plátano).";
		
		posMono = 0;
		posCaja = 1;
		subidoCaja = false;
		tienePlatano = false;
	 	nombreOperador = "";
	 	repEstado = "("+posMono+","+posCaja+","+subidoCaja+","+tienePlatano+")";
	}
	
	/**
	 * Constructor principal, inicializa los atributos a los valores indicados.
	 * @param pMono Posición del mono.
	 * @param pCaja Posición de la caja.
	 * @param sCaja Si se está subido o no en la caja.
	 * @param tPlatano Si se tiene o no el platano.
	 */
	public Mono(int pMono, int pCaja, boolean sCaja, boolean tPlatano){
		enunciado = "Hay un mono en la puerta de una habitación. En el centro de la"
				+ " habitación hay un plátano colgando del techo. El mono está"
				+ " hambriento y quiere conseguir el plátano pero no alcanza porque"
				+ " está muy alto. En la habitación también hay una ventana y"
				+ " debajo de ella hay una caja que le permitiría alcanzar el "
				+ "plátano si se subiera a ella. El mono puede realizar las "
				+ "siguientes acciones: andar por el suelo, subirse a la caja, "
				+ "empujar la caja (si el mono está en la misma posición que la "
				+ "caja) y coger el plátano (si está subido encima de la caja y la"
				+ " caja está justo debajo del plátano).";
		
		posMono = pMono;
		posCaja = pCaja;
		subidoCaja = sCaja;
		tienePlatano = tPlatano;
	 	nombreOperador = "";
	 	repEstado = "("+posMono+","+posCaja+","+subidoCaja+","+tienePlatano+")";
	}
	
	/**
	 * Genera el valor heurístico del estado.
	 * @return Valor de la heúristica. 
	 */
	public float h() {
		int h = 0;
		
		// Si no está el mono en el centro de la habitación.
		if (posMono != 2){
			h++;
		}
		
		// Si el mono no está subido a la caja.
		if (!subidoCaja){
			h++;
		}
		
		// Si la caja está en el centro de la habitación.
		if (posCaja != 2){
			h++;
		}
		
		// Si el mono no tiene el platano. 
		if (!tienePlatano){
			h++;
		}
		return (float)h;
	}

	/**
	 * Indica si estamos ante un estado solución.
	 * @return Valor indicando si el estado es solución o no.
	 */
	public boolean isGoal() {
		return tienePlatano;
	}

	/**
	 * Comprueba si el estado del problema es válido.
	 * @return Valor indicando la validez del estado.
	 */
	protected boolean isValid() {
		if (nodosExpandidos > maxNodos){
			return false;
		}
		return true;
	}

	/**
	 * Genera todos los posibles estados sucesores del estado actual.
	 * @return Conjunto de estados sucesores.	 
	 */
	public Enumeration successors() {

		// OPERADORES:
		//  - Operador 0: Mono anda por el suelo hasta la ventana.
		//  - Operador 1: Mono anda por el suelo hasta el centro.
		//  - Operador 2: Mono anda por el suelo hasta la puerta.
		//  - Operador 3: Mono empuja la caja hacia la ventana.
		//  - Operador 4: Mono empuja la caja hacia el centro. 
		//  - Operador 5: Mono empuja la caja hacia la puerta. 
		//  - Operador 6: Mono se sube a la caja. 
		//  - Operador 7: Mono coje el platano. 
		
	 	// Operador usado.
	 	int numOperador;
	 	
	 	// Nuevas posiciones.
	 	int nposMono = 0;
	 	int nposCaja = 1;
	 	boolean nsubidoCaja = false;
	 	boolean ntienePlatano = false;
	 	Vector successorVec = new Vector();
	 	
	 	nodosExpandidos++;

	 	for (numOperador=0; numOperador<8; numOperador++){
	 		
			// Operador 0: Mono anda por el suelo hasta la ventana. 
	 		if (numOperador == 0){
	 			// Si el mono no está en la ventana y no está subido a la caja.
	 			if ((posMono != 1) && (!subidoCaja)){
	 				nombreOperador = "Mono anda a ventana.";
	 				// Cambiamos la posición del mono.
	 				nposMono = 1;
		 			nposCaja = posCaja;
		 			nsubidoCaja = subidoCaja;
		 			ntienePlatano = tienePlatano;
	 			}	
	 		}
	 		
			// Operador 1: Mono anda por el suelo hasta el centro. 
	 		if (numOperador == 1){
	 			// Si el mono no está en el centro y no está subido a la caja.
	 			if ((posMono != 2) && (!subidoCaja)){
	 				nombreOperador = "Mono anda al centro.";
	 				// Cambiamos la posición del mono.
	 				nposMono = 2;
		 			nposCaja = posCaja;
		 			nsubidoCaja = subidoCaja;
		 			ntienePlatano = tienePlatano;
	 			}	
	 		}
	 		
			// Operador 2: Mono anda por el suelo hasta la puerta. 
	 		if (numOperador == 2){
	 			// Si el mono no está en la puerta y no está subido a la caja.
	 			// subido a ella.
	 			if ((posMono != 0) && (!subidoCaja)){
	 				nombreOperador = "Mono anda hacia puerta.";
	 				// Cambiamos la posición del mono.
	 				nposMono = 0;
		 			nposCaja = posCaja;
		 			nsubidoCaja = subidoCaja;
		 			ntienePlatano = tienePlatano;
	 			}	
	 		}
	 		
			// Operador 3: Mono empuja la caja hacia la ventana. 
	 		if (numOperador == 3){
	 			// Si el mono no está en la ventana, tiene la caja y no está
	 			// subido a ella.
	 			if ((posMono != 1) && (posMono == posCaja) && (!subidoCaja)){
	 				nombreOperador = "Mono empuja caja hacia ventana.";
	 				// Cambiamos la posición del mono.
	 				nposMono = 1;
	 				// Cambiamos la posición de la caja.
		 			nposCaja = 1;
		 			nsubidoCaja = subidoCaja;
		 			ntienePlatano = tienePlatano;
	 			}	
	 		}
	 		
			// Operador 4: Mono empuja la caja hacia el centro. 
	 		if (numOperador == 4){
	 			// Si el mono no está en el centro, tiene la caja y no está
	 			// subido a ella.
	 			if ((posMono != 2) && (posMono == posCaja) && (!subidoCaja)){
	 				nombreOperador = "Mono empuja caja hacia centro.";
	 				// Cambiamos la posición del mono.
	 				nposMono = 2;
	 				// Cambiamos la posición de la caja.
		 			nposCaja = 2;
		 			nsubidoCaja = subidoCaja;
		 			ntienePlatano = tienePlatano;
	 			}	
	 		}
	 		
			// Operador 5: Mono empuja la caja hacia la puerta. 
	 		if (numOperador == 5){
	 			// Si el mono no está en la puerta, tiene la caja y no está
	 			// subido a ella.
	 			if ((posMono != 0) && (posMono == posCaja) && (!subidoCaja)){
	 				nombreOperador = "Mono empuja caja hacia puerta.";
	 				// Cambiamos la posición del mono.
	 				nposMono = 0;
	 				// Cambiamos la posición de la caja.
		 			nposCaja = 0;
		 			nsubidoCaja = subidoCaja;
		 			ntienePlatano = tienePlatano;
	 			}	
	 		}
	 		
			// Operador 6: Mono se sube a la caja. 
	 		if (numOperador == 6){
	 			// Si el mono tiene la caja y no está subido a ella.
	 			if ((posMono == posCaja) && (!subidoCaja)){
	 				nombreOperador = "Mono se sube a la caja.";
	 				nposMono = posMono;
		 			nposCaja = posCaja;
		 			// Indicamos que se sube a la caja.
		 			nsubidoCaja = true;
		 			ntienePlatano = tienePlatano;
	 			}	
	 		}

			// Operador 7: Mono coje el platano. 
	 		if (numOperador == 7){
	 			// Si el mono está subido a la caja, está en el centro y
	 			// no tiene el platano.
	 			if ((posMono == 2) && (subidoCaja)&&(!tienePlatano)){
	 				nombreOperador = "Mono coje el platano.";
	 				nposMono = posMono;
		 			nposCaja = posCaja;
		 			nsubidoCaja = subidoCaja;
		 			// Indicamos que coje el platano.
		 			ntienePlatano = true;
	 			}	
	 		}
	 		
	 	    // Creamos el nuevo estado.
	 		Mono nuevoEstado = new Mono(nposMono,nposCaja,nsubidoCaja,ntienePlatano);
	 	 	
	 		// Comprobamos si el nuevo estado es válido.
	 	 	if(nuevoEstado.isValid()){	 	 		
	 	 		// Añadimos el estado como sucesor.
	 	 		successorVec.addElement(new Successor(nuevoEstado,nombreOperador,1)); 
	 	 	}
	 	}

	 	return successorVec.elements();
	}

}
