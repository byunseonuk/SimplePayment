package com.simple.payment.model;

public class OrderProductVO {
	private int owner_id;
	private int product_id;
	private String atoken;
	private int amount;
	public int getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getAtoken() {
		return atoken;
	}
	public void setAtoken(String atoken) {
		this.atoken = atoken;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
