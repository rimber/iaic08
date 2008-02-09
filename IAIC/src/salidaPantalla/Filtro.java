package salidaPantalla;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * Clase que implementa un filtro para solo poder
 * seleccionar ficheros con la extensión ".trk"
 * que es la que nosotros utilizamos.
 */

public class Filtro extends FileFilter{
    
	/**
	 * Comprobador de extensión '.trk'en fichero.
	 * @param f es un fichero cualquiera
	 * @return si el fichero de entrada tiene la extensión
	 * ".trk"
	 */
    public boolean accept(File f){
        return f.getName().toLowerCase().endsWith(".trk")||f.isDirectory();
    }
    
    /**
	 * Método que devuelve la descripción de los ficheros que se pueden 
	 * seleccionar.
	 * @return el tipo de ficheros que se pueden seleccionar.
	 */
	public String getDescription(){
		return " .trk" ;
	}
}
