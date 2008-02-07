package Cubo;

// ESTA CLASE NO DEBERIAMOS ELIMINARLA YA?

public class Main {
    
	public static void main(String[] args) {

		// Dimensión del edificio (PREELIMINAR PARA PRUEBAS)
		int dimension = 3;
		
		// Creamos el edificio y lo inicializamos.
		Edificio edi = new Edificio(dimension,null);
		edi.inicia();
		
		// PROCESO: salida del edificio por backtracking.
		
		// Inicializamos la direccion.
		int direccion = 0;
       
		/*while (!edi.salida() && !edi.cerrado()){
			
			if (!edi.avanza(direccion)){
                direccion++;                     
            }
			else{
                direccion=0;
            }
			
			// Vueltra atrás.
            if (direccion>5){
        	   direccion = edi.retrocede()+1;
            }
		}            
		
		// Informamos del resultado.
		
		// Se queda encerrado.
		if (edi.cerrado()){
			System.out.println("Encerrado!");
			System.exit(0);
		}
		else{ // Consigue salir.
			System.out.println("He salido!");
			edi.muestraRecorrido();
			System.exit(1);
       }*/
    }
}
