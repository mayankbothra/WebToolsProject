package com.me.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Manufacturer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@NotEmpty(message="Company name cannot be empty")
	private String companyName;	
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="manufacturer")
	private List<Person> personList;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="manufacturer")
	private List<Product> productList;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="manufacturer")
	private List<Depot> depotList;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="manufacturer")
	private List<OrderDetails> orderDetailsList;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="manufacturer")
	private List<DepotOrderDetails> depotOrderDetails;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public List<Depot> getDepotList() {
		return depotList;
	}
	public void setDepotList(List<Depot> depotList) {
		this.depotList = depotList;
	}
	public List<Person> getPersonList() {
		return personList;
	}
	public void setPersonList(List<Person> personList) {
		this.personList = personList;
	}
	public List<OrderDetails> getOrderDetailsList() {
		return orderDetailsList;
	}
	public void setOrderDetailsList(List<OrderDetails> orderDetailsList) {
		this.orderDetailsList = orderDetailsList;
	}
	public List<DepotOrderDetails> getDepotOrderDetails() {
		return depotOrderDetails;
	}
	public void setDepotOrderDetails(List<DepotOrderDetails> depotOrderDetails) {
		this.depotOrderDetails = depotOrderDetails;
	}
}
