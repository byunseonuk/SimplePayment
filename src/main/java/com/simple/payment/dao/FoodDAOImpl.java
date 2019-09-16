package com.simple.payment.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simple.payment.model.FoodVO;

@Repository
public class FoodDAOImpl implements FoodDAO{
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<FoodVO> getFoodList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("foodMapper.getFoodList");
	}

	@Override
	public FoodVO getFood(int food_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("foodMapper.getFood",food_id);
	}
	
}
