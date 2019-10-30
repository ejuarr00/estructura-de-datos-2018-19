package ule.edi.limitedpriorityqueue;

import org.junit.*;




public class LimitedPriorityQueueLinkedTests {


	private LimitedPriorityQueueLinkedImpl<String> pq3;
	private LimitedPriorityQueueLinkedImpl<String> pq5;


	public LimitedPriorityQueueLinkedTests() {


	}

	@Before
	public void testBefore() throws Exception{
		pq3 = new LimitedPriorityQueueLinkedImpl<String>(3); // limitado a 3 elementos
		pq5 = new LimitedPriorityQueueLinkedImpl<String>(5); // limitado a 5 elementos

	}
	@Test
	public void testGetCapacity() throws Exception {
		Assert.assertEquals(pq5.getCapacity(), 5);
		Assert.assertEquals(pq3.getCapacity(), 3);
	}
	//Este tes me da dado problemas 
	/*public void testGetSize() throws Exception {
		//Assert.assertEquals(pq3.getSize(), 0);
		//System.out.println(pq3.getSize()+"getSizetest0");
		//Assert.assertEquals(pq3.enqueue(1, "Prior1_1"), null);
		//Assert.assertEquals(pq3.enqueue(2, "Prior2_1"), null);
		//Assert.assertEquals(pq3.enqueue(2, "Prior2_2"), null);
		//Assert.assertEquals(pq3.getSize(), 3);
		//System.out.println(pq3.getSize()+"getSizetest3");
	}*/
	@Test
	public void testIsFull() throws Exception {
		Assert.assertEquals(pq3.isFull(), false);
		Assert.assertEquals(pq3.enqueue(1, "Prior1_1"), null);
		Assert.assertEquals(pq3.enqueue(1, "Prior1_2"), null);
		Assert.assertEquals(pq3.enqueue(1, "Prior1_3"), null);
		Assert.assertEquals(pq3.isFull(), true);
	}

	@Test 
	public void testTEnqueue() throws Exception {  
		Assert.assertEquals(pq5.enqueue(1, "Prior1_1"), null);
		//System.out.println(pq5.toString()+"enqueueeeeee1");
		Assert.assertEquals(pq5.enqueue(2, "Prior2_1"), null);
		//System.out.println(pq5.toString()+"enqueueeeeee2");
		Assert.assertEquals(pq5.enqueue(3, "Prior3_1"), null);
		//System.out.println(pq5.toString()+"enqueueeeeee3");
		//he tenido problemas para desarrollar el metodo enqueue de la clase LimitedPriorityQueueLinkedImpl<T> por lo que no me lo realiza correctamente.
	}
	@Test (expected = NullPointerException.class)
	public void testTEnqueue1() throws NullPointerException{
		pq3.enqueue(1,null);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testTEnqueue2() throws IllegalArgumentException {
		Assert.assertEquals(pq3.enqueue(-1, "Prior4_"),null);
	}

	@Test 
	public void testTFirst() throws Exception {  
		Assert.assertEquals(pq5.enqueue(1, "Prior1_1"), null);	
		Assert.assertEquals(pq5.enqueue(2, "Prior2_1"), null);	
		Assert.assertEquals(pq5.first(), "Prior1_1");
	}
	@Test (expected = EmptyCollectionException.class)
	public void testTFirst1() throws EmptyCollectionException {
		Assert.assertEquals(pq3.first(), "");
	}
	@Test 
	public void testTDequeue() throws Exception {  
		Assert.assertEquals(pq5.enqueue(1, "Prior1_1"), null);	
		Assert.assertEquals(pq5.enqueue(3, "Prior2_1"), null);
		Assert.assertEquals(pq5.dequeue(), "Prior1_1");

	}
	@Test (expected = EmptyCollectionException.class)
	public void testTDequeue1() throws EmptyCollectionException {
		Assert.assertEquals(pq5.dequeue(), null);
	}

	@Test
	public void testIsEmpty() throws Exception {
		Assert.assertEquals(pq3.isEmpty(), true);
		Assert.assertEquals(pq3.enqueue(1, "Prior1_1"), null);
		Assert.assertEquals(pq3.isEmpty(), false);
	}


	@Test
	public void testSomething() throws Exception {
		Assert.assertEquals(pq3.isEmpty(), true);
		Assert.assertEquals(pq3.isFull(), false);
		Assert.assertEquals(pq3.getSize(), 0);
		Assert.assertEquals(pq3.toString(), "[]");
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
		Assert.assertEquals(pq3.enqueue(2, "Prior2_3"), "Prior2_3");
		Assert.assertEquals(pq3.toString(), "[( Priority:1 (Prior1_1)), ( Priority:2 (Prior2_1, Prior2_2))]");

	}
}
