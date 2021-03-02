package observer;


import java.util.ArrayList;

import model.Customer;

public class Promotion implements Sender{
	//singelton class
	private ArrayList<String> confirmed; //array that save all the names of the customer that gets promotion
	private static Promotion prom = new Promotion();
	private Promotion() {
		confirmed = new ArrayList<String>();
	}

	public static Promotion getProm() {
		return prom;
	}

	@Override
	public void sendPromotion(Customer toSend) {
		toSend.receivePromotion();		//send promotions to the resiver customer
	}

	public ArrayList<String> getconfirmed(){
		return confirmed;
	}

	@Override
	public void confirmedPromotion(Customer customer) {
		confirmed.add(customer.getName());	//adds the customer name 
	}


}
