package ule.edi.limitedpriorityqueue;

public class LinkedQueue<T> implements QueueADT<T> {

	protected static class Node<D> {
		D element;
		Node<D> next;

		Node() {
			this.element = null;
			this.next = null;
		}
		Node(D element) {
			this.element = element;
			this.next = null;
		}
	}

	private int count;
	private Node<T> front, rear; 

	public LinkedQueue(){
		count=0;
		// TODO Auto-generated method stub
	} 

	@Override
	public void enqueue(T element) {
		Node<T> elemenNew= new Node<T>(element);
		Node<T> cabecera=front;

		if(element==null){
			throw new NullPointerException("El elemento es null");
		}
		if(front==rear){
			front=elemenNew;
			elemenNew.next=rear;
			return;	
		}
		while(cabecera!=rear){
			if(cabecera.next==rear){
				elemenNew.next=rear;
				cabecera.next=elemenNew;
				break;
			}
			cabecera=cabecera.next;
		}	 
	} 
	@Override
	public T dequeue() throws EmptyCollectionException{
		Node<T> elemenRemove= new Node<T>();	 
		if(front==null){			
			throw new EmptyCollectionException("La cola esta vacia");	
		}
		elemenRemove.element=front.element;
		front=front.next;	
		return elemenRemove.element;
	} 
	@Override
	public T first()  throws EmptyCollectionException{
		Node<T> elementoMostrar = new Node<T>();
		if(front==null){	
			throw new EmptyCollectionException("La cola esta vacia");
		}
		elementoMostrar.element= front.element;	
		return elementoMostrar.element;		

	}

	@Override 
	public boolean isEmpty() {
		if(size()==0){
			return true;	
		}
		return false;
	}

	@Override
	public int size() {
		count = 0;
		Node<T> aux = new Node<T>();
		aux=front;
		while (aux!=rear){	
			aux=aux.next;
			count++;
		}
		return count;
	}
	@Override
	public T dequeueLast() throws EmptyCollectionException {
		Node<T> finalEliminar = new Node<T>();
		Node<T> cabeza = front;

		if(isEmpty()){
			throw new EmptyCollectionException("La cola esta vacia");
		}
		if(size()==1){
			finalEliminar.element=cabeza.element ;
			front=rear;
			return finalEliminar.element;
		}
		if(size()==2){

			finalEliminar.element=cabeza.next.element ;
			cabeza.next=rear;
			return finalEliminar.element;
		}
		while(cabeza !=rear){
			if(cabeza.next.next == rear){
				finalEliminar.element=cabeza.next.element ;
				cabeza.next = rear; 
			}
			cabeza=cabeza.next; 
			//System.out.println(cabeza.element+"eliminarhgchfgchfgxcfinal");
		}
		return finalEliminar.element;
	}

	@Override 
	public String toString() {
		if (! this.isEmpty()) {
			StringBuffer rx = new StringBuffer();
			Node<T> actual=front;
			while (actual!=null) {
				rx.append(actual.element.toString());
				rx.append(", ");
				actual=actual.next;
			}
			rx.delete(rx.length() - 2, rx.length());
			return rx.toString();
		}
		return ""; 


	};


}
