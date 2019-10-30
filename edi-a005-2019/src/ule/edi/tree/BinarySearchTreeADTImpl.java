package ule.edi.tree;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Stack;

import javax.swing.border.EtchedBorder;

/**
 * Árbol binario de búsqueda (binary search tree, BST).
 * 
 * El código fuente está en UTF-8, y la constante 
 * EMPTY_TREE_MARK definida en AbstractTreeADT del
 * proyecto API debería ser el símbolo de conjunto vacío: ∅
 * 
 * Si aparecen caracteres "raros", es porque
 * el proyecto no está bien configurado en Eclipse para
 * usar esa codificación de caracteres.
 *
 * En el toString() que está ya implementado en AbstractTreeADT
 * se usa el formato:
 * 
 * 		Un árbol vacío se representa como "∅". Un árbol no vacío
 * 		como "{(información raíz), sub-árbol 1, sub-árbol 2, ...}".
 * 
 * 		Por ejemplo, {A, {B, ∅, ∅}, ∅} es un árbol binario con 
 * 		raíz "A" y un único sub-árbol, a su izquierda, con raíz "B".
 * 
 * El método render() también representa un árbol, pero con otro
 * formato; por ejemplo, un árbol {M, {E, ∅, ∅}, {S, ∅, ∅}} se
 * muestra como:
 * 
 * M
 * |  E
 * |  |  ∅
 * |  |  ∅
 * |  S
 * |  |  ∅
 * |  |  ∅
 * 
 * Cualquier nodo puede llevar asociados pares (clave,valor) para
 * adjuntar información extra. Si es el caso, tanto toString() como
 * render() mostrarán los pares asociados a cada nodo.
 * 
 * Con {@link #setTag(String, Object)} se inserta un par (clave,valor)
 * y con {@link #getTag(String)} se consulta.
 * 
 * 
 * Con <T extends Comparable<? super T>> se pide que exista un orden en
 * los elementos. Se necesita para poder comparar elementos al insertar.
 * 
 * Si se usara <T extends Comparable<T>> sería muy restrictivo; en
 * su lugar se permiten tipos que sean comparables no sólo con exactamente
 * T sino también con tipos por encima de T en la herencia.
 * 
 * @param <T>
 *            tipo de la información en cada nodo, comparable.
 */
public class BinarySearchTreeADTImpl<T extends Comparable<? super T>> extends
AbstractBinaryTreeADT<T> {

	/**
	 * Devuelve el árbol binario de búsqueda izquierdo.
	 */
	protected BinarySearchTreeADTImpl<T> getLeftBST() {
		//	El atributo leftSubtree es de tipo AbstractBinaryTreeADT<T> pero
		//	aquí se sabe que es además de búsqueda binario
		//
		return (BinarySearchTreeADTImpl<T>) leftSubtree;
	}

	private void setLeftBST(BinarySearchTreeADTImpl<T> left) {
		this.leftSubtree = left;
	}

	/**
	 * Devuelve el árbol binario de búsqueda derecho.
	 */
	protected BinarySearchTreeADTImpl<T> getRightBST() {
		return (BinarySearchTreeADTImpl<T>) rightSubtree;
	}

	private void setRightBST(BinarySearchTreeADTImpl<T> right) {
		this.rightSubtree = right;
	}

	/**
	 * Árbol BST vacío
	 */
	public BinarySearchTreeADTImpl() {

		setContent(null);

		setLeftBST(null);
		setRightBST(null);
	}

	private BinarySearchTreeADTImpl<T> emptyBST() {
		return new BinarySearchTreeADTImpl<T>();
	}

	/**
	 * Inserta todos los elementos de una colección en el árbol.
	 * 
	 * No se permiten elementos null.
	 * 
	 * @param elements
	 *            valores a insertar.
	 */
	public void insert(Collection<T> elements) {
		for(T i:elements){ 								
			if(i==null){
				throw new IllegalArgumentException("");
			}
		}
		for(T j: elements){
			insert(j);
		}
	}

	/**
	 * Inserta todos los elementos de un array en el árbol.
	 * 
	 * No se permiten elementos null.
	 * 
	 * @param elements elementos a insertar.
	 */
	@SuppressWarnings ("unchecked")//linea amarilla
	public void insert(T ... elements) {

		for(T j: elements){
			insert(j);
		}

	}

	/**
	 * Inserta de forma recursiva (como hoja) un nuevo elemento en el árbol de búsqueda.
	 * 
	 * No se permiten elementos null. Si el elemento ya existe en el árbol NO lo inserta.
	 * 
	 * @param element
	 *            valor a insertar.
	 */
	public void insert(T element) {

		if(isEmpty()){
			setContent(element);
			//hijos vacios
			setLeftBST(emptyBST());
			setRightBST(emptyBST());
		}
		else{
			//compara con el valor de la raiz
			int compara=element.compareTo(content);
			if(compara==0){
				return;//ya estaba arbol 
			}
			if(compara<0){// menor izquierdo
				getLeftBST().insert(element);
			}
			if(compara>0){// derecho
				getRightBST().insert(element);
			}
		}
	}



	/**
	 * Elimina los elementos de la colección del árbol.
	 */
	public void withdraw(Collection<T> elements) {

		for(T i:elements){ 	
			if(i==null){
				throw new IllegalArgumentException("");
			}
		}
		for(T e: elements){
			withdraw(e);
		}
	}

	/**
	 * Elimina los valores en un array del árbol.
	 */

	@SuppressWarnings("unchecked")
	public void withdraw(T ... elements) {
		for(T e: elements){
			withdraw(e);
		}
	}

	/**
	 * Elimina un elemento del árbol.
	 * 
	 * @throws NoSuchElementException si el elemento a eliminar no está en el árbol           
	 */
	public void withdraw(T element) {
		// 	Si el elemento tiene dos hijos, se tomará el criterio de sustituir el elemento por el mayor de sus menores y eliminar el mayor de los menores.
		//     Si el elemento tiene dos hijos, se tomará el criterio de sustituir el elemento por el mayor de sus menores y eliminar el mayor de los menores.
		if (element == null) {
			// Eliminado (no estaba)
			return;
		}
		if(isEmpty())
			throw new NoSuchElementException();

		// Compara con la información en la raíz
		int diff = element.compareTo(getContent());
		if (diff == 0) {
			// Eliminar este nodo
			// Si es una hoja
			if (getLeftBST().isEmpty() && getRightBST().isEmpty()) {
				setContent(null);
				setLeftBST(null);
				setRightBST(null);
			}
			// Si tiene sub-Árbol izquierdo pero no derecho
			else if (!getLeftBST().isEmpty() && getRightBST().isEmpty()) {
				setContent(getLeftBST().getContent());

				setRightBST(getLeftBST().getRightBST());
				setLeftBST(getLeftBST().getLeftBST());
			}
			// Si tiene sub-Árbol derecho pero no izquierdo
			else if (getLeftBST().isEmpty() && !getRightBST().isEmpty()) {
				setContent(getRightBST().getContent());
				setLeftBST(getRightBST().getLeftBST());
				setRightBST(getRightBST().getRightBST());

			}
			// // Si tiene sub-Árbol izquierdo y derecho
			else if (!getLeftBST().isEmpty() && !getRightBST().isEmpty()) {


				T searchMax = getLeftBST().findMax(); 
				content = searchMax;

				getLeftBST().withdraw(searchMax);

			}
		}
		// Si no esta, busco por la rama adecuada
		else {
			if (diff > 0) {
				if(getRightBST().isEmpty())
					throw new NoSuchElementException();
				getRightBST().withdraw(element);
			}

			else {
				if(getLeftBST().isEmpty())
					throw new NoSuchElementException();
				getLeftBST().withdraw(element);
			}
		}
	}

	private T findMax() {

		//    busco con recursividad, bajando por la derecha
		if (!isEmpty()) {
			if (getRightBST().isEmpty()) {
				return getContent();
			} else {
				return getRightBST().findMax();
			}
		} else {
			return null;
		}

	}

	/**
	 * Devuelve el sub-árbol indicado. (para tests)
	 * path será el camino para obtener el sub-arbol. Está formado por 0 y 1.
	 * Si se codifica "bajar por la izquierda" como "0" y
	 * "bajar por la derecha" como "1", el camino desde un 
	 * nodo N hasta un nodo M (en uno de sus sub-árboles) será la
	 * cadena de 0s y 1s que indica cómo llegar desde N hasta M.
	 *
	 * Se define también el camino vacío desde un nodo N hasta
	 * él mismo, como cadena vacía.
	 * 
	 * Si el subarbol no existe lanzará la excepción NoSuchElementException.
	 * 
	 * @param path
	 * @return
	 * @throws NoSuchElementException si el subarbol no existe
	 */
	public BinarySearchTreeADTImpl<T> getSubtreeWithPath(String path) {

		if(path.equals("")){
			return this;
		}
		for(int i=0; i<path.length();i++){
			if(path.charAt(i)!='0' && path.charAt(i)!='1'){
				throw new IllegalArgumentException();
			}
		}
		String aux= path.substring(0, 1);
		path=path.substring(1, path.length());

		if(aux.equals("0")){
			if(leftSubtree.isEmpty()){
				throw new NoSuchElementException();
			}else{
				return getLeftBST().getSubtreeWithPath(path);
			}
		}else{
			if(rightSubtree.isEmpty()){
				throw new NoSuchElementException();
			}else{
				return getRightBST().getSubtreeWithPath(path);
			}
		}
	}	

	/**
	 * Acumula en orden descendente, una lista con los pares 'padre-hijo' en este árbol.
	 * 
	 * Por ejemplo, sea un árbol "A":
	 * 
	 * {10, {5, {2, ∅, ∅}, ∅}, {20, ∅, {30, ∅, ∅}}}
	 * 
	 * 10
	 * |  5
	 * |  |  2
	 * |  |  |  ∅
	 * |  |  |  ∅
	 * |  |  ∅
	 * |  20
	 * |  |  ∅
	 * |  |  30
	 * |  |  |  ∅
	 * |  |  |  ∅
	 * 
	 * el resultado sería una lista de cadenas:
	 * 
	 * 	[(20,30), (10,20), (10,5), (5,2)]
	 * 
	 * y además quedaría etiquetado como:
	 * 
	 *  {10 [(descend, 3)], 
	 *       {5 [(descend, 4)], {2 [(descend, 5)], ∅, ∅}, ∅}, 
	 *       {20 [(descend, 2)], ∅, {30 [(descend, 1)], ∅, ∅}}}
	 * 
	 * @param buffer lista con el resultado.
	 */
	public void parentChildPairsTagDescend(List<String> buffer) {

		if(!rightSubtree.isEmpty()){
			getRightBST().parentChildPairsTagDescend(buffer);
			buffer.add("("+content+","+rightSubtree.content+")");
		}

		if(!leftSubtree.isEmpty()){
			getLeftBST().parentChildPairsTagDescend(buffer);
			buffer.add("("+content+","+leftSubtree.content+")");
		}
		tagDescend();
		//System.out.println(buffer+"bufer");
	}
	private void tagDescend() {
		List<T>lista=new ArrayList<T>();
		lista=recorridodescendente(lista);
		tagDescend(lista.size(), lista);

	}

	private List<T> recorridodescendente(List<T> lista) {
		if(getRightBST() != null){
			getRightBST().recorridodescendente( lista);	
		}
		if(content != null){
			if(!lista.contains(content)){
				//System.out.print(content+" ");
				lista.add(content);
				//System.out.println(lista+"listaaaa");
			}
		}
		if(getLeftBST() != null){
			getLeftBST().recorridodescendente( lista);
		}
		return lista;
	}

	private void tagDescend(int contador, List<T> lista) {
		while(lista.size()>0){
			buscoArbol(lista.get(0), contador, "descend");
			lista.remove(0);
			--contador;
		}

	}

	/**
	 * Importante: Solamente se debe recorrer el árbol una vez
	 * 
	 * Comprueba si los elementos de la lista coinciden con algún camino desde la raiz.
	 * Además, si existe algún camino que coincida con los elementos de la lista, los etiqueta en el árbol,
	 * numerandolos empezando por la raiz como 1.
	 * 
	 * Por ejemplo, el árbol
	 * 
	 * {50, {30, {10, ∅, ∅}, {40, ∅, ∅}}, {80, {60, ∅, ∅}, ∅}}
	 * 
	 * si path = [50, 30, 10]
	 * 
	 * devolvería true y el árbol quedaría así etiquetado:
	 * 
	 * {50 [(path, 1)], {30 [(path, 2)], {10 [(path, 3)], ∅, ∅}, {40, ∅, ∅}}, {80, {60, ∅, ∅}, ∅}}
	 * 
	 *Para el mismo árbol, si path es [50, 40]  devolvería false y el árbol no se etiqueta:
	 * {50, {30, {10, ∅, ∅}, {40, ∅, ∅}}, {80, {60, ∅, ∅}, ∅}}
	 * 
	 * Para el mismo árbol, si path es [50, 80]  devolvería true y el árbol quedaría así etiquetado:
	 *{50 [(path, 1)], {30, {10, ∅, ∅}, {40, ∅, ∅}}, {80 [(path, 2)], {60, ∅, ∅}, ∅}}
	 * 
	 * 
	 * @return  true si los elementos de la lista coinciden con algún camino desde la raiz,  falso si no es así
	 */
	public boolean isPathIn(List<T> path) {
		if(isEmpty()){
			return false;
		}
		boolean isPath=isPathIn(path, 0);
		if(isPath){
			//si es etiqueto
			for(int i=0; i<path.size(); i++){
				buscoArbol(path.get(i), (i+1), "path");
			}
		}

		return isPath;
	}

	private boolean isPathIn(List<T> path, int contador) {
		if(content.equals(path.get(contador))){
			if(contador<path.size()-1){
				if(path.get(contador).compareTo(path.get(contador+1))>0){
					//setTag("path", contador+1);
					return getLeftBST().isPathIn(path, ++contador);
				}
				else if(path.get(contador).compareTo(path.get(contador+1))<0){
					//setTag("path", contador+1);
					return getRightBST().isPathIn(path, ++contador);
				}
				else{
					return false;
				}
			}
			else{//ultimo elemento, solo uno
				//setTag("path", contador+1);
				return true;
			}
		}
		else{
			return false;		
		}
	}

	/**
	 * 
	 * Etiqueta cada nodo con su posición en el recorrido en anchura, con la etiqueta "width"
	 * 
	 *  Por ejemplo, el árbol
	 * 
	 * {50, {30, {10, ∅, ∅}, {40, ∅, ∅}}, {80, {60, ∅, ∅}, ∅}}
	 * 
	 *  queda etiquetado como 
	 * 
	 *   {50 [(width, 1)], 
                 {30 [(width, 2)], {10 [(width, 4)], ∅, ∅},{40 [(width, 5)], ∅, ∅}},
                 {80 [(width, 3)], {60 [(width, 6)], ∅, ∅}, ∅}}
	 * 

	 */	
	public void tagWidth(){
		tagWith(size());
		//	TODO Implementar método
	}


	private void tagWith(int tamaño) {
		// TODO Apéndice de método generado automáticamente
		Stack<T> pila=(Stack<T>) recorridoAnchura();

		while(!pila.isEmpty()){
			buscoArbol(pila.pop(), tamaño, "width");
			--tamaño;
		}
	}


	private void buscoArbol(T pop, int tamaño, String etiqueta) {
		// TODO Apéndice de método generado automáticamente
		if(content.compareTo(pop)==0){
			setTag(etiqueta, tamaño);
		}else if(content.compareTo(pop)>0){
			getLeftBST().buscoArbol(pop, tamaño, etiqueta);
		}else{
			getRightBST().buscoArbol(pop, tamaño, etiqueta);
		}		
	}

	List<T> recorridoAnchura() {
		List<T> lista =new Stack<T>();
		Queue<TreeADT<T>> queue=new LinkedList<TreeADT<T>>();
		TreeADT<T> arbolaux=new BinarySearchTreeADTImpl<T>();
		//encolo el arbol general
		queue.add(this);
		//si quedan arboles sin recorrer
		while(!queue.isEmpty()){
			//saca de la cola el siguiente que procesa
			arbolaux=queue.poll();
			//si no es vacio, visita el nodo y lo encola los subarboles
			if(!arbolaux.isEmpty()){
				T addContenido= arbolaux.getContent();
				lista.add(addContenido);
				//los sub arboles quedan pendietes de ser visitados 
				for(int i=0; i<arbolaux.getMaxDegree();i++){
					queue.add(arbolaux.getSubtree(i));
				}
			}
		}
		return lista;
	}

	private int size() {
		if(!isEmpty()){
			return 1+getLeftBST().size()+getRightBST().size();
		}
		return 0;
	}

	/**	
	 * Devuelve un iterador que recorre los elementos del arbol en inorden (de menor a mayor)
	 * 
	 * Por ejemplo, con el árbol
	 * 
	 * 		{50, {30, {10, ∅, ∅}, {40, ∅, ∅}}, {80, {60, ∅, ∅}, ∅}}
	 * 
	 * y devolvería el iterador que recorrería los ndos en el orden: 10, 30, 40, 50, 60, 80
	 * 
	 * 		
	 * 
	 * @return iterador para el recorrido inorden o ascendente
	 */
	public Iterator<T> iteratorInorden() {
		return new iteratorInorderImp();
	}	
	private class iteratorInorderImp implements Iterator<T> {
		List<T> lista=new ArrayList<T>();
		int tamaño;

		public iteratorInorderImp(){
			this.lista= recorridoInOrden(lista);
			tamaño=0;
			//System.out.println(lista);
		}
		@Override
		public boolean hasNext(){
			if(tamaño<lista.size()){
				return true;
			}
			return false;
		}
		@Override
		public T next(){
			T element=lista.get(tamaño);
			tamaño++;
			return element;
		}
		@Override
		public void remove(){
			throw new UnsupportedOperationException();
		}
	}

	List<T> recorridoInOrden(List<T> lista){
		if(getLeftBST() != null){
			getLeftBST().recorridoInOrden( lista);

		}
		if(content != null){
			if(!lista.contains(content)){
				//System.out.print(content+" ");
				lista.add(content);
				//System.out.println(lista+"listaaaa");
			}

		}
		if(getRightBST() != null){
			getRightBST().recorridoInOrden(lista);
		}
		return lista;
	}

}

