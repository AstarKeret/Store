package model;

import observer.Promotion;
import observer.Receiver;

public class Customer implements Receiver{
	private String name;
	private long phoneNumber;
	private boolean getPromotion;
	
	public Customer(String name, long phoneNumber, boolean getPromotion) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.getPromotion = getPromotion;
	}

	//default constructor
	public Customer() {
	}

	//copy constructor
	public Customer(Customer customer) {
		this(customer.getName(), customer.getPhoneNumber(), customer.getGetPromotion());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean getGetPromotion() {
		return getPromotion;
	}

	public void setGetPromotion(boolean getPromotion) {
		this.getPromotion = getPromotion;
	}

	@Override
	public void receivePromotion() {
		if(getPromotion)	//checks id the customer want to get promotions 
			Promotion.getProm().confirmedPromotion(this);		//send confirmation  to the promotion sender
	}
	
}
