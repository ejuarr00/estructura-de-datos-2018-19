package ule.edi.limitedpriorityqueue;

/**
 * Interface LimitedPriorityQueue<T> define una cola de prioridad limitada.
 *
 * Elementos de tipo T se encolan con una prioridad dada.
 * 
 * Al desencolar se saca el elemento de mayor prioridad que más tiempo lleve en
 * la cola.
 * 
 * El orden de los elementos con la misma prioridad es FIFO (se comporta como
 * una cola para los de la misma prioridad)
 * 
 * No se aceptan objetos Null para encolar en la cola de prioridad. Los métodos
 * deben lanzar la excepción NullPointerException si se el elemento a insertar
 * es Null
 * 
 */
public interface LimitedPriorityQueue<T> {

	/**
	 * Inserta un elemento en la cola.
	 *
	 * Si el número de elementos de la cola iguala su capacidad, se inserta el
	 * elemento según su prioridad y se saca el elemento de menor prioridad que
	 * lleve menos tiempo en la estructura.
	 * 
	 * @param p
	 *            prioridad del elemento
	 * @param elem
	 *            elemento a encolar según su prioridad
	 * 
	 * @return si la cola de prioridad estaba llena, devuelve el elemento de menor prioridad que lleve menos tiempo en la cola;
	 *         si no estaba llena, devuelve null 
	 *  @throws IllegalArgumentException  si la prioridad no es correcta       
	 */
	public T enqueue(int p, T elem);

	/**
	 * Obtener el elemento con la prioridad mayor que más tiempo lleve en la
	 * cola de prioridad
	 * 
	 * El elemento no se elimina de la cola.
	 * 
	 * @return el elemento con la prioridad más alta de la cola que más tiempo
	 *         lleve en la cola de prioridad.
	 * @throws EmptyCollectionException  si la cola está vacia. 
	 * @throws NullPointerException si el elemento es null  
	 */
	public T first() throws EmptyCollectionException;

	/**
	 * Elimina y devuelve el elemento con la prioridad mayor que más tiempo
	 * lleve en la cola de prioridad devuelve el elemento con mayor prioridad
	 * que más tiempo lleve en la cola.
	 *   
	 * 
	 * @return el elemento con mayor prioridad que más tiempo lleve en la cola.
	 * @throws EmptyCollectionException  si la cola está vacia. 
	 */
	public T dequeue() throws EmptyCollectionException;

	/**
	 * La capacidad total de la cola de prioridad limitada.
	 * 
	 * @return número max de elementos en la cola..
	 */
	public int getCapacity();

	/**
	 * Número de elementos actualmente en la cola.
	 * 
	 * @return número de elementos en la cola.
	 */
	public int getSize();

	/**
	 * Indica si la cola está al máximo de capacidad (está llena)
	 * 
	 * @return 'true' si la cola está al máximo de capacidad.
	 */
	public boolean isFull();

	/**
	 * Indica si la cola está vacía
	 * 
	 * @return 'true' si la cola está vacia.
	 */
	public boolean isEmpty();

}
