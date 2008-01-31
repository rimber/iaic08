package problemas;

public class Reloj extends Thread {
	int tiempo;
    
    /** Creates a new instance of Reloj */
    
    public Reloj(){
    }
        
    public void activaReloj(){
        tiempo = 1800; // 30 minutos
    }
    
    public void activaReloj(int t){
        tiempo = t*60; // tiempo en segundos
    }
    
    public boolean terminado(){
        return tiempo == 0;
    } 
    
    public void hora(){
        String s = new String();
        s =(tiempo/3600)+" H ";
        tiempo = tiempo%3600;
        s= s+(tiempo/60)+" Min " +(tiempo%60)+" Seg";
    }
        
    public void run(){

    }
}
