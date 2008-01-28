package problemas;

public class Pruebas {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Problema juego = new RojoAzul();
		for (int i=6; i<7; i++){
			System.out.println("Estrategia: "+ juego.estrategiaAplicada(i));
			juego.setNodosExpandidos(0);
			juego.resolver(i);			
			System.out.println("Número de nodos expandidos : "+ juego.getNodosExpandidos()+"\n");
		}		
	}

}
