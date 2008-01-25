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
 */
public class Mono extends Problema{

	/**
	 * 
	 */
	public Mono(){
		
	}
	
	/**
	 * 
	 */
	public float h() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 
	 */
	public boolean isGoal() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 
	 */
	protected boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 
	 */
	public Enumeration successors() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	protected boolean resolverA() {
		boolean resuelto = listPath((new AStarSearch(this)).search());
		return resuelto;
	}

	/**
	 * 
	 */
	protected boolean resolverCosteUni() {
		boolean resuelto = listPath((new UniformCostSearch(this)).search());
		return resuelto;
	}

	/**
	 * 
	 */
	protected boolean resolverEscalada() {
		boolean resuelto = listPath((new GreedySearch(this)).search());
		return resuelto;
	}

	/**
	 * 
	 */
	protected boolean resolverPrimAnchura() {
		boolean resuelto = listPath((new BreadthFirstSearch(this)).search());
		return resuelto;
	}

	/**
	 * 
	 */
	protected boolean resolverProfIt() {
		boolean resuelto=listPath((new IteratedDeepeningSearch(this)).search());
		return resuelto;
	}

	/**
	 * 
	 */
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
    
}
