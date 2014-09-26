package com.me.forms;

import javax.validation.Valid;

import com.me.pojo.Address;
import com.me.pojo.Manufacturer;
import com.me.pojo.Person;
import com.me.pojo.UserAccount;

public class ManufacturerForm {
	
	@Valid
	private Person person;
	@Valid
	private Manufacturer manufacturer;
	@Valid
	private Address address;
	@Valid
	private UserAccount userAccount;
	
	public ManufacturerForm(){
		person = new Person();
		manufacturer = new Manufacturer();
		userAccount = new UserAccount();
		address= new Address();
	}
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Manufacturer getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
}
