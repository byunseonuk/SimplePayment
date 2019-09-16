package com.simple.payment.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.simple.payment.model.OrderVO;
import com.simple.payment.model.UserVO;

public interface UserService {

	public int createUser(UserVO uservo);

	public int login(UserVO uservo);

	public JSONObject getToken();

	public boolean checkID(String email);

	public List<OrderVO> getOrderList();

	public String getReToken();

	public String getAcToken(String rtoken);

	public int orderProduct(OrderVO order);

	public String requestPayment(String atoken, int owner_id, int amount);

	public JSONObject getReceipt(int tno, String atoken);

	public JSONObject getReceiptList(String atoken);

	public void completeOrder(int _id);

}
