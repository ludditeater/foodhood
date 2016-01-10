package com.wattscorp.foodhoodui.dto;

import java.util.Date;

public class OrderHistorySubItem {

	private String orderItemDetailId;
	private String menuItemId;
	private String itemName;
	private String totalPrice;
	private String quantity;
	private Date chefItemAvailableTill;

	public String getOrderItemDetailId() {
		return orderItemDetailId;
	}

	public void setOrderItemDetailId(String orderItemDetailId) {
		this.orderItemDetailId = orderItemDetailId;
	}

	public String getMenuItemId() {
		return menuItemId;
	}

	public void setMenuItemId(String menuItemId) {
		this.menuItemId = menuItemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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

	public Date getChefItemAvailableTill() {
		return chefItemAvailableTill;
	}

	public void setChefItemAvailableTill(Date chefItemAvailableTill) {
		this.chefItemAvailableTill = chefItemAvailableTill;
	}

}
