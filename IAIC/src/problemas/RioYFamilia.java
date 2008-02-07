/**
 * Contiene el conjunto de los problemas implementados según el paradigma
 * del espacio de estados.
 */
package problemas;

import java.util.Enumeration;
import java.util.Vector;

import aima.search.DepthBoundedSearch;
import aima.search.Successor;

/**
 * Clase que implementa el problema de la familia,
 * el ladron y el policia cruzando un rio según
 * el paradigma del espacio de estados.
 */
public class RioYFamilia extends Problema {

	/**
	 * Posición de la barca (Margen del río: 1-izquierda, 0-derecha).
	 */
	private int posBarca;
	
	/**
	 * Posición del policia (Margen del río: 1-izquierda, 0-derecha).
	 */
	private int posPolicia;
	
	/**
	 * Posición del ladron (Margen del río: 1-izquierda, 0-derecha).
	 */
	private int posLadron;
	
	/**
	 * Posición de la madre (Margen del río: 1-izquierda, 0-derecha).
	 */
	private int posMadre;
	
	/**
	 * Posición del padre (Margen del río: 1-izquierda, 0-derecha).
	 */
	private int posPadre;
	
	/**
	 * Posición del hijo1 (Margen del río: 1-izquierda, 0-derecha).
	 */
	private int posHijo1;
	
	/**
	 * Posición del hijo2 (Margen del río: 1-izquierda, 0-derecha).
	 */
	private int posHijo2;
	
	/**
	 * Posición de la hija1 (Margen del río: 1-izquierda, 0-derecha).
	 */
	private int posHija1;
	
	/**
	 * Posición de la hija2 (Margen del río: 1-izquierda, 0-derecha).
	 */
	private int posHija2;
	
	/**
	 * Constructor por defecto: inicializa las componentes a valores por defecto.
	 */
	public RioYFamilia(){
		enunciado = " En un rio se encuentra una familia compuesta por un padre, una\n" +
		    " madre, dos hijas, dos hijos, un policia y un ladrón. Hay una barca\n" +
		    " en la que solo caben 2 personas.\n"+
		 	" El padre, la madre y el policia son los únicos que saben manejar\n" +
		 	" la barcar. La madre no puede estar con los hijos sin el padre.\n"+
		 	" El padre no puede estar con las hijas sin la madre.\n "+
		 	"El ladrón no puede estar con nadie de la familia sin el policia.\n";
		posBarca=1;
		posPolicia=1;
		posLadron=1;
		posMadre=1;
		posPadre=1;
		posHijo1=1;
		posHijo2=1;
		posHija1=1;
		posHija2=1;
		repEstado = "("+posBarca+","+posPolicia+","+posLadron+","+posMadre+","+posPadre+
					   ","+posHijo1+","+posHijo2+","+posHija1+","+posHija2+")";
		nombreOperador = "";		 	
				 	
	}
	
	/**
	 * Constructor principal, inicializa los atributos a los valores indicados.
	 * @param bar posción de la barca.
	 * @param pol posición del policia.
	 * @param lad posición del ladrón.
	 * @param mad posición de la madre.
	 * @param pad posición del padre.
	 * @param ho1 posición del hijo1.
	 * @param ho2 posición del hijo2.
	 * @param ha1 posición de la hija1.
	 * @param ha2 posición de la hija2.
	 */	
	public RioYFamilia(int bar,int pol,int lad, int mad,int pad,int ho1,int ho2,int ha1,int ha2){
		enunciado = " En un rio se encuentra una familia compuesta por un padre, una\n" +
				    " madre, dos hijas, dos hijos, un policia y un ladrón. Hay una barca\n" +
				    " en la que solo caben 2 personas.\n"+
				 	" El padre, la madre y el policia son los únicos que saben manejar\n" +
				 	" la barcar. La madre no puede estar con los hijos sin el padre.\n"+
				 	" El padre no puede estar con las hijas sin la madre.\n "+
				 	"El ladrón no puede estar con nadie de la familia sin el policia.\n";
		posBarca=bar;
		posPolicia=pol;
		posLadron=lad;
		posMadre=mad;
		posPadre=pad;
		posHijo1=ho1;
		posHijo2=ho2;
		posHija1=ha1;
		posHija2=ha2;
		repEstado = "("+posBarca+","+posPolicia+","+posLadron+","+posMadre+","+posPadre+
				   ","+posHijo1+","+posHijo2+","+posHija1+","+posHija2+")";
		nombreOperador = "";
	}
	
	/**
	 * Genera el valor heurístico del estado.
	 * @return Valor de la heúristica. 
	 */
	public float h() {
		// Heurística: Mejor cuanto menor sea h, es decir, cuanto menos componentes
		// estén en el lado izquierdo.
		int h = posBarca+posPolicia+posLadron+posMadre+posPadre+
		   		+posHijo1+posHijo2+posHija1+posHija2;
		return (float)h;
	}

	/**
	 * Indica si estamos ante un estado solución.
	 * @return Valor indicando si el estado es solución o no.
	 */
	public boolean isGoal() {
		// Solución si todos los componentes están a la derecha.
		return (h()== 0);
	}

	/**
	 * Comprueba si el estado del problema es válido.
	 * @return Valor indicando la validez del estado.
	 */
	protected boolean isValid() {
		if (nodosExpandidos > maxNodos){
			return false;
		}
		
		//Si el ladrón no está con el policia y está con alguien
		//de la familia.
		if((posPolicia != posLadron)&&(!estaSoloLadron())){
			return false;
		}
		
		//Si el padre está sin la madre y con alguna de las hijas.
		if((posPadre != posMadre)&&((posPadre == posHija1)||(posPadre == posHija2))){
			return false;
		}
		
		//Si la madre está sin el padre y con alguno de los hijos.
		if((posMadre != posPadre)&&((posMadre == posHijo1)||(posMadre == posHijo2))){
			return false;
		}
		return true;
	}

	/**
	 * Método privado creado para dar claridad a las restricciones.
	 * @return true si el ladrón no está con nadie de la familia.
	 */
	private boolean estaSoloLadron() {
		return ((posLadron != posMadre)&&(posLadron != posPadre)&&(posLadron != posHijo1)
				&&(posLadron != posHijo2)&&(posLadron != posHija1)&&(posLadron != posHija2));
	}

	/**
	 * Genera todos los posibles estados sucesores del estado actual.
	 * @return Conjunto de estados sucesores.	 
	 */
	public Enumeration<Successor> successors() {
		// Tenemos 15 operadores
		// Operador 0 : Cruza el policía solo (con la barca).
		// Operador 1 : Cruza la madre sola (con la barca).
		// Operador 2 : Cruza el padre solo (con la barca).
		// Operador 3 : Cruza el policía con la madre (con la barca).
		// Operador 4 : Cruza el policía con el padre (con la barca).
		// Operador 5 : Cruza el policía con el ladrón (con la barca).
		// Operador 6 : Cruza el policía con hijo1 (con la barca).
		// Operador 7 : Cruza el policía con hijo2 (con la barca).
		// Operador 8 : Cruza el policía con hija1 (con la barca).
		// Operador 9 : Cruza el policía con hija2 (con la barca).
		// Operador 10 : Cruza la madre y el padre (con la barca).
		// Operador 11 : Cruza la madre y la hija1 (con la barca).
		// Operador 12 : Cruza la madre y la hija2 (con la barca).
		// Operador 13 : Cruza el padre y el hijo1 (con la barca).
		// Operador 14 : Cruza el padre y el hijo2 (con la barca).
		
		// Operador usado.
	 	int numOperador;
	 	
	 	// Nuevas posiciones.
	 	int nposBarca = 1;
	 	int nposPolicia = 1;
	 	int nposLadron = 1;
	 	int nposMadre  = 1;
	 	int nposPadre = 1;
	 	int nposHijo1 = 1;
	 	int nposHijo2 = 1;
	 	int nposHija1  = 1;
	 	int nposHija2 = 1;
	 	
	 	// Incrementamos el número de nodos expandidos.
	 	nodosExpandidos++;
	 	
	 	Vector<Successor> successor = new Vector<Successor>();
	 	
	 	for (numOperador = 0; numOperador<15;numOperador++){
	 		boolean operadorAplicado = false;
	 		// Operador 0 : Cruza el policía solo (con la barca).
	 		if(numOperador == 0){
	 			//Para poder cruzar el policía tiene que estar con la barca.
	 			if(posBarca == posPolicia){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaPolicía";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza el policía.
	 				nposPolicia = 1 - posPolicia;
	 				//Los demás se quedan donde están.
	 				nposLadron = posLadron;
	 			 	nposMadre  = posMadre;
	 			 	nposPadre = posPadre;
	 			 	nposHijo1 = posHijo1;
	 			 	nposHijo2 = posHijo2;
	 			 	nposHija1 = posHija1;
	 			 	nposHija2 = posHija2;
	 			}
	 		}
	 		// Operador 1 : Cruza la madre sola (con la barca).
	 		if(numOperador == 1){
	 			//Para poder cruzar la madre tiene que estar con la barca.
	 			if(posBarca == posMadre){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaMadre";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza la madre.
	 				nposMadre = 1 - posMadre;
	 				//Los demás se quedan donde están.
	 				nposLadron = posLadron;
	 			 	nposPolicia  = posPolicia;
	 			 	nposPadre = posPadre;
	 			 	nposHijo1 = posHijo1;
	 			 	nposHijo2 = posHijo2;
	 			 	nposHija1 = posHija1;
	 			 	nposHija2 = posHija2;
	 			}
	 		}
	 		// Operador 2 : Cruza el padre solo (con la barca).
	 		if(numOperador == 2){
	 			//Para poder cruzar el padre tiene que estar con la barca.
	 			if(posBarca == posPadre){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaPadre";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza el padre.
	 				nposPadre = 1 - posPadre;
	 				//Los demás se quedan donde están.
	 				nposLadron = posLadron;
	 			 	nposMadre  = posMadre;
	 			 	nposPolicia = posPolicia;
	 			 	nposHijo1 = posHijo1;
	 			 	nposHijo2 = posHijo2;
	 			 	nposHija1 = posHija1;
	 			 	nposHija2 = posHija2;
	 			}
	 		}
	 		// Operador 3 : Cruza el policía con la madre (con la barca).
	 		if(numOperador == 3){
	 			//Para poder cruzar el policía y la madre tienen que estar con la barca.
	 			if((posBarca == posPolicia)&&(posBarca == posMadre)){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaPolicíaMadre";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza el policía.
	 				nposPolicia = 1 - posPolicia;
	 				//Cruza la madre.
	 			 	nposMadre  = 1 - posMadre;
	 				//Los demás se quedan donde están.
	 				nposLadron = posLadron;
	 			 	nposPadre = posPadre;
	 			 	nposHijo1 = posHijo1;
	 			 	nposHijo2 = posHijo2;
	 			 	nposHija1 = posHija1;
	 			 	nposHija2 = posHija2;
	 			}
	 		}
	 		// Operador 4 : Cruza el policía con el padre (con la barca).
	 		if(numOperador == 4){
	 			//Para poder cruzar el policía y el padre tienen que estar con la barca.
	 			if((posBarca == posPolicia)&&(posBarca == posPadre)){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaPolicíaPadre";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza el policía.
	 				nposPolicia = 1 - posPolicia;
	 				//Cruza el padre.
	 			 	nposPadre  = 1 - posPadre;
	 				//Los demás se quedan donde están.
	 				nposLadron = posLadron;
	 			 	nposMadre = posMadre;
	 			 	nposHijo1 = posHijo1;
	 			 	nposHijo2 = posHijo2;
	 			 	nposHija1 = posHija1;
	 			 	nposHija2 = posHija2;
	 			}
	 		}
	 		// Operador 5 : Cruza el policía con el ladrón (con la barca).
	 		if(numOperador == 5){
	 			//Para poder cruzar el policía y el ladrón tienen que estar con la barca.
	 			if((posBarca == posPolicia)&&(posBarca == posLadron)){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaPolicíaLadron";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza el policía.
	 				nposPolicia = 1 - posPolicia;
	 				//Cruza el ladrón.
	 			 	nposLadron  = 1 - posLadron;
	 				//Los demás se quedan donde están.
	 				nposPadre = posPadre;
	 			 	nposMadre = posMadre;
	 			 	nposHijo1 = posHijo1;
	 			 	nposHijo2 = posHijo2;
	 			 	nposHija1 = posHija1;
	 			 	nposHija2 = posHija2;
	 			}
	 		}
	 		// Operador 6 : Cruza el policía con el hijo1 (con la barca).
	 		if(numOperador == 6){
	 			//Para poder cruzar el policía y el hijo1 tienen que estar con la barca.
	 			if((posBarca == posPolicia)&&(posBarca == posHijo1)){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaPolicíaHijo1";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza el policía.
	 				nposPolicia = 1 - posPolicia;
	 				//Cruza el hijo1.
	 			 	nposHijo1  = 1 - posHijo1;
	 				//Los demás se quedan donde están.
	 				nposPadre = posPadre;
	 			 	nposMadre = posMadre;
	 			 	nposLadron = posLadron;
	 			 	nposHijo2 = posHijo2;
	 			 	nposHija1 = posHija1;
	 			 	nposHija2 = posHija2;
	 			}
	 		}
	 		// Operador 7 : Cruza el policía con el hijo2 (con la barca).
	 		if(numOperador == 7){
	 			//Para poder cruzar el policía y el hijo2 tienen que estar con la barca.
	 			if((posBarca == posPolicia)&&(posBarca == posHijo2)){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaPolicíaHijo2";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza el policía.
	 				nposPolicia = 1 - posPolicia;
	 				//Cruza el hijo2.
	 			 	nposHijo2  = 1 - posHijo2;
	 				//Los demás se quedan donde están.
	 				nposPadre = posPadre;
	 			 	nposMadre = posMadre;
	 			 	nposLadron = posLadron;
	 			 	nposHijo1 = posHijo1;
	 			 	nposHija1 = posHija1;
	 			 	nposHija2 = posHija2;
	 			}
	 		}
	 		// Operador 8 : Cruza el policía con la hija1 (con la barca).
	 		if(numOperador == 8){
	 			//Para poder cruzar el policía y la hija1 tienen que estar con la barca.
	 			if((posBarca == posPolicia)&&(posBarca == posHija1)){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaPolicíaHija1";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza el policía.
	 				nposPolicia = 1 - posPolicia;
	 				//Cruza la hija1.
	 			 	nposHija1  = 1 - posHija1;
	 				//Los demás se quedan donde están.
	 				nposPadre = posPadre;
	 			 	nposMadre = posMadre;
	 			 	nposLadron = posLadron;
	 			 	nposHijo2 = posHijo2;
	 			 	nposHijo1 = posHijo1;
	 			 	nposHija2 = posHija2;
	 			}
	 		}
	 		// Operador 9 : Cruza el policía con la hija2 (con la barca).
	 		if(numOperador == 9){
	 			//Para poder cruzar el policía y la hija2 tienen que estar con la barca.
	 			if((posBarca == posPolicia)&&(posBarca == posHija2)){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaPolicíaHija2";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza el policía.
	 				nposPolicia = 1 - posPolicia;
	 				//Cruza la hija2.
	 			 	nposHija2  = 1 - posHija2;
	 				//Los demás se quedan donde están.
	 				nposPadre = posPadre;
	 			 	nposMadre = posMadre;
	 			 	nposLadron = posLadron;
	 			 	nposHijo2 = posHijo2;
	 			 	nposHijo1 = posHijo1;
	 			 	nposHija1 = posHija1;
	 			}
	 		}
	 		// Operador 10 : Cruza la madre y el padre (con la barca).
	 		if(numOperador == 10){
	 			//Para poder cruzar la madre y el padre tienen que estar con la barca.
	 			if((posBarca == posMadre)&&(posBarca == posPadre)){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaMadrePadre";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza la madre.
	 				nposMadre = 1 - posMadre;
	 				//Cruza el padre.
	 			 	nposPadre  = 1 - posPadre;
	 				//Los demás se quedan donde están.
	 				nposPolicia = posPolicia;
	 			 	nposHija2 = posHija2;
	 			 	nposLadron = posLadron;
	 			 	nposHijo2 = posHijo2;
	 			 	nposHijo1 = posHijo1;
	 			 	nposHija1 = posHija1;
	 			}
	 		}
			// Operador 11 : Cruza la madre y la hija1 (con la barca).
	 		if(numOperador == 11){
	 			//Para poder cruzar la madre y la hija1 tienen que estar con la barca.
	 			if((posBarca == posMadre)&&(posBarca == posHija1)){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaMadreHija1";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza la madre.
	 				nposMadre = 1 - posMadre;
	 				//Cruza la hija1.
	 			 	nposHija1  = 1 - posHija1;
	 				//Los demás se quedan donde están.
	 				nposPolicia = posPolicia;
	 			 	nposHija2 = posHija2;
	 			 	nposLadron = posLadron;
	 			 	nposHijo2 = posHijo2;
	 			 	nposHijo1 = posHijo1;
	 			 	nposPadre = posPadre;
	 			}
	 		}
			// Operador 12 : Cruza la madre y la hija2 (con la barca).
	 		if(numOperador == 12){
	 			//Para poder cruzar la madre y la hija2 tienen que estar con la barca.
	 			if((posBarca == posMadre)&&(posBarca == posHija2)){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaMadreHija2";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza la madre.
	 				nposMadre = 1 - posMadre;
	 				//Cruza la hija2.
	 			 	nposHija2  = 1 - posHija2;
	 				//Los demás se quedan donde están.
	 				nposPolicia = posPolicia;
	 			 	nposHija1 = posHija1;
	 			 	nposLadron = posLadron;
	 			 	nposHijo2 = posHijo2;
	 			 	nposHijo1 = posHijo1;
	 			 	nposPadre = posPadre;
	 			}
	 		}
			// Operador 13 : Cruza el padre y el hijo1 (con la barca).
	 		if(numOperador == 13){
	 			//Para poder cruzar el padre y el hijo1 tienen que estar con la barca.
	 			if((posBarca == posPadre)&&(posBarca == posHijo1)){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaPadreHijo1";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza la padre.
	 				nposPadre = 1 - posPadre;
	 				//Cruza la hijo1.
	 			 	nposHijo1  = 1 - posHijo1;
	 				//Los demás se quedan donde están.
	 				nposPolicia = posPolicia;
	 			 	nposHija1 = posHija1;
	 			 	nposLadron = posLadron;
	 			 	nposHijo2 = posHijo2;
	 			 	nposHija2 = posHija2;
	 			 	nposMadre = posMadre;
	 			}
	 		}
			// Operador 14 : Cruza el padre y el hijo2 (con la barca).
	 		if(numOperador == 14){
	 			//Para poder cruzar el padre y el hijo2 tienen que estar con la barca.
	 			if((posBarca == posPadre)&&(posBarca == posHijo2)){
	 				operadorAplicado = true;
	 				nombreOperador = "cruzaPadreHijo2";
	 				//Cruza la barca.
	 				nposBarca = 1 - posBarca;
	 				//Cruza la padre.
	 				nposPadre = 1 - posPadre;
	 				//Cruza la hijo2.
	 			 	nposHijo2  = 1 - posHijo2;
	 				//Los demás se quedan donde están.
	 				nposPolicia = posPolicia;
	 			 	nposHija1 = posHija1;
	 			 	nposLadron = posLadron;
	 			 	nposHijo1 = posHijo1;
	 			 	nposHija2 = posHija2;
	 			 	nposMadre = posMadre;
	 			}
	 		}
	 		if (operadorAplicado){
		 		// Creamos el nuevo estado.
		 	 	RioYFamilia nuevoEstado = new RioYFamilia(nposBarca,nposPolicia,nposLadron,nposMadre,nposPadre,
		 	 											  nposHijo1,nposHijo2,nposHija1,nposHija2);
		 	 		
		 	 	// Comprobamos si el nuevo estado es válido.
		 	 	if(nuevoEstado.isValid()){	 	 		
		 	 		// Añadimos el estado como sucesor.
		 	 		successor.addElement(new Successor(nuevoEstado,nombreOperador,1)); 
		 	 	}
	 		}
	 	}
		return successor.elements();
	}
	
	/**
 	 * Método que intenta resolver un problema según la estrategia de búsqueda
 	 * en profundidad iterativa pero limitandola a un nivel máximo
 	 * debido a que en este problema usar la búsqueda implementada
 	 * en el aima tarda demasiado tiempo en terminar o no termina.
 	 * @return Si se ha resuelto o no el problema.
 	 */
	protected boolean resolverProfIt() {
		int i = 1;
		boolean resuelto= false;
		while((i<=maxNivel)&&(!resuelto)){
			System.out.println("Profundidad iterativa, nivel = "+i+"\n");
			resuelto=listPath((new DepthBoundedSearch(this,i)).search());
			i++;
		}
		if(i>maxNivel){
			return false;
		}
		return resuelto;
	}
	public String dameTitulo(){
		return "Rio y Familia";
		
	}
	
}
