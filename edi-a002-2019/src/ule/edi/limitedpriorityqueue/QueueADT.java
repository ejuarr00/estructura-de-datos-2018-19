package ule.edi.limitedpriorityqueue;

public interface QueueADT<T> {
	 /**
     * Inserta un elemento en la cola.
     * 
     * @param elem  elemento a encolar 
     * 
     * @throws NullPointerException si el elemento es null
     */
	 public void enqueue (T element);
	 
	 
	 /**
	   * Elimina el elemento del principio de la cola y devuelve una
    *  referencia a él. 
    *  Dispara la excepción EmptyCollectionException si la cola está vacia. 
    *  
    * @return el elemento al principio de la cola
    * @throws EmptyCollectionException 

	     */
	 public T dequeue( ) throws EmptyCollectionException;
	 
	 /**
	     * Obtener sin eliminarlo el primer elemento de la cola
	     *  
	     * @return el elemento del principio de la cola
	     * @throws EmptyCollectionException 
	     */
	 public T first() throws EmptyCollectionException;

	 /**
	     * Indica si la cola está vacía
	     * 
	     * @return 'true' si la cola está vacia.
	     */
	 public boolean isEmpty();
	 
	 /**
	     * Número de elementos actualmente en la cola.
	     * 
	     * @return número de elementos en la cola.
	     */
	 public int size();
	 
	 /**
	     * El elimina el elemento del final de la cola (el que menos tiempo lleva)
	     * y devuelve una referencia a él
	     *  Dispara la excepción EmptyCollectionException si la cola está vacia. 
     *  
    * @return el elemento del final de la cola
    * @throws EmptyCollectionException 
    */
	 public T dequeueLast() throws EmptyCollectionException;
	
}
