package problemas;

public class Pruebas {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Puzzle8 juego = new Puzzle8();
		for (int i = 4; i<5; i++){
			System.out.println("Estrategia: "+ i);
			boolean resuelto = juego.resolver(i);
		}
		
	}

}
