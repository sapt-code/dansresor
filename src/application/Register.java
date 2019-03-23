package application;

import java.util.ArrayList;

public class Register {
	private ArrayList<Customer> customers;
	
	//Constructor
	public Register() {
		this.customers = new ArrayList<Customer>();
	}
	
	//Getters och setters
	public ArrayList<Customer> getCustomers() {
		return this.customers;
	}
	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}
	
	//Metoder
	public void addCustomer(Customer customer) {
		this.customers.add(customer);
	}
	
	public void removeCustomer(Customer customer) {
		this.customers.remove(customer);
	}
	
	//Lägg till findmetod genom filtrering
}
