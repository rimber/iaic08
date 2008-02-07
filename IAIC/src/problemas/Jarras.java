/**
 * Contiene el conjunto de los problemas implementados según el paradigma
 * del espacio de estados.
 */
package problemas;

import java.util.Enumeration;
import java.util.Vector;

import aima.search.Successor;

/** 
 * Clase que implementa el problema de las jarras según
 * el paradigma del espacio de estados.
 */

public class Jarras extends Problema {
	
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
		enunciado = " Se tienen dos garrafas vacías con capacidades de 3 y 4 litros\n " +
					"respectivamente pero sin ninguna marca de medida parcial. Las\n " +
					"garrafas pueden vaciarse o llenarse de agua, así como verter\n " +
					"el contenido de una a otra. El objetivo consiste en tener\n " +
					"exactamente 2 litros de agua en la garrafa de 4 litros.";
		jarra4 = 0;
		jarra3 = 0;
		repEstado = "("+jarra4+","+jarra3+")";
		nombreOperador = "";
	}
	
	/**
	 * Constructor de la clase Jarras que crea
	 * el estado inicial (i,j)
	 * @param i litros en la jarra de 4 litros
	 * @param j litros en la jarra de 3 litros.
	 */	
	public Jarras(int i,int j){
		enunciado = " Se tienen dos garrafas vacías con capacidades de 3 y 4 litros\n " +
					"respectivamente pero sin ninguna marca de medida parcial. Las\n " +
					"garrafas pueden vaciarse o llenarse de agua, así como verter\n " +
					"el contenido de una a otra. El objetivo consiste en tener\n " +
					"exactamente 2 litros de agua en la garrafa de 4 litros.";
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
		repEstado = "("+jarra4+","+jarra3+")";
		nombreOperador = "";
	}

	/**
	 * Método que calcula el valor de la heurística.
	 * @return el valor de la heurística.
	 */
	public float h(){
		//Heuristica: Mejor cuanto más próximo a tener 2 litros en la jarra de 4 Litros.
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
		//¿Tenemos 2 litros en la jarra de 4 litros?.
		return jarra4 == 2;
	}
	
	/**
	 * Método que dice si un estado es válido.
	 * @return true si es válido false en otro caso.
	 */	
	protected boolean isValid(){
		return ((jarra4>=0)&&(jarra4<=4)&&(jarra3>=0)&&(jarra3<=3)
				&& (nodosExpandidos < maxNodos)) ;
	}

	/**
	 * Genera todos los posibles estados sucesores del estado actual.
	 * @return Conjunto de estados sucesores.	 
	 */
	public Enumeration<Successor> successors(){
		
		// Tenemos 6 operadores:
		// Operador 0: Llenar jarra de 4 Litros.
		// Operador 1: Llenar jarra de 3 Litros.
		// Operador 2: Vaciar jarra de 4 Litros.
		// Operador 3: Llenar jarra de 3 Litros.
		// Operador 4: Verter jarra de 4 en la de 3 Litros.
		// Operador 5: Verter jarra de 3 en la de 4 Litros.
		
	 	// Operador usado.
	 	int numOperador;
	 	
		// Nuevas posiciones.
	 	int nuevaJarra3 = 0;
	 	int nuevaJarra4 = 0;
	 	
		// Incrementamos el número de nodos expandidos.
	 	nodosExpandidos++;
	 	
	 	Vector<Successor> successor = new Vector<Successor>();
	 	for(numOperador = 0; numOperador <6; numOperador++){
	 		boolean operadorAplicado = false;
	 		//Llenar garrafa de 4L.
	 		if(numOperador == 0 && jarra4<4 ){
	 			operadorAplicado = true;
	 			nuevaJarra4 = 4;
	 			nuevaJarra3 = jarra3;
	 			nombreOperador ="llena4";
	 		}
	 		//Llenar garrafa de 3L.
	 		if(numOperador == 1 && jarra3<3 ){
	 			operadorAplicado = true;
	 			nuevaJarra3 = 3;
	 			nuevaJarra4 = jarra4;
	 			nombreOperador ="llena3";
	 		}
	 		//Vaciar garrafa de 4L.
	 		if(numOperador == 2 && jarra4>0 ){
	 			operadorAplicado = true;
	 			nuevaJarra4 = 0;
	 			nuevaJarra3 = jarra3;
	 			nombreOperador ="vacia4";
	 		}
	 		//Vaciar garrafa de 3L.
	 		if(numOperador == 3 && jarra3>0 ){
	 			operadorAplicado = true;
	 			nuevaJarra4 = jarra4;
	 			nuevaJarra3 = 0;
	 			nombreOperador ="vacia3";
	 		}
	 		//Verter garrafa de 4L sobre garrafa de 3L.
	 		if(numOperador == 4 && jarra4>0 && jarra3<3 ){
	 			operadorAplicado = true;
	 			if(jarra3+jarra4 <= 3){
	 				nuevaJarra3=jarra3+jarra4;
	 			}
	 			else{
	 				nuevaJarra3 = 3;
	 			}
	 			nuevaJarra4 = jarra4-(nuevaJarra3-jarra3);
	 			nombreOperador ="vierte4";
	 		}
	 		//Verter garrafa de 3L sobre garrafa de 4L.
	 		if(numOperador == 5 && jarra3>0 && jarra4<4 ){
	 			operadorAplicado = true;
	 			if(jarra3+jarra4 <= 4){
	 				nuevaJarra4=jarra3+jarra4;
	 			}
	 			else{
	 				nuevaJarra4 = 4;
	 			}
	 			nuevaJarra3 = jarra3 -(nuevaJarra4-jarra4);
	 			nombreOperador ="vierte3";
	 		}
	 		
	 		if (operadorAplicado){
		 		// Creamos el nuevo estado.
		 		Jarras nuevoEstado = new Jarras(nuevaJarra4,nuevaJarra3);
		 		
		 		// Comprobamos si el nuevo estado es válido.
		 		if(nuevoEstado.isValid()){
		 		// Añadimos el estado como sucesor.
		 			successor.addElement(new Successor(nuevoEstado,nombreOperador, 1 ));
		 		}
	 		}
	 	}
	 	return successor.elements();
	}
	public String dameTitulo(){
		return "Jarras";
		
	}
}
