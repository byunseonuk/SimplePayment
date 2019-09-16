package com.simple.payment.dao;

import java.util.List;

import com.simple.payment.model.OrderVO;
import com.simple.payment.model.PaymentVO;
import com.simple.payment.model.UserVO;

public interface UserDAO {

	public int createUser(UserVO uservo);

	public UserVO getUser(String email);

	public boolean checkID(String email);

	public List<OrderVO> getOrderList();

	public int insertPayment(PaymentVO p);

	public int orderProduct(OrderVO order);

	public void completeOrder(int _id);

}
