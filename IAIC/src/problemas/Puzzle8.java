/**
 * Contiene el conjunto de los problemas implementados según el paradigma
 * del espacio de estados.
 */
package problemas;

import java.util.*;
import aima.search.*;

/**
 * Clase que implementa el problema del Puzzle de 8 según
 * el paradigma del espacio de estados.
 */
public class Puzzle8 extends Problema{

	/**
	 * Coordenada 'x' del array del tablero de la casilla hueco.
	 */
	private int coorX;
	
	/**
	 * Coordenada 'y' del array del tablero de la casilla hueco.
	 */
	private int coorY;
	
	/**
	 * Tablero de 9 casillas.
	 */
	private int [][] tablero; // Tablero.
	
	/**
	 * Constructor por defecto: inicializa las componentes a valores por defecto.
	 */
	public Puzzle8(){
		
		enunciado = " Se presenta un tablero de 3x3, en el que tenemos 8 fichas\n" +
				" numéricas, de valores únicos comprendidos entre 1 y 8, y un\n" +
				" hueco. El hueco puede intercambiarse con la ficha de su derecha,\n" +
				" de su izquierda, por la que tiene encima o por la que tiene\n" +
				" debajo (intentando conservar esa prioridad de orden), siempre y\n" +
				" cuando sea posible.\n";
			
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
	 * @param x Coordenada 'x' del hueco.
	 * @param y Coordena 'y' del hueco.
	 * @param t Tablero.
	 */
	public Puzzle8(int x, int y, int [][] t){
		
		enunciado = " Se presenta un tablero de 3x3, en el que tenemos 8 fichas" +
		" numéricas, de valores únicos comprendidos entre 1 y 8, y un" +
		" hueco. El hueco puede intercambiarse con la ficha de su derecha," +
		" de su izquierda, por la que tiene encima o por la que tiene" +
		" debajo (intentando conservar esa prioridad de orden), siempre y" +
		" cuando sea posible.";
		
		coorX = x;
		coorY = y;
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
	 	int h = 0;
	 	
	 	// Si el elemento está en su casilla correcta incrementamos h.
	 	if(tablero[0][0]!=1){
	 		h++;
	 	}
	 	
	 	if(tablero[0][1]!=2){
	 		h++;
	 	}
	 	
	 	if(tablero[0][2]!=3){
	 		h++;
	 	}
	 	
	 	if(tablero[1][0]!=8){
	 		h++;
	 	}
	 	
	 	if(tablero[1][2]!=4){
	 		h++;
	 	}
	 	
	 	if(tablero[2][0]!=7){
	 		h++;
	 	}
	 	
	 	if(tablero[2][1]!=6){
	 		h++;
	 	}
	 	
	 	if(tablero[2][2]!=5){
	 		h++;
	 	}
	 		
	 	return (float)h;
	}

	/**
	 * Indica si estamos ante un estado solución.
	 * @return Valor indicando si el estado es solución o no.
	 */
	public boolean isGoal() {
		return ((tablero[0][0] == 1) &&	(tablero[0][1] == 2) && (tablero[0][2] == 3) &&	(tablero[1][0] == 8) &&
	 		(tablero[1][1] == 0) && (tablero[1][2] == 4) &&	(tablero[2][0] == 7) &&	(tablero[2][1] == 6) &&	(tablero[2][2] == 5));
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
		
		// OPERADORES:
		//  - Operador 0: Mueve el hueco a la derecha.
		//  - Operador 1: Mueve el hueco a la izquierda.
		//  - Operador 2: Mueve el hueco hacia arriba.
		//  - Operador 3: Mueve el hueco hacia abajo.
		
	 	// Operador usado.
	 	int numOperador;
		
		// Nuevas posiciones.
	 	Vector<Successor> successorVec = new Vector<Successor>();
	 	nodosExpandidos++;
	 	
	 	for (numOperador=0; numOperador<4; numOperador++){
	 		
	 		Puzzle8 nuevoEstado = new Puzzle8(coorX,coorY,tablero);
	 		boolean aplicado = false;
	 		
	 		// Operador 0: Mueve el hueco a la derecha.
	 		if (numOperador == 0){
	 			// Si el hueco se puede mover hacia la derecha.
	 			if (coorY<2){
		 			nombreOperador = "derecha";
		 			aplicado = true;
	 				// Copiamos el valor de la derecha.
		 			int valor = tablero[coorX][coorY+1];
		 			// Indicamos nuevo hueco.
		 			nuevoEstado.tablero[coorX][coorY+1] = 0;
		 			// Sustituimos el hueco por el valor.
		 			nuevoEstado.tablero[coorX][coorY] = valor;
		 			// Actualizamos la posición del hueco.
		 			nuevoEstado.coorY = coorY + 1;
		 			// Actualizamos la representación del estado.
		 			nuevoEstado.setRepEstado();
	 			}
	 		}
	 		
	 		// Operador 1: Mueve el hueco hacia la izquierda.
	 		if (numOperador == 1){
	 			// Si el hueco se puede mover hacia la izquierda.
	 			if (coorY>0){
		 			nombreOperador = "izquierda";
		 			aplicado = true;
	 				// Copiamos el valor de la izquierda del hueco.
		 			int valor = tablero[coorX][coorY-1];
		 			// Indicamos nuevo hueco.
		 			nuevoEstado.tablero[coorX][coorY-1] = 0;
		 			// Sustituimos el hueco por el valor.
		 			nuevoEstado.tablero[coorX][coorY] = valor;
		 			// Actualizamos la posición del hueco.
		 			nuevoEstado.coorY = coorY - 1;
		 			// Actualizamos la representación del estado.
		 			nuevoEstado.setRepEstado();
	 			}
	 		}
	 		
			// Operador 2: Mueve el hueco hacia arriba.
	 		if (numOperador == 2){
	 			// Si el hueco se puede mover hacia arriba.
	 			if (coorX>0){
		 			nombreOperador = "arriba";
		 			aplicado = true;
	 				// Copiamos el valor de arriba del hueco.
		 			int valor = tablero[coorX-1][coorY];
		 			// Indicamos nuevo hueco.
		 			nuevoEstado.tablero[coorX-1][coorY] = 0;
		 			// Sustituimos el hueco por el valor.
		 			nuevoEstado.tablero[coorX][coorY] = valor;
		 			// Actualizamos la posición del hueco.
		 			nuevoEstado.coorX = coorX - 1;
		 			// Actualizamos la representación del estado.
		 			nuevoEstado.setRepEstado();
	 			}
	 		}
	 		
			// Operador 3: Mueve el hueco hacia abajo.
	 		if (numOperador == 3){
	 			// Si el hueco se puede mover hacia abajo.
	 			if (coorX<2){
		 			nombreOperador = "abajo";
		 			aplicado = true;
	 				// Copiamos el valor de abajo del hueco.
		 			int valor = tablero[coorX+1][coorY];
		 			// Indicamos nuevo hueco.
		 			nuevoEstado.tablero[coorX+1][coorY] = 0;
		 			// Sustituimos el hueco por el valor.
		 			nuevoEstado.tablero[coorX][coorY] = valor;
		 			// Actualizamos la posición del hueco.
		 			nuevoEstado.coorX = coorX + 1;
		 			// Actualizamos la representación del estado.
		 			nuevoEstado.setRepEstado();
	 			}
	 		}
	 		
	 		if (aplicado){
		  	 	if(nuevoEstado.isValid()){
		  	 		successorVec.addElement(new Successor(nuevoEstado,nombreOperador,1));
				} 
	 		}
	 	}
	 	
	 	return successorVec.elements();
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
	 			int valor = tablero[i][j]; 
	 			String repValor =" ";
	 			if (valor!=0){
	 				repValor = ""+tablero[i][j];
	 			}
	 			repEstado += " " + repValor + " ";
	 			if (j==2 && i!=2){
	 				repEstado+=")"+ "\n"+ "(";
	 			}
	 			if (j==2 && i == 2){
	 				repEstado+=")";
	 			}
	 		}
	 	}
    }
    
    /**
     * Inicializa el tablero con unos valores determinados.
     */
    private void tableroUno(){
    	// Tablero.
	   	tablero[0][0] = 1;
	   	tablero[0][1] = 3;
	   	tablero[0][2] = 4;
	   	tablero[1][0] = 8;
		tablero[1][1] = 0;
		tablero[1][2] = 2;
		tablero[2][0] = 7;
		tablero[2][1] = 6;
		tablero[2][2] = 5;
		
		// Hueco en (1,1).
		coorX = 1;
		coorY = 1;
    }
    
    /**
     * Inicializa el tablero con unos valores determinados.
     */
    private void tableroDos(){
    	// Tablero.
	   	tablero[0][0] = 1;
	   	tablero[0][1] = 3;
	   	tablero[0][2] = 4;
	   	tablero[1][0] = 8;
		tablero[1][1] = 2;
		tablero[1][2] = 0;
		tablero[2][0] = 7;
		tablero[2][1] = 6;
		tablero[2][2] = 5;
		
		// Hueco en (1,2).
		coorX = 1;
		coorY = 2;
    }
    
    /**
     * Inicializa el tablero con unos valores determinados.
     */
    private void tableroTres(){
    	// Tablero.
	   	tablero[0][0] = 8;
	   	tablero[0][1] = 1;
	   	tablero[0][2] = 3;
	   	tablero[1][0] = 0;
		tablero[1][1] = 2;
		tablero[1][2] = 4;
		tablero[2][0] = 7;
		tablero[2][1] = 6;
		tablero[2][2] = 5;
		
		// Hueco en (1,0).
		coorX = 1;
		coorY = 0;
    }
    
    /**
     * Inicializa el tablero con unos valores determinados.
     */
    private void tableroCuatro(){
    	// Tablero.
	   	tablero[0][0] = 8;
	   	tablero[0][1] = 1;
	   	tablero[0][2] = 3;
	   	tablero[1][0] = 7;
		tablero[1][1] = 2;
		tablero[1][2] = 0;
		tablero[2][0] = 6;
		tablero[2][1] = 5;
		tablero[2][2] = 4;
		
		// Hueco en (1,2).
		coorX = 1;
		coorY = 2;
    }
    
    /**
     * Inicializa el tablero con unos valores determinados.
     */
    private void tableroCinco(){
    	// Tablero.
	   	tablero[0][0] = 8;
	   	tablero[0][1] = 1;
	   	tablero[0][2] = 3;
	   	tablero[1][0] = 7;
		tablero[1][1] = 2;
		tablero[1][2] = 4;
		tablero[2][0] = 6;
		tablero[2][1] = 0;
		tablero[2][2] = 5;
		
		// Hueco en (2,1).
		coorX = 2;
		coorY = 1;
    }

	public String dameTitulo(){
		return "Puzzle 8";
		
	}
}
