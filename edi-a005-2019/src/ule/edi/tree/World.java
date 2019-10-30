package ule.edi.tree;

import java.util.ArrayList;
import java.util.Collections;

import java.util.LinkedList;
import java.util.List;


/**
 * Un mundo es un árbol binario. 
 * En cada nodo de un mundo se almacena una lista de entidades, cada una con su tipo y
 * cardinalidad. Ver {@link Entity}.
 * 
 * Si se codifica "bajar por la izquierda" como "0" y
 * "bajar por la derecha" como "1", el camino desde un 
 * nodo N hasta un nodo M (en uno de sus sub-árboles) será la
 * cadena de 0s y 1s que indica cómo llegar desde N hasta M.
 *
 * Se define también el camino vacío desde un nodo N hasta
 * él mismo, como cadena vacía.
 * 
 * Por ejemplo, el mundo:
 * 
 * {[F(1)], {[F(1)], {[D(2), P(1)], ∅, ∅}, {[C(1)], ∅, ∅}}, ∅}
 * 
 * o lo que es igual:
 * 
 * [F(1)]
 * |  [F(1)]
 * |  |  [D(2), P(1)]
 * |  |  |  ∅
 * |  |  |  ∅
 * |  |  [C(1)]
 * |  |  |  ∅
 * |  |  |  ∅
 * |  ∅
 * 
 * contiene un bosque (forest) en "", otro en "0", dos dragones y una princesa en "00" y
 * un castillo en "01".
 * @param <T>
 * 
 */
public class World extends AbstractBinaryTreeADT<LinkedList<Entity>> {

	/**
	 * Devuelve el mundo al que se llega al avanzar a la izquierda.
	 * 
	 * @return
	 */
	protected World travelLeft() {

		return (World) leftSubtree;
	}

	private void setLeft(World left) {

		this.leftSubtree = left;
	}

	/**
	 * Devuelve el mundo al que se llega al avanzar a la derecha.
	 * 
	 * @return
	 */
	protected World travelRight() {

		return (World) rightSubtree;
	}

	private void setRight(World right) {

		this.rightSubtree = right;
	}

	private World() {

		
	}

	public static World createEmptyWorld() {

		return new World();
	}

	/**
	 * Inserta la entidad indicada en este árbol.
	 * 
	 * La inserción se produce en el nodo indicado por la dirección; todos
	 * los nodos recorridos para alcanzar aquél que no estén creados se
	 * inicializarán con una entidad 'forest'.
	 * 
	 * La dirección se supondrá correcta, compuesta de cero o más 0s y 1s.
	 * 
	 * Dentro de la lista del nodo indicado, la inserción de nuevas entidades
	 * se realizará al final, como último elemento.
	 * 
	 * Por ejemplo, en un árbol vacío se pide insertar un 'dragón' en la
	 * dirección "00". El resultado final será:
	 * 
	 * [F(1)]
	 * |  [F(1)]
	 * |  |  [D(1)]
	 * |  |  |  ∅
	 * |  |  |  ∅
	 * |  |  ∅
	 * |  ∅
	 * 
	 * La dirección "" indica la raíz, de forma que insertar un 'guerrero' en
	 * "" en el árbol anterior genera:
	 * 
	 * [F(1), W(1)]
	 * |  [F(1)]
	 * |  |  [D(1)]
	 * |  |  |  ∅
	 * |  |  |  ∅
	 * |  |  ∅
	 * |  ∅
	 * 
	 * La inserción tiene en cuenta la cardinalidad, de forma que al volver a
	 * insertar un guerrero en "" se tiene:
	 * 
	 * [F(1), W(2)]
	 * |  [F(1)]
	 * |  |  [D(1)]
	 * |  |  |  ∅
	 * |  |  |  ∅
	 * |  |  ∅
	 * |  ∅
	 *  
	 * @param address dirección donde insertar la entidad.
	 * @param e entidad a insertar.
	 */
	public void insert(String address, Entity e) {

		if(content==null){//
			escribirContenido(content,e,address);
		}
		if(address.equals("")){
			escribirContenido(content,e,address);
		}
		else{//se puede hacer el metodo viajar con esto
			if(address.substring(0, 1).equals("0")){ //camino izquiera
				address=address.substring(1, address.length());
				travelLeft().insert(address, e);
			}
			else{
				address=address.substring(1,address.length());
				travelRight().insert(address, e);
			}
		}


	}



	private void escribirContenido(LinkedList<Entity> listaContenido, Entity e, String address) {
		int contador=0;
		//solo entro si la lista esta vacia
		if(listaContenido==null){
			LinkedList<Entity> contenido= new LinkedList<Entity>();
			if(!address.equals("")){//añadi forest si no hay nada
				contenido.add(Entity.forests(1));
			}
			this.setContent(contenido);
			setLeft(new World());
			setRight(new World());
		}
		else{
			for(int i=0; i<listaContenido.size();i++){
				if(listaContenido.get(i).getType()==e.getType()){//si ya existe una entidad
					listaContenido.get(i).setCount(listaContenido.get(i).getCount()+e.getCount());//incrementa en uno los elementos
					contador++;
				}
			}
			if(contador==0){//si no esta en la lista lo añado
				this.content.add(e);
			}
		}
		// TODO Apéndice de método generado automáticamente

	}

	/**
	 * Indica cuántas entidades del tipo hay en este nodo.
	 * 
	 * @param type tipo de entidad.
	 * @return cuántas entidades de ese tipo hay en este nodo.
	 */
	public long countEntityNode(int type) {
		long cont=0;
		if(isEmpty()){
			return cont;
		}
		for(int i=0;i<content.size();i++){
			if(content.get(i).getType()==type){
				cont+=content.get(i).getCount();
			}
		}	
		return cont;
	}


	/**
	 * Indica cuántas entidades del tipo hay en este mundo (en el árbol completo).
	 * 
	 * @param type tipo de entidad.
	 * @return cuántas entidades de ese tipo hay en este árbol.
	 */
	public long countEntity(int type) {

		long cont=0;
		if(isEmpty()){
			return cont;
		}
		for(int i=0;i<content.size();i++){
			if(content.get(i).getType()==type){
				cont+=content.get(i).getCount();
			}
			cont*= travelLeft().countEntity(type)+travelRight().countEntity(type);
		}	
		return cont;
	}



	/**
	 * Calcula el número de princesas accesibles que hay en este mundo situadas en la altura h, e introduce en una lista las referencias a los nodos en las que se encuentran. 
	 * 
	 * Una princesa es accesible si en el camino desde la raiz hasta ella no aparece ningún Dragón
	 * 
	 * @param List<World> donde dejará las referencias a los nodos situados en altura h que contienen princesas accesibles.
	 * @return el número de princesas accesibles situadas a altura h
	 */
	public long countAccesiblePrincesHeight(int h, List<World> lista){
		if(isEmpty()){
			return 0;
		}
		ArrayList<String> caminoscorrectos= new ArrayList<String>();
		//tengo la lista de los caminos que llevan a la princesas sin dragones
		caminoscorrectos=listaInOrdenCaminosSinDragon("", caminoscorrectos, false);
		//System.out.println(caminoscorrectos);
		//System.out.println("tamaño camino"+caminoscorrectos.size());	

		for(int i=0;i<caminoscorrectos.size(); i++){
			if(caminoscorrectos.get(i).length()+1==h){
				World nuevoMundo=new World();
				nuevoMundo.insert(caminoscorrectos.get(i), Entity.princesses(1));
				lista.add(nuevoMundo);
			}
		}
		long sumPrincesas=0;
		for(int i=0; i<caminoscorrectos.size(); i++){
			if(caminoscorrectos.get(i).length()==h-1){
				sumPrincesas+= sumaCamino(caminoscorrectos.get(i), 0);	
			}
		}
		return sumPrincesas;

	}
	private long sumaCamino(String string, long suma){
		if(string.length()!=0){
			if(string.substring(0, 1).equals("0")){
				string=string.substring(1,string.length());
				return travelLeft().sumaCamino(string, suma);
			}
			else{
				string=string.substring(1,string.length());
				return travelRight().sumaCamino(string, suma);

			}
		}
		//estoy en el nodo que me indica el camino
		for(int i=0; i<content.size(); i++){
			if(content.get(i).getType()==3){
				suma+=content.get(i).getCount();
			}
		}
		return suma;
	}





	private ArrayList<String> listaInOrdenCaminosSinDragon(String recorrido, ArrayList<String> caminoscorrectos,boolean existeDragon) {
		if(content!=null){
			for(int i=0; i<content.size(); i++){
				if(content.get(i).getType()==1){
					existeDragon= true;
				}
			}
		}
		if(!existeDragon){
			for(int i=0; i<content.size(); i++){
				if(content.get(i).getType()==3){
					caminoscorrectos.add(recorrido);
				}

			}
		}
		if(travelLeft()!=null&& !existeDragon){
			return travelLeft().listaInOrdenCaminosSinDragon(recorrido+"0", caminoscorrectos, existeDragon);
		}
		if(travelRight()!=null&& !existeDragon){
			return travelRight().listaInOrdenCaminosSinDragon(recorrido+"1", caminoscorrectos, existeDragon);
		}
		// TODO Apéndice de método generado automáticamente
		return caminoscorrectos;
	}

	@Override
	public String toString() {
		if (! isEmpty()) {
			//	Construye el resultado de forma eficiente
			StringBuffer result = new StringBuffer();

			//	Raíz
			Collections.sort(content);
			result.append("{" + content.toString());

			if (! tags.isEmpty()) {
				result.append(" [");

				List<String> sk = new LinkedList<String>(tags.keySet());

				Collections.sort(sk);
				for (String k : sk) {
					result.append("(" + k + ", " + tags.get(k) + "), ");
				}
				result.delete(result.length() - 2, result.length());
				result.append("]");
			}

			//	Y cada sub-árbol
			for (int i = 0; i < getMaxDegree(); i++) {
				result.append(", " + getSubtree(i).toString());
			}
			//	Cierra la "}" de este árbol
			result.append("}");

			return result.toString();
		} else {
			return AbstractTreeADT.EMPTY_TREE_MARK;
		}
	}	


}
