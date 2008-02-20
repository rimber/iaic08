/**
 * Contiene el conjunto de clases que implementan el micromundo del edificio cúbico.
 */
package Cubo;

import java.util.ArrayList;
import java.util.Random;
import problemas.*;

/**
 * Clase que implementa la representación de una puerta en el micromundo.
 */
public class Puerta {

	/**
	 * Instancia del problema que requiere la puerta para ser abierta.
	 */
	private Problema prob;

	/**
	 * Estado actual de la puerta.
	 * 0. Cerrada. 
	 * 1. Abierta. 
	 * 2. Bloqueada.
	 */
	private int estado;


	/**
	 * Método que se va a usar para abrir la puerta, sólo se usa en caso de cargar de archivo
	 * 
	 * */
	
	private int metodo;
	
	
	/**
	 * Constructor principal de Puerta.
	 */
	public Puerta() {
		estado = 0;
	}

	/**
	 * Constructor por parámetros de Puerta.
	 * @param e Estado en que comienza la puerta.
	 * @param p Problema asociado a la puerta.
	 * @param m Método con el que se va a abrir la puerta
	 */
	public Puerta(int e, Problema p,int m) {
		estado = e;
		prob = p;
		metodo=m;
	}

	/**
	 * Constructor por parámetros de Puerta.
	 * @param e Estado actual de la puerta.
	 */
	public Puerta(int e) {
		estado = e;
		if (e == 0) {

			// Incluimos un problema aleatoriamente.
			Random rnd = new Random();
			int opc = rnd.nextInt(Problema.numProblemas);

			switch (opc) {
			case 0:
				prob = new Canibales();
				break;

			case 1:
				prob = new Jarras();
				break;

			case 2:
				prob = new Granjero();
				break;
			case 3:
				prob = new Mono();
				break;
			case 4:
				prob = new Palillos();
				break;
			case 5:
				prob = new Puente();
				break;
			case 6:
				prob = new Puzzle8();
				break;
			case 7:
				prob = new RioYFamilia();
				break;
			case 8:
				prob = new Robot();
				break;
			case 9:
				prob = new RojoAzul();
				break;
			default:
				prob = new Granjero();
			}
		}

		if (e == 2) {
			prob = new Jarras();
		}
		if (e == 1) {
			prob = new Jarras();
		}
	}

	
	public Problema dameProblema(){
		return prob;		
	}
	
	
	public int dameEstrategia(){
		
		return metodo;
	}
	/**
	 * Método que genera la nueva instancia del problema y lo coloca en la puerta.
	 * @param problema Identificador del problema a elegir.
	 */
	public void setProblema(int problema) {
		switch (problema) {
		case 0:
			prob = new Canibales();
			break;
		case 1:
			prob = new Jarras();
			break;
		case 2:
			prob = new Granjero();
			break;
		case 3:
			prob = new Mono();
			break;
		case 4:
			prob = new Palillos();
			break;
		case 5:
			prob = new Puente();
			break;
		case 6:
			prob = new Puzzle8();
			break;
		case 7:
			prob = new RioYFamilia();
			break;
		case 8:
			prob = new Robot();
			break;
		case 9:
			prob = new RojoAzul();
			break;
		default:
			prob = new Mono();
		}
	}

	/**
	 * Método accesor al estado actual de la puerta.
	 * @return Estado actual de la puerta.
	 */
	public int dameEstado() {
		return estado;
	}

	/**
	 * Método accesor a la posible solución encontrada para la instancia concreta del problema asociado a la puerta.
	 * @return Descripción de la solución al problema asociado a la puerta.
	 * */
	public ArrayList<String> dameSolucion() {
		return prob.getCamino();
	}

	/**
	 * Método que pone la puerta al estado que se le pasa por parametro.
	 * @param e Estado al que va a pasar la puerta.
	 */
	public void setEstado(int e) {
		if ((e >= 0) && (e < 3)){
			estado = e;
		} else{
			estado = 0;
		}
	}

	/**
	 * Metodo accesor al enunciado del problema asociado a la puerta.
	 * @return Enunciado del problema asociado a la puerta.
	 */
	public String dameDescripcion() {

		return prob.getEnunciado();
	}

	/**
	 * Metodo accesor al título del problema asociado a la puerta.
	 * @return Título del problema asociado a la puerta.
	 */
	public String dameTitulo() {
		return prob.dameTitulo();
	}

	/**
	 * Método que intenta resolver el problema asociado a la puerta utilizándo la estrategia asociada.
	 * @return Si se ha conseguido resolver el problema y abrir la puerta.
	 */
	public boolean abre() {
		if (estado == 2) {
			// Si está bloqueada no se puede abrir.
			return false;
		}

		if (estado == 1) {
			// Sin está abierta.
			return true;
		}

		if (estado == 0) {
			// Si está cerrada, trata de resolver.
			if (prob.resolver(metodo)) {
				// Abre la puerta.
				estado = 1;
				return true;
			} else {
				// Bloquea la puerta.
				estado = 2;
				return false;
			}
		}
		return true;
	}

	/**
	 * Método que intenta resolver el problema asociado a la puerta y abrirla.
	 * @param est Estrategia con la que se quiere resolver el problema asociado, si el problema
	 * ya tenia asociada una estrategia, se abrirá con la que el problema tenia asignado, no la que se ha pasado
	 * @return Si se ha conseguido resolver el problema y abrir la puerta.
	 */
	public boolean abre(int est) { //mirar cuando se llama a esto

		if (estado == 2) {
			// Si está bloqueada no se puede abrir.
			return false;
		}

		if (estado == 1) {
			// Sin está abierta.
			return true;
		}

		if (estado == 0) {
			// Si está cerrada, trata de resolver.
			if (metodo==0)
			if (prob.resolver(est)) {
				// Abre la puerta.
				estado = 1;
				return true;
			} else {
				// Bloquea la puerta.
				estado = 2;
				return false;
			}
			else if (prob.resolver(metodo)) {
				// Abre la puerta.
				estado = 1;
				return true;
			} else {
				// Bloquea la puerta.
				estado = 2;
				return false;
			}
		}
		return true;
	}
}
