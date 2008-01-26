package problemas;

public class Pruebas {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Problema juego = new RioYFamilia();
		for (int i=4; i<5; i++){
			System.out.println("Estrategia: "+ juego.estrategiaAplicada(i));
			juego.setNodosExpandidos(0);
			boolean resuelto = juego.resolver(i);			
			System.out.println("Número de nodos expandidos : "+ juego.getNodosExpandidos()+"\n");
		}
		
	}

}
