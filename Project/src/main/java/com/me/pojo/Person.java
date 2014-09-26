package com.me.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Person {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@NotEmpty(message="First name cannot be empty")
	private String fName;
	@NotEmpty(message="Last name cannot be empty")
	private String lName;
	@NotEmpty(message="Email cannot be empty")
//	@Email(message="Not in Email format")
	private String email;
	@NotEmpty(message="Contact cannot be empty")
	private String contact;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="person")
	private Address address;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="person")
	private List<OrderDetails> orderList;
	
	@ManyToOne
	@JoinColumn(name="manufacturer_id")
	private Manufacturer manufacturer;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="person")
	private UserAccount userAccount;
	
//	@OneToOne(cascade=CascadeType.ALL, mappedBy="person")
//	private Depot depot;
	
	
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<OrderDetails> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<OrderDetails> orderList) {
		this.orderList = orderList;
	}
	public Manufacturer getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	public UserAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
//	public Depot getDepot() {
//		return depot;
//	}
//	public void setDepot(Depot depot) {
//		this.depot = depot;
//	}
}
