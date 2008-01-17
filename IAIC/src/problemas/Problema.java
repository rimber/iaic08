package problemas;

import aima.search.*;
import java.util.*;

public abstract class Problema implements State,Heuristic{

	public String enunciado;
	
	public abstract boolean isGoal();
	
	protected abstract boolean isValid();

	public abstract Enumeration successors();

	public abstract float h();
	
}
