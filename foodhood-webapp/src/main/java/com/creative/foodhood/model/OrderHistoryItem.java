package com.creative.foodhood.model;

import java.util.List;

public class OrderHistoryItem {
	private String orderId;
	private String paymentStatus;
	private String chefName;
	private String chefAddress1;
	private String chefAddress2;
	private String chefCityStateZipcode;
	private String chefId;
	private List<OrderHistorySubItem> orderEachHistoryItems;
	private String customerName;
	private String customerId;
	private String totalAmount;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<OrderHistorySubItem> getOrderEachHistoryItems() {
		return orderEachHistoryItems;
	}

	public void setOrderEachHistoryItems(List<OrderHistorySubItem> orderEachHistoryItems) {
		this.orderEachHistoryItems = orderEachHistoryItems;
	}

}
