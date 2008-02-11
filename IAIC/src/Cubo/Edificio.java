/**
 * Contiene el conjunto de clases que implementan el micromundo del edificio cúbico.
 */
package Cubo;

import java.util.*;
import salidaPantalla.VPrincipal;

/**
 * Clase que implementa la representación del edificio en el micromundo.
 */
public class Edificio {

	/**
	 * Matriz tridimensional de habitaciones para representar las habitaciones en el edificio.
	 */
	private Habitacion habitaciones[][][];

	/**
	 * Sub-solución encontrada hasta el momento.
	 */
	private ArrayList<Coord3d> recorrido;

	/**
	 * Tamaño del edificio.
	 */
	private int dimension;

	/**
	 * Referencia al formulario principal para poder pasarle datos de la resolución
	 * del problema general.
	 * 
	 */
	private VPrincipal enlace;

	/**
	 * Indica si hemos llegado a un conjunto cerrados de nodos expandidos entre los que no 
	 * se encuentra la solucion.
	 */
	private boolean edificioCerrado;

	/**
	 * Constructor principal del edificio.
	 * @param n Tamaño del edificio a crear.
	 * @param v Referencia al formulario principal que ha creado el edificio.
	 */
	public Edificio(int n, VPrincipal v) {
		if(n>0){
			habitaciones = new Habitacion[n][n][n];
			dimension = n;
			recorrido = new ArrayList<Coord3d>();
			edificioCerrado = false;
			enlace = v;
		}
	}

	/**
	 * Método accesor del titulo del siguiente problema a resolver.
	 * @param direccion Direccion de la puerta dentro de la habitación.
	 * @return Título del siguiente problema a resolver.
	 */
	public String muestraTituloSiguienteProblema(int direccion) {
		String devolver = "";
		int posicion = recorrido.size();
		Coord3d c = (Coord3d) recorrido.get(posicion - 1);
		Habitacion h = habitaciones[c.getx()][c.gety()][c.getz()];
		devolver = h.dameTitulo(direccion);		
		return devolver;

	}

	/**
	 * Método accesor de la descripcion del siguiente problema a resolver.
	 * @param direccion Dirección de la puerta dentro de la habitación.
	 * @return Descripcion del siguiente problema a resolver.
	 */
	public String muestraDescripcionSiguienteProblema(int direccion) {
		String devolver = "";
		int posicion = recorrido.size();
		Coord3d c = (Coord3d) recorrido.get(posicion - 1);
		Habitacion h = habitaciones[c.getx()][c.gety()][c.getz()];
		devolver = h.dameDescripcion(direccion);
		return devolver;
	}

	/**
	 * Método accesor de la subsolución encontrada hasta ahora.
	 * @return Descripcion del siguiente problema a resolver en una variable y por pantalla.
	 */
	public String muestraRecorrido() {

		String devolver = " Recorrido:\n ";
		int linea = 1;
		for (int i = 0; i < recorrido.size(); i++) {
			Coord3d c = (Coord3d) recorrido.get(i);
			devolver += ("(" + c.getx() + "," + c.gety() + "," + c.getz() + "), ");
			if (linea == 8){
				devolver += "\n ";
				linea = 0;
			}
			linea++;
		}
		return devolver;
	}

	/**
	 * Método que genera la primera habitación del edificio de forma aleatoria.
	 * @return Coordenada de inicio.
	 */
	public Coord3d inicia() {

		// Genera una coordenada aleatoria para colocar al personaje.
		Random rnd = new Random();

		int x = rnd.nextInt(dimension);
		int y = rnd.nextInt(dimension);
		int z = rnd.nextInt(dimension);

		habitaciones[x][y][z] = new Habitacion();
		Coord3d coord = new Coord3d(x, y, z);
		ponPuertas(coord);
		recorrido.add(coord);
		return coord;
	}

	/**
	 * Metodo accesor para saber si se ha salido del edificcio.
	 * @return Devuelve si se ha encontrado una salida al edificio.
	 */
	public boolean salida() {
		int posicion = recorrido.size();
		Coord3d c = (Coord3d) recorrido.get(posicion - 1);
		if (c.gety() == 0) {
			if ((c.getz() == dimension - 1) || (c.getz() == 0)) {
				if ((c.getx() == 0) || (c.getx() == dimension - 1)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Método accesor para saber si no existe solución al problema de salir del edificio.
	 * @return Devuelve si no quedan más nodos por expandir y no se ha encontrado solución.
	 */
	public boolean cerrado() {
		/* Devuelve si has llegado a un conjunto cerrado
		 * de puertas, si el camino no tiene solución.
		 */
		return edificioCerrado;
	}

	/**
	 * Método que aplica el operador de avance.
	 * @param direccion Indica la dirección dentro de la habitación en la que estamos hacia la que vamos a avanzar.
	 * @param estrategia Indica la estrategia que vamos a usar para abrir la siguiente puerta para avanzar.
	 * @return Devuelve si se ha podido abrir la puerta y avanzar.
	 */
	public boolean avanza(int direccion, int estrategia) {

		// Seleccionamos la habitacion en la que estamos.
		int posicion = recorrido.size();
		Coord3d c = (Coord3d) recorrido.get(posicion - 1);
		Habitacion h = habitaciones[c.getx()][c.gety()][c.getz()];		
		// Abrimos la puerta indicada        
		if (h.abrePuerta(direccion, estrategia)) {
			/* Si la puerta se ha abierto, puede ser porque  hallamos llegado
			 * a una habitacion donde ya hemos estado, o porque estemos en una habitacion
			 * nueva (la comparación de habitaciones[x][y][z]==NULL nos dice si hemos
			 * estado antes en la habitacion).
			 */
			
			ArrayList <String> solucion=h.dameSolucion(direccion);
			if(solucion==null){
				solucion=new ArrayList<String>();				
			}
			enlace.ponSolucion(solucion);
			switch (direccion) {
			case 0:
				if (habitaciones[c.getx()][c.gety() - 1][c.getz()] == null) {
					habitaciones[c.getx()][c.gety() - 1][c.getz()] = new Habitacion();
					Coord3d nuevac = new Coord3d(c.getx(), c.gety() - 1, c
							.getz());
					recorrido.add(nuevac);
					ponPuertas(nuevac);
					return true;
				}
				break;

			case 1:
				if (habitaciones[c.getx() + 1][c.gety()][c.getz()] == null) {
					habitaciones[c.getx() + 1][c.gety()][c.getz()] = new Habitacion();
					Coord3d nuevac = new Coord3d(c.getx() + 1, c.gety(), c
							.getz());
					recorrido.add(nuevac);
					ponPuertas(nuevac);
					return true;
				}
				break;

			case 2:
				if (habitaciones[c.getx()][c.gety() + 1][c.getz()] == null) {
					habitaciones[c.getx()][c.gety() + 1][c.getz()] = new Habitacion();
					Coord3d nuevac = new Coord3d(c.getx(), c.gety() + 1, c
							.getz());
					recorrido.add(nuevac);
					ponPuertas(nuevac);
					return true;
				}
				break;

			case 3:
				if (habitaciones[c.getx() - 1][c.gety()][c.getz()] == null) {
					habitaciones[c.getx() - 1][c.gety()][c.getz()] = new Habitacion();
					Coord3d nuevac = new Coord3d(c.getx() - 1, c.gety(), c
							.getz());
					recorrido.add(nuevac);
					ponPuertas(nuevac);
					return true;
				}
				break;

			case 4:
				if (habitaciones[c.getx()][c.gety()][c.getz() - 1] == null) {
					habitaciones[c.getx()][c.gety()][c.getz() - 1] = new Habitacion();
					Coord3d nuevac = new Coord3d(c.getx(), c.gety(),
							c.getz() - 1);
					recorrido.add(nuevac);
					ponPuertas(nuevac);
					return true;
				}
				break;

			case 5:
				if (habitaciones[c.getx()][c.gety()][c.getz() + 1] == null) {
					habitaciones[c.getx()][c.gety()][c.getz() + 1] = new Habitacion();
					Coord3d nuevac = new Coord3d(c.getx(), c.gety(),
							c.getz() + 1);
					recorrido.add(nuevac);
					ponPuertas(nuevac);
					return true;
				}
				break;

			default:
				return false;
			}
		}else{
			// Si no has podido abrir la puerta o has llegado a una de las otras.
			
			ArrayList <String> solucion=new ArrayList<String>();
			String mensaje = "\n No se ha podido abrir la puerta!";
			/*
			if (estadoViejoPuerta == 2){
				mensaje = mensaje + "\n ¡La puerta estaba bloqueada!";
			}else{
				mensaje = mensaje + "\n ¡No se ha resuelto el problema!" + "\n ¡La puerta se ha bloqueado!";
			}
			*/
			solucion.add(mensaje);
			enlace.ponSolucion(solucion);
		}

		return false;
	}

	/**
	 * Método que aplica el operador de avance.
	 * @param direccion Indica la dirección dentro de la habitción en la que estamos hacia la que vamos a avanzar.
	 * @param estrategia Indica la estrategia que vamos a usar para abrir la siguiente puerta para avanzar.
	 * @param problema Indica el problema que vamos a poner en la siguiente puerta para avanzar.
	 * @return Devuelve si se ha podido abrir la puerta con el problema dado y avanzar.
	 */
	public boolean avanza(int direccion, int estrategia, int problema) {

		// Seleccionamos la habitacion en la que estamos.
		int posicion = recorrido.size();
		Coord3d c = (Coord3d) recorrido.get(posicion - 1);
		Habitacion h = habitaciones[c.getx()][c.gety()][c.getz()];
     
		Puerta p=h.damePuerta(direccion);
		//meter el problema en la puerta
		// Abrimos la puerta indicada   
		p.setProblema(problema);
		if (h.abrePuerta(direccion, estrategia)) {
			/* Si la puerta se ha abierto, puede ser porque  hallamos llegado
			 * a una habitacion donde ya hemos estado, o porque estemos en una habitacion
			 * nueva (la comparación de habitaciones[x][y][z]==NULL nos dice si hemos
			 * estado antes en la habitacion).
			 */
			
			ArrayList <String> solucion=h.dameSolucion(direccion);
			if(solucion==null){
				solucion=new ArrayList<String>();				
			}
			enlace.ponSolucion(solucion);
			switch (direccion) {
			case 0:
				if (habitaciones[c.getx()][c.gety() - 1][c.getz()] == null) {
					habitaciones[c.getx()][c.gety() - 1][c.getz()] = new Habitacion();
					Coord3d nuevac = new Coord3d(c.getx(), c.gety() - 1, c
							.getz());
					recorrido.add(nuevac);
					ponPuertas(nuevac);
					return true;
				}
				break;

			case 1:
				if (habitaciones[c.getx() + 1][c.gety()][c.getz()] == null) {
					habitaciones[c.getx() + 1][c.gety()][c.getz()] = new Habitacion();
					Coord3d nuevac = new Coord3d(c.getx() + 1, c.gety(), c
							.getz());
					recorrido.add(nuevac);
					ponPuertas(nuevac);
					return true;
				}
				break;

			case 2:
				if (habitaciones[c.getx()][c.gety() + 1][c.getz()] == null) {
					habitaciones[c.getx()][c.gety() + 1][c.getz()] = new Habitacion();
					Coord3d nuevac = new Coord3d(c.getx(), c.gety() + 1, c
							.getz());
					recorrido.add(nuevac);
					ponPuertas(nuevac);
					return true;
				}
				break;

			case 3:
				if (habitaciones[c.getx() - 1][c.gety()][c.getz()] == null) {
					habitaciones[c.getx() - 1][c.gety()][c.getz()] = new Habitacion();
					Coord3d nuevac = new Coord3d(c.getx() - 1, c.gety(), c
							.getz());
					recorrido.add(nuevac);
					ponPuertas(nuevac);
					return true;
				}
				break;

			case 4:
				if (habitaciones[c.getx()][c.gety()][c.getz() - 1] == null) {
					habitaciones[c.getx()][c.gety()][c.getz() - 1] = new Habitacion();
					Coord3d nuevac = new Coord3d(c.getx(), c.gety(),
							c.getz() - 1);
					recorrido.add(nuevac);
					ponPuertas(nuevac);
					return true;
				}
				break;

			case 5:
				if (habitaciones[c.getx()][c.gety()][c.getz() + 1] == null) {
					habitaciones[c.getx()][c.gety()][c.getz() + 1] = new Habitacion();
					Coord3d nuevac = new Coord3d(c.getx(), c.gety(),
							c.getz() + 1);
					recorrido.add(nuevac);
					ponPuertas(nuevac);
					return true;
				}
				break;

			default:
				return false;
			}
		}
		// Si no has podido abrir la puerta o has llegado a una de las otras.
		return false;
	}

	/**
	 * Método que coloca las puertas a la habitación indicada, comprobando por su
     * situación, si esas puertas ya han sido generadas antes para otras habitaciones adyacentes.
	 * @param c Indica las coordenadas de la habitación a la que se le deben poner las puertas.
	 */
	private void ponPuertas(Coord3d c) {

		Coord3d comprobar;
		Puerta p;

		// Comprobamos la existencia de puertas aledañas.
		if (enlace.esManual()) {
			comprobar = c.izquierda();
			if (!existe(comprobar)) {
				// Puerta cerrada.
				p = new Puerta(0);
			} 
			else {
				p = habitaciones[comprobar.getx()][comprobar.gety()][comprobar
						.getz()].damePuerta(1);
			}
			habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p, 3);

			comprobar = c.derecha();
			if (!existe(comprobar)) {
				// Puerta cerrada.
				p = new Puerta(0);
			} 
			else {
				p = habitaciones[comprobar.getx()][comprobar.gety()][comprobar
						.getz()].damePuerta(3);
			}
			habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p, 1);

			comprobar = c.arriba();
			if (!existe(comprobar)) {
				// Puerta cerrada.
				p = new Puerta(0);
			}
			else {
				p = habitaciones[comprobar.getx()][comprobar.gety()][comprobar
						.getz()].damePuerta(0);
			}
			habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p, 2);

			comprobar = c.abajo();
			if (!existe(comprobar)) {
				// Puerta cerrada.
				p = new Puerta(0);
			}
			else {
				p = habitaciones[comprobar.getx()][comprobar.gety()][comprobar
						.getz()].damePuerta(2);
			}
			habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p, 0);

			comprobar = c.delante();
			if (!existe(comprobar)) {
				// Puerta cerrada.
				p = new Puerta(0);
			}
			else {
				p = habitaciones[comprobar.getx()][comprobar.gety()][comprobar
						.getz()].damePuerta(5);
			}
			habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p, 4);

			comprobar = c.detras();
			if (!existe(comprobar)) {
				// Puerta cerrada.
				p = new Puerta(0);
			}
			else {
				p = habitaciones[comprobar.getx()][comprobar.gety()][comprobar
						.getz()].damePuerta(4);
			}
			habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p, 5);

			// Bloqueamos puertas laterales. 	
			if (c.getx() == 0) {
				p = new Puerta(2);
				habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p, 3);
			}

			if (c.getx() == dimension - 1) {
				p = new Puerta(2);
				habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p, 1);
			}

			if (c.gety() == 0) {
				p = new Puerta(2);
				habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p, 0);
			}

			if (c.gety() == dimension - 1) {
				p = new Puerta(2);
				habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p, 2);
			}

			if (c.getz() == 0) {
				p = new Puerta(2);
				habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p, 4);
			}

			if (c.getz() == dimension - 1) {
				p = new Puerta(2);
				habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p, 5);
			}
		} 
		/* MODO CARGAR DE ARCHIVO, 
		 * CONSTRUCTOR DE PUERTA CON PROBLEMA
		 */
		else {
			comprobar = c.izquierda();
			if (!existe(comprobar)) {
				// Puerta cerrada.
				p = new Puerta(0);
			} 
			else {
				p = habitaciones[comprobar.getx()][comprobar.gety()][comprobar
						.getz()].damePuerta(1);
			}
			habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p, 3);

			comprobar = c.derecha();
			if (!existe(comprobar)) {
				// Puerta cerrada.
				p = new Puerta(0);
			} 
			else {
				p = habitaciones[comprobar.getx()][comprobar.gety()][comprobar
						.getz()].damePuerta(3);
			}
			habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p, 1);

			comprobar = c.arriba();
			if (!existe(comprobar)) {
				// Puerta cerrada.
				p = new Puerta(0);
			}
			else {
				p = habitaciones[comprobar.getx()][comprobar.gety()][comprobar
						.getz()].damePuerta(0);
			}
			habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p, 2);

			comprobar = c.abajo();
			if (!existe(comprobar)) {
				// Puerta cerrada.
				p = new Puerta(0);
			}
			else {
				p = habitaciones[comprobar.getx()][comprobar.gety()][comprobar
						.getz()].damePuerta(2);
			}
			habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p, 0);

			comprobar = c.delante();
			if (!existe(comprobar)) {
				// Puerta cerrada.
				p = new Puerta(0);
			}
			else {
				p = habitaciones[comprobar.getx()][comprobar.gety()][comprobar
						.getz()].damePuerta(5);
			}
			habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p, 4);

			comprobar = c.detras();
			if (!existe(comprobar)) {
				// Puerta cerrada.
				p = new Puerta(0);
			}
			else {
				p = habitaciones[comprobar.getx()][comprobar.gety()][comprobar
						.getz()].damePuerta(4);
			}
			habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p, 5);

			// Bloqueamos puertas laterales. 	
			if (c.getx() == 0) {
				p = new Puerta(2);
				habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p, 3);
			}

			if (c.getx() == dimension - 1) {
				p = new Puerta(2);
				habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p, 1);
			}

			if (c.gety() == 0) {
				p = new Puerta(2);
				habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p, 0);
			}

			if (c.gety() == dimension - 1) {
				p = new Puerta(2);
				habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p, 2);
			}

			if (c.getz() == 0) {
				p = new Puerta(2);
				habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p, 4);
			}

			if (c.getz() == dimension - 1) {
				p = new Puerta(2);
				habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p, 5);
			}
		}
	}

	/**
	 * Método que comprueba si una habitación ha sido creada en nuestro edificio.
	 * @param c Coordenada de la habitacion a buscar.
	 * @return Si la habitación ha sido creada en nuestro edificio.
	 */
	public boolean existe(Coord3d c) {

		if ((c.getx() > dimension - 1) || (c.getx() < 0)) {
			return false;
		}
		if ((c.gety() > dimension - 1) || (c.gety() < 0)) {
			return false;
		}
		if ((c.getz() > dimension - 1) || (c.getz() < 0)) {
			return false;
		}

		return habitaciones[c.getx()][c.gety()][c.getz()] != null;
	}

	/**
	 * Método que implementa la vuelta atras en el algoritmo de backtracking usado para salir
	 * del edificio.
	 * @return Última puerta probada de la habitación a la que hemos vuelto.
	 */
	public int retrocede() {

		if (recorrido.size() > 1) {
			recorrido.remove(recorrido.size() - 1);
			Coord3d c = (Coord3d) recorrido.get(recorrido.size() - 1);
			return habitaciones[c.getx()][c.gety()][c.getz()]
					.dameUltimaPuertaProbada();
		} else {
			edificioCerrado = true;
			return 5;
		}

	}
}
