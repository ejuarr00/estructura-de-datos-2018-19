package ule.edi.SimpleList;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;



public class SingleLinkedListImpl<T> extends AbstractSingleLinkedListImpl<T> {

	public SingleLinkedListImpl(T ... elements) {
		//constructor no recursivo
		/*for(T vi: elements){
			addLast(vi);
		}*/
		// IMPLEMENTAR DE FORMA RECURSIVA 


		constructorre(elements, 0);
	}
	private void constructorre(T[] elements, int posicion){
		if(elements.length!=posicion){
			addLast(elements[posicion]);
			constructorre(elements, ++posicion);
		}
	}

	//constructor vacio
	/*public SingleLinkedListImpl() {
		// TODO Apéndice de constructor generado automáticamente
		header=null;
	}*/

	@Override
	public void addLast(T element) {
		if(header==null){
			addFirst(element);
		}else{
			addLastRe(header,element);
		}
		// TODO Auto-generated method stub
	}
	private void addLastRe(Node<T> cabecera, T element){
		if(cabecera.next==null){
			Node<T> elementofinalre=new Node<T>(element);
			cabecera.next=elementofinalre;
		}else{
			addLastRe(cabecera.next, element);
		}	
	}

	@Override
	public Iterator<T> iterator() {
		return new iteradorImpl();
	}
	private class iteradorImpl implements Iterator<T>{
		Node<T> actual=header;

		@Override
		public boolean hasNext(){
			return (actual!=null);
		}
		@Override
		public T next(){
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			T elementodevolver=actual.content;
			actual=actual.next;
			return elementodevolver;
		}
		@Override
		public void remove(){
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (size()==0);
	}
	@Override
	public int size() {
		if(header==null){
			return 0;
		}else{
			return sizeRe(header);
		}
		// TODO Auto-generated method stub
	}
	private int sizeRe(Node<T> cabecera){
		if(cabecera==null){
			return 0;
		}else{
			return 1 + sizeRe(cabecera.next);
		}	
	}
	@Override
	public void addFirst(T element) {
		Node<T> elementoprincipio=new  Node<T>(element);
		if(isEmpty()){
			header=elementoprincipio;
		}else{
			elementoprincipio.next=header;
			header=elementoprincipio;
		}
		// TODO Auto-generated method stub
	}
	@Override
	public void addAtPos(T element, int p) {
		//Node<T>elementoposicion=new Node<T>(element);
		if(p<1){
			throw new IllegalArgumentException();
		}
		else if(p==1){
			addFirst(element);
		}

		else if(p>=size()){	
			addLast(element);	
		}
		else{
			addAtPossRe(p-1, element, header);
		}
		// TODO Auto-generated method stub
	}
	private void addAtPossRe(int p, T element, Node<T>cabecera){
		Node<T> elementoposicion= new Node<T>(element);
		Node<T> elementodelante= new Node<T>(element);
		Node<T> elementodetras= new Node<T>(element);

		if(p==1){
			elementodelante=cabecera;
			elementodetras=cabecera.next;
			elementodelante.next=elementoposicion;
			elementoposicion.next=elementodetras;
		}
		else{
			addAtPossRe(--p, element, cabecera.next);

		}
	}
	@Override
	public void addNTimes(T element, int n) {
		if(n<0){
			throw new IllegalArgumentException();
		}
		if(n>0){
			addLast(element);
			addNTimes(element, --n);
		}
	}

	@Override
	public int indexOf(T elem) {
		int posicion=indexOfRe(header, elem, -1, 1);
		if(posicion==-1){
			throw new NoSuchElementException();
		}
		//System.out.println(posicion+"indexof");
		return posicion;


		// TODO Auto-generated method stub
	}
	private int indexOfRe(Node<T> indice, T elem, int posicion, int contador) {
		if(indice==null||posicion!=-1){//quitando el posiccion funcionaria
			return posicion;
		}
		else{
			if(indice.content.equals(elem)){
				posicion=contador;
				//return posicion;
			}
			return indexOfRe(indice.next,elem,posicion,++contador);
		}
	}

	@Override
	public T removeLast() throws EmptyCollectionException {
		T elementodevuelto=null;
		if(isEmpty()){
			throw new EmptyCollectionException("");
		}
		if(header.next==null){
			elementodevuelto= header.content;
			header=null;
			return elementodevuelto;
		}
		// TODO Auto-generated method stub
		return removeLastRe(header);
	}
	private T removeLastRe(Node<T> indice){
		T elementodevuelto=null;
		if(indice.next.next==null){
			elementodevuelto=indice.next.content;
			indice.next=null;
			return elementodevuelto;
		}
		return removeLastRe(indice.next);
	}


	@Override
	public T removeLast(T elem) throws EmptyCollectionException {
		T elementoultimaaparicion=null;
		if(isEmpty()){
			throw new EmptyCollectionException("");
		}
		if(header.next==null&& header.content.equals(elem)){
			elementoultimaaparicion=header.content;
			header=null;
			return elementoultimaaparicion;
		}
		int posicion= indexLast(header, elem, -1, 1);
		if(posicion==-1){
			throw new NoSuchElementException();
		}
		if(posicion==1){
			header=header.next;
			return elem;
		}
		return removeLastRe2(header, elem, 1, posicion);

		// TODO Auto-generated method stub
	}

	private int indexLast(Node<T> indice, T elem, int posicion, int contador){
		if(indice==null){
			return posicion;
		}
		if(indice.content.equals(elem)){
			posicion=contador;
		}
		return(indexLast(indice.next, elem, posicion, ++contador));
	}

	private T removeLastRe2(Node<T> cabecera, T elem, int contador, int posicion){

		if(contador==posicion-1){
			//T elementoeliminar=cabecera.content;
			cabecera.next= cabecera.next.next;

			return elem;

		}

		return removeLastRe2(cabecera.next, elem, ++contador, posicion);
	}

	@Override
	public AbstractSingleLinkedListImpl<T> reverse() {
		AbstractSingleLinkedListImpl<T> listaVacia= new SingleLinkedListImpl<T>();
		return reverseRe(header, listaVacia);
		// TODO Auto-generated method stub
	}
	private AbstractSingleLinkedListImpl<T> reverseRe(Node<T> indice, AbstractSingleLinkedListImpl<T> listaVacia){
		if(indice==null){
			return listaVacia;
		}
		else{
			listaVacia.addFirst(indice.content);
			return reverseRe(indice.next, listaVacia);
		}
	}

	@Override
	public int isSubList(AbstractSingleLinkedListImpl<T> part) {
		int contador=1;
		int posicion=-1;
		boolean coincide=false;
		Node <T> indice=header;
		Node<T> indicepart=part.header;
		if(part.header==null){
			return 1;
		}
		if(header==null||size()<part.size()){
			return -1;
		}
		return isSubListRe(header, part.header,part.header,contador,posicion, coincide);

		// TODO Auto-generated method stub
	}
	private int isSubListRe(Node<T> indice, Node<T> parteInicio, Node<T> indicePart, int contador, int posicion, boolean igual){
		if(indice!=null&& indicePart!=null){
			if(indice.content.equals(indicePart.content)){
				if(!igual){
					posicion=contador;
				}
				indice=indice.next;
				indicePart=indicePart.next;
				igual=true;
				contador++;

			}
			else{
				if(!igual){
					indice=indice.next;
					contador++;
				}
				posicion=-1;
				indicePart=parteInicio;
				igual=false;
			}
			return isSubListRe(indice, parteInicio, indicePart, contador, posicion, igual);
		}
		if(indice==null&&indicePart!=null){
			posicion=-1;

		}
		return posicion;
	}

}
