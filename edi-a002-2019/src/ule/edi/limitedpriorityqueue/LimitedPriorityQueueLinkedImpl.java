package ule.edi.limitedpriorityqueue;

import java.util.Comparator;
import java.util.PriorityQueue;


public class LimitedPriorityQueueLinkedImpl<T> implements LimitedPriorityQueue<T> {
	private int capacity;

	private QueueNode<T> first;
	private int count;


	private static class QueueNode<E> {

		public QueueNode(int priority, E content) {
			this.priority = priority;
			this.content = content;
			this.next = null;
		}

		public int priority;

		public E content;

		public QueueNode<E> next;
	};
	public LimitedPriorityQueueLinkedImpl(int capacity) {
		this.capacity=capacity;	
	}
	@Override
	public int getCapacity() {
		return capacity;
	}
	@Override
	public int getSize() {
		return count ;
	}
	@Override
	public boolean isFull() {
		if(capacity==count){
			return true;
		}
		return false;
	}
	@Override
	public T enqueue(int p, T element) {
		//Este metodo me ha dado varios priblemas al encolar con prioridad, con lo que no esta completamente realizado.
		QueueNode <T> elementNuevo= new QueueNode<T>(p, element);
		QueueNode <T> elementEliminado= new QueueNode<T>(p, element);
		QueueNode <T> cabecera= new QueueNode<T>(p, element);
		cabecera=first;

		if(p<0){
			throw new IllegalArgumentException();
		}
		if(element == null) {
			throw new NullPointerException();
		}
		if(isEmpty()){
			elementNuevo.next=null;
			first= elementNuevo;	
		}
		if(!isEmpty()){
			while(cabecera.next==null){
				if(cabecera.priority<elementNuevo.priority){ 
					elementNuevo.next=null;
					cabecera.next=elementNuevo;	
					break;
				}else{
					elementNuevo.next=first;
					first=elementNuevo;
					break;
				}
			}
			cabecera=cabecera.next;
		} 
		if(isFull()){
			//aqui deberia primero meter en la lista de prioridad elementeos y luego recorrerla y sacar el de menor prioridad que mas tiempo lleve.

		}
		count++;

		return null;
	}
	@Override
	public T first() throws EmptyCollectionException {
		if(first==null){
			throw new EmptyCollectionException("la cola esta vacia");
		}else{
			return first.content;
		}
	}
	@Override
	public T dequeue() throws EmptyCollectionException {
		QueueNode <T> elementprimeroborrado=first;
		if(first==null){
			throw new EmptyCollectionException("la cola esta vacia");
		}else{
			elementprimeroborrado.content=first.content;
			first=first.next;
			return elementprimeroborrado.content;
		}
	}

	@Override
	public boolean isEmpty() {
		if(getSize()==0){
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		if (! this.isEmpty()) {
			StringBuffer rx = new StringBuffer();
			rx.append("[");
			QueueNode<T> actual =first;

			while (actual != null){
				rx.append("( Priority:" +actual.priority +" (");
				rx.append(actual.content.toString());
				rx.append(")), ");
				actual=actual.next;
			}

			rx.delete(rx.length() - 2, rx.length());
			// TODO : MOSTRAR LOS ELEMENTOS DE LA COLA DE PRIORIDAD CON EL MISMO FORMATO QUE LA OTRA IMPLEMENTACIÓN
			rx.append("]");
			return rx.toString();
		} else {
			return "[]";
		}
	}
}
