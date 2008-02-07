package salidaPantalla;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class Filtro extends FileFilter{
    
	/**
	 * 
	 */
    public Filtro() {
    }
    
    /**
     * 
     * @param f
     * @return 
     */
    public boolean accept(File f){
        return f.getName().toLowerCase().endsWith(".txt")||f.isDirectory();
    }
    
    /**
     * 
     * @return 
     */
	public String getDescription(){
		return " .trk" ;
	}
}
