package problemas;

import java.util.Enumeration;

public class Robot extends Problema {
	
	public Robot(){
	enunciado = "Sea un micro-mundo formado por 3 habitaciones y un robot aspirador." +
			" Hay una habitación a la izquierda, otra en el centro y otra a la derecha, " +
			"cuyas alfombras pueden	estar sucias o limpias. El robot puede estar en" +
			" cualquiera de las habitaciones y puede ejecutar dos tipos de operaciones: " +
			"aspirar o moverse. La operación de aspirar requiere que la habitación en la" +
			" que se encuentra el robot esté sucia y su resultado es que dicha habitación" +
			" pasa a estar limpia. La operación mover tiene dos opciones: mover hacia la " +
			"izquierda, que requiere que haya alguna habitación a la izquierda de aquella" +
			" en la que se encuentra el robot, y mover hacia la derecha que requiere la" +
			" existencia de alguna habitación situada a la derecha. En cualquier caso, los" +
			" movimientos serán elementales, es	decir, sólo permitirán el paso a la habitación" +
			" contigua a la actual.";
	}

	@Override
	public float h() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isGoal() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Enumeration successors() {
		// TODO Auto-generated method stub
		return null;
	}

}
