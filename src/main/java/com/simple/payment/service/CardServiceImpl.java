package com.simple.payment.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.payment.dao.CardDAO;
import com.simple.payment.model.CardVO;

@Service
public class CardServiceImpl implements CardService{
	
	@Autowired
	CardDAO cardDAO;

	@Override
	public void insertCard(CardVO cardvo) {
		cardDAO.insertCard(cardvo);
		
	}

	@Override
	public CardVO getCard(int user_id) {
		// TODO Auto-generated method stub
		return cardDAO.getCard(user_id);
	}
}
