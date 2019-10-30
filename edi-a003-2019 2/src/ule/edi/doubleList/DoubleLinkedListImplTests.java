package ule.edi.doubleList;



import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;


public class DoubleLinkedListImplTests {

	private DoubleLinkedListImpl<String> lS;
	private DoubleLinkedListImpl<String> lSABC;
	private DoubleLinkedListImpl<String> lSABCDE;


	@Before
	public void setup() {
		this.lS = new DoubleLinkedListImpl<String>();
	    this.lSABC=new DoubleLinkedListImpl<String>("A", "B", "C");
	    this.lSABCDE=new DoubleLinkedListImpl<String>("A", "B", "C", "D", "E");
	}
	@Test
	public void testisEmpty(){
		Assert.assertEquals(lS.isEmpty(), true);
		lS.addFirst("A");
		Assert.assertEquals(lS.isEmpty(), false);
	}
	@Test
	public void testsize() {	
		Assert.assertEquals(lS.size(), 0);
		//System.out.println(lS.toString()+"testSize0");
		//System.out.println(lS.size()+"testSize0A");
		lS.addFirst("A");
		//System.out.println(lS.toString()+"testSize1");
		//System.out.println(lS.size()+"testSize1A");
		Assert.assertEquals(lS.size(), 1);
		lS.addFirst("B");
		//System.out.println(lS.toString()+"testSize2");
		//System.out.println(lS.size()+"testSize2A");
		Assert.assertEquals(lS.size(), 2);
		
	}
	@Test
	public void testaddFirst(){	
		//System.out.println(lS.toString()+"testaddfirst0");
		Assert.assertEquals(lS.toString(),"[]");
		lS.addFirst("A");
		//System.out.println(lS.toString()+"testaddfirst1");
		Assert.assertEquals(lS.toString(),"[A]");
		lS.addFirst("B");
		//System.out.println(lS.toString()+"testaddfirst2");
		Assert.assertEquals(lS.toString(),"[B, A]");	
		lS.addFirst("C");
		//System.out.println(lS.toString()+"testaddfirst3");
		Assert.assertEquals(lS.toString(),"[C, B, A]");	
	}
	@Test
	public void testaddLast(){	
		//System.out.println(lS.toString()+"testaddLast0");
		Assert.assertEquals(lS.toString(),"[]");
		lS.addLast("A");
		//System.out.println(lS.toString()+"testaddlast1");
		Assert.assertEquals(lS.toString(),"[A]");
		lS.addLast("B");
		//System.out.println(lS.toString()+"testaddlasst2");
		Assert.assertEquals(lS.toString(),"[A, B]");	
		lS.addLast("C");
		//System.out.println(lS.toString()+"testaddlasst3");
		Assert.assertEquals(lS.toString(),"[A, B, C]");	
	}
	@Test
	public void testaddAtPos(){	
		lSABC.addAtPos("Z",5);
		//System.out.println(lSABC.toString()+"testaddpossmayor");
		Assert.assertEquals(lSABC.toString(),"[A, B, C, Z]");
		lSABC.addAtPos("M",1);
		//System.out.println(lSABC.toString()+"testaddpossmenor");
		Assert.assertEquals(lSABC.toString(),"[M, A, B, C, Z]");
		lSABC.addAtPos("L",3);
		//System.out.println(lSABC.toString()+"testaddpossmedio");
		Assert.assertEquals(lSABC.toString(),"[M, A, L, B, C, Z]");
	}
	@Test
	public void testaddNTimes(){	
		lSABC.addNTimes("Z",2);
		//System.out.println(lSABC.toString()+"testaddNveces1");
		Assert.assertEquals(lSABC.toString(),"[A, B, C, Z, Z]");
		lSABC.addNTimes("Z",4);
		//System.out.println(lSABC.toString()+"testaddNveces2");
		Assert.assertEquals(lSABC.toString(),"[A, B, C, Z, Z, Z, Z, Z, Z]");	
	}
	@Test
	public void testgetElem(){	
		//System.out.println(lSABC.getElem(1)+"  primerelemento");
		Assert.assertEquals(lSABC.getElem(1),"A");
		//System.out.println(lSABC.getElem(2)+"  segundoelemento");
		Assert.assertEquals(lSABC.getElem(2),"B");
		//System.out.println(lSABC.getElem(3)+"  tercerelemento");
		Assert.assertEquals(lSABC.getElem(3),"C");
		
	}
		
	@Test (expected = IndexOutOfBoundsException.class)
	public void testgetElem1() throws IndexOutOfBoundsException{
		lSABC.getElem(-1);
		lSABC.getElem(4);
		
	}
	@Test
	public void testSetElem(){	
		//System.out.println(lSABC.toString()+"testSetElement0");
		Assert.assertEquals(lSABC.toString(),"[A, B, C]");
		lSABC.setElem("E",3);
		//System.out.println(lSABC.toString()+"testSetElement1");
		Assert.assertEquals(lSABC.toString(),"[A, B, E]");
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void testSetElem1() throws IndexOutOfBoundsException{
		lSABC.setElem("A",4);
	}
	@Test
	public void testindexOf(){	
		Assert.assertEquals(lSABC.indexOf("A"),1);
		//System.out.println(lSABC.indexOf("A")+"  posicion1");
		Assert.assertEquals(lSABC.indexOf("B"),2);
		//System.out.println(lSABC.indexOf("B")+"  posicion2");
		Assert.assertEquals(lSABC.indexOf("C"),3);
		//System.out.println(lSABC.indexOf("C")+"  posicion3");
	}	
	
	@Test (expected = NoSuchElementException.class)
	public void testindexOf1() throws NoSuchElementException{
		lSABC.indexOf("E");
	}
	@Test
	public void testindexOf2(){	
		Assert.assertEquals(lSABC.indexOf("A",1),1);
		Assert.assertEquals(lSABC.indexOf("B",1),2);
		Assert.assertEquals(lSABC.indexOf("B",2),1);
		Assert.assertEquals(lSABC.indexOf("C",1),3);
		Assert.assertEquals(lSABC.indexOf("C",2),2);
		Assert.assertEquals(lSABC.indexOf("C",3),1);
		Assert.assertEquals(lSABCDE.indexOf("E",2),4);
		
		
	}	
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void testindexOf3() throws IndexOutOfBoundsException{
		lSABC.indexOf("E",6);
	}
	
	
	
	@Test
	public void testremoveFirst() throws EmptyCollectionException{	
		//System.out.println(lS.toString()+"removeFirst0");
		lS.addLast("A");
		lS.addLast("B");
		lS.addLast("C");
		//System.out.println(lS.toString()+"removeFirst0.1");
		Assert.assertEquals(lS.removeFirst("A"),  "A");
		//System.out.println(lS.toString()+"removeFirst1");
		
		Assert.assertEquals(lS.removeFirst("C"),  "C");
		//System.out.println(lS.toString()+"removeFirst3");
		
		Assert.assertEquals(lS.removeFirst("B"),  "B");
		//System.out.println(lS.toString()+"removeFirst2");
		
	}	
	
	@Test (expected = EmptyCollectionException.class)
	public void testremovefirst1() throws EmptyCollectionException{
		lS.removeFirst("A");
	}
	/*@Test (expected = NoSuchElementException.class)
	public void testremovefirst2() throws NoSuchElementException, EmptyCollectionException{
		lS.removeFirst("E");
	}*/
	
	@Test
	public void testremoveAll() throws EmptyCollectionException{	
		//System.out.println(lS.toString()+"removeAll0");
		lS.addLast("A");
		lS.addLast("B");
		lS.addLast("A");
		lS.addLast("C");
		lS.addLast("A");
		//System.out.println(lS.toString()+"removeAll0.1");
		//lS.removeAll("A");
		Assert.assertEquals(lS.removeAll("A"),  "A");
		//System.out.println(lS.toString()+"removeAll1");
		Assert.assertEquals(lS.removeAll("C"),  "C");
		//System.out.println(lS.toString()+"removeAll2");
		
	}	
	
	@Test (expected = EmptyCollectionException.class)
	public void testremoveAll1() throws EmptyCollectionException{
		lS.removeAll("A");
	}
	/*@Test (expected = NoSuchElementException.class)
	public void testremoveAll2() throws NoSuchElementException, EmptyCollectionException{
		lS.removeAll("E");
	}*/
	@Test
	public void testremoveLast() throws EmptyCollectionException{	
		//System.out.println(lS.toString()+"removeLast0");
		lS.addLast("A");
		lS.addLast("B");
		lS.addLast("C");
		//System.out.println(lS.toString()+"removeLast0.1");
		
		Assert.assertEquals(lS.removeLast(), "C");
		//System.out.println(lS.toString()+"removeLast1");
		Assert.assertEquals(lS.removeLast(), "B");
		//System.out.println(lS.toString()+"removeLast2");
		Assert.assertEquals(lS.removeLast(), "A");
		//System.out.println(lS.toString()+"removeLast3");
		
	}	
	
	@Test (expected = EmptyCollectionException.class)
	public void testremoveLast1() throws EmptyCollectionException{
		lS.removeLast();
	}
	
	/*@Test (expected = NoSuchElementException.class)
	public void testremoveLast2() throws NoSuchElementException, EmptyCollectionException{
		lS.removeLast();
	}*/
	@Test
	public void testreverse() throws EmptyCollectionException{	
		//System.out.println(lS.toString()+"reverse0");
		lS.addLast("A");
		lS.addLast("B");
		lS.addLast("C");
		lS.addLast("D");
		lS.addLast("E");
		lS.addLast("F");
		lS.addLast("G");
		//System.out.println(lS.toString()+"reverse0.1");
		lS.reverse();
		//System.out.println(lS.toString()+"reverse1");
		
	}
	@Test
	public void testisSublist() throws EmptyCollectionException{	
		DoubleLinkedListImpl<String> lS1=new DoubleLinkedListImpl<String>("A", "B", "A", "B", "C" );
		DoubleLinkedListImpl<String> lS11=new DoubleLinkedListImpl<String>("B", "A", "X" );
		//DoubleLinkedListImpl<String> lS21=new DoubleLinkedListImpl<String>("B", "A" );
		DoubleLinkedListImpl<String> lS3=new DoubleLinkedListImpl<String>("A", "B", "A", "B" );
		DoubleLinkedListImpl<String> lS31=new DoubleLinkedListImpl<String>("A", "B" );
		//DoubleLinkedListImpl<String> lS4=new DoubleLinkedListImpl<String>("A", "B", "A", "B", "C", "X", "A" );
		//DoubleLinkedListImpl<String> lS41=new DoubleLinkedListImpl<String>("B", "C", "X" );
		
		Assert.assertEquals(lS3.isSubList(lS31), 1);//caso3
		
		Assert.assertEquals(lS1.isSubList(lS11), -1);//caso 1
		
		//Assert.assertEquals(lS1.isSubList(lS21), 2);//caso2
		
		//Assert.assertEquals(lS4.isSubList(lS41), 4);//caso4
		
		

		
		
	}
	@Test
	public void testinterlace() throws EmptyCollectionException{	
		//System.out.println(lSABCDE.toString()+"interlace0.1");
		lSABCDE.interlace(lSABC);
		//System.out.println(lSABCDE.toString()+"interlace1");
		
		//System.out.println(lSABC.toString()+"interlace0.2");
		//lSABC.interlace(lSABCDE);
		//System.out.println(lSABC.toString()+"interlace2");
		
		//System.out.println(lSABC.toString()+"interlace0.3");
		//lSABC.interlace(lS);
		//System.out.println(lSABC.toString()+"interlace3");
		
		//System.out.println(lS.toString()+"interlace0.4");
		//lS.interlace(lSABC);
		//System.out.println(lS.toString()+"interlace4");
	}
	
	
	
	@Test
	public void testToStringVacio(){
		Assert.assertEquals(lS.toString(),"[]");		
	}
	
	@Test
	public void testToStringNoVacio(){
		Assert.assertEquals(lSABC.toString(),"[A, B, C]");		
	}
	
	@Test
	public void testConstructorConLista(){
		DoubleLinkedListImpl<String> nueva= new DoubleLinkedListImpl<String>(lSABCDE);
		Assert.assertEquals("[A, B, C, D, E]", nueva.toString());
		//System.out.println(nueva.toString()+"constructorlista");
		
	}
	
	@Test
	public void testForwardIt() {
		lS = new DoubleLinkedListImpl<String>("A", "B", "C", "D");
		Iterator<String> i = lS.iterator();
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("A", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("B", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("C", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("D", i.next());
		Assert.assertFalse(i.hasNext());
	    Assert.assertEquals("[A, B, C, D]", lS.toString());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testForwardItException() {
		Iterator<String> i = lS.iterator();
		Assert.assertFalse(i.hasNext());
		i.next();
	}
	
	@Test
	public void testreverseIterator() {
		lS = new DoubleLinkedListImpl<String>("A", "B", "C", "D");
		Assert.assertEquals(lS.toString(),"[A, B, C, D]");
		Iterator<String> i = lS.reverseIterator();
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("D", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("C", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("B", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("A", i.next());
		Assert.assertFalse(i.hasNext());
	    Assert.assertEquals("[D, C, B, A]", lS.toString());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testreverseIterator1() {
		Iterator<String> i = lS.reverseIterator();
		Assert.assertFalse(i.hasNext());
		i.next();
	}
	
	@Test
	public void testoddAndEvenIterator() {
		lS = new DoubleLinkedListImpl<String>("A", "B", "C", "D", "E");
		Assert.assertEquals(lS.toString(),"[A, B, C, D, E]");
		Iterator<String> i = lS.oddAndEvenIterator();
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("B", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("D", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("A", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("C", i.next());
		Assert.assertTrue(i.hasNext());
		Assert.assertEquals("E", i.next());
		Assert.assertFalse(i.hasNext());
	    Assert.assertEquals("[A, B, C, D, E]", lS.toString());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testoddAndEvenIterator1() {
		Iterator<String> i = lS.oddAndEvenIterator();
		Assert.assertFalse(i.hasNext());
		i.next();
	}
}
