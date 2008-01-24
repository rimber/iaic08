package problemas;

public class Pruebas {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Granjero juego = new Granjero();
		for (int i = 0; i<6; i++){
			System.out.println("Estrategia: "+ i);
			boolean resuelto = juego.resolver(i);
		}
		
	}

}
