package com.giffgaff.web.entity;

import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_details")
public class Order {

	@Id
	@GeneratedValue
	private int orderid;
	


	private String user;
	
	 
	   @OneToOne(fetch = FetchType.LAZY, optional = false)
		  @JoinColumn(name = "mobile_id", nullable = false) 
	private Mobile mobile;
	
	
	public Order() {
		
	}

	public Mobile getMobile() {
		return mobile;
	}

	public void setMobile(Mobile mobile) {
		this.mobile = mobile;
	}
	
	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	
	
	
}
