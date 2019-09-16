package com.simple.payment.dao;

import com.simple.payment.model.CardVO;

public interface CardDAO {

	void insertCard(CardVO cardvo);
	CardVO getCard(int user_id);
}
