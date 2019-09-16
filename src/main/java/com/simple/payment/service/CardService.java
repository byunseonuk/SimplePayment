package com.simple.payment.service;

import com.simple.payment.model.CardVO;

public interface CardService {

	void insertCard(CardVO cardvo);

	CardVO getCard(int user_id);

}
