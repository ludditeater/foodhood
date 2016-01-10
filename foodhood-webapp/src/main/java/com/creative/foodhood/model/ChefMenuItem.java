package com.creative.foodhood.model;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

public class ChefMenuItem {

	private String itemName;
	private String chefFirstName;
	private String chefLastName;
	private String chefHomeNum;
	private String chefStreetName;
	private String chefCity;
	private String chefZipcode;
	private String chefState;
	private String price;
	
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	

}
