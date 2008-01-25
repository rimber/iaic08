package problemas;

public class Pruebas {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Palillos juego = new Palillos();
		for (int i = 0; i<=5; i++){
			System.out.println("Estrategia: "+ juego.estrategiaAplicada(i));
			boolean resuelto = juego.resolver(i);
		}
		
	}

}
