package ule.edi.doubleList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedListImpl<T> implements DoubleLinkedList<T> {
	/**
	 * Nodo doblemente enlazado.
	 * 
	 * Como es estática, no tiene en ámbito el parámetro 'T' de la
	 * clase que la contiene. El parámetro 'D' será sustituído por
	 * un tipo particular cuando se use el nodo, por ejemplo:
	 * 
	 * 		DoubleNode<T> cab;
	 * 
	 * en la lista.
	 *
	 * @param <D>
	 */
	static class DoubleNode<D> {

		DoubleNode(D element) {
			this.content = element;
			this.previous = null;
			this.next = null;
		}
		
		//	dato a almacenar en el nodo
		D content;
		
		DoubleNode<D> previous;
		
		DoubleNode<D> next;
	}

	/**
	 * Apunta al nodo cabecera; siempre habrá un nodo vacío (sin elemento) que actua de cabecera
	 *  OJO!!! ESTE NODO CABECERA DEBERÁ CREARSE EN CADA CONSTRUCTOR DE LA LISTA
	 */
	private DoubleNode<T> cab;
	
	
	
	//////////////////////
	/////  CONSTRUCTORES
	//////////////////////
	
	
	/**
	 * Construye una lista vacía.
	 */
	public DoubleLinkedListImpl() {
		
		cab=null;
		
		//TODO
		// Deberá crear el nodo cabecera vacío
	
	}
	
	/**
	 * Construye una lista con los elementos dados.
	 * 
	 * Java creará un array 'elements' con los dados en la
	 * llamada al constructor; por ejemplo:
	 * 
	 * 	x = new DoubleLinkedList<String>("A", "B", "C");
	 * 
	 * ejecuta este método con un array [A, B, C] en 
	 * 'elements'.
	 * 
	 * @param elements
	 */
	
	public DoubleLinkedListImpl(T ... elements) {
		
		for(T lista:elements){
			addLast(lista);
		}
		//TODO
	}
	
	/**
	 * Construye una lista a partir de otra.
	 * 
	 * Las listas tienen nodos independientes, con los mismos
	 * contenidos.
	 */
	public DoubleLinkedListImpl(DoubleLinkedList<T> other) {
		/*DoubleNode<T> cabecera= other.cab;
		
		while(cabecera!=null){
			addLast(cabecera.content);
			cabecera=cabecera.next;
			
		}*/
		
		//TODO
	}
	
	//////////////////////
	/////  ITERADORES
	//////////////////////
	
	private class ForwardIterator implements Iterator<T> {

		private DoubleNode<T> at ;
		
		@Override
		public boolean hasNext() {
			return false;
			// TODO Auto-generated method stub
			
		}

		@Override
		public T next() {
			return null;
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}
	};
	
	
	private class reverseIterator implements Iterator<T> {

		private DoubleNode<T> at ;
		
		@Override
		public boolean hasNext() {
			return false;
			// TODO Auto-generated method stub
			
		}

		@Override
		public T next() {
			return null;
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}
	};

		private class OddAndEvenIterator implements Iterator<T> {

		// Definir los atributos necesarios para implementar el iterador
		
		public OddAndEvenIterator(){
			
		}
		
		@Override
		public boolean hasNext() {
			return false;
			// TODO Auto-generated method stub
			
		}

		@Override
		public T next() {
			return null;
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}
	};
	
		
	
	
	////// FIN DE ITERADORES ///////
	////////////////////////////////
	
	
	@Override
	public boolean isEmpty() {
		if(size()==0){
			return true;
		}
		return false;
		// TODO Auto-generated method stub	
	}

	@Override
	public int size() {
		int tamaño=0;
		DoubleNode<T> cabecera=cab;
		while(cabecera!=null){
			cabecera=cabecera.next;
			tamaño++;
		}
		// TODO Auto-generated method stub
		return tamaño;
	}
	
	@Override
	public void addFirst(T element) {
		DoubleNode<T> elementoNuevo=new DoubleNode<T>(element);
		DoubleNode<T> cabecera=cab;
		if(cabecera==cabecera.next){
			cabecera.next=elementoNuevo;
			cabecera.previous=elementoNuevo;
			System.out.println(elementoNuevo.content+"principio1");
		}
		else{
			elementoNuevo.next=cabecera.next;
			cabecera.previous=elementoNuevo;
			cabecera=elementoNuevo;
			System.out.println(elementoNuevo.content+"principio2");
		}
		// TODO Auto-generated method stub
	}

	@Override
	public void addLast(T element) {
		DoubleNode<T> elementoNuevofin=new DoubleNode<T>(element);
		DoubleNode<T> cabecera=cab;
		if(cabecera==cabecera.next){
			cabecera.next=elementoNuevofin;
			cabecera.previous=elementoNuevofin;
			System.out.println(elementoNuevofin+"contenido del final1");
		}
		else{
			cabecera.previous=elementoNuevofin;
			elementoNuevofin.previous=cabecera.previous;
			cabecera.previous=elementoNuevofin;
			System.out.println(elementoNuevofin+"contenido del final2");
		}
		// TODO Auto-generated method stub
	}

	@Override
	public void addAtPos(T element, int p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNTimes(T element, int n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T getElem(int p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setElem(T elem, int p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int indexOf(T elem) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int indexOf(T elem, int p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public T removeFirst(T elem) throws EmptyCollectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T removeAll(T elem) throws EmptyCollectionException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public T removeLast() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

	@Override
	public void reverse() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int isSubList(DoubleLinkedList<T> part) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void interlace(DoubleLinkedList<T> other) {
		// TODO Auto-generated method stub
		
	}	
	
	@Override
	public String toString() {
		
		if (cab != cab.next) {
			StringBuffer rx = new StringBuffer();
			rx.append("[");
			DoubleNode<T> i = cab.next;
			while (i != cab) {
				rx.append(i.content);
				rx.append(", ");
				i = i.next;
			}
			rx.delete(rx.length() - 2, rx.length());
			rx.append("]");
			
			return rx.toString();
		} else {
			return "[]";
		}
	}

	

	///////////////////////////////////////////
	  // métodos que devuelve iteradores
	 ///////////////////////////////////////
	@Override
	public Iterator<T> oddAndEvenIterator() {
		return null;
		// TODO Auto-generated method stub
		
		
	}

	 @Override
		public Iterator<T> iterator() {
	    	return new ForwardIterator();
			
		}

		@Override
		public Iterator<T> reverseIterator() {
			// TODO Auto-generated method stub
			return null;
		}

		
	
}
