package com.simple.payment.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simple.payment.model.BeverageVO;

@Repository
public class BeverageDAOImpl implements BeverageDAO{
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<BeverageVO> getBeverageList() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("beverageMapper.getBeverageList");
	}

	@Override
	public BeverageVO getBeverage(int beverage_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("beverageMapper.getBeverage",beverage_id);
	}
}
