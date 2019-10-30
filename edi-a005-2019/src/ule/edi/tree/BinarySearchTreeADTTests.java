package ule.edi.tree;


import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;







public class BinarySearchTreeADTTests {

	/*
	 * ∅
	 */
	private BinarySearchTreeADTImpl<Integer> TE = null;

	/*
	 * 1
	 * |  ∅
	 * |  2
	 * |  |  ∅
	 * |  |  3
	 * |  |  |  ∅
	 * |  |  |  4
	 * |  |  |  |  ∅
	 * |  |  |  |  ∅
	 */	
	private BinarySearchTreeADTImpl<Integer> T1234 = null;

	/*
	 * 4
	 * |  3
	 * |  |  2
	 * |  |  |  1
	 * |  |  |  |  ∅
	 * |  |  |  |  ∅
	 * |  |  |  ∅
	 * |  |  ∅
	 * |  ∅
	 */	
	private BinarySearchTreeADTImpl<Integer> T4321 = null;

	/*
	 * 50
	 * |  20
	 * |  |  10
	 * |  |  |  ∅
	 * |  |  |  ∅
	 * |  |  30
	 * |  |  |  ∅
	 * |  |  |  ∅
	 * |  80
	 * |  |  70
	 * |  |  |  ∅
	 * |  |  |  ∅
	 * |  |  90
	 * |  |  |  ∅
	 * |  |  |  ∅
	 */	
	private BinarySearchTreeADTImpl<Integer> TC3 = null;

	/*
	 * 10
	 * |  5
	 * |  |  ∅
	 * |  |  ∅
	 * |  20
	 * |  |  ∅
	 * |  |  30
	 * |  |  |  ∅
	 * |  |  |  ∅
	 */
	private BinarySearchTreeADTImpl<Integer> TEx = null;

	/*
	 * 10
	 * |  5
	 * |  |  ∅
	 * |  |  7
	 * |  |  |  6
	 * |  |  |  |  ∅
	 * |  |  |  |  ∅
	 * |  |  |  ∅
	 * |  15
	 * |  |  ∅
	 * |  |  ∅
	 * 
	 */
	private BinarySearchTreeADTImpl<Integer> TV1 = null;

	@Before
	public void setupBSTs() {

		TE = new BinarySearchTreeADTImpl<Integer>();

		T1234 = new BinarySearchTreeADTImpl<Integer>();
		T1234.insert(1,2,3,4);
		Assert.assertEquals(T1234.toString(), "{1, ∅, {2, ∅, {3, ∅, {4, ∅, ∅}}}}");

		T4321 = new BinarySearchTreeADTImpl<Integer>();
		T4321.insert(4, 3, 2, 1);
		Assert.assertEquals(T4321.toString(), "{4, {3, {2, {1, ∅, ∅}, ∅}, ∅}, ∅}");

		TC3 = new BinarySearchTreeADTImpl<Integer>();
		TC3.insert(50, 20, 80, 10, 30, 70, 90);
		Assert.assertEquals(TC3.toString(), "{50, {20, {10, ∅, ∅}, {30, ∅, ∅}}, {80, {70, ∅, ∅}, {90, ∅, ∅}}}");

		TEx = new BinarySearchTreeADTImpl<Integer>();
		TEx.insert(10, 20, 30, 5);
		Assert.assertEquals(TEx.toString(), "{10, {5, ∅, ∅}, {20, ∅, {30, ∅, ∅}}}");

		TV1 = new BinarySearchTreeADTImpl<Integer>();
		TV1.insert(10, 5, 7, 6, 15);		
		Assert.assertEquals(TV1.toString(), "{10, {5, ∅, {7, {6, ∅, ∅}, ∅}}, {15, ∅, ∅}}");
	}

	@Test
	public void insertarcollection(){
		ArrayList<Integer> lista= new ArrayList<Integer>();
		lista.add(60);
		lista.add(30);
		lista.add(80);
		lista.add(70);
		TE.insert(lista);
		//System.out.println(TE);
		Assert.assertEquals("{60, {30, ∅, ∅}, {80, {70, ∅, ∅}, ∅}}", TE.toString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void insertarcollection1() throws IllegalArgumentException {
		ArrayList<Integer> lista= new ArrayList<Integer>();
		lista.add(null);
		TE.insert(lista);
		//System.out.println(TE);
		//Assert.assertEquals("{∅}", TE.toString());
	}

	@Test
	public void insertar(){ 
		TE.insert(4);
		TE.insert(6);
		TE.insert(2);
		TE.insert(7);
		//System.out.println(TE);
		Assert.assertEquals("{4, {2, ∅, ∅}, {6, ∅, {7, ∅, ∅}}}", TE.toString());
		TE.insert(5);
		//System.out.println(TE);
		Assert.assertEquals("{4, {2, ∅, ∅}, {6, {5, ∅, ∅}, {7, ∅, ∅}}}", TE.toString());
	}

	/*@Test(expected = IllegalArgumentException.class)
	public void insertar1() throws IllegalArgumentException { 

		TE.insert();

	}*/


	@Test
	public void testwithdrawcollection(){
		ArrayList<Integer> lista= new ArrayList<Integer>();
		ArrayList<Integer> lista1= new ArrayList<Integer>();
		lista.add(60);
		lista.add(30);
		lista.add(80);
		lista.add(70);
		lista1.add(60);
		lista1.add(30);
		TE.insert(lista);
		//System.out.println(TE);
		Assert.assertEquals("{60, {30, ∅, ∅}, {80, {70, ∅, ∅}, ∅}}", TE.toString());
		TE.withdraw(lista1);
		//System.out.println(TE);
		Assert.assertEquals("{80, {70, ∅, ∅}, ∅}", TE.toString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testwithdrawcollection1() throws IllegalArgumentException {
		ArrayList<Integer> lista= new ArrayList<Integer>();
		lista.add(null);
		TE.withdraw(lista);
		//System.out.println(TE);
		//Assert.assertEquals("{∅}", TE.toString());
	}

	@Test
	public void testwithdraw(){ 
		TE.insert(4);
		TE.insert(6);
		TE.insert(2);
		TE.insert(7);
		//System.out.println(TE);
		Assert.assertEquals("{4, {2, ∅, ∅}, {6, ∅, {7, ∅, ∅}}}", TE.toString());
		TE.insert(5);
		//System.out.println(TE);
		Assert.assertEquals("{4, {2, ∅, ∅}, {6, {5, ∅, ∅}, {7, ∅, ∅}}}", TE.toString());

		TE.withdraw(5);
		Assert.assertEquals("{4, {2, ∅, ∅}, {6, ∅, {7, ∅, ∅}}}", TE.toString());
		TE.withdraw(7);
		Assert.assertEquals("{4, {2, ∅, ∅}, {6, ∅, ∅}}", TE.toString());

	}

	@Test(expected = NoSuchElementException.class)
	public void testwithdraw1() throws NoSuchElementException { 
		TE.insert(4);
		TE.insert(6);
		TE.withdraw(6);
		TE.withdraw(4);
		TE.withdraw(7);
	}

	@Test(expected = NoSuchElementException.class)
	public void testwithdraw2() throws NoSuchElementException { 
		TE.insert(4);
		TE.insert(6);
		TE.insert(7);
		TE.withdraw(8);

	}
	@Test(expected = NoSuchElementException.class)
	public void testwithdraw3() throws NoSuchElementException { 
		TE.insert(4);
		TE.insert(3);
		TE.insert(2);
		TE.withdraw(1);
	}

	@Test
	public void testgetsubtreewhitpath(){
		BinarySearchTreeADTImpl<Integer> TC3_1 =TC3.getSubtreeWithPath("10");
		Assert.assertEquals("{70, ∅, ∅}",TC3_1.toString());

		BinarySearchTreeADTImpl<Integer> p1234 =T1234.getSubtreeWithPath("1");
		Assert.assertEquals("{2, ∅, {3, ∅, {4, ∅, ∅}}}",p1234.toString());

	}

	@Test(expected = IllegalArgumentException.class)
	public void testgetsubtreewhitpath1() throws IllegalArgumentException { 
		TC3.getSubtreeWithPath("5");
	}
	@Test(expected = NoSuchElementException.class)
	public void testgetsubtreewhitpath2() throws NoSuchElementException { 
		T1234.getSubtreeWithPath("0");

	}
	@Test(expected = NoSuchElementException.class)
	public void testgetsubtreewhitpath3() throws NoSuchElementException { 
		T4321.getSubtreeWithPath("1");

	}

	@Test
	public void testtagwidth(){
		TC3.tagWidth();
		Assert.assertEquals(TC3.toString(), "{50 [(width, 1)], {20 [(width, 2)], {10 [(width, 4)], ∅, ∅}, {30 [(width, 5)], ∅, ∅}}, {80 [(width, 3)], {70 [(width, 6)], ∅, ∅}, {90 [(width, 7)], ∅, ∅}}}");
	}

	@Test
	public void testisPathIn(){
		List<Integer> lista =new ArrayList<Integer>();
		lista.add(50);
		lista.add(20);
		TC3.isPathIn(lista);
		Assert.assertTrue(TC3.isPathIn(lista));
		Assert.assertEquals(TC3.toString(), "{50 [(path, 1)], {20 [(path, 2)], {10, ∅, ∅}, {30, ∅, ∅}}, {80, {70, ∅, ∅}, {90, ∅, ∅}}}");
	}

	@Test
	public void testisPathIn1(){
		List<Integer> lista =new ArrayList<Integer>();
		lista.add(50);
		lista.add(20);
		lista.add(12);
		TC3.isPathIn(lista);
		Assert.assertFalse(TC3.isPathIn(lista));
		//Assert.assertEquals(TC3.toString(), "[(80, 90), (80, 70), (50, 80), (50, 20), (20, 30), (20, 10)]");
	}
	@Test
	public void testisPathIn2(){
		List<Integer> lista =new ArrayList<Integer>();
		lista.add(50);
		lista.add(20);
		lista.add(20);
		TC3.isPathIn(lista);
		Assert.assertFalse(TC3.isPathIn(lista));
		Assert.assertEquals(TC3.toString(), "{50, {20, {10, ∅, ∅}, {30, ∅, ∅}}, {80, {70, ∅, ∅}, {90, ∅, ∅}}}");
	}

	@Test
	public void testisPathIn3(){
		List<Integer> lista =new ArrayList<Integer>();
		lista.add(null);
		TE.isPathIn(lista);
		Assert.assertFalse(TE.isPathIn(lista));
		Assert.assertEquals(TE.toString(), "∅");
	}



	@Test
	public void testiteratorInorderImp() {
		Iterator<Integer> oIterador = TC3.iteratorInorden();
		ArrayList<Integer> lista=new ArrayList<Integer>();
		while(oIterador.hasNext()){
			lista.add(oIterador.next());
		}
		Assert.assertEquals("[10, 20, 30, 50, 70, 80, 90]", lista.toString());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testiteratorInorderImp2(){
		Iterator<Integer> oIterador = TE.iteratorInorden();
		oIterador.remove();
	}



	@Test
	public void testTagDescendTC4() {
		List<String> lista= new LinkedList<String>();
		TC3.parentChildPairsTagDescend(lista);
		Assert.assertEquals(lista.toString(), "[(80, 90), (80, 70), (50, 80), (50, 20), (20, 30), (20, 10)]");
		TC3.filterTags("descend");
		Assert.assertEquals("{50 [(descend, 4)], {20 [(descend, 6)], {10 [(descend, 7)], ∅, ∅}, {30 [(descend, 5)], ∅, ∅}}, {80 [(descend, 2)], {70 [(descend, 3)], ∅, ∅}, {90 [(descend, 1)], ∅, ∅}}}", TC3.toString());

	}
}


