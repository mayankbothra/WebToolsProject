package com.me.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class DepotOrderItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int quantity;
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
		
	@ManyToOne
	@JoinColumn(name="depotOrderDetails_id")
	private DepotOrderDetails depotOrderDetails;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public DepotOrderDetails getDepotOrderDetails() {
		return depotOrderDetails;
	}
	public void setDepotOrderDetails(DepotOrderDetails depotOrderDetails) {
		this.depotOrderDetails = depotOrderDetails;
	}
}
