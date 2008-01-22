/*
 * Edificio.java
 *
 * Created on 10 de enero de 2008, 15:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Cubo;

import java.util.ArrayList;
import java.util.*;
/**
 *
 * @author usuario_local
 */
public class Edificio {
    

    private Habitacion habitaciones[][][];
    private ArrayList recorrido;
    private int dimension;
    private boolean edificioCerrado;
    
    /** Creates a new instance of Edificio */
    
    public Edificio() {
        
    }
    
    public Edificio (int n){
        habitaciones=new Habitacion[n][n][n]; 
        dimension=n;
        recorrido=new ArrayList();
        edificioCerrado=false;
    }
    
    public void muestraRecorrido(){
    	for (int i=0;i<recorrido.size();i++){
    		Coord3d c=(Coord3d)recorrido.get(i);
    		System.out.println("(" +c.getx()+","+c.gety()+","+c.getz()+")");    		
    	}
    	
    } 
    public Coord3d inicia (){
        //hacer los random
    	Random rnd = new Random(4578);
    	
        int x=rnd.nextInt()%dimension;
        if (x<0) x=-x;
        int y=rnd.nextInt()%dimension;
        if (y<0) y=-y;
        int z=rnd.nextInt()%dimension;
        if (z<0) z=-z;
        
        habitaciones[x][y][z]=new Habitacion(); 
        Coord3d coord= new Coord3d(x,y,z);
        ponPuertas(coord);
        recorrido.add(coord);        
        return coord;
    }
    
    public boolean salida(){
        int posicion=recorrido.size();
        Coord3d c=(Coord3d)recorrido.get(posicion-1);
        if (c.gety()==0){//tenemos que estar en el piso de abajo
            if ((c.getz()==dimension-1)||(c.getz()==0)){
                if ((c.getx()==0)||(c.getx()==dimension-1)){
                    return true;
                }                      
            }            
        }        
      return false;
   }
    
    public boolean cerrado(){
/*devuelve si has llegado a un conjunto cerrado
 *de puertas, si el camino no tiene solución
 **/
        
    return edificioCerrado;
    }
    
    public boolean avanza(int direccion){
    	
    char caracter='1';
    	try{
    	System.out.println("Dame Estrategia:");
    	caracter = (char) System.in.read(); 
    	}
    	catch(Exception e){}
    	
    	int estrategia=(int)caracter;
    	estrategia=estrategia%6;
    	if (estrategia<0) estrategia=-estrategia;
    	//abrir un banner y pedir una estrategia
    	
        //seleccionamos la habitacion en la que estamos
        int posicion=recorrido.size();
        Coord3d c=(Coord3d)recorrido.get(posicion-1);
         //puede ser posicion -1, depende de la implementacion de ArrayList
        Habitacion h=habitaciones[c.getx()][c.gety()][c.getz()];
       //abrimos la puerta indicada 
        if (h.abrePuerta(direccion,estrategia)){
            /*si la puerta se ha abierto, puede ser porque  hallamos llegado
             * a una habitacion donde ya hemos estado, o porque estemos en una habitacion
             * nueva, la comparación de habitaciones[x][y][z]==NULL nos dice si hemos
             *estado antes en la habitacion
             */
             switch (direccion){
                 case 0:if (habitaciones[c.getx()][c.gety()-1][c.getz()]==null){
                            habitaciones[c.getx()][c.gety()-1][c.getz()]=new Habitacion();
                            Coord3d nuevac=new Coord3d(c.getx(),c.gety()-1,c.getz()); 
                            recorrido.add(nuevac);
                            ponPuertas(nuevac);
                            return true;
                        }                        
                        break;
                         
                 case 1:if (habitaciones[c.getx()+1][c.gety()][c.getz()]==null){
                            habitaciones[c.getx()+1][c.gety()][c.getz()]=new Habitacion();
                            Coord3d nuevac=new Coord3d(c.getx()+1,c.gety(),c.getz());
                            recorrido.add(nuevac);
                            ponPuertas(nuevac);
                            return true;
                        }                     
                       break;    
                 
                 case 2:if (habitaciones[c.getx()][c.gety()+1][c.getz()]==null){
                            habitaciones[c.getx()][c.gety()+1][c.getz()]=new Habitacion();
                            Coord3d nuevac=new Coord3d(c.getx(),c.gety()+1,c.getz()); 
                            recorrido.add(nuevac);
                            ponPuertas(nuevac);
                            return true;
                        }
                        break;
                        
                 case 3:if (habitaciones[c.getx()-1][c.gety()][c.getz()]==null){
                            habitaciones[c.getx()-1][c.gety()][c.getz()]=new Habitacion();
                            Coord3d nuevac=new Coord3d(c.getx()-1,c.gety(),c.getz()); 
                            recorrido.add(nuevac);
                            ponPuertas(nuevac);
                            return true;
                        }                     
                       break;    
                       
                 case 4:if (habitaciones[c.getx()][c.gety()][c.getz()-1]==null){
                            habitaciones[c.getx()][c.gety()][c.getz()-1]=new Habitacion();
                            Coord3d nuevac=new Coord3d(c.getx(),c.gety(),c.getz()-1); 
                            recorrido.add(nuevac);
                            ponPuertas(nuevac);
                            return true;
                        }
                       break;
                       
                 case 5:if (habitaciones[c.getx()][c.gety()][c.getz()+1]==null){
                            habitaciones[c.getx()][c.gety()][c.getz()+1]=new Habitacion();
                            Coord3d nuevac=new Coord3d(c.getx(),c.gety(),c.getz()+1); 
                            recorrido.add(nuevac);
                            ponPuertas(nuevac);
                            return true;
                        }
                       break;
                 default: return false;
            //no deberia pasar nunca por el default, pero por si acaso
             }
            }     
        return false;//si no has podido abrir la puerta o has llegado a una de las otras
    }
    
    private void ponPuertas(Coord3d c){
     

      Coord3d comprobar;
      Puerta p;
      comprobar = c.izquierda();
           
      if (!existe(comprobar)){
    	  p=new Puerta(0);//puerta cerrada         
      }
      else{
            p=habitaciones[comprobar.getx()][comprobar.gety()][comprobar.getz()].damePuerta(1);       
           //no deberia devolver null porque hemos comprobado si existe y esta rama es que si
      }
      habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p,3);
      comprobar = c.derecha();
     
      if (!existe(comprobar)){
    	  p=new Puerta(0);//puerta cerrada           
      }
      else{
           p=habitaciones[comprobar.getx()][comprobar.gety()][comprobar.getz()].damePuerta(3);
          //no deberia devolver null porque hemos comprobado si existe y esta rama es que si
      }
      habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p,1);                     
      comprobar = c.arriba();
     
      if (!existe(comprobar)){
    	  p=new Puerta(0);//puerta cerrada             
      }
      else{
           p=habitaciones[comprobar.getx()][comprobar.gety()][comprobar.getz()].damePuerta(0);
          //no deberia devolver null porque hemos comprobado si existe y esta rama es que si
      }
      habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p,2);              
      comprobar = c.abajo();
     
      if (!existe(comprobar)){
    	  p=new Puerta(0);//puerta cerrada           
      }
      else{
           p=habitaciones[comprobar.getx()][comprobar.gety()][comprobar.getz()].damePuerta(2);
          //no deberia devolver null porque hemos comprobado si existe y esta rama es que si
      }
      habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p,0);          
      comprobar = c.delante();
     
      if (!existe(comprobar)){
    	  p=new Puerta(0);//puerta cerrada           
      }
      else{
           p=habitaciones[comprobar.getx()][comprobar.gety()][comprobar.getz()].damePuerta(5);
          //no deberia devolver null porque hemos comprobado si existe y esta rama es que si
      }
      habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p,4);                
      comprobar = c.detras();
     
      if (!existe(comprobar)){
    	  p=new Puerta(0);//puerta cerrada       
      }
      else{
           p=habitaciones[comprobar.getx()][comprobar.gety()][comprobar.getz()].damePuerta(4);
          //no deberia devolver null porque hemos comprobado si existe y esta rama es que si
      }
      habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p,5);          
      
        //comprobamos los laterales del cubo
        if (c.getx()==0){
            p= new Puerta(2);//2 por ser bloqueado
            habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p,3);
        }
      
        if (c.getx()==dimension-1){
            p= new Puerta(2);//2 por ser bloqueado
            habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p,1);
        }
        
        if (c.gety()==0){
            p= new Puerta(2);//2 por ser bloqueado
            habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p,0);
        }
      
        if (c.gety()==dimension-1){
            p= new Puerta(2);//2 por ser bloqueado
            habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p,2);
        }
        
        if (c.getz()==0){
            p= new Puerta(2);//2 por ser bloqueado
            habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p,4);
        }
      
        if (c.getz()==dimension-1){
            p= new Puerta(2);//2 por ser bloqueado
            habitaciones[c.getx()][c.gety()][c.getz()].setPuerta(p,5);
        }
    }
    
    public boolean existe(Coord3d c){
     
    	if ((c.getx()>dimension-1)||(c.getx()<0)){return false;}
    	if ((c.gety()>dimension-1)||(c.gety()<0)){return false;}
    	if ((c.getz()>dimension-1)||(c.getz()<0)){return false;}
    	return habitaciones[c.getx()][c.gety()][c.getz()]!=null;
    }
    
    public int retrocede(){
        
    	if (recorrido.size()>1){
    		recorrido.remove(recorrido.size()-1);
    		Coord3d c=(Coord3d)recorrido.get(recorrido.size()-1); 
           	return habitaciones[c.getx()][c.gety()][c.getz()].dameUltimaPuertaProbada();
    	}
    	else{
    		edificioCerrado=true;
    		return 5;    		
    	}
    		
    		
    /*		
    		
        //debe ser el mismo que el anterior, si cambias el de arriba, cambias este
    		Coord3d c=(Coord3d)recorrido.get(recorrido.size()-1);        
        	if (c.compara(coordInicial)&&habitaciones[c.getx()][c.gety()][c.getz()].dameUltimaPuertaProbada()==6){
        		edificioCerrado=true;
    	}
        
        else{
        	       
        	if (c.compara(coordInicial)&&habitaciones[c.getx()][c.gety()][c.getz()].dameUltimaPuertaProbada()==6){
        		edificioCerrado=true;
        	}
 
        }
        return 5;*/
      }
}
