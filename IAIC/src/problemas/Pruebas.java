package problemas;

public class Pruebas {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Problema juego = new RojoAzul();
		for (int i=5; i<6; i++){
			System.out.println("Estrategia: "+ juego.estrategiaAplicada(i));
			juego.resolver(i);			
			System.out.println("Número de nodos expandidos : "+ juego.getNodosExpandidos()+"\n");
		}		
	}

}
