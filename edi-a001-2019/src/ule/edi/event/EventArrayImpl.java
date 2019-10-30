package ule.edi.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ule.edi.model.Configuration.Type;
import ule.edi.model.*;

public class EventArrayImpl implements Event {


	private String name;
	private Date date;

	private Double priceGold;    // precio de entradas tipo GOLD
	private Double priceSilver;  // precio de entradas tipo SILVER

	private int nGold;    // Nº de butacas de tipo GOLD
	private int nSilver;  // Nº de butacas de tipo SILVER

	private Seat[] gold;
	private Seat[] silver;



	public Double getPriceGold() {
		return priceGold;
	}


	public void setPriceGold(Double priceGold) {
		this.priceGold = priceGold;
	}


	public Double getPriceSilver() {
		return priceSilver;
	}


	public void setPriceSilver(Double priceSilver) {
		this.priceSilver = priceSilver;
	}


	public EventArrayImpl(String name, Date date, int nGold, int nSilver){

		// utiliza los precios por defecto: DEFAULT_PRICE_GOLD y DEFAULT_PRICE_SILVER definidos en Configuration.java

		// Debe crear los arrays de butacas gold y silver
		this.nGold =nGold;
		this.nSilver=nSilver;
		this.name=name;
		this.date=date;

		gold = new Seat[nGold];
		silver = new Seat[nSilver];

	}


	public EventArrayImpl(String name, Date date, int nGold, int nSilver, Double priceGold, Double priceSilver){

		this.nGold =nGold;
		this.nSilver=nSilver;
		this.name=name;
		this.date=date;
		this.priceGold=priceGold;
		this.priceSilver=priceSilver;

		gold = new Seat[nGold];
		silver = new Seat[nSilver]; 
	}

	@Override
	public String getName() {

		return name;
	}

	@Override
	public Date getDate() {

		return date;
	}


	@Override
	public int getNumberOfAttendingChildren() {
		int niños=0;

		for(int i=0; i<nGold; i++){
			if(gold[i]!=null){
				if(0<= gold[i].getHolder().getAge() &&gold[i].getHolder().getAge()< Configuration.CHILDREN_EXMAX_AGE){
					niños++;
				}
			}
		}
		for(int i=0; i<nSilver; i++){
			if(silver[i]!=null){
				if(0< silver[i].getHolder().getAge() && silver[i].getHolder().getAge() < Configuration.CHILDREN_EXMAX_AGE){
					niños++;
				}
			}
		}
		return niños ;
	}

	@Override
	public int getNumberOfAttendingAdults() {
		int adultos=0;
		int i;

		for(i=0; i<nGold; i++){
			if(gold[i]!=null){
				if(gold[i].getHolder().getAge()>=Configuration.CHILDREN_EXMAX_AGE && gold[i].getHolder().getAge()<Configuration.ELDERLY_PERSON_INMIN_AGE ){
					adultos++;
				}
			}
		}
		for(i=0; i<nSilver; i++){
			if(silver[i]!=null){
				if(silver[i].getHolder().getAge()>Configuration.CHILDREN_EXMAX_AGE && silver[i].getHolder().getAge()<Configuration.ELDERLY_PERSON_INMIN_AGE ){
					adultos++;
				}
			}
		}
		return adultos;
	}

	@Override
	public int getNumberOfAttendingElderlyPeople() {
		int ancianos=0;
		int i;
		for(i=0; i<nGold; i++){
			if(gold[i]!=null){
				if(gold[i].getHolder().getAge()>=Configuration.ELDERLY_PERSON_INMIN_AGE){
					ancianos++;
				}
			}
		}
		for(i=0; i<nSilver; i++){
			if(silver[i]!=null){
				if(silver[i].getHolder().getAge()>=Configuration.ELDERLY_PERSON_INMIN_AGE){
					ancianos++;
				}
			}
		}
		return ancianos++;
	}

	@Override
	public int getNumberOfSoldSeats() {

		return getNumberOfSoldGoldSeats()+getNumberOfSoldSilverSeats();
	}

	@Override
	public int getNumberOfSoldGoldSeats() {
		int soldGold=0;
		for(int i=0; i<nGold; i++){
			if(gold[i]!=null){
				soldGold++;
			}
		}
		return soldGold;
	}

	@Override
	public int getNumberOfSoldSilverSeats() {

		int soldSilver=0;
		for(int i=0; i<nGold; i++){
			if(silver[i]!=null){
				soldSilver++;
			}
		}
		return soldSilver;
	}

	@Override
	public int getNumberOfSeats() {

		return nGold+nSilver;
	}

	@Override
	public int getNumberOfGoldSeats() {

		return nGold;
	}

	@Override
	public int getNumberOfSilverSeats() {

		return nSilver;
	}


	@Override
	public int getNumberOfAvailableSeats() {
		int availableSeats=0;

		for(int i=0; i<nGold; i++){
			if(gold[i]==null){
				availableSeats++;
			}
		}
		for(int j=0; j<nSilver; j++){
			if(silver[j]==null){
				availableSeats++;
			}
		}
		return availableSeats;
	}

	@Override
	public Seat getSeat(int pos, Type type) {
		if(type==Configuration.Type.GOLD){
			if(pos-1<nGold){
				if(gold[pos-1]!=null){
					return gold[pos-1];
				}
			}
		}
		if(type==Configuration.Type.SILVER){
			if(pos-1<nSilver){
				if(silver[pos-1]!=null){
					return silver[pos-1];
				}
			}
		}
		return null;
	}


	@Override
	public Person refundSeat(int pos, Type type) {
		Person personDev;
		if(type==Configuration.Type.GOLD){
			if(pos-1<=nGold){ 
				if(gold[pos-1]!=null){
					personDev=gold[pos-1].getHolder();
					gold[pos-1]= null;
					return personDev;	
				}	
			}	
		}
		if(type==Configuration.Type.SILVER){
			if(pos-1<=nSilver){
				if(silver[pos-1]!=null){
					personDev=silver[pos-1].getHolder();
					silver[pos-1]=null;
					return personDev;
				}
			}
		}

		return null;
	}


	@Override
	public boolean sellSeat(int pos, Person p, Type type) {
		if(type==Configuration.Type.GOLD){
			if(pos-1<nGold){
				if(gold[pos-1]==null){
					gold[pos-1]= new Seat(this,pos,type, p);
					return true;
				}
			}
		}
		if(type==Configuration.Type.SILVER){
			if(pos-1<nSilver){
				if(silver[pos-1]==null){
					silver[pos-1]= new Seat(this,pos,type,p);
					return true;
				}
			}
		}
		return false;	
	}

	@Override
	public List<Integer> getAvailableGoldSeatsList() {
		List<Integer> listaGold = new ArrayList<Integer>();
		for(int i=0; i<nGold; i++){
			if(gold[i]==null){
				listaGold.add(i+1);
			}
		}			
		return listaGold;
	}

	@Override
	public List<Integer> getAvailableSilverSeatsList() {
		List<Integer> listaSilver = new ArrayList<Integer>();
		for(int i=0; i<nSilver; i++){
			if(silver[i]==null){
				listaSilver.add(i+1);
			}
		}			
		return listaSilver;
	}


	@Override
	public Double getPrice(Seat seat) {
		double price=0;
		if(seat.getType()==Configuration.Type.GOLD){
			price= getPriceGold();		
		}
		if(seat.getType()==Configuration.Type.SILVER){
			price= getPriceSilver();	
		}
		return price;
	}


	@Override
	public Double getCollectionEvent() {
		Double importeTotal;

		importeTotal= (getNumberOfSoldSilverSeats()*priceSilver)+(getNumberOfSoldGoldSeats()*priceGold);

		return importeTotal;

	}


	@Override
	public int getPosPersonGold(Person p) {
		int posGold=1;
		for (int i=0; i<nGold; i++){
			if(gold[i]!=null){
				if(p.getNif().equals(gold[i].getHolder().getNif())){
					posGold++;
					return posGold;
				}

			}
		}
		return -1;
	}


	@Override
	public int getPosPersonSilver(Person p) {
		int i;
		int posSilver=1;
		for (i=0; i<nSilver; i++){
			if(silver[i]!=null){
				if(p.getNif().equals(silver[i].getHolder().getNif())){
					posSilver++;
					return posSilver;
				}
			}
		}
		return -1;
	}


	@Override
	public boolean isGold(Person p) {
		for(int i=0; i<nGold; i++){
			if(gold[i]!=null){
				if(p.getNif().equals(gold[i].getHolder().getNif())){
					return true;
				}
			}
		}
		return false;

	}

	@Override
	public boolean isSilver(Person p) {
		int i;
		for(i=0; i<nSilver; i++){
			if(silver[i]!=null){
				if(p.getNif().equals(silver[i].getHolder().getNif())){
					return true;
				}
			}
		}
		return false;
	}
}
