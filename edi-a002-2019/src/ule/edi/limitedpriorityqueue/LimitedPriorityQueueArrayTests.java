package ule.edi.limitedpriorityqueue;

import org.junit.*;







public class LimitedPriorityQueueArrayTests {


	private LimitedPriorityQueueArrayImpl<String> pq3;
	private LimitedPriorityQueueArrayImpl<String> pq5;
	private LinkedQueue<String> pq2;

	public LimitedPriorityQueueArrayTests() {



	}

	@Before
	public void testBefore() throws Exception{
		pq3 = new LimitedPriorityQueueArrayImpl<String>(3,2); // limitado a 3 elementos, las posibles prioridades son [1,2]
		pq5 = new LimitedPriorityQueueArrayImpl<String>(5,3); // limitado a 5 elementos, las posibles prioridades son [1,2,3]
		pq2 = new LinkedQueue<String>();

	}

	//Test de la clase LinkedQueue()

	@Test
	public void testenqueue() throws Exception {
		pq2.enqueue("1");
		pq2.enqueue("2");
		pq2.enqueue("2");
		Assert.assertEquals(pq2.toString(),"1, 2, 2"); 
		//System.out.println(pq2.toString()+"enqueuenormatest");
	}

	@Test (expected =NullPointerException.class)
	public void testenqueue1() throws NullPointerException {
		pq2.enqueue(null);
	}

	@Test
	public void testdequeue() throws Exception {
		pq2.enqueue("1");
		pq2.enqueue("2");
		Assert.assertEquals(pq2.dequeue(), "1"); 
		Assert.assertEquals(pq2.dequeue(), "2");

	}

	@Test (expected =EmptyCollectionException.class)
	public void testdequeue1() throws EmptyCollectionException {

		Assert.assertEquals(pq2.toString(), "");
		pq2.enqueue("1");
		Assert.assertEquals(pq2.toString(), "1");
		pq2.enqueue("2");
		Assert.assertEquals(pq2.toString(), "1, 2");
		Assert.assertEquals(pq2.dequeue(), "1");
		Assert.assertEquals(pq2.toString(), "2");
		Assert.assertEquals(pq2.dequeue(), "2");
		Assert.assertEquals(pq2.toString(), "");
		Assert.assertEquals(pq2.dequeue(), null);

	}

	@Test
	public void  testfirst() throws Exception {

		pq2.enqueue("1");
		pq2.enqueue("1");
		pq2.enqueue("2");
		Assert.assertEquals(pq2.first(), "1");
		Assert.assertEquals(pq2.dequeue(), "1");
		Assert.assertEquals(pq2.dequeue(), "1");
		Assert.assertEquals(pq2.first(), "2");

	}
	@Test (expected =EmptyCollectionException.class)
	public void testfirst1() throws EmptyCollectionException {

		pq2.first();

	}
	@Test
	public void testisempty() throws Exception{
		Assert.assertEquals(pq2.isEmpty(), true);
		pq2.enqueue("1");
		Assert.assertEquals(pq2.toString(),"1");
		Assert.assertEquals(pq2.isEmpty(), false);

	}
	@Test
	public void  testsize() throws Exception {

		pq2.enqueue("1");
		Assert.assertEquals(pq2.size(), 1);
		pq2.enqueue("2");
		Assert.assertEquals(pq2.size(), 2);
		pq2.enqueue("1");
		pq2.enqueue("1");
		pq2.enqueue("1");
		pq2.enqueue("1");
		Assert.assertEquals(pq2.size(), 6);
	}



	@Test
	public void testdequeueLast() throws EmptyCollectionException {
		pq2.enqueue("1");
		Assert.assertEquals(pq2.dequeueLast(), "1");

		pq2.enqueue("1");
		pq2.enqueue("2");
		Assert.assertEquals(pq2.dequeueLast(), "2");

		pq2.enqueue("1");
		pq2.enqueue("2");
		pq2.enqueue("3");
		pq2.enqueue("4");
		//System.out.println("LA LISTA ----"+pq2.toString());
		Assert.assertEquals(pq2.dequeueLast(), "4");
		Assert.assertEquals(pq2.dequeueLast(), "3");
		Assert.assertEquals(pq2.dequeueLast(), "2");
		Assert.assertEquals(pq2.dequeueLast(), "1");

	}
	@Test (expected =EmptyCollectionException.class)
	public void testdequeuelast1() throws EmptyCollectionException {

		Assert.assertEquals(pq2.dequeueLast(), "");

	}

	/*--------------------------------------------------------//
	//		Tests de la clase LimitedPriorityQueueArrayImp	  //
	/=========================================================*/



	@Test
	public void testGetCapacity() throws Exception {
		Assert.assertEquals(pq3.getCapacity(), 3);
	}
	@Test
	public void testGetSize() throws Exception {
		Assert.assertEquals(pq3.getSize(), 0);
		//System.out.println(pq3.getSize()+"getSizetest0");
		Assert.assertEquals(pq3.enqueue(1, "Prior1_1"), null);
		Assert.assertEquals(pq3.enqueue(2, "Prior2_1"), null);
		Assert.assertEquals(pq3.enqueue(2, "Prior2_2"), null);
		Assert.assertEquals(pq3.getSize(), 3);
		//System.out.println(pq3.getSize()+"getSizetest3");
	}
	@Test
	public void testIsFull() throws Exception {
		Assert.assertEquals(pq3.enqueue(1, "Prior1_1"), null);
		Assert.assertEquals(pq3.isFull(), false);
		Assert.assertEquals(pq3.enqueue(2, "Prior2_1"), null);
		Assert.assertEquals(pq3.isFull(), false);
		Assert.assertEquals(pq3.enqueue(2, "Prior2_2"), null);
		Assert.assertEquals(pq3.isFull(), true);	
	}

	@Test 
	public void testTEnqueue() throws Exception {  
		Assert.assertEquals(pq3.enqueue(1, "Prior1_1"), null);	
		Assert.assertEquals(pq3.enqueue(1, "Prior1_2"), null);
		Assert.assertEquals(pq3.enqueue(2, "Prior2_1"), null);
		Assert.assertEquals(pq3.enqueue(2, "Prior2_2"), "Prior2_2");
		Assert.assertEquals(pq3.enqueue(1, "Prior1_3"), "Prior2_1");

		//System.out.println(pq3.toString()+  "eduencolaarrayp1.3");
	}

	@Test (expected = NullPointerException.class)
	public void testTEnqueue1() {

		pq3.enqueue(1,null);
	}
	@Test (expected = IllegalArgumentException.class)
	public void testTEnqueue2() throws EmptyCollectionException {

		Assert.assertEquals(pq3.enqueue(0, "Prior4_"),null);
		Assert.assertEquals(pq3.enqueue(4, "Prior4_1"),null);
	}

	@Test 
	public void testTFirst() throws Exception {  

		Assert.assertEquals(pq5.enqueue(1, "Prior1_1"), null);	
		Assert.assertEquals(pq5.enqueue(1, "Prior1_2"), null);	
		Assert.assertEquals(pq5.enqueue(3, "Prior3_1"), null);
		Assert.assertEquals(pq5.enqueue(2, "Prior2_1"), null);
		Assert.assertEquals(pq5.enqueue(2, "Prior2_2"), null);
		Assert.assertEquals(pq5.first(), "Prior1_1");
		//System.out.println(pq5.toString()+  "muestraprimero");


	}
	@Test (expected = EmptyCollectionException.class)
	public void testTFirst1() throws EmptyCollectionException {
		Assert.assertEquals(pq3.first(), null);
	}
	@Test 
	public void testTDequeue() throws Exception {  
		Assert.assertEquals(pq5.enqueue(1, "Prior1_1"), null);	
		Assert.assertEquals(pq5.enqueue(1, "Prior1_2"), null);	
		Assert.assertEquals(pq5.enqueue(3, "Prior3_1"), null);
		Assert.assertEquals(pq5.enqueue(2, "Prior2_1"), null);
		Assert.assertEquals(pq5.dequeue(), "Prior1_1");
		//System.out.println(pq5.toString()+  "borraprimero");

	}
	@Test (expected = EmptyCollectionException.class)
	public void testTDequeue1() throws EmptyCollectionException {
		Assert.assertEquals(pq5.dequeue(), null);
	}
	@Test
	public void testIsEmpty() throws Exception{
		Assert.assertEquals(pq3.isEmpty(), true);
		Assert.assertEquals(pq3.enqueue(1, "Prior1_1"), null);	
		Assert.assertEquals(pq3.enqueue(1, "Prior1_2"), null);
		Assert.assertEquals(pq3.enqueue(2, "Prior2_1"), null);
		Assert.assertEquals(pq3.isEmpty(), false);
	}

	@Test
	public void testEnVacia() throws Exception {

		Assert.assertEquals(pq3.isEmpty(), true);
		Assert.assertEquals(pq3.isFull(), false);
		Assert.assertEquals(pq3.getSize(), 0);
		Assert.assertEquals(pq3.toString(), "[]");
		Assert.assertEquals(pq3.getCapacity(), 3);
	}

	@Test
	public void testInsertarHastaLLenar() throws Exception{
		Assert.assertEquals(pq3.enqueue(1, "Prior1_1"), null);
		Assert.assertEquals(pq3.isEmpty(), false);
		Assert.assertEquals(pq3.getSize(), 1);
		Assert.assertEquals(pq3.enqueue(2, "Prior2_1"), null);
		Assert.assertEquals(pq3.isEmpty(), false);
		Assert.assertEquals(pq3.getSize(), 2);	
		Assert.assertEquals(pq3.enqueue(2, "Prior2_2"), null);
		Assert.assertEquals(pq3.isEmpty(), false);
		Assert.assertEquals(pq3.getSize(), 3);	
		Assert.assertEquals(pq3.isFull(), true);
		Assert.assertEquals(pq3.toString(), "[( Priority:1 (Prior1_1)), ( Priority:2 (Prior2_1, Prior2_2))]");

	}

	@Test
	public void testInsertarMenorPrioEnLLena() throws Exception{
		Assert.assertEquals(pq3.enqueue(1, "Prior1_1"), null);
		Assert.assertEquals(pq3.enqueue(2, "Prior2_1"), null);
		Assert.assertEquals(pq3.enqueue(2, "Prior2_2"), null);
		Assert.assertEquals(pq3.toString(), "[( Priority:1 (Prior1_1)), ( Priority:2 (Prior2_1, Prior2_2))]");
		Assert.assertEquals(pq3.enqueue(2, "Prior2_3"), "Prior2_3");    // El elemento insertado tiene menor prioridad que los que estaban, por tanto es el que sale
		Assert.assertEquals(pq3.toString(), "[( Priority:1 (Prior1_1)), ( Priority:2 (Prior2_1, Prior2_2))]");

	}

	@Test
	public void testInsertarMayorPrioEnLLena() throws Exception{
		Assert.assertEquals(pq3.enqueue(1, "Prior1_1"), null);
		Assert.assertEquals(pq3.enqueue(2, "Prior2_1"), null);
		Assert.assertEquals(pq3.enqueue(2, "Prior2_2"), null);
		Assert.assertEquals(pq3.toString(), "[( Priority:1 (Prior1_1)), ( Priority:2 (Prior2_1, Prior2_2))]");
		Assert.assertEquals(pq3.enqueue(1, "Prior1_2"), "Prior2_2");
		Assert.assertEquals(pq3.toString(), "[( Priority:1 (Prior1_1, Prior1_2)), ( Priority:2 (Prior2_1))]");

	}
}
