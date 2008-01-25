package problemas;

public class Pruebas {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Problema juego = new Mono();
		for (int i = 5; i<6; i++){
			System.out.println("Estrategia: "+ juego.estrategiaAplicada(i));
			boolean resuelto = juego.resolver(i);
		}
		
	}

}
