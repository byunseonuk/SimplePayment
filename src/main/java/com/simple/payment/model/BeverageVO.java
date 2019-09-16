package com.simple.payment.model;

public class BeverageVO {
	private int _id;
	private String beverage_name;
	private int price;
	private String image;
	
	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getBeverage_name() {
		return beverage_name;
	}

	public void setBeverage_name(String beverage_name) {
		this.beverage_name = beverage_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
