/**
 * Contiene el conjunto de los problemas implementados seg�n el paradigma
 * del espacio de estados.
 */
package problemas;

import java.util.Enumeration;
import java.util.Random;
import java.util.Vector;

import aima.search.Successor;

/**
 * Clase que implementa el problema de minimizar el n�mero
 * de pares de casillas pintadas del mismo color seg�n
 * el paradigma del espacio de estados.
 */
public class RojoAzul extends Problema {

	/**
	 * Constante que representa el color rojo
	 */
	private final int rojo = 1;
	
	/**
	 * Constante que representa el color azul
	 */
	private final int azul = -1;
	
	/**
	 * Tablero de 9 casillas.
	 */
	private int [][] tablero; // Tablero.
	
	/**
	 * Constructor por defecto: inicializa las componentes a valores por defecto.
	 */
	public RojoAzul(){
		enunciado = "Dada una rejilla de 3*3 casillas coloreadas\n" +
					"en rojo (R) o azul (A), se pretende alcanzar\n"+
					"un estado de la rejilla en el que el n�mero de\n"+
					"pares de casillas adyacentes (en vertical y en\n"+
					"horizontal, pero no en diagonal) coloreadas del\n"+
					"mismo color sea m�nimo.\n"+
					"Se dispone de 9 operadores que permiten cambiar\n"+
					"el color de cada una de las casillas" +
					"(de rojo a azul o viceversa).";
		// Escogemos un tablero base.
		Random generador = new Random();
		int numTablero	= generador.nextInt(5);
	    tablero = new int [3][3];
		numTablero = 0; // De momento para ver que funciona
		switch (numTablero){
			case 0: 
				tableroUno();
				break;
			case 1:
				tableroDos();
				break;
			case 2:
				tableroTres();
				break;
			case 3:
				tableroCuatro();
				break;
			case 4:
				tableroCinco();
				break;
		}

		// Inicializamos el nombre del operador.
		nombreOperador = "";		
		setRepEstado();
	}

	/**
	 * Constructor principal, inicializa los atributos a los valores indicados.
	 * @param t Tablero.
	 */
	public RojoAzul(int[][] t) {
		enunciado = "Dada una rejilla de 3*3 casillas coloreadas\n" +
					"en rojo (R) o azul (A), se pretende alcanzar\n"+
					"un estado de la rejilla en el que el n�mero de\n"+
					"pares de casillas adyacentes (en vertical y en\n"+
					"horizontal, pero no en diagonal) coloreadas del\n"+
					"mismo color sea m�nimo.\n"+
					"Se dispone de 9 operadores que permiten cambiar\n"+
					"el color de cada una de las casillas" +
					"(de rojo a azul o viceversa).";
		tablero = new int [3][3];		
		for (int i = 0; i<3; i++){
			for (int j = 0; j<3; j++){
				tablero[i][j] = t[i][j];
			}
		}
		
		// Inicializamos el nombre del operador.
		nombreOperador = "";
		setRepEstado();
	}

	/**
	 * Genera el valor heur�stico del estado.
	 * @return Valor de la he�ristica. 
	 */
	public float h() {
		// heur�stica = n� de casillas contiguas de color rojo 
		//            + n� de casillas contiguas de color azul.
		// Que es lo mismo que el n� de casillas contiguas del mismo color.
		float numCasillas = 0;
		for (int i = 0; i<3; i++){
			for (int j = 0; j<2; j++){
				if( tablero[i][j] == tablero [i][j+1]){
					numCasillas++;
				}
				if(tablero [i][j] == tablero [i+1][j]){
					numCasillas++;
				}
			}
		}
		return numCasillas;
	}

	/**
	 * Indica si estamos ante un estado soluci�n.
	 * @return Valor indicando si el estado es soluci�n o no.
	 */
	public boolean isGoal() {
		// Es soluci�n si no hay casillas contiguas de igualar color.
		return  (h() == 0);
	}

	/**
	 * Comprueba si el estado del problema es v�lido.
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
		// Tenemos 9 operadores
		// Operador 0 : Cambiar color casilla [0][0].
		// Operador 1 : Cambiar color casilla [0][1].
		// Operador 2 : Cambiar color casilla [0][2].
		// Operador 3 : Cambiar color casilla [1][0].
		// Operador 4 : Cambiar color casilla [1][1].
		// Operador 5 : Cambiar color casilla [1][2].
		// Operador 6 : Cambiar color casilla [2][0].
		// Operador 7 : Cambiar color casilla [2][1].
		// Operador 8 : Cambiar color casilla [2][2].
		
		// Operador usado.
	 	int numOperador;
	 	
	 	// Incrementamos el n�mero de nodos expandidos.
	 	nodosExpandidos++;
	 	
	 	Vector successor = new Vector();
	 	
	 	for (numOperador = 0; numOperador<9;numOperador++){
	 		RojoAzul nuevoEstado = new RojoAzul(tablero);
	 		
	 		// Operador 0 : Cambiar color casilla [0][0].
	 		if(numOperador == 0){
	 			//Si era de color rojo.
	 			if(nuevoEstado.tablero[0][0] == rojo){
	 				//ahora es azul.
	 				nuevoEstado.tablero [0][0] = azul;
	 			}
	 			else{
	 				//si no ahora es rojo.
	 				nuevoEstado.tablero[0][0] = rojo;
	 			}
	 		}
			// Operador 1 : Cambiar color casilla [0][1].
	 		if(numOperador == 1){
	 			//Si era de color rojo.
	 			if(nuevoEstado.tablero[0][1] == rojo){
	 				//ahora es azul.
	 				nuevoEstado.tablero [0][1] = azul;
	 			}
	 			else{
	 				//si no ahora es rojo.
	 				nuevoEstado.tablero[0][1] = rojo;
	 			}
	 		}
			// Operador 2 : Cambiar color casilla [0][2].
	 		if(numOperador == 2){
	 			//Si era de color rojo.
	 			if(nuevoEstado.tablero[0][2] == rojo){
	 				//ahora es azul.
	 				nuevoEstado.tablero [0][2] = azul;
	 			}
	 			else{
	 				//si no ahora es rojo.
	 				nuevoEstado.tablero[0][2] = rojo;
	 			}
	 		}
	 		// Operador 3 : Cambiar color casilla [1][0].
	 		if(numOperador == 3){
	 			//Si era de color rojo.
	 			if(nuevoEstado.tablero[1][0] == rojo){
	 				//ahora es azul.
	 				nuevoEstado.tablero [1][0] = azul;
	 			}
	 			else{
	 				//si no ahora es rojo.
	 				nuevoEstado.tablero[1][0] = rojo;
	 			}
	 		}
	 		// Operador 4 : Cambiar color casilla [1][1].
	 		if(numOperador == 4){
	 			//Si era de color rojo.
	 			if(nuevoEstado.tablero[1][1] == rojo){
	 				//ahora es azul.
	 				nuevoEstado.tablero [1][1] = azul;
	 			}
	 			else{
	 				//si no ahora es rojo.
	 				nuevoEstado.tablero[1][1] = rojo;
	 			}
	 		}
	 		// Operador 5 : Cambiar color casilla [1][2].
	 		if(numOperador == 5){
	 			//Si era de color rojo.
	 			if(nuevoEstado.tablero[1][2] == rojo){
	 				//ahora es azul.
	 				nuevoEstado.tablero [1][2] = azul;
	 			}
	 			else{
	 				//si no ahora es rojo.
	 				nuevoEstado.tablero[1][2] = rojo;
	 			}
	 		}
	 		// Operador 6 : Cambiar color casilla [2][0].
	 		if(numOperador == 6){
	 			//Si era de color rojo.
	 			if(nuevoEstado.tablero[2][0] == rojo){
	 				//ahora es azul.
	 				nuevoEstado.tablero [2][0] = azul;
	 			}
	 			else{
	 				//si no ahora es rojo.
	 				nuevoEstado.tablero[2][0] = rojo;
	 			}
	 		}
	 		// Operador 7 : Cambiar color casilla [2][1].
	 		if(numOperador == 7){
	 			//Si era de color rojo.
	 			if(nuevoEstado.tablero[2][1] == rojo){
	 				//ahora es azul.
	 				nuevoEstado.tablero [2][1] = azul;
	 			}
	 			else{
	 				//si no ahora es rojo.
	 				nuevoEstado.tablero[2][1] = rojo;
	 			}
	 		}
	 		// Operador 8 : Cambiar color casilla [2][2].
	 		if(numOperador == 8){
	 			//Si era de color rojo.
	 			if(nuevoEstado.tablero[2][2] == rojo){
	 				//ahora es azul.
	 				nuevoEstado.tablero [2][2] = azul;
	 			}
	 			else{
	 				//si no ahora es rojo.
	 				nuevoEstado.tablero[2][2] = rojo;
	 			}
	 		}
	 		if(nuevoEstado.isValid()){
	  	 		successor.addElement(new Successor(nuevoEstado,nombreOperador,1));
			} 
 		} 	
	 	return successor.elements();
	}

	 /**
     * Actualizador de la representaci�n de estado en funci�n del
     * valor actual de los atributos del objeto.
     */
    private void setRepEstado(){
    	
		// Hacemos la representaci�n del estado.
		repEstado = "\n(";
	 	for (int i=0; i<3; i++){
	 		for (int j=0; j<3; j++){
	 			int valor = tablero[i][j]; 
	 			String repValor =" ";
	 			if (valor!=0){
	 				if(tablero[i][j] == rojo){	 					
	 					repValor = "R";
	 				}
	 				else{
	 					repValor = "A";
	 				}
	 			}
	 			repEstado += " " + repValor + " ";
	 			if (j==2 && i!=2){
	 				repEstado+=")"+ "\n"+ "(";
	 			}
	 			if (j==2 && i == 2){
	 				repEstado+=")"+ "\n";
	 			}
	 		}
	 	}
    }
    /**
     * Inicializa el tablero con unos valores determinados.
     */
    private void tableroUno(){
    	// Tablero.
	   	tablero[0][0] = rojo;
	   	tablero[0][1] = rojo;
	   	tablero[0][2] = rojo;
	   	tablero[1][0] = azul;
		tablero[1][1] = rojo;
		tablero[1][2] = rojo;
		tablero[2][0] = azul;
		tablero[2][1] = azul;
		tablero[2][2] = azul;
    }
    
    /**
     * Inicializa el tablero con unos valores determinados.
     */
    private void tableroDos(){
    	// Tablero.
	   	tablero[0][0] = 8;
	   	tablero[0][1] = 7;
	   	tablero[0][2] = 5;
	   	tablero[1][0] = 3;
		tablero[1][1] = 0;
		tablero[1][2] = 2;
		tablero[2][0] = 4;
		tablero[2][1] = 6;
		tablero[2][2] = 1;
    }
    
    /**
     * Inicializa el tablero con unos valores determinados.
     */
    private void tableroTres(){
    	// Tablero.
	   	tablero[0][0] = 5;
	   	tablero[0][1] = 8;
	   	tablero[0][2] = 2;
	   	tablero[1][0] = 3;
		tablero[1][1] = 4;
		tablero[1][2] = 1;
		tablero[2][0] = 6;
		tablero[2][1] = 7;
		tablero[2][2] = 0;
    }
    
    /**
     * Inicializa el tablero con unos valores determinados.
     */
    private void tableroCuatro(){
    	// Tablero.
	   	tablero[0][0] = 8;
	   	tablero[0][1] = 5;
	   	tablero[0][2] = 4;
	   	tablero[1][0] = 2;
		tablero[1][1] = 0;
		tablero[1][2] = 6;
		tablero[2][0] = 7;
		tablero[2][1] = 1;
		tablero[2][2] = 3;
    }
    
    /**
     * Inicializa el tablero con unos valores determinados.
     */
    private void tableroCinco(){
    	// Tablero.
	   	tablero[0][0] = 0;
	   	tablero[0][1] = 6;
	   	tablero[0][2] = 2;
	   	tablero[1][0] = 7;
		tablero[1][1] = 4;
		tablero[1][2] = 1;
		tablero[2][0] = 8;
		tablero[2][1] = 5;
		tablero[2][2] = 3;
    }
}
