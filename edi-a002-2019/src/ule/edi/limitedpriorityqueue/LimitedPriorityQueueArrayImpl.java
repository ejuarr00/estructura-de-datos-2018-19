package ule.edi.limitedpriorityqueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import ule.edi.limitedpriorityqueue.LinkedQueue.Node;




public class LimitedPriorityQueueArrayImpl<T> implements LimitedPriorityQueue<T> {
	    private int capacity;
	    private int npriorities;
	    private int count;

	    private ArrayList<LinkedQueue<T>> colas;
	
	

	public LimitedPriorityQueueArrayImpl(int capacity, int npriorities) {
		ArrayList<LinkedQueue<T>> LimitedPriority = new ArrayList<LinkedQueue<T>>();
		
		this.capacity= capacity;
		this.npriorities=npriorities;
		//this.count= count;
		colas=new  ArrayList<LinkedQueue<T>>();
		for(int i=0; i<npriorities; i++){
			colas.add(new LinkedQueue<T>());
		}
		
      //TODO  asignar los valores de los atributos
	  // Crear el arrayList, y añadir una cola por cada una de las prioridades (1..npriorities)
	  // Si capacidad <=0 disparar la excepción: IllegalArgumentException
		
	}
		
    @Override
    public int getCapacity() {
		return capacity;	
    }
    @Override
    public int getSize() {  
    	int tamaño=0;
    	for(int a=0; a<colas.size();a++){
    		tamaño=tamaño+colas.get(a).size();
   		} 
    	return tamaño;
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
		if(element == null) {
			throw new NullPointerException();
		}
		if(p<=0||p>npriorities){
			throw new IllegalArgumentException();
		}
		if(isFull()){
			colas.get(p-1).enqueue(element);
			for(int a=npriorities;a>=0; a--){
				if(colas.get(a-1)!=null){
					try{
						return colas.get(a-1).dequeueLast();
					}catch (EmptyCollectionException e) {
						// TODO: handle exception
						e.printStackTrace();
					} 
				}
			} 
		}
		else{ 
			colas.get(p-1).enqueue(element);
			//System.out.println(colas+"colasssssssssssssssss");
			count++;
		}
		return null;
	} 


	@Override
	public T first() throws EmptyCollectionException {
		if(isEmpty()) {
			throw new EmptyCollectionException("esta vacia");
		}
		for(int a=0;a<npriorities; a++){
			if(colas.get(a)!=null){
				return colas.get(a).first();
			}
		}
		return null;
	}
	@Override
	public T dequeue() throws EmptyCollectionException {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			throw new EmptyCollectionException("cola vacia");
		}
		for(int a=0;a<npriorities; a++){
			if(colas.get(a)!=null){
				
				return colas.get(a).dequeue();
			}
		}
		return null;		
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
		boolean separator=false;
		if (! this.isEmpty()) {
			StringBuffer rx = new StringBuffer();
			rx.append("[");
			for (int n = 0; n < this.npriorities; ++n) {
				if (!colas.get(n).isEmpty()){
					rx.append("( Priority:"+(n+1)+" ("); 
					rx.append(colas.get(n).toString());
					rx.append(")), ");
					separator=true;
				}       
			}
			if (separator) 
				rx.delete(rx.length() - 2,rx.length());
			rx.append("]");
			return rx.toString();
		} else {
			return "[]"; 
		}
	}

};
  

