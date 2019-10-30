package ule.edi.event;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.*;

import ule.edi.model.*;
import ule.edi.model.Configuration.Type;

public class EventArrayImplTests {

	private DateFormat dformat = null;
	private EventArrayImpl e;
	private EventArrayImpl eve;

	private Date parseLocalDate(String spec) throws ParseException {
		return dformat.parse(spec);
	}

	public EventArrayImplTests() {

		dformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	@Before
	public void testBefore() throws Exception{
		e = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 10, 100);
		eve= new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 10, 100, 150.00, 50.00);

	}
	@Test
	public void testSellSeat() throws Exception{
		Person persona=new Person( "Alice","10203040A", 34);

		Assert.assertEquals(true,e.sellSeat(10, persona, Configuration.Type.GOLD));
		Assert.assertEquals(false, e.sellSeat(300,persona, Configuration.Type.GOLD));
		Assert.assertEquals(false, e.sellSeat(10, persona, Configuration.Type.GOLD));
		Assert.assertEquals(true, e.sellSeat(1, persona, Configuration.Type.GOLD));

		Assert.assertEquals(true,e.sellSeat(10, persona, Configuration.Type.SILVER));
		Assert.assertEquals(false, e.sellSeat(300,persona, Configuration.Type.SILVER));
		Assert.assertEquals(false, e.sellSeat(10, persona, Configuration.Type.SILVER));
		Assert.assertEquals(true,e.sellSeat(1, persona, Configuration.Type.SILVER));


	}
	@Test
	public void testRefundSeat() throws Exception{
		Person persona1=new Person("Alice","10203040A",33);
		Person persona2=new Person("Manuel","10203041A",35);
		e.sellSeat(3, persona1, Configuration.Type.GOLD); 
		e.sellSeat(10, persona2, Configuration.Type.SILVER);  
		Assert.assertEquals(null, e.refundSeat(4, Configuration.Type.GOLD));
		Assert.assertEquals(null, e.refundSeat(7, Configuration.Type.SILVER)); 
		Assert.assertEquals(persona1.getNif(), e.refundSeat(3, Configuration.Type.GOLD).getNif());
		Assert.assertEquals(persona2.getNif(), e.refundSeat(10, Configuration.Type.SILVER).getNif());	
		Assert.assertEquals(e.refundSeat(130, Configuration.Type.SILVER), null);
		Assert.assertEquals(e.refundSeat(130, Configuration.Type.GOLD), null);
	}


	@Test
	public void testSomething() throws Exception {
		Assert.assertEquals(e.getNumberOfAttendingAdults(), 0);
		//Assert.assertEquals(e.getNumberOfAvailableSeats(),110);
		//Assert.assertEquals(e.getNumberOfSilverSeats(), 100);
		//Assert.assertEquals(e.getNumberOfGoldSeats(), 10);
		//Assert.assertEquals(e.getNumberOfSeats(), 110);

	}

	@Test
	public void testgetNumberOfSeats() throws Exception {
		Event  ep = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 0, 0);
		Event  ep1 = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 2, 2);
		Assert.assertEquals(ep.getNumberOfSeats(), 0);
		Assert.assertEquals(ep1.getNumberOfSeats(), 4);

	}
	@Test
	public void testgetNumberOfGoldSeats() throws Exception {
		Assert.assertEquals(e.getNumberOfGoldSeats(), 10);

	}
	@Test
	public void testgetNumberOfSilverSeats() throws Exception {
		Assert.assertEquals(e.getNumberOfSilverSeats(), 100);

	}
	@Test
	public void testgetNumberOfAvailableSeats() throws Exception {
		Assert.assertEquals(e.getNumberOfAvailableSeats(),110);
		e.sellSeat(3, null, Configuration.Type.GOLD); 
		e.sellSeat(10, null, Configuration.Type.SILVER);

		Assert.assertEquals(e.getNumberOfAvailableSeats(), 108);

	}

	@Test
	public void testSellSeatAdult() throws Exception{
		Person persona1=new Person("Alice","10203040A",13);
		Person persona2=new Person("Manuel","10203041A",35); 
		Person persona3=new Person("Manuel","10203042A",65);
		Person persona4=new Person("Alice","10203043A",13);
		Person persona5=new Person("Manuel","10203044A",35); 
		Person persona6=new Person("Manuel","10203045A",65);

		Assert.assertEquals(e.getNumberOfAttendingAdults(), 0); 
		e.sellSeat(3, persona1, Configuration.Type.GOLD); 
		e.sellSeat(4, persona2, Configuration.Type.GOLD);
		e.sellSeat(5, persona3, Configuration.Type.GOLD);
		e.sellSeat(10, persona4, Configuration.Type.SILVER); 
		e.sellSeat(11, persona5, Configuration.Type.SILVER); 
		e.sellSeat(12, persona6, Configuration.Type.SILVER); 

		Assert.assertEquals(e.getNumberOfAttendingAdults(), 2);

	}
	@Test
	public void testSellSeatChildren() throws Exception{	
		Person persona1=new Person("Alice","10203040A",-1);
		Person persona2=new Person("Manuel","10203041A",13); 
		Person persona3=new Person("Manuel","10203042A",14); 

		Assert.assertEquals(e.getNumberOfAttendingChildren(), 0);
		e.sellSeat(3, persona1, Configuration.Type.GOLD); 
		e.sellSeat(4, persona2, Configuration.Type.GOLD);
		e.sellSeat(10, persona3, Configuration.Type.GOLD); 
		e.sellSeat(3, persona1, Configuration.Type.SILVER); 
		e.sellSeat(4, persona2, Configuration.Type.SILVER);
		e.sellSeat(10, persona3, Configuration.Type.SILVER); 
		Assert.assertEquals(e.getNumberOfAttendingChildren(), 2);

	}

	@Test 
	public void testSellSeatElderyPerson() throws Exception{	
		Person persona1=new Person("Alice","10203040A",99);
		Person persona2=new Person("Manuel","10203041A", 63);

		Assert.assertEquals(e.getNumberOfAttendingElderlyPeople(), 0);
		e.sellSeat(1, persona1, Configuration.Type.GOLD); 
		e.sellSeat(4, persona2, Configuration.Type.GOLD);
		e.sellSeat(10, persona1, Configuration.Type.SILVER);
		e.sellSeat(11, persona2, Configuration.Type.SILVER);
		
		Assert.assertEquals(e.getNumberOfAttendingElderlyPeople(), 2);

	}

	@Test
	public void testGetNumberOfSoldSeats() throws Exception{
		e.sellSeat(1, new Person("Alice","10203041A", 30),Configuration.Type.GOLD);
		e.sellSeat(1, new Person("Alice","10203041A", 30),Configuration.Type.SILVER);	
		Assert.assertEquals(e.getNumberOfSoldSeats(), 2);			
	}

	@Test
	public void testgetNumberOfSoldGoldSeats() throws Exception{
		Assert.assertEquals(e.getNumberOfSoldGoldSeats(), 0);
		Event  e = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"),2,2);
		e.sellSeat(1, new Person("Alice","10203043A", 10),Type.GOLD);
		e.sellSeat(1, new Person("1010", "AA", 10), Configuration.Type.GOLD);
		Assert.assertEquals(e.getNumberOfSoldGoldSeats(), 1);	
		e.sellSeat(2, new Person("1010", "AA", 10), Configuration.Type.GOLD);
		Assert.assertEquals(e.getNumberOfSoldGoldSeats(), 2);	
	}
	@Test
	public void testgetNumberOfSoldSilverSeats() throws Exception{
		Assert.assertEquals(e.getNumberOfSoldSilverSeats(), 0);
		Event  e = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"),2,2);
		e.sellSeat(1, new Person("Alice","10203043A", 10),Type.SILVER);
		e.sellSeat(1, new Person("1010", "AA", 10), Configuration.Type.SILVER);
		Assert.assertEquals(e.getNumberOfSoldSilverSeats(), 1);	
		e.sellSeat(2, new Person("1010", "AA", 10), Configuration.Type.SILVER);
		Assert.assertEquals(e.getNumberOfSoldSilverSeats(), 2);	

	}
	@Test
	public void testGetAvailableSilverSeatsListBasic() throws Exception{
		Event  ep = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 2, 2);
		ep.sellSeat(1, new Person("1010", "AA", 10), Configuration.Type.SILVER);
		Assert.assertEquals(ep.getAvailableSilverSeatsList().toString(), "[2]");
		ep.sellSeat(2, new Person("1010", "AA", 10), Configuration.Type.SILVER);
		Assert.assertEquals(ep.getAvailableSilverSeatsList().toString(), "[]");

	}
	@Test
	public void testGetAvailableGoldSeatsListBasic() throws Exception{
		Event  ep = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 2, 2);
		ep.sellSeat(1, new Person("1010", "AA", 10), Configuration.Type.GOLD);
		Assert.assertEquals(ep.getAvailableGoldSeatsList().toString(), "[2]");
		ep.sellSeat(2, new Person("1010", "AA", 10), Configuration.Type.GOLD);
		Assert.assertEquals(ep.getAvailableGoldSeatsList().toString(), "[]");
	}
	@Test
	public void testGetPrice() throws Exception{
		e.setPriceGold(10.00);
		double priceG =10.00;
		Seat asientoG = new Seat(null,2,Configuration.Type.GOLD,null);
		Assert.assertEquals(e.getPrice(asientoG), priceG,0);
		e.setPriceSilver(5.00);
		double priceS =5.00;
		Seat asientoS = new Seat(null,2,Configuration.Type.SILVER,null);
		Assert.assertEquals(e.getPrice(asientoS), priceS,0);	
	}
	@Test
	public void testGetCollectionEvent() throws Exception{
		e.setPriceGold(10.00);
		double priceG =10.00;
		e.setPriceSilver(5.00);
		double priceS =5.00;
		e.sellSeat(1, new Person("Alice","10203041A", 30),Configuration.Type.GOLD);
		e.sellSeat(1, new Person("Alice","10203041A", 30),Configuration.Type.SILVER);	
		Assert.assertEquals(e.getCollectionEvent(), priceG+priceS, 2);
	}

	@Test
	public void testIsGold() throws Exception{

		Person persona=new Person("Alice","10203043A",34);
		Person persona1=new Person("Alice","10203041A",34);

		e.sellSeat(1, persona , Configuration.Type.GOLD);
		e.sellSeat(1, persona1 , Configuration.Type.SILVER);
		Assert.assertEquals(true,e.isGold (persona));
		Assert.assertEquals(false,e.isGold(persona1));



	}
	@Test
	public void testIsSilver() throws Exception{
		Person persona=new Person("Alice","10203043A",34);
		Person persona1=new Person("Alice","10203041A",34);

		e.sellSeat(1, persona , Configuration.Type.SILVER);
		e.sellSeat(1, persona1 , Configuration.Type.GOLD);
		Assert.assertEquals(true,e.isSilver (persona));
		Assert.assertEquals(false,e.isSilver(persona1));

	}
	@Test
	public void testGetSeat() throws Exception{

		Assert.assertEquals(null, e.getSeat(1, Configuration.Type.GOLD));
		Assert.assertEquals(null, e.getSeat(4, Configuration.Type.SILVER));

		e.sellSeat(1, new Person("Alice","10203041A", 30) , Configuration.Type.GOLD);
		Assert.assertEquals(e.getSeat(1, Configuration.Type.GOLD).getPosition(), 1);
		Assert.assertEquals(e.getSeat(1, Configuration.Type.GOLD).getType(), Configuration.Type.GOLD);

		e.sellSeat(2, new Person("Alice","10203041A", 30) , Configuration.Type.SILVER);
		Assert.assertEquals(e.getSeat(2, Configuration.Type.SILVER).getPosition(), 2);
		Assert.assertEquals(e.getSeat(2, Configuration.Type.SILVER).getType(), Configuration.Type.SILVER);

		Event  ep = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 2, 2);
		ep.sellSeat(1, new Person("Alice","10203041A", 30) , Configuration.Type.SILVER);
		ep.sellSeat(1, new Person("Alice","10203041A", 30) , Configuration.Type.GOLD);
		ep.sellSeat(2, new Person("Alice","10203041A", 30) , Configuration.Type.SILVER);
		ep.sellSeat(2, new Person("Alice","10203041A", 30) , Configuration.Type.GOLD);

		Assert.assertEquals(ep.getSeat(3, Configuration.Type.SILVER), null);
		Assert.assertEquals(ep.getSeat(3, Configuration.Type.GOLD), null);



	}
	@Test
	public void testEquals() throws Exception{
		Person persona1=new Person("Alice","10203043A",34);
		Person persona=new Person("Alice","10203041A",34);
		Person persona2=new Person("Alice","10203041A",34);

		Assert.assertTrue(persona.equals(persona2));
		Assert.assertFalse(persona.equals(persona1));	
	} 

	@Test
	public void testgetPosPersonGold() throws Exception{
		Person persona=new Person("Alice","10203043A",34);
		e.sellSeat(2, persona , Configuration.Type.GOLD);
		Assert.assertEquals(e.getPosPersonGold(persona), 2);
		Person persona2=new Person("Alice","10203044A",34);
		Assert.assertEquals(e.getPosPersonGold(persona2),-1);

	}
	@Test
	public void testgetPosPersonSilver() throws Exception{
		Person persona=new Person("Alice","10203041A",34);
		e.sellSeat(2, persona, Configuration.Type.SILVER);
		Assert.assertEquals(e.getPosPersonSilver(persona), 2);
		Person persona2=new Person("Alice","10203044A",34);
		Assert.assertEquals(e.getPosPersonSilver(persona2),-1);
	}

	@Test
	public void testgetname() throws Exception{

		Person persona=new Person("Alice","10203043A",34);
		Assert.assertEquals(persona.getName(), "Alice");		
		Event  ep = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 2, 2);
		Assert.assertEquals(ep.getName(), "The Fabulous Five");
	}
	@Test
	public void testgetDate() throws Exception{
		Event  ep = new EventArrayImpl("The Fabulous Five", parseLocalDate("24/02/2018 17:00:00"), 2, 2);
		Assert.assertEquals(ep.getDate(), parseLocalDate("24/02/2018 17:00:00"));
	}

}
