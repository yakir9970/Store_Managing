package model;

import java.util.Observable;
import java.util.Observer;

public class Customer implements Observer{
	private String name;
	private String phoneNumber;
	private boolean updates;

	public Customer(String name, String phone, boolean updates) {
		this.name = name;
		this.phoneNumber = phone;
		this.updates = updates;
	}

	public Customer() {
		
	}

	public String getName() {
		return this.name;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public boolean getUpdates() {
		return this.updates;
	}

	@Override
	public String toString() {
		return "Customer name: " + name + ",    phone number: " + phoneNumber + ",    updates: " + updates+"\n";
	}

	public String stringToFile() {
		
		return name + "," + phoneNumber + "," + updates;
	}

	
	public void update(Model o, Object arg) {
		o.confirm(this.name);
	}

	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
