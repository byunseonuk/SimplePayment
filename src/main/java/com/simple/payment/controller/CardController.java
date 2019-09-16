package com.simple.payment.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.payment.model.CardVO;
import com.simple.payment.service.CardService;

@Controller
public class CardController {
	
	@Autowired
	CardService cardService;
	
	@RequestMapping(value="/insertCard")
	public void insertCard(CardVO cardvo) {
		cardService.insertCard(cardvo);
	}
	
	@RequestMapping(value="/getCard")
	@ResponseBody
	public CardVO getCard(HttpServletRequest request) {
		int user_id = Integer.parseInt(request.getParameter("_id"));
		return cardService.getCard(user_id);
	}
}
