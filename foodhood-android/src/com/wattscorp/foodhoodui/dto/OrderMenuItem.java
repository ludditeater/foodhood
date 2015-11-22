package com.wattscorp.foodhoodui.dto;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.std.DateSerializer;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderMenuItem implements Parcelable {

	private String itemName;
	private String chefFirstName;
	private String chefLastName;
	private String chefHomeNum;
	private String chefStreetName;
	private String chefCity;
	private String chefZipcode;
	private String chefState;
	private String price;
	private String noOfItems;
	private String userFirstName;
	private String userLastName;
	private String paymentStatus;
	private String orderDate;
	

	public OrderMenuItem() {

	}

	private OrderMenuItem(Parcel in) {
		itemName = in.readString();
		chefFirstName = in.readString();
		chefLastName = in.readString();
		chefHomeNum = in.readString();
		chefStreetName = in.readString();
		chefCity = in.readString();
		chefZipcode = in.readString();
		chefState = in.readString();
		price = in.readString();
	}

	@JsonSerialize(using = DateSerializer.class)
	private Date chefItemAvailableFrom;

	@JsonSerialize(using = DateSerializer.class)
	private Date chefItemAvailableTill;
	private String imageSrc;
	private String chefId;

	public String getChefFirstName() {
		return chefFirstName;
	}

	public void setChefFirstName(String chefFirstName) {
		this.chefFirstName = chefFirstName;
	}

	public String getChefLastName() {
		return chefLastName;
	}

	public void setChefLastName(String chefLastName) {
		this.chefLastName = chefLastName;
	}

	public String getChefHomeNum() {
		return chefHomeNum;
	}

	public void setChefHomeNum(String chefHomeNum) {
		this.chefHomeNum = chefHomeNum;
	}

	public String getChefStreetName() {
		return chefStreetName;
	}

	public void setChefStreetName(String chefStreetName) {
		this.chefStreetName = chefStreetName;
	}

	public String getChefCity() {
		return chefCity;
	}

	public void setChefCity(String chefCity) {
		this.chefCity = chefCity;
	}

	public String getChefZipcode() {
		return chefZipcode;
	}

	public void setChefZipcode(String chefZipcode) {
		this.chefZipcode = chefZipcode;
	}

	public String getChefState() {
		return chefState;
	}

	public void setChefState(String chefState) {
		this.chefState = chefState;
	}

	public Date getChefItemAvailableFrom() {
		return chefItemAvailableFrom;
	}

	public void setChefItemAvailableFrom(Date chefItemAvailableFrom) {
		this.chefItemAvailableFrom = chefItemAvailableFrom;
	}

	public Date getChefItemAvailableTill() {
		return chefItemAvailableTill;
	}

	public void setChefItemAvailableTill(Date chefItemAvailableTill) {
		this.chefItemAvailableTill = chefItemAvailableTill;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public String getChefId() {
		return chefId;
	}

	public void setChefId(String chefId) {
		this.chefId = chefId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(itemName);
		dest.writeString(chefFirstName);
		dest.writeString(chefLastName);
		dest.writeString(chefHomeNum);
		dest.writeString(chefStreetName);
		dest.writeString(chefCity);
		dest.writeString(chefZipcode);
		dest.writeString(chefState);
		dest.writeString(price);
	}

	public static final Parcelable.Creator<OrderMenuItem> CREATOR = new Parcelable.Creator<OrderMenuItem>() {
		public OrderMenuItem createFromParcel(Parcel in) {
			return new OrderMenuItem(in);
		}

		public OrderMenuItem[] newArray(int size) {
			return new OrderMenuItem[size];
		}
	};

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
