package com.simple.payment.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simple.payment.model.OrderVO;
import com.simple.payment.model.PaymentVO;
import com.simple.payment.model.UserVO;

@Repository
public class UserDAOImpl implements UserDAO{

	@Autowired
	SqlSession sqlSession;
	

	@Override
	public int createUser(UserVO uservo) {
		sqlSession.insert("userMapper.createUser", uservo);
		return uservo.get_id();
		
	}

	@Override
	public UserVO getUser(String email) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("userMapper.getUser",email);
	}

	@Override
	public boolean checkID(String email) {
		UserVO user = sqlSession.selectOne("userMapper.getUser",email);
		System.out.println("CheckID result : "+user);
		if(user==null)
			return false;
		return true;
	}

	@Override
	public List<OrderVO> getOrderList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("userMapper.getOrderList");
	}

	@Override
	public int insertPayment(PaymentVO p) {
		// TODO Auto-generated method stub
		sqlSession.insert("userMapper.insertPayment",p);
		return p.get_id();
	}

	@Override
	public int orderProduct(OrderVO order) {
		sqlSession.insert("userMapper.orderProduct",order);
		return order.get_id();
	}

	@Override
	public void completeOrder(int _id) {
		sqlSession.delete("userMapper.completeOrder",_id);
	}

}
