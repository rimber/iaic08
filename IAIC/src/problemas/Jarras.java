package problemas;

import java.util.Enumeration;

/** 
 * Clase que implementa el problema de las jarras.
 */

public class Jarras extends Problema {
	
	/**
	 * Constante que indica el máximo número de
	 * nodos que se pueden expandir.
	 */	
	private static final int maxNodos = 1000;
	
	/**
	 * Contador de nodos expandidos.
	 */
	private static int nodosExpandidos = 0;
	
	/**
	 * Representa la jarra de 4 litros.
	 */	
	private int jarra4;
	
	/**
	 * Representa la jarra de 3 litros.
	 */
	private int jarra3;
	
	/**
	 * Constructor de la clase Jarras que crea
	 * el estado inicial (0,0) que representa
	 * a las dos jarras vacías.
	 */
	public Jarras(){
		jarra4 = 0;
		jarra3 = 0;
	}
	
	/**
	 * Constructor de la clase Jarras que crea
	 * el estado inicial (i,j)
	 * @param i litros en la jarra de 4 litros
	 * @param j litros en la jarra de 3 litros.
	 */	
	public Jarras(int i,int j){
		if((i<0)||(i>4)){
			String mensaje = new String();
			mensaje = "No se puede crear el estado "+ String.valueOf(i)+" litros\n";
			mensaje = mensaje + "En la jarra de 4 litros. Se aplica el estado 0 litros.";
			System.out.println(mensaje);
			jarra4 = 0;
		}
		else{
			jarra4 = i;
		}
		if((j<0)||(j>3)){
			String mensaje = new String();
			mensaje = "No se puede crear el estado "+ String.valueOf(i)+" litros\n";
			mensaje = mensaje + "En la jarra de 3 litros. Se aplica el estado 0 litros.";
			System.out.println(mensaje);
			jarra3 = 0;
		}
		else{
			jarra3 = j;
		}
	}

	/**
	 * Método que calcula el valor de la heurística.
	 * @return el valor de la heurística.
	 */
	public float h(){
		float heuristica = 0;
		heuristica = (2 - jarra4);
		if(heuristica>0){
			return heuristica;
		}
		else{
			return (jarra4 - 2);
		}
	}

	/**
	 * Método que dice si un estado es solución.
	 * @return true si es solución false en otro caso.
	 */
	public boolean isGoal(){
		//¿Tenemos 2 litros en la jarra de 4 litros?
		return jarra4 == 2;
	}
	
	/**
	 * Método que dice si un estado es válido.
	 * @return true si es válido false en otro caso.
	 */	
	protected boolean isValid(){
		return ((jarra4>=0)&&(jarra4<=4)&&(jarra3>=0)&&(jarra3<=3)
				&& (nodosExpandidos <= maxNodos)) ;
	}

	/**
	 * Método que calcula los estados alcanzables
	 * desde el estado actual.
	 * @return Conjunto de estados nuevos alcanzables.
	 */
	public Enumeration successors(){
		// TODO Auto-generated method stub		
		return null;
	}

	/**
	 * @return los nodos expandidos.
	 */
	public static int getNodosExpandidos() {
		return nodosExpandidos;
	}

	/**
	 * @param n que es el número de nodos expandidos.
	 */
	public static void setNodosExpandidos(int n) {
		nodosExpandidos = n;
	}

	protected boolean resolverA() {
		// TODO Auto-generated method stub
		return false;
	}

	protected boolean resolverCosteUni() {
		// TODO Auto-generated method stub
		return false;
	}

	protected boolean resolverEscalada() {
		// TODO Auto-generated method stub
		return false;
	}

	protected boolean resolverPrimAnchura() {
		// TODO Auto-generated method stub
		return true;
	}

	protected boolean resolverProfIt() {
		// TODO Auto-generated method stub
		return false;
	}

	protected boolean resolverProfundidad() {
		// TODO Auto-generated method stub
		return false;
	}

}
