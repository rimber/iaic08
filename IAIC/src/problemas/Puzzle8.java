/**
 * Contiene el conjunto de los problemas implementados según el paradigma
 * del espacio de estados.
 */
package problemas;

import java.util.Enumeration;
import java.util.Vector;
import aima.search.*;;

/**
 * Clase que implementa el problema del Puzzle de 8 según
 * el paradigma del espacio de estados.
 */
public class Puzzle8 extends Problema{

	/**
	 * Coordenada x de la casilla hueco.
	 */
	private int coorX;
	
	/**
	 * Coordenada y de la casilla hueco.
	 */
	private int coorY;
	
	/**
	 * Tablero de 9 casillas.
	 */
	private int [][] tablero; // Tablero.
	
	/**
	 * 
	 */
	public Puzzle8(){
		// Escogemos un tablero base.
	    tablero = new int [3][3];
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
		
		// Inicializamos el nombre del operador.
		nombreOperador = "";
		
		setRepEstado();
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param t
	 */
	public Puzzle8(int x, int y, int [][] t){
		
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
	 * 
	 */
	public float h() {
	 	int h = 0;
	 	
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
	 * 
	 */
	public boolean isGoal() {
		return ((tablero[0][0] == 1) &&	(tablero[0][1] == 2) && (tablero[0][2] == 3) &&	(tablero[1][0] == 8) &&
	 		(tablero[1][1] == 0) && (tablero[1][2] == 4) &&	(tablero[2][0] == 7) &&	(tablero[2][1] == 6) &&	(tablero[2][2] == 5));
	}

	/**
	 * 
	 */
	protected boolean isValid() {
		return true;
	}

	/**
	 * 
	 */
	public Enumeration successors() {
		
		// Tenemos  operadores:
		// Operador 0: Mueve el hueco a la derecha.
		// Operador 1: Mueve el hueco a la izquierda.
		// Operador 2: Mueve el hueco hacia arriba.
		// Operador 3: Mueve el hueco hacia abajo.
		
	 	// Operador usado.
	 	int numOperador;
		
		// Nuevas posiciones.
	 	int nCoorX = coorX;
	 	int nCoorY = coorY;
	 	Vector successorVec = new Vector<Puzzle8>();

	 	for (numOperador=0; numOperador<4; numOperador++){
	 		
	 		Puzzle8 nuevoEstado = new Puzzle8(coorX,coorY,tablero);
	 		boolean aplicado = false;
	 		
	 		// Operador 0: Mueve el hueco a la derecha.
	 		if (numOperador == 0){
	 			// Si el hueco se puede mover hacia la derecha.
	 			if (coorY<2){
		 			nombreOperador = "Derecha.";
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
		 			nombreOperador = "Izquierda.";
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
		 			nombreOperador = "Arriba.";
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
		 			nombreOperador = "Abajo.";
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

	protected boolean resolverA() {
		boolean resuelto = listPath((new AStarSearch(this)).search());
		return resuelto;
	}

	protected boolean resolverCosteUni() {
		boolean resuelto = listPath((new UniformCostSearch(this)).search());
		return resuelto;
	}

	protected boolean resolverEscalada() {
		boolean resuelto = listPath((new GreedySearch(this)).search());
		return resuelto;
	}

	protected boolean resolverPrimAnchura() {
		boolean resuelto = listPath((new BreadthFirstSearch(this)).search());
		return resuelto;
	}

	protected boolean resolverProfIt() {
		boolean resuelto=listPath((new IteratedDeepeningSearch(this)).search());
		return resuelto;
	}

	protected boolean resolverProfundidad() {
		boolean resuelto=listPath((new DepthBoundedSearch(this,7)).search());
		return resuelto;
	}
	
	/**
	 * 
	 */
    public String toString(){
    	return repEstado;
    }
    
    /**
     * 
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
	 				repEstado+=")"+ "\n";
	 			}
	 		}
	 	}
    }

}
