package com.simple.payment.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simple.payment.model.CardVO;

@Repository
public class CardDAOImpl implements CardDAO{
	@Autowired
	SqlSession sqlSession;

	@Override
	public void insertCard(CardVO cardvo) {
		sqlSession.insert("cardMapper.insertCard",cardvo);
		
	}

	@Override
	public CardVO getCard(int user_id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("cardMapper.getCard",user_id);
	}
}
