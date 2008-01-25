package problemas;

public class Pruebas {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Jarras juego = new Jarras();
		for (int i = 0; i<=1; i++){
			System.out.println("Estrategia: "+ i);
			boolean resuelto = juego.resolver(i);
		}
		
	}

}
