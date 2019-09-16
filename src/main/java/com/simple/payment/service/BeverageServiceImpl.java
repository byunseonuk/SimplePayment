package com.simple.payment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.payment.dao.BeverageDAO;
import com.simple.payment.model.BeverageVO;

@Service
public class BeverageServiceImpl implements BeverageService{

	@Autowired
	BeverageDAO beverageDAO;

	@Override
	public List<BeverageVO> getBeverageList() {
		// TODO Auto-generated method stub
		return beverageDAO.getBeverageList();
	}

	@Override
	public BeverageVO getBeverage(int beverage_id) {
		// TODO Auto-generated method stub
		return beverageDAO.getBeverage(beverage_id);
	}
}
