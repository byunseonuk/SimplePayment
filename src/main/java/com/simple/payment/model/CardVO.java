package com.simple.payment.model;

public class CardVO {
	private int _id;
	private String cardno;
	private int owner;
	private String exprY;
	private String exprM;
	private String bno;
	private String card2pw;
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public int getOwner() {
		return owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}
	public String getExprY() {
		return exprY;
	}
	public void setExprY(String exprY) {
		this.exprY = exprY;
	}
	public String getExprM() {
		return exprM;
	}
	public void setExprM(String exprM) {
		this.exprM = exprM;
	}
	public String getBno() {
		return bno;
	}
	public void setBno(String bno) {
		this.bno = bno;
	}
	public String getCard2pw() {
		return card2pw;
	}
	public void setCard2pw(String card2pw) {
		this.card2pw = card2pw;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	@Override
	public String toString() {
		return "CardVO [_id=" + _id + ", cardno=" + cardno + ", owner=" + owner + ", exprY=" + exprY + ", exprM="
				+ exprM + ", bno=" + bno + ", card2pw=" + card2pw + "]";
	}
	
	
	
}
