package com.creative.foodhood.entity;

import java.util.Date;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table("order")
public class OrderedEntity {

	@PrimaryKey
	private String orderId;
	private String itemNames;
	private String chefName;
	private String chefAddress1;
	private String chefAddress2;
	private String chefCityStateZipcode;
	private String chefId;
	private Date chefItemAvailableTill;
	private String totalPrice;
	private String quantity;
	private String customerName;
	private String paymentStatus;
	private String customerId;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getItemNames() {
		return itemNames;
	}
	public void setItemNames(String itemNames) {
		this.itemNames = itemNames;
	}
	public String getChefName() {
		return chefName;
	}
	public void setChefName(String chefName) {
		this.chefName = chefName;
	}
	public String getChefAddress1() {
		return chefAddress1;
	}
	public void setChefAddress1(String chefAddress1) {
		this.chefAddress1 = chefAddress1;
	}
	public String getChefAddress2() {
		return chefAddress2;
	}
	public void setChefAddress2(String chefAddress2) {
		this.chefAddress2 = chefAddress2;
	}
	public String getChefCityStateZipcode() {
		return chefCityStateZipcode;
	}
	public void setChefCityStateZipcode(String chefCityStateZipcode) {
		this.chefCityStateZipcode = chefCityStateZipcode;
	}
	public String getChefId() {
		return chefId;
	}
	public void setChefId(String chefId) {
		this.chefId = chefId;
	}
	public Date getChefItemAvailableTill() {
		return chefItemAvailableTill;
	}
	public void setChefItemAvailableTill(Date chefItemAvailableTill) {
		this.chefItemAvailableTill = chefItemAvailableTill;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


}
