package Cubo;
/*
 * Main.java
 *
 * Created on 10 de enero de 2008, 14:59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author usuario_local
 */
public class Main {
    
    /** Creates a new instance of Main */
    
    public Main() {
               
    }
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
       int dimension=3;//numero que luego le pasaremos al edificio para saber su tamaño
       Edificio edi= new Edificio(dimension);
       edi.inicia();
       int direccion=0;
       
       while (!edi.salida()&& !edi.cerrado()){
 

           if (!edi.avanza(direccion)){
                direccion++;                     
           }
           else{
                direccion=0;
           }
           if (direccion>5){
        	   System.out.println("Vuelta atras");
        	   direccion=edi.retrocede()+1;
  
            }
       }                
       if (edi.cerrado()){
    	   System.out.println("Encerrado");
    	   System.exit(0);
        //avisar que te has muerto por falta de agua y eficiencia
       }
       else{
    	   System.out.println("HE SALIDO¡¡");
    	   edi.muestraRecorrido();
    	   System.exit(1);
    	
        //VICTORIA¡¡¡¡¡
       }
    }
    
}
