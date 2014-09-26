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

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Depot {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
//	@Column(unique=true)
	private int id;
	@NotEmpty(message="Select Location")
	private String location;
	
	@ManyToOne
	@JoinColumn(name="manufacturer_id")
	private Manufacturer manufacturer;
	
	@OneToOne
	@JoinColumn(name="person_id")
	private Person person;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="depot")
	private List<StockItem> stockItemList;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="depot")
	private List<DepotOrderDetails> depotOrderDetails;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Manufacturer getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	public List<StockItem> getStockItemList() {
		return stockItemList;
	}
	public void setStockItemList(List<StockItem> stockItemList) {
		this.stockItemList = stockItemList;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public List<DepotOrderDetails> getDepotOrderDetails() {
		return depotOrderDetails;
	}
	public void setDepotOrderDetails(List<DepotOrderDetails> depotOrderDetails) {
		this.depotOrderDetails = depotOrderDetails;
	}
}
