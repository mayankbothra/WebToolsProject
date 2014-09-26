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

@Entity
public class DepotOrderDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String status;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="depotOrderDetails")
	private List<DepotOrderItem> depotOrderItemList;
	
	@ManyToOne
	@JoinColumn(name="depot_id")
	private Depot depot;
	
	@ManyToOne
	@JoinColumn(name="manufacturer_id")
	private Manufacturer manufacturer;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Manufacturer getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	public Depot getDepot() {
		return depot;
	}
	public void setDepot(Depot depot) {
		this.depot = depot;
	}
	public List<DepotOrderItem> getDepotOrderItemList() {
		return depotOrderItemList;
	}
	public void setDepotOrderItemList(List<DepotOrderItem> depotOrderItemList) {
		this.depotOrderItemList = depotOrderItemList;
	}
	
	
}
