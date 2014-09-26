package com.me.pojo;

import java.util.ArrayList;
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
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
public class Product {
		
    public static final String TV = "TV & Video";
    public static final String AUDIO = "Home Audio & Theatre";
    public static final String CAMERA = "Camera, Photo & Video";
    public static final String CELLPHONES = "Cell Phones & Accessories";
    public static final String LAPTOP = "Laptops, Tablets & Netbooks";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@NotEmpty(message="Product name cannot be empty")
	private String productName;
	@NotEmpty(message="Product type cannot be empty")
	private String productType;
	@NumberFormat(style=Style.CURRENCY)
	private int price;
	@NotEmpty(message="Description cannot be empty")
	private String description;
	private String fileName;
	
	@ManyToOne
	@JoinColumn(name="manufacturer_id")
	private Manufacturer manufacturer;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="product")
	private List<OrderItem> orderItemList;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="product")
	private List<DepotOrderItem> depotOrderItemList;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="product")
	private List<StockItem> stockItemList;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Manufacturer getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setStockItemList(ArrayList<StockItem> stockItemList) {
		this.stockItemList = stockItemList;
	}
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	public List<StockItem> getStockItemList() {
		return stockItemList;
	}
	public void setStockItemList(List<StockItem> stockItemList) {
		this.stockItemList = stockItemList;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public List<DepotOrderItem> getDepotOrderItemList() {
		return depotOrderItemList;
	}
	public void setDepotOrderItemList(List<DepotOrderItem> depotOrderItemList) {
		this.depotOrderItemList = depotOrderItemList;
	}
}
