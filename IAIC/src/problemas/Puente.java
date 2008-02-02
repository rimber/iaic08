/**
 * Contiene el conjunto de los problemas implementados según el paradigma
 * del espacio de estados.
 */
package problemas;

import java.util.Enumeration;
import java.util.Vector;

import aima.search.Successor;

/**
 * Clase que implementa el problema de los amigos
 * que quieren cruzar el puente con una linterna
 * según el paradigma del espacio de estados.
 * @author  Pablo Fernández Poblaciones 
 * 		    Luis González de Paula		   
 * 		    Antonio Murillo Melero
 * 			IAIC 4ºA
 */
public class Puente extends Problema {
	
	/**
	 * Posición de la linterna (Lado del puente: 1-izquierda, 0-derecha).
	 */
	private int posLinterna;
	
	/**
	 * Posición de Ana (Lado del puente: 1-izquierda, 0-derecha).
	 */
	private int posAna;
	
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
	
	/**
	 * Tiempo que queda disponible para cruzar el puente.
	 */
	private float tiempo;
	
	/**
	 * Constructor por defecto: inicializa las componentes a valores por defecto.
	 */
	public Puente(){
		enunciado = "Cuatro amigos deben cruzar un frágil puente de madera.\n"+
					"Es de noche y es indispensable usar una linterna para\n"+
					"cruzarlo. El puente sólo puede aguantar el peso de dos\n"+
					"personas como máximo y solo tienen una linterna. Tienen\n"+
					"que cruzarlo antes de 15 minutos.Ana tarda 8 minutos en\n"+
					"cruzarlo, Benito 4 minutos,Carlos tarda 2 y David 1.";
		posLinterna = 1;		
		posAna = 1;		
		posBenito = 1;	
		posCarlos = 1;		
		posDavid = 1;
		repEstado = "("+posLinterna+","+posAna+","+posBenito+","+posCarlos+","+posDavid+")";
		nombreOperador = "";
		tiempo = 15;
	}
	
	/**
	 * Constructor principal, inicializa los atributos a los valores indicados.
	 * @param l Posición de la linterna.
	 * @param a Posición de Ana.
	 * @param b Posición de Benito.
	 * @param c Posición de Carlos.
	 * @param d Posición de David.
	 * @param tiempoDisponible cuanto tiempo queda.
	 */
	
	public Puente(int l,int a,int b,int c,int d,int tiempoDisponible){
		enunciado = "Cuatro amigos deben cruzar un frágil puente de madera.\n"+
					"Es de noche y es indispensable usar una linterna para\n"+
					"cruzarlo. El puente sólo puede aguantar el peso de dos\n"+
					"personas como máximo y solo tienen una linterna. Tienen\n"+
					"que cruzarlo antes de 15 minutos.Ana tarda 8 minutos en\n"+
					"cruzarlo, Benito 4 minutos,Carlos tarda 2 y David 1.";
		posLinterna = l;		
		posAna = a;		
		posBenito = b;	
		posCarlos = c;		
		posDavid = d;
		repEstado = "("+posLinterna+","+posAna+","+posBenito+","+posCarlos+","+posDavid+")";
		nombreOperador = "";
		tiempo = tiempoDisponible;
	}
	
	/**
	 * Genera el valor heurístico del estado.
	 * @return Valor de la heúristica. 
	 */
	public float h() {
		// Heurística: Mejor cuanto menor sea h, es decir, cuanto menos componentes
		// estén en el lado izquierdo teniendo en cuenta lo que tardan en cruzar.
		int h = posLinterna + posAna*8 + posBenito*4 + posCarlos*2 + posDavid;
		return (float)h;
	}

	/**
	 * Indica si estamos ante un estado solución.
	 * @return Valor indicando si el estado es solución o no.
	 */
	public boolean isGoal() {
		// Solución si todos los componentes están a la derecha.
		return ((posLinterna == 0)&&(posAna == 0)&&(posBenito == 0)&&(posCarlos == 0)&&(posDavid == 0));
	}

	/**
	 * Comprueba si el estado del problema es válido.
	 * @return Valor indicando la validez del estado.
	 */
	protected boolean isValid() {		
		if (nodosExpandidos > maxNodos){
			return false;
		}
		//Es válido si queda tiempo.
		return (tiempo>=0);
	}

	/**
	 * Genera todos los posibles estados sucesores del estado actual.
	 * @return Conjunto de estados sucesores.	 
	 */
	public Enumeration<Successor> successors() {
		// Tenemos 10 operadores:
		// Operador 0: Cruza Ana sola (con la linterna).
		// Operador 1: Cruza Benito solo (con la linterna).
		// Operador 2: Cruza Carlos solo (con la linterna).
		// Operador 3: Cruza David solo (con la linterna).
		// Operador 4: Cruza Ana con Benito (con la linterna).
		// Operador 5: Cruza Ana con Carlos (con la linterna).
		// Operador 6: Cruza Ana con David (con la linterna).
		// Operador 7: Cruza Benito con Carlos (con la linterna).
		// Operador 8: Cruza Benito con David (con la linterna).
		// Operador 9: Cruza Carlos con David (con la linterna).
		
	 	// Operador usado.
	 	int numOperador;
	 	
	 	// Nuevas posiciones.
	 	int nposLinterna = 1;
	 	int nposAna = 1;
	 	int nposBenito = 1;
	 	int nposCarlos  = 1;
	 	int nposDavid = 1;
	 	int coste = 0;
	 	int nTiempo = 0;
	 	
	 	// Incrementamos el número de nodos expandidos.
	 	nodosExpandidos++;
	 	
	 	Vector<Successor> successor = new Vector<Successor>();
	 	
	 	for (numOperador = 0; numOperador<10;numOperador++){
	 		boolean operadorAplicado = false;
	 		// Operador 0: Cruza Ana sola (con la linterna).
	 		if (numOperador == 0){
	 			// Para poder cruzar Ana la linterna tiene que estar con ella.
	 			if(posLinterna == posAna){
	 				operadorAplicado = true;
	 				nombreOperador = "Cruza Ana sola.";
	 				//Cruza la linterna.
	 				nposLinterna = 1 - posLinterna;
	 				//Cruza Ana.
	 				nposAna = 1 - posAna;
	 				// Los demás se quedan donde están.
	 				nposBenito = posBenito;
	 				nposCarlos = posCarlos;
	 				nposDavid = posDavid;
	 				//Actualizar el coste y el tiempo.
	 				coste = 8; // Tiempo que tarda Ana en cruzar.
	 				nTiempo = (int) (tiempo - coste);
	 			}
	 		}
	 		// Operador 1: Cruza Benito solo (con la linterna).
	 		if(numOperador == 1){
	 			// Para poder cruzar Benito la linterna tiene que estar con el.
	 			if(posLinterna == posBenito){
	 				operadorAplicado = true;
	 				nombreOperador = "Cruza Benito solo.";
	 				//Cruza la linterna.
	 				nposLinterna = 1 - posLinterna;
	 				//Cruza Benito
	 				nposBenito = 1 - posBenito;
	 				// Los demás se quedan donde están.
	 				nposAna = posAna;
	 				nposCarlos = posCarlos;
	 				nposDavid = posDavid;
	 				//Actualizar el coste y el tiempo.
	 				coste = 4; // Tiempo que tarda Benito en cruzar.
	 				nTiempo = (int) (tiempo - coste);
	 			}
	 		}
	 		// Operador 2: Cruza Carlos solo (con la linterna).
	 		if(numOperador == 2){
	 			// Para poder cruzar Carlos la linterna tiene que estar con el.
	 			if(posLinterna == posCarlos){
	 				operadorAplicado = true;
	 				nombreOperador = "Cruza Carlos solo.";
	 				//Cruza la linterna.
	 				nposLinterna = 1 - posLinterna;
	 				//Cruza Carlos.
	 				nposCarlos = 1 - posCarlos;
	 				// Los demás se quedan donde están.
	 				nposAna = posAna;
	 				nposBenito = posBenito;
	 				nposDavid = posDavid;
	 				//Actualizar el coste y el tiempo.
	 				coste = 2; // Tiempo que tarda Carlos en cruzar.
	 				nTiempo = (int) (tiempo - coste);
	 			}
	 		}
	 		// Operador 3: Cruza David solo (con la linterna).
	 		if(numOperador == 3){
	 			// Para poder cruzar David la linterna tiene que estar con el.
	 			if(posLinterna == posDavid){
	 				operadorAplicado = true;
	 				nombreOperador = "Cruza David solo.";
	 				//Cruza la linterna.
	 				nposLinterna = 1 - posLinterna;
	 				//Cruza David.
	 				nposDavid = 1 - posDavid;
	 				// Los demás se quedan donde están.
	 				nposAna = posAna;
	 				nposBenito = posBenito;
	 				nposCarlos = posCarlos;
	 				//Actualizar el coste y el tiempo.
	 				coste = 1; // Tiempo que tarda David en cruzar.
	 				nTiempo = (int) (tiempo - coste);
	 			}
	 		}
	 		// Operador 4: Cruza Ana con Benito (con la linterna).
	 		if (numOperador == 4){
	 			// Para poder cruzar Ana y Benito la linterna tiene que estar con ellos.
	 			if((posLinterna == posAna)&&(posLinterna == posBenito)){
	 				operadorAplicado = true;
	 				nombreOperador = "Cruzan Ana y Benito.";
	 				//Cruza la linterna.
	 				nposLinterna = 1 - posLinterna;
	 				//Cruza Ana.
	 				nposAna = 1 - posAna;
	 				//Cruza Benito.
	 				nposBenito = 1 - posBenito;
	 				// Los demás se quedan donde están.
	 				nposCarlos = posCarlos;
	 				nposDavid = posDavid;
	 				//Actualizar el coste y el tiempo.
	 				coste = 8; // Tiempo del que más tarda en cruzar que es Ana.
	 				nTiempo = (int) (tiempo - coste);
	 			}
	 		}
	 		// Operador 5: Cruza Ana con Carlos (con la linterna).
	 		if (numOperador == 5){
	 			// Para poder cruzar Ana y Carlos la linterna tiene que estar con ellos.
	 			if((posLinterna == posAna)&&(posLinterna == posCarlos)){
	 				operadorAplicado = true;
	 				nombreOperador = "Cruzan Ana y Carlos.";
	 				//Cruza la linterna.
	 				nposLinterna = 1 - posLinterna;
	 				//Cruza Ana.
	 				nposAna = 1 - posAna;
	 				//Cruza Carlos.
	 				nposCarlos = 1 - posCarlos;
	 				// Los demás se quedan donde están.
	 				nposBenito = posBenito;
	 				nposDavid = posDavid;
	 				//Actualizar el coste y el tiempo.
	 				coste = 8; // Tiempo del que más tarda en cruzar que es Ana.
	 				nTiempo = (int) (tiempo - coste);
	 			}
	 		}
	 		// Operador 6: Cruza Ana con David (con la linterna).
	 		if (numOperador == 6){
	 			// Para poder cruzar Ana y David la linterna tiene que estar con ellos.
	 			if((posLinterna == posAna)&&(posLinterna == posDavid)){
	 				operadorAplicado = true;
	 				nombreOperador = "Cruzan Ana y David.";
	 				//Cruza la linterna.
	 				nposLinterna = 1 - posLinterna;
	 				//Cruza Ana.
	 				nposAna = 1 - posAna;
	 				//Cruza David.
	 				nposDavid = 1 - posDavid;
	 				// Los demás se quedan donde están.
	 				nposBenito = posBenito;
	 				nposCarlos = posCarlos;
	 				//Actualizar el coste y el tiempo.
	 				coste = 8; // Tiempo del que más tarda en cruzar que es Ana.
	 				nTiempo = (int) (tiempo - coste);
	 			}
	 		}
	 		// Operador 7: Cruza Benito con Carlos (con la linterna).
	 		if (numOperador == 7){
	 			// Para poder cruzar Benito y Carlos la linterna tiene que estar con ellos.
	 			if((posLinterna == posBenito)&&(posLinterna == posCarlos)){
	 				operadorAplicado = true;
	 				nombreOperador = "Cruzan Benito y Carlos.";
	 				//Cruza la linterna.
	 				nposLinterna = 1 - posLinterna;
	 				//Cruza Benito.
	 				nposBenito = 1 - posBenito;
	 				//Cruza Carlos.
	 				nposCarlos = 1 - posCarlos;
	 				// Los demás se quedan donde están.
	 				nposAna = posAna;
	 				nposDavid = posDavid;
	 				//Actualizar el coste y el tiempo.
	 				coste = 4; // Tiempo del que más tarda en cruzar que es Benito.
	 				nTiempo = (int) (tiempo - coste);
	 			}
	 		}
	 		// Operador 8: Cruza Benito con David (con la linterna).
	 		if (numOperador == 8){
	 			// Para poder cruzar Benito y David la linterna tiene que estar con ellos.
	 			if((posLinterna == posBenito)&&(posLinterna == posDavid)){
	 				operadorAplicado = true;
	 				nombreOperador = "Cruzan Benito y David.";
	 				//Cruza la linterna.
	 				nposLinterna = 1 - posLinterna;
	 				//Cruza Benito.
	 				nposBenito = 1 - posBenito;
	 				//Cruza David.
	 				nposDavid = 1 - posDavid;
	 				// Los demás se quedan donde están.
	 				nposAna = posAna;
	 				nposCarlos = posCarlos;
	 				//Actualizar el coste y el tiempo.
	 				coste = 4; // Tiempo del que más tarda en cruzar que es Benito.
	 				nTiempo = (int) (tiempo - coste);
	 			}
	 		}
	 		// Operador 9: Cruza Carlos con David (con la linterna).
	 		if (numOperador == 9){
	 			// Para poder cruzar Carlos y David la linterna tiene que estar con ellos.
	 			if((posLinterna == posCarlos)&&(posLinterna == posDavid)){
	 				operadorAplicado = true;
	 				nombreOperador = "Cruzan Carlos y David.";
	 				//Cruza la linterna.
	 				nposLinterna = 1 - posLinterna;
	 				//Cruza Carlos.
	 				nposCarlos = 1 - posCarlos;
	 				//Cruza David.
	 				nposDavid = 1 - posDavid;
	 				// Los demás se quedan donde están.
	 				nposAna = posAna;
	 				nposBenito = posBenito;
	 				//Actualizar el coste y el tiempo.
	 				coste = 2; // Tiempo del que más tarda en cruzar que es Carlos.
	 				nTiempo = (int) (tiempo - coste);
	 			}
	 		}
	 		
	 		if (operadorAplicado){
		 		// Creamos el nuevo estado.
		 	 	Puente nuevoEstado = new Puente(nposLinterna,nposAna,nposBenito,nposCarlos,nposDavid,nTiempo);
		 	 		
		 	 	// Comprobamos si el nuevo estado es válido.
		 	 	if(nuevoEstado.isValid()){	 	 		
		 	 		// Añadimos el estado como sucesor.
		 	 		successor.addElement(new Successor(nuevoEstado,nombreOperador,coste)); 
		 	 	}
	 		}
	 	}
	 	return successor.elements();
	}
	
	public String dameTitulo(){
		return "Puente";
		
	}
}
