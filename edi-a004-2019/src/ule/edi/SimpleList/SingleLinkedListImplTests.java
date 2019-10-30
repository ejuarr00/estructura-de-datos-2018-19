package ule.edi.SimpleList;



import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ule.edi.model.Person;



;


public class SingleLinkedListImplTests {



	private SingleLinkedListImpl<String> lS;
	private SingleLinkedListImpl<String> lSABC;
	private SingleLinkedListImpl<Person> listaPersona;
	private SingleLinkedListImpl<Person> listaPersona1;
	private SingleLinkedListImpl<Person> listaPersona2;


	@Before
	public void setUp() {
		this.lS = new SingleLinkedListImpl<String>();
		this.lSABC = new SingleLinkedListImpl<String>("A", "B", "C");
		this.listaPersona= new SingleLinkedListImpl<Person>();
		this.listaPersona1= new SingleLinkedListImpl<Person>();
		this.listaPersona2= new SingleLinkedListImpl<Person>();

	}

	@Test
	public void constructorElemens(){
		lS=new SingleLinkedListImpl<String>("A", "B", "C", "D");
		Assert.assertEquals("[A, B, C, D]", lS.toString());
		Assert.assertEquals("[A, B, C, D]", lS.toString());
	}
	@Test
	public void addLast(){
		//System.out.println(lS.toString()+"añadirfinal0");
		lS.addLast("A");
		//System.out.println(lS.toString()+"añadirfinal1");
		Assert.assertEquals("[A]", lS.toString());
		lS.addLast("B");
		//System.out.println(lS.toString()+"añadirfinal1");
		Assert.assertEquals("[A, B]", lS.toString());
		lSABC.addLast("Z");
		//System.out.println(lSABC.toString()+"añadirfinal1.1");
		Assert.assertEquals("[A, B, C, Z]", lSABC.toString()); 
	}
	@Test
	public void addLastPerson(){
		Person persona1= new Person("Eduardo", "12345678A", 22);
		Person persona2= new Person("Juan", "12345677A", 23);
		listaPersona.addLast(persona1);
		listaPersona.addLast(persona2);
		Assert.assertEquals("[{ NIF: 12345678A  Name : Eduardo, Age:22}, { NIF: 12345677A  Name : Juan, Age:23}]", listaPersona.toString()); 
		//System.out.println(listaPersona.toString()+"listapersonalast");	
	}

	@Test
	public void size(){
		Assert.assertEquals(lS.size(), 0);
		//System.out.println(lS.size()+"tamañols");
		Assert.assertEquals(lSABC.size(), 3);
		//System.out.println(lSABC.size()+"tamañolsabc");
	}
	@Test
	public void sizePerson(){
		Person persona1= new Person("Eduardo", "12345678A", 22);
		Person persona2= new Person("Juan", "12345677A", 23);
		Assert.assertEquals(listaPersona.size(), 0);
		listaPersona.addFirst(persona1);
		Assert.assertEquals(listaPersona.size(), 1);
		listaPersona.addFirst(persona2);
		Assert.assertEquals(listaPersona.size(), 2);
		//System.out.println(listaPersona.size()+"listapersonatamaño");
	}

	@Test
	public void addFirst(){
		//System.out.println(lS.toString()+"añadirprincipio0");
		lS.addFirst("A");
		//System.out.println(lS.toString()+"añadirprincipio1");
		Assert.assertEquals("[A]", lS.toString());

		//System.out.println(lSABC.toString()+"añadirprincipio0.1");
		lSABC.addFirst("Z");
		//System.out.println(lSABC.toString()+"añadirprincipio1.1");
		Assert.assertEquals("[Z, A, B, C]", lSABC.toString());

	}
	@Test
	public void addFirstPerson(){
		Person persona1= new Person("Eduardo", "12345678A", 22);
		Person persona2= new Person("Juan", "12345677A", 23);
		listaPersona.addFirst(persona1);
		//System.out.println(listaPersona.toString()+"listapersonafirs1");
		Assert.assertEquals("[{ NIF: 12345678A  Name : Eduardo, Age:22}]", listaPersona.toString());
		listaPersona.addFirst(persona2);
		//System.out.println(listaPersona.toString()+"listapersonafirs2");	
		Assert.assertEquals("[{ NIF: 12345677A  Name : Juan, Age:23}, { NIF: 12345678A  Name : Eduardo, Age:22}]", listaPersona.toString());
	}

	@Test
	public void addAtPos(){
		lS.addAtPos("B", 1);
		Assert.assertEquals("[B]", lS.toString());
		//System.out.println(lS+ "listaposicionprincipio");
		lS.addAtPos("A", 1);
		Assert.assertEquals("[A, B]", lS.toString());
		//System.out.println(lS+ "listaposicionprincipio");
		lS.addAtPos("D", 5);
		Assert.assertEquals("[A, B, D]", lS.toString());
		//System.out.println(lS+ "listaposicionfinal");
		lS.addAtPos("Z", 2);
		//System.out.println(lS+ "listamedioposicion2");
		Assert.assertEquals("[A, Z, B, D]", lS.toString());
		lS.addAtPos("X", 3);
		//System.out.println(lS+ "listamedioposicion3");
		Assert.assertEquals("[A, Z, X, B, D]", lS.toString());

	}
	@Test
	public void addAtPosPerson(){
		Person persona1= new Person("Eduardo", "12345678A", 22);
		Person persona2= new Person("Juan", "12345677A", 23);
		Person persona3= new Person("Laura", "12345666A", 24);
		Person persona4= new Person("Maria", "12345555A", 25);

		listaPersona.addAtPos(persona1,1);
		//System.out.println(listaPersona.toString()+"listapersonapos1");
		Assert.assertEquals("[{ NIF: 12345678A  Name : Eduardo, Age:22}]", listaPersona.toString());
		listaPersona.addAtPos(persona2, 1);
		//System.out.println(listaPersona.toString()+"listapersonapos2");	
		Assert.assertEquals("[{ NIF: 12345677A  Name : Juan, Age:23}, { NIF: 12345678A  Name : Eduardo, Age:22}]", listaPersona.toString());
		listaPersona.addAtPos(persona3, 1);
		//System.out.println(listaPersona.toString()+"listapersonapos3");	
		Assert.assertEquals("[{ NIF: 12345666A  Name : Laura, Age:24}, { NIF: 12345677A  Name : Juan, Age:23}, { NIF: 12345678A  Name : Eduardo, Age:22}]", listaPersona.toString());
		listaPersona.addAtPos(persona4, 2);
		//System.out.println(listaPersona.toString()+"listapersonapos4");	
		Assert.assertEquals("[{ NIF: 12345666A  Name : Laura, Age:24}, { NIF: 12345555A  Name : Maria, Age:25}, { NIF: 12345677A  Name : Juan, Age:23}, { NIF: 12345678A  Name : Eduardo, Age:22}]", listaPersona.toString());


	}

	@Test(expected = IllegalArgumentException.class)
	public void addAtPos1()throws EmptyCollectionException{
		lS.addAtPos("A", -1);
	}


	@Test
	public void addNTimes(){
		lS.addNTimes("A", 3);
		//System.out.println(lS.toString()+"3vecesA");
		Assert.assertEquals("[A, A, A]", lS.toString());
		lS.addNTimes("B", 2);
		//System.out.println(lS.toString()+"3vecesA+2vecesB");
		Assert.assertEquals("[A, A, A, B, B]", lS.toString());
	}
	@Test
	public void addNTimesPerson(){
		Person persona1= new Person("Eduardo", "12345678A", 22);
		listaPersona.addNTimes(persona1,2);
		//System.out.println(listaPersona.toString()+"listapersonaNtimes1");
		Assert.assertEquals("[{ NIF: 12345678A  Name : Eduardo, Age:22}, { NIF: 12345678A  Name : Eduardo, Age:22}]", listaPersona.toString());		
	}


	@Test(expected = IllegalArgumentException.class)
	public void addNTimes1() throws EmptyCollectionException{
		lS.addNTimes("A", -1);
	}


	@Test
	public void indexOf(){
		//System.out.println(lSABC.toString()+"listaindexof0");
		Assert.assertEquals(lSABC.indexOf("A"),1);
		lSABC.addLast("A");
		//System.out.println(lSABC.toString()+"listaindexof2");
		Assert.assertEquals(lSABC.indexOf("A"),1);  
	}
	@Test
	public void indexOfPerson(){
		Person persona1= new Person("Eduardo", "12345678A", 22);
		Person persona2= new Person("Juan", "12345677A", 23);
		Person persona3= new Person("Laura", "12345666A", 24);
		Person persona4= new Person("Maria", "12345555A", 25);
		listaPersona.addLast(persona1);
		listaPersona.addLast(persona2);
		listaPersona.addLast(persona3);
		listaPersona.addLast(persona4);
		//System.out.println(listaPersona.toString()+"listapersonaIndexOf0");	
		Assert.assertEquals(listaPersona.indexOf(persona1),1);
		listaPersona.addLast(persona1);
		//System.out.println(listaPersona.toString()+"listapersonaIndexOf1");
		Assert.assertEquals(listaPersona.indexOf(persona1),1);
	}



	@Test(expected = NoSuchElementException.class)
	public void indexOf1()throws EmptyCollectionException{
		lSABC.indexOf("Z");   
	}

	@Test
	public void removeLast() throws EmptyCollectionException{
		lS.addLast("A");
		//System.out.println(lS.toString()+"removeLast0");
		Assert.assertEquals(lS.removeLast(),"A"); 
		//System.out.println(lS.toString()+"removeLast1");

		Assert.assertEquals(lSABC.removeLast(),"C");
		//System.out.println(lSABC.toString()+"removeLast2");
		Assert.assertEquals(lSABC.removeLast(),"B");
		//System.out.println(lSABC.toString()+"removeLast3");
		Assert.assertEquals(lSABC.removeLast(),"A");
		//System.out.println(lSABC.toString()+"removeLast4");
	}
	@Test
	public void removeLastPerson() throws EmptyCollectionException{
		Person persona1= new Person("Eduardo", "12345678A", 22);
		Person persona2= new Person("Juan", "12345677A", 23);
		Person persona3= new Person("Laura", "12345666A", 24);
		Person persona4= new Person("Maria", "12345555A", 25);

		listaPersona.addLast(persona1);
		Assert.assertEquals("[{ NIF: 12345678A  Name : Eduardo, Age:22}]", listaPersona.toString());
		//System.out.println(listaPersona.toString()+"listapersonaremovelast0");
		listaPersona.removeLast();
		//System.out.println(listaPersona.toString()+"listapersonaremovelast1");
		Assert.assertEquals("[]", listaPersona.toString());

		listaPersona.addLast(persona2);
		listaPersona.addLast(persona3);
		listaPersona.addLast(persona4);
		//System.out.println(listaPersona.toString()+"listapersonaremovelast0.1");
		Assert.assertEquals("[{ NIF: 12345677A  Name : Juan, Age:23}, { NIF: 12345666A  Name : Laura, Age:24}, { NIF: 12345555A  Name : Maria, Age:25}]", listaPersona.toString());
		listaPersona.removeLast();
		//System.out.println(listaPersona.toString()+"listapersonaremovelast1.1");
		Assert.assertEquals("[{ NIF: 12345677A  Name : Juan, Age:23}, { NIF: 12345666A  Name : Laura, Age:24}]", listaPersona.toString());


	}

	@Test(expected = EmptyCollectionException.class)
	public void removeLast1() throws EmptyCollectionException{
		lS.removeLast();  

	}
	@Test
	public void removeLast2() throws EmptyCollectionException{//el del elemento
		lS.addFirst("Z");
		//System.out.println(lS.toString()+"listaeliminarultimo0");
		Assert.assertEquals("[Z]", lS.toString());
		Assert.assertEquals(lS.removeLast("Z"),"Z");
		Assert.assertEquals("[]", lS.toString());
		//System.out.println(lS.toString()+"listaeliminarultimo0.1");

		lS = new SingleLinkedListImpl<String>("A", "C", "B", "C", "D", "C");
		Assert.assertEquals("[A, C, B, C, D, C]", lS.toString());
		//System.out.println(lS.toString()+"listaeliminarultimo0.2");
		Assert.assertEquals(lS.removeLast("C"),"C");
		//System.out.println(lS.toString()+"listaeliminarultimo1");
		Assert.assertEquals("[A, C, B, C, D]", lS.toString());
		Assert.assertEquals(lS.removeLast("B"),"B");
		//System.out.println(lS.toString()+"listaeliminarultimo2");
		Assert.assertEquals("[A, C, C, D]", lS.toString());
		Assert.assertEquals(lS.removeLast("A"),"A");
		//System.out.println(lS.toString()+"listaeliminarultimo3");
		Assert.assertEquals("[C, C, D]", lS.toString());

	}
	@Test
	public void removeLastPerson2() throws EmptyCollectionException{//el del elemento
		Person persona1= new Person("Eduardo", "12345678A", 22);
		Person persona2= new Person("Juan", "12345677A", 23);
		Person persona3= new Person("Laura", "12345666A", 24);
		Person persona4= new Person("Maria", "12345555A", 25);
		Person persona5= new Person("Sheila", "12545555A", 20);

		listaPersona.addLast(persona1);
		Assert.assertEquals("[{ NIF: 12345678A  Name : Eduardo, Age:22}]", listaPersona.toString());
		//System.out.println(listaPersona.toString()+"listapersonaremovelast0");
		listaPersona.removeLast(persona1);
		//System.out.println(listaPersona.toString()+"listapersonaremovelast1");
		Assert.assertEquals("[]", listaPersona.toString());

		listaPersona.addLast(persona2);
		listaPersona.addLast(persona4);
		listaPersona.addLast(persona3);
		listaPersona.addLast(persona4);
		listaPersona.addLast(persona5);
		listaPersona.addLast(persona4);
		//System.out.println(listaPersona.toString()+"listapersonaremovelast0.1");
		Assert.assertEquals("[{ NIF: 12345677A  Name : Juan, Age:23}, { NIF: 12345555A  Name : Maria, Age:25}, { NIF: 12345666A  Name : Laura, Age:24}, { NIF: 12345555A  Name : Maria, Age:25}, { NIF: 12545555A  Name : Sheila, Age:20}, { NIF: 12345555A  Name : Maria, Age:25}]", listaPersona.toString());

		listaPersona.removeLast(persona3);
		//System.out.println(listaPersona.toString()+"listapersonaremovelast1.1");
		Assert.assertEquals("[{ NIF: 12345677A  Name : Juan, Age:23}, { NIF: 12345555A  Name : Maria, Age:25}, { NIF: 12345555A  Name : Maria, Age:25}, { NIF: 12545555A  Name : Sheila, Age:20}, { NIF: 12345555A  Name : Maria, Age:25}]", listaPersona.toString());
		listaPersona.removeLast(persona4);
		//System.out.println(listaPersona.toString()+"listapersonaremovelast1.2");
		Assert.assertEquals("[{ NIF: 12345677A  Name : Juan, Age:23}, { NIF: 12345555A  Name : Maria, Age:25}, { NIF: 12345555A  Name : Maria, Age:25}, { NIF: 12545555A  Name : Sheila, Age:20}]", listaPersona.toString());

	}




	@Test(expected = EmptyCollectionException.class)
	public void removeLast3() throws EmptyCollectionException{
		//System.out.println(lS+"listaeliminarelemento11");
		lS.removeLast("A");  
	}
	@Test(expected = NoSuchElementException.class)
	public void removeLast4() throws EmptyCollectionException{
		//System.out.println(lS+"listaeliminarelemento11");
		lSABC.removeLast("Z");  
	}

	@Test
	public void reverse(){
		lS= new SingleLinkedListImpl<String>("A", "B", "C");
		AbstractSingleLinkedListImpl<String> listaalreves= lS.reverse();
		Assert.assertEquals("[C, B, A]", listaalreves.toString()); 
	}
	@Test
	public void reversePerson(){
		Person persona2= new Person("Juan", "12345677A", 23);
		Person persona3= new Person("Laura", "12345666A", 24);
		Person persona4= new Person("Maria", "12345555A", 25);
		Person persona5= new Person("Sheila", "12545555A", 20);

		listaPersona.addLast(persona2);
		listaPersona.addLast(persona4);
		listaPersona.addLast(persona3);
		listaPersona.addLast(persona4);
		listaPersona.addLast(persona5);
		listaPersona.addLast(persona4);
		System.out.println(listaPersona.toString()+"listapersonareverse0");
		Assert.assertEquals("[{ NIF: 12345677A  Name : Juan, Age:23}, { NIF: 12345555A  Name : Maria, Age:25}, { NIF: 12345666A  Name : Laura, Age:24}, { NIF: 12345555A  Name : Maria, Age:25}, { NIF: 12545555A  Name : Sheila, Age:20}, { NIF: 12345555A  Name : Maria, Age:25}]", listaPersona.toString());
		AbstractSingleLinkedListImpl<Person> listaalrevespersona= listaPersona.reverse();
		System.out.println(listaalrevespersona.toString()+"listapersonareverse1");
		Assert.assertEquals("[{ NIF: 12345555A  Name : Maria, Age:25}, { NIF: 12545555A  Name : Sheila, Age:20}, { NIF: 12345555A  Name : Maria, Age:25}, { NIF: 12345666A  Name : Laura, Age:24}, { NIF: 12345555A  Name : Maria, Age:25}, { NIF: 12345677A  Name : Juan, Age:23}]", listaalrevespersona.toString()); 
	}

	@Test
	public void testiterador() {
		lS = new SingleLinkedListImpl<String>("A", "B", "C", "D");
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
	public void testiterador1() {
		Iterator<String> i = lS.iterator();
		Assert.assertFalse(i.hasNext());
		i.next();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testiterador2(){
		Iterator<String> i =lS.iterator();
		i.remove();
	}

	// TEST DE SUBLIST
	@Test
	public void tesSubListEnListaVacia() {

		Assert.assertEquals(-1, lS.isSubList(lSABC));		
	}

	@Test
	public void tesSubListConSubListaVacia() {
		Assert.assertEquals(1, lSABC.isSubList(lS));		
	}


	@Test
	public void subListVarios() {
		lS = new SingleLinkedListImpl<String>("A", "B", "C", "D", "E");
		Assert.assertEquals(1, lS.isSubList(lSABC));	
		lS = new SingleLinkedListImpl<String>("Z", "X", "A", "B", "C", "D", "E");
		Assert.assertEquals(3, lS.isSubList(lSABC));	
		lS = new SingleLinkedListImpl<String>("Z", "X", "A", "B", "C");
		Assert.assertEquals(3, lS.isSubList(lSABC));	
		lS = new SingleLinkedListImpl<String>("A", "B", "C");
		Assert.assertEquals(1, lS.isSubList(lSABC));	
	}
	@Test
	public void subListVariosPerson() {
		Person persona1= new Person("Juana", "77345677A", 21);
		Person persona2= new Person("Juan", "12345677A", 23);
		Person persona3= new Person("Laura", "12345666A", 24);
		Person persona4= new Person("Maria", "12345555A", 25);
		Person persona5= new Person("Sheila", "12545555A", 20);

		listaPersona.addLast(persona1);
		listaPersona.addLast(persona2);
		listaPersona.addLast(persona3);
		listaPersona.addLast(persona4);
		listaPersona.addLast(persona5);
		//System.out.println(listaPersona.toString()+"listapersonasublist0");

		listaPersona1.addLast(persona1);
		listaPersona1.addLast(persona2);
		listaPersona1.addLast(persona3);
		//System.out.println(listaPersona1.toString()+"listapersonasublist0.0");
		Assert.assertEquals(1, listaPersona.isSubList(listaPersona1));

		listaPersona2.addLast(persona2);
		listaPersona2.addLast(persona3);
		listaPersona2.addLast(persona4);
		//System.out.println(listaPersona2.toString()+"listapersonasublist0.0.0");
		Assert.assertEquals(2, listaPersona.isSubList(listaPersona2));
	}




	@Test
	public void testclasepersona() {
		Person Persona1=new Person("Emilio","12345678A",22);
		Person Persona3=new Person("Emilio","12345678A",22);
		Person Persona4=new Person("Emilio","12345678A",22);
		Person Persona5=Persona1;

		Person Persona2=new Person("Eduardo","87654321K",28);

		Assert.assertTrue(Persona1.equals(Persona3));
		Assert.assertTrue(Persona1.equals(Persona4));
		Assert.assertTrue(Persona1.equals(Persona5));


		Assert.assertFalse(Persona1.equals(Persona2));
		Assert.assertFalse(Persona1.equals(lSABC));

		Persona1.setName("Juan");
		Persona1.setNif("71234567A");
		Persona1.setAge(33);
		Assert.assertEquals(Persona1.getName(),"Juan");
		Assert.assertEquals(Persona1.getNif(),"71234567A");
		Assert.assertEquals(Persona1.getAge(),33);
		Assert.assertEquals(Persona1.toString(),"{ NIF: 71234567A  Name : Juan, Age:33}");
	}




}
