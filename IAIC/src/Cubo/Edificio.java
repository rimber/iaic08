/**
 * 
 */
package Cubo;

import java.util.*;

import salidaPantalla.VPrincipal;

/**
 *
 */
public class Edificio {

	/**
	 * 
	 */
	private Habitacion habitaciones[][][];

	/**
	 * 
	 */
	private ArrayList<Coord3d> recorrido;

	/**
	 * 
	 */
	private int dimension;

	private VPrincipal enlace;

	/**
	 * 
	 */
	private boolean edificioCerrado;

	/**
	 * 
	 * @param n
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
	 * 
	 */

	public String muestraTituloSiguienteProblema(int direccion) {
		String devolver = "";
		int posicion = recorrido.size();
		Coord3d c = (Coord3d) recorrido.get(posicion - 1);
		Habitacion h = habitaciones[c.getx()][c.gety()][c.getz()];
		devolver = h.dameTitulo(direccion);		
		return devolver;

	}

	public String muestraDescripcionSiguienteProblema(int direccion) {
		String devolver = "";
		int posicion = recorrido.size();
		Coord3d c = (Coord3d) recorrido.get(posicion - 1);
		Habitacion h = habitaciones[c.getx()][c.gety()][c.getz()];
		devolver = h.dameDescripcion(direccion);
		return devolver;
	}

	public String muestraRecorrido() {

		String devolver = "Recorrido: \n ";

		for (int i = 0; i < recorrido.size(); i++) {
			Coord3d c = (Coord3d) recorrido.get(i);
			devolver += ("  (" + c.getx() + "," + c.gety() + "," + c.getz() + ")");
			if ((i % 8) == 0 && (i > 0))
				devolver += "\n";
		}
		System.out.println(devolver);
		return devolver;
	}

	/**
	 * 
	 * @return
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
	 * 
	 * @return
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
	 * 
	 * @return
	 */
	public boolean cerrado() {
		/* Devuelve si has llegado a un conjunto cerrado
		 * de puertas, si el camino no tiene solución.
		 */
		return edificioCerrado;
	}

	/**
	 * 
	 * @param direccion
	 * @return
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
		}
		// Si no has podido abrir la puerta o has llegado a una de las otras.
		return false;
	}

	/**
	 * 
	 * @param c
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
	 * 
	 * @param c
	 * @return
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
	 * 
	 * @return
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
