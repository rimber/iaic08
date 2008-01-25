/**
 * 
 */
package problemas;

import java.util.Enumeration;
import aima.search.AStarSearch;
import aima.search.BreadthFirstSearch;
import aima.search.DepthBoundedSearch;
import aima.search.GreedySearch;
import aima.search.IteratedDeepeningSearch;
import aima.search.UniformCostSearch;

/**
 * 
 *
 */
public class Puzzle8 extends Problema{

	/**
	 * 
	 */
	private int coorX; // Coordenada x (casilla libre).
	
	/**
	 * 
	 */
	private int coorY; // Coordenada y (casilla libre).
	
	/**
	 * 
	 */
	private int [][] tablero; // Tablero.
	
	/**
	 * 
	 */
	public Puzzle8(){
		// Escogemos un tablero aleatorio.
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
		
		for(int i = 0; i<3; i++){
			for(int j = 0; j<3; j++){
				tablero[i][j] = t[i][j];
			}
		}
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

	@Override
	public Enumeration successors() {
		// TODO Auto-generated method stub
		return null;
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
	
    public String toString(){
    	return repEstado;
    }

}
