package application;

import java.util.ArrayList;

public class Customer {
	private String name;
	private String persNr;
	private String email;
	private String phoneNr;
	private String address;
	private String customerNr; //id
	private ArrayList<String> allergies;
	private String danceSkill;
	
	//Constructor
	public Customer(String name, String customerNr, String danceSkill) {
		this.name = name;
		this.customerNr = customerNr;
		this.allergies = new ArrayList<String>();
		this.danceSkill = danceSkill;
	}

	//Getter and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPersNr() {
		return persNr;
	}
	public void setPersNr(String persNr) {
		this.persNr = persNr;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNr() {
		return phoneNr;
	}
	public void setPhoneNr(String phoneNr) {
		this.phoneNr = phoneNr;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCustomerNr() {
		return customerNr;
	}
	public void setCustomerNr(String customerNr) {
		this.customerNr = customerNr;
	}
	public ArrayList<String> getAllergies() {
		return allergies;
	}
	public void setAllergies(ArrayList<String> allergies) {
		this.allergies = allergies;
	}
	public String getDanceSkill() {
		return danceSkill;
	}
	public void setDanceSkill(String danceSkill) {
		this.danceSkill = danceSkill;
	}
}
