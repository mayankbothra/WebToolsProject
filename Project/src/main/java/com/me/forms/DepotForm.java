package com.me.forms;

import javax.validation.Valid;

import com.me.pojo.Address;
import com.me.pojo.Depot;
import com.me.pojo.Manufacturer;
import com.me.pojo.Person;
import com.me.pojo.UserAccount;

public class DepotForm {
	
	@Valid
	private Person person;
	@Valid
	private Address address;
	@Valid
	private UserAccount userAccount;
//	@Valid
//	private Depot depot;
	
	public DepotForm(){
		person = new Person();
//		depot = new Depot();
		userAccount = new UserAccount();
		address= new Address();
	}
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
//	public Depot getDepot() {
//		return depot;
//	}
//
//	public void setDepot(Depot depot) {
//		this.depot = depot;
//	}

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
