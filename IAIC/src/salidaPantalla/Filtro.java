package salidaPantalla;


import java.io.File;
import javax.swing.filechooser.FileFilter;

public class Filtro extends FileFilter{
    
    /** Creates a new instance of Filtro */
    
    public Filtro() {
    }
    
    public boolean accept(File f){
        return f.getName().toLowerCase().endsWith(".txt")||f.isDirectory();
    }
	public String getDescription(){
    return " .trk" ;
	}

}

	

