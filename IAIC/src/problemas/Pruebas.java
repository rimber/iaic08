package problemas;

public class Pruebas {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Problema juego = new Jarras();
		for (int i=0; i<6; i++){
			System.out.println("Estrategia: "+ juego.estrategiaAplicada(i));
			juego.setNodosExpandidos(0);
			boolean resuelto = juego.resolver(i);			
			System.out.println("Número de nodos expandidos : "+ juego.getNodosExpandidos()+"\n");
		}		
	}

}
