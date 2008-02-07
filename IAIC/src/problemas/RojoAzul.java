/**
 * Contiene el conjunto de los problemas implementados según el paradigma
 * del espacio de estados.
 */
package problemas;

import java.util.Enumeration;
import java.util.Random;
import java.util.Vector;

import aima.search.Successor;

/**
 * Clase que implementa el problema de minimizar el número
 * de pares de casillas pintadas del mismo color según
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
		enunciado = " Dada una rejilla de 3*3 casillas coloreadas\n" +
					" en rojo (R) o azul (A), se pretende alcanzar\n"+
					" un estado de la rejilla en el que el número de\n"+
					" pares de casillas adyacentes (en vertical y en\n"+
					" horizontal, pero no en diagonal) coloreadas del\n"+
					" mismo color sea mínimo.\n"+
					" Se dispone de 9 operadores que permiten cambiar\n"+
					" el color de cada una de las casillas" +
					" (de rojo a azul o viceversa).";
		// Escogemos un tablero base.
		Random generador = new Random();
		int numTablero	= generador.nextInt(5);
	    tablero = new int [3][3];
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
		enunciado = " Dada una rejilla de 3*3 casillas coloreadas\n" +
					" en rojo (R) o azul (A), se pretende alcanzar\n"+
					" un estado de la rejilla en el que el número de\n"+
					" pares de casillas adyacentes (en vertical y en\n"+
					" horizontal, pero no en diagonal) coloreadas del\n"+
					" mismo color sea mínimo.\n"+
					" Se dispone de 9 operadores que permiten cambiar\n"+
					" el color de cada una de las casillas" +
					" (de rojo a azul o viceversa).";
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
	 * Genera el valor heurístico del estado.
	 * @return Valor de la heúristica. 
	 */
	public float h() {
		// heurística = nº de casillas contiguas de color rojo 
		//            + nº de casillas contiguas de color azul.
		// Que es lo mismo que el nº de casillas contiguas del mismo color.
		float numCasillas = 0;
		for (int i = 0; i<3; i++){
			for (int j = 0; j<2; j++){
				if( tablero[i][j] == tablero [i][j+1]){
					numCasillas++;
				}
			}
		}
		for (int i = 0; i<2; i++){
			for (int j = 0; j<3; j++){
				if(tablero [i][j] == tablero [i+1][j]){
					numCasillas++;
				}
			}
		}
		return numCasillas;
	}

	/**
	 * Indica si estamos ante un estado solución.
	 * @return Valor indicando si el estado es solución o no.
	 */
	public boolean isGoal() {
		// Es solución si no hay casillas contiguas de igualar color.
		return  (h() == 0);
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
	public Enumeration<Successor> successors() {
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
	 	
	 	// Incrementamos el número de nodos expandidos.
	 	nodosExpandidos++;
	 	
	 	Vector<Successor> successor = new Vector<Successor>();
	 	
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
	 			nombreOperador = "Cambia de color la casilla [0][0].";
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
	 			nombreOperador = "Cambia de color la casilla [0][1].";
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
	 			nombreOperador = "Cambia de color la casilla [0][2].";
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
	 			nombreOperador = "Cambia de color la casilla [1][0].";
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
	 			nombreOperador = "Cambia de color la casilla [1][1].";
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
	 			nombreOperador = "Cambia de color la casilla [1][2].";
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
	 			nombreOperador = "Cambia de color la casilla [2][0].";
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
	 			nombreOperador = "Cambia de color la casilla [2][1].";
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
	 			nombreOperador = "Cambia de color la casilla [2][2].";
	 		}
	 		// Actualizamos la representación del estado.
 			nuevoEstado.setRepEstado();
	 		if(nuevoEstado.isValid()){
	  	 		successor.addElement(new Successor(nuevoEstado,nombreOperador,1));
			} 
 		} 	
	 	return successor.elements();
	}

	 /**
     * Actualizador de la representación de estado en función del
     * valor actual de los atributos del objeto.
     */
    private void setRepEstado(){
    	
		// Hacemos la representación del estado.
		repEstado = "\n(";
	 	for (int i=0; i<3; i++){
	 		for (int j=0; j<3; j++){
	 			int valor = this.tablero[i][j]; 
	 			String repValor =" ";
	 			if (valor!=0){
	 				if(this.tablero[i][j] == rojo){	 					
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
    	tablero[0][0] = rojo;
	   	tablero[0][1] = azul;
	   	tablero[0][2] = azul;
	   	tablero[1][0] = azul;
		tablero[1][1] = rojo;
		tablero[1][2] = rojo;
		tablero[2][0] = azul;
		tablero[2][1] = azul;
		tablero[2][2] = rojo;
    }
    
    /**
     * Inicializa el tablero con unos valores determinados.
     */
    private void tableroTres(){
    	// Tablero.
    	tablero[0][0] = rojo;
	   	tablero[0][1] = rojo;
	   	tablero[0][2] = rojo;
	   	tablero[1][0] = rojo;
		tablero[1][1] = rojo;
		tablero[1][2] = rojo;
		tablero[2][0] = azul;
		tablero[2][1] = rojo;
		tablero[2][2] = azul;
    }
    
    /**
     * Inicializa el tablero con unos valores determinados.
     */
    private void tableroCuatro(){
    	// Tablero.
    	tablero[0][0] = azul;
	   	tablero[0][1] = azul;
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
    private void tableroCinco(){
    	// Tablero.
    	tablero[0][0] = rojo;
	   	tablero[0][1] = rojo;
	   	tablero[0][2] = azul;
	   	tablero[1][0] = azul;
		tablero[1][1] = rojo;
		tablero[1][2] = azul;
		tablero[2][0] = azul;
		tablero[2][1] = azul;
		tablero[2][2] = azul;
    }
    
	public String dameTitulo(){
		return "RojoAzul";
		
	}
}
