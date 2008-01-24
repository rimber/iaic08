package problemas;

public class Pruebas {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Granjero juego = new Granjero();
		Jarras juego1 = new Jarras();
		/*for (int i = 0; i<6; i++){
			System.out.println("Estrategia: "+ i);
			boolean resuelto = juego.resolver(i);
		}*/
		boolean resuelto = juego1.resolver(5);
		
	}

}
