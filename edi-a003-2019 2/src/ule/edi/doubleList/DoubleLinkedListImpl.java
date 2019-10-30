package ule.edi.doubleList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.swing.text.AbstractDocument.ElementEdit;

//import ule.edi.doubleList.DoubleLinkedListImpl.DoubleNode;

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
		cab= new DoubleNode<T> (null);
		cab.content=null;
		cab.next=cab;
		cab.previous=cab;

		
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
		cab = new DoubleNode<T>(null);
		cab.next = cab;
		cab.previous=cab;
		ArrayList<T> listaElementosDados = new ArrayList<T>();
		for (int i = 0; i < elements.length; i++) {
			addLast(elements[i]);
		}
		
	}

	/**
	 * Construye una lista a partir de otra.
	 * 
	 * Las listas tienen nodos independientes, con los mismos
	 * contenidos.
	 */
	public DoubleLinkedListImpl(DoubleLinkedList<T> other) {
		cab = new DoubleNode<T>(null);
		cab.next = cab;
		cab.previous=cab;
		T elemento=null;
		int posicion=1;
		//ArrayList<T> listadeotralista = new ArrayList<T>();
		for(int i=1; i<=other.size();i++){
			elemento=other.getElem(i);
			addAtPos(elemento, posicion);	
			posicion++;	
		}
	}



	//////////////////////
	/////  ITERADORES
	//////////////////////

	private class ForwardIterator implements Iterator<T> {

		private DoubleNode<T> at=cab.next;

		@Override
		public boolean hasNext() {
			if(at!=cab){
				return true;
			}else{
				return false;
			}
			// TODO Auto-generated method stub
		}
		@Override
		public T next() {
			T cabecera;
			if(!hasNext()){
				throw new NoSuchElementException("no mas");
			}else{
				cabecera=at.content;
				at=at.next;
			}
			return cabecera;
			// TODO Auto-generated method stub
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}
	};


	private class reverseIterator implements Iterator<T> {

		private DoubleNode<T> at=cab.previous;
		@Override
		public boolean hasNext() {
			if(at!=cab){
				return true;
			}else{
				return false;
			}
			// TODO Auto-generated method stub
		}
		@Override
		public T next() {
			T cabecera;
			if(!hasNext()){
				throw new NoSuchElementException("no mas");
			}else{
				
				cabecera=at.content;
				
				at=at.previous.previous;
			}
			//System.out.println(cabecera+"iterador2");
			return cabecera;
			// TODO Auto-generated method stub
		}
		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}
	};

	private class OddAndEvenIterator implements Iterator<T> {
		private DoubleNode<T> at;
		// Definir los atributos necesarios para implementar el iterador

		public OddAndEvenIterator(){
			at=cab;
			
			if(cab!=cab.next){
				at=at.next.next;
			}
		}

		@Override
		public boolean hasNext() {
			if(at!=cab){
				return true;
			}else{
				return false;
			}
			// TODO Auto-generated method stub
		}

		@Override
		public T next() {
			if(!hasNext()){
				throw new NoSuchElementException("no mas");
			}
			T cabecera=null;
			cabecera= at.content;
			at=at.next;
			if(at!=at.next){
				at=at.next;
			}
			if(at.next==cab){
				at=at.next.next;
			}
			return cabecera;
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
		DoubleNode<T> aux= cab.next;
		while(cab!=aux){
			aux=aux.next;
			tamaño++;
		}
		//System.out.println(tamaño+"eduardocontado");
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
			elementoNuevo.next=cabecera;
			elementoNuevo.previous=cabecera;
			//System.out.println(toString()+"principio1");	
		}else if(cabecera.next.next==cabecera){
			cabecera.next=elementoNuevo;
			cabecera.previous.previous=elementoNuevo;
			elementoNuevo.next=cabecera.previous;
			elementoNuevo.previous=cabecera;
			//System.out.println(toString()+"principio2");
		}
		else if(cabecera.next.next!=cabecera){
			cabecera.next.previous=elementoNuevo;
			elementoNuevo.previous=cabecera;
			elementoNuevo.next= cabecera.next;
			cabecera.next= elementoNuevo;
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
			elementoNuevofin.next=cabecera;
			elementoNuevofin.previous=cabecera;
			//System.out.println(elementoNuevofin.content+"  contenido del final1");
		}
		else if(cabecera.next.next==cabecera){
			cabecera.previous=elementoNuevofin;
			cabecera.next.next=elementoNuevofin;
			elementoNuevofin.next=cabecera;	
			//System.out.println(elementoNuevofin.content+"   contenido del final2");
		}
		if(cabecera.next.next!=cabecera){
			cabecera.previous.next=elementoNuevofin;
			elementoNuevofin.next=cabecera;
			cabecera.previous=elementoNuevofin;
			elementoNuevofin.previous=cabecera.previous;

		}
		// TODO Auto-generated method stub
	}
	@Override
	public void addAtPos(T element, int p) {
		DoubleNode<T> elementoNuevopos=new DoubleNode<T>(element);
		DoubleNode<T> cabecera=cab;
		if(size()<=p){
			addLast(element);
		}
		else{
			if(p==1){
				addFirst(element); 
			}
			else{
				for (int i = 1 ; i <= p-1; i++){
					cabecera = cabecera.next;	
				}
				cabecera.next.previous= elementoNuevopos;
				elementoNuevopos.next = cabecera.next;
				cabecera.next=elementoNuevopos;
				elementoNuevopos.previous=cabecera;
				//System.out.println("addAtPos: "+elementoNuevopos.content);
			}
		}
		// TODO Auto-generated method stub
	}

	@Override
	public void addNTimes(T element, int n) {
		for(int i=0;i<n;i++){
			addLast(element);
		}
		// TODO Auto-generated method stub
	}

	@Override
	public T getElem(int p) {
		DoubleNode<T> cabecera=cab;
		T elementoPos = null;
		if(0>p&&p<size()){
			throw new IndexOutOfBoundsException();
		}
		if(p<=size()){
			for (int i = 1 ; i <= p ; i++){
				cabecera = cabecera.next;
			} 
			elementoPos=cabecera.content;
		}
		return elementoPos;
	}

	@Override
	public void setElem(T elem, int p) {
		DoubleNode<T> elementocambiarpos=new DoubleNode<T>(elem);
		DoubleNode<T> cabecera=cab.next;
		if(p<=size()){
			for(int i=1; i<p;i++){
				cabecera=cabecera.next;
			}
			cabecera.content=elementocambiarpos.content;
			//System.out.println("elementocambiarintercambio  "+elementocambiarpos.content);


		}else{
			throw new IndexOutOfBoundsException();
		}
		// TODO Auto-generated method stub
	}


	@Override
	public int indexOf(T elem) {
		DoubleNode<T> elementobuscar=new DoubleNode<T>(elem);
		DoubleNode<T> cabecera=cab;
		int posicion=0;
		if(elem!=null){
			for(int i=1;i<=size();i++){
				if(elementobuscar.content!=cabecera.content){
					cabecera=cabecera.next;
					posicion=posicion+1;
				}
			}
		}
		
		// TODO Auto-generated method stub
		return posicion;
		
	}

	@Override
	public int indexOf(T elem, int p) {
		
		DoubleNode<T> elementobuscar=new DoubleNode<T>(elem);
		DoubleNode<T> cabecera=cab;
		int posicion=0;
		if(p>size()){
			throw new IndexOutOfBoundsException();	
		}
		if(elem!=null){
			for(int i=1;i<=size();i++){
				if(elementobuscar.content!=cabecera.content){
					cabecera=cabecera.next;
					posicion=posicion+1;
					
				}
			}
		}
		// TODO Auto-generated method stub
		return posicion-p+1;	
	}

	@Override
	public T removeFirst(T elem) throws EmptyCollectionException {
		DoubleNode<T> cabecera=cab;
		DoubleNode<T> elementoeliminar=null;
		T eliminado=null;
		
		if(isEmpty()){
			throw new EmptyCollectionException("Lista vacia");
		}else{
			while(cabecera.next!=cabecera&&cabecera.content!=elem){
				elementoeliminar=cabecera;
	            cabecera=cabecera.next;   
	            eliminado=cabecera.content;
			}
			
	             elementoeliminar.next=cabecera.next;
	            // cabecera=null;
			
	}
	//System.out.println(eliminado+" :elementoeliminado");
	return eliminado;
}

	
	@Override
	public T removeAll(T elem) throws EmptyCollectionException {
		//DoubleNode<T> cabecera=cab;
		//DoubleNode<T> elementoeliminar=null;
		T eliminado=null;
		if(isEmpty()){
			throw new EmptyCollectionException("Lista vacia");
		}else{
			for(int i=1;i<=size();i++){
				eliminado=removeFirst(elem);
				
			}
		}
		//System.out.println(eliminado+"elleeeeeeeeeeee");
		return eliminado;
		
	}
	@Override
	public T removeLast()  throws EmptyCollectionException{
		DoubleNode<T> cabecera=cab;
		DoubleNode<T> ultimoEliminado=null;
		T elementoUltimoEliminado=null;
		int ultimap=size();
		int contador=1;
		if(isEmpty()){
			throw new EmptyCollectionException("lista vacia");
		}
		
		else{
			while((cabecera.next!=cab)&&contador<ultimap){
				contador++;
				cabecera=cabecera.next;
			}
			ultimoEliminado=cabecera;
            cabecera=cabecera.next;   
            elementoUltimoEliminado=cabecera.content;
		}
		ultimoEliminado.next=cabecera.next;
			
				
				
		
		return elementoUltimoEliminado;
	}

	@Override
	public void reverse() {		
		DoubleNode<T> cabecera=cab;
		DoubleNode<T> elementosiguiente=null;
		int contador=size();
		int aux=size();
			while(contador>=0){
				elementosiguiente=cabecera;
				cabecera=cabecera.next;
				addFirst(elementosiguiente.content);
				contador--;
			}
			while(contador<=(aux-1)){
				try {
					removeLast();
				} catch (EmptyCollectionException e) {
					// TODO Bloque catch generado automáticamente
					e.printStackTrace();
				}
				contador++;
			}
		// TODO Auto-generated method stub
	}

	@Override
	public int isSubList(DoubleLinkedList<T> part) {
		DoubleNode<T> cabecera=cab.next;
		int contadorpart=part.size();
		int contadorpart2=1;
		int posicion=1;
		T elementopart=null;
		DoubleNode<T> elementolista=null;
		while(contadorpart2<=contadorpart){
			elementopart=part.getElem(contadorpart2);
			//System.out.println(elementopart+"elemenpart");
			elementolista=cabecera;
			//System.out.println(elementolista.content+"  elemenlista");
			if(elementolista.content!=elementopart){
				contadorpart2++;
				//System.out.println(elementolista.content+"  "+elementopart+"  nosublist");
				posicion=-1;
			}else{
				//System.out.println(elementolista.content+"  "+elementopart+"  SIes");
				cabecera=cabecera.next;
				//System.out.println(cabecera.content+"cabeceraasdfvsdfvfv");
			}
			contadorpart2++;
		}
		//System.out.println(posicion+"posicion");
		return posicion;
	}

	@Override
	public void interlace(DoubleLinkedList<T> other) {
		T elementodetras=null;
		int posicion=1;
		
		for(int i=1; i<=other.size();i++){
			elementodetras=other.getElem(i);
			//System.out.println(posicion*2+"posicioo");
			addAtPos(elementodetras, posicion*2);	
			posicion++;	
		}
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
		OddAndEvenIterator pareseimpares=new OddAndEvenIterator();
		return pareseimpares;
		
		// TODO Auto-generated method stub


	}

	@Override
	public Iterator<T> iterator() {
		
		return new ForwardIterator();

	}

	@Override
	public Iterator<T> reverseIterator() {
		 reverseIterator reverse=new reverseIterator();
		return reverse;
		// TODO Auto-generated method stub
		
	}



}
