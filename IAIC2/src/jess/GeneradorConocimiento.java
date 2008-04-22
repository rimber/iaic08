/**
 * 
 */
package jess;

/**
 *
 * @author 
 */
public class GeneradorConocimiento {
    
	/**
	 * Motor de iferencia de conocimiento.
	 */
	private Rete motorIferencia;
	
	/**
	 * Nombre del fichero fuente del programa a cargar en el motor de iferencia.
	 */
	private String nombreFichero;
 
	/**
	 * 
	 * @param fichero
	 */
    public GeneradorConocimiento(String nombreFuente) {
    	// Creamos el motor de iferencia.
    	motorIferencia = new Rete();
    	
    	// Asociamos programa fuente.
    	nombreFichero = nombreFuente;
    	
    	// Cargamos el fichero fuente en el motor de iferencia.
    	cargaFichero(nombreFichero);
    }
    
    /**
     * 
     * @param fichero
     */
    private void cargaFichero(String nombreFuente){
    	try{
    		motorIferencia.executeCommand("(batch \"" + nombreFuente + "\")"); 
    	}catch(JessException e){
    		System.out.println("Error: no puedo leer programa " + nombreFuente);
    		System.out.println("Excepción:" + e.getMessage());
    	}
    }
    
    /**
     * 
     * @return
     */
    public Rete getMotorIferencia(){
    	return motorIferencia;
    }
    
}
