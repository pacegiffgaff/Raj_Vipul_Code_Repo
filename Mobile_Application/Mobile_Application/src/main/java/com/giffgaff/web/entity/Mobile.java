package com.giffgaff.web.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "mobile")
public class Mobile {
	
	@Id
	@GeneratedValue
	private int id;
	@Size(min=5, message="Enter at least 5 Characters...")
	private String model;
    private String brand;
    private Long price;
    
    @OneToOne(mappedBy = "mobile", fetch = FetchType.LAZY, cascade =CascadeType.ALL)
	private Order order;
    
    public Order getOrder() {
		return order;
	}



	public void setOrder(Order order) {
		this.order = order;
	}



	public Mobile() {
    	
    }
    
    
    
	public Mobile(String model, String brand,Long price) {
		super();
		this.model = model;
		this.brand = brand;
		this.price = price;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
    
    
   
    
}