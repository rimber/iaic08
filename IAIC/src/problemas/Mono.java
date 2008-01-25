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

}
