package com.simple.payment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.payment.dao.FoodDAO;
import com.simple.payment.model.FoodVO;

@Service
public class FoodServiceImpl implements FoodService{

	@Autowired
	FoodDAO foodDAO;

	@Override
	public List<FoodVO> getFoodList() {
		// TODO Auto-generated method stub
		return foodDAO.getFoodList();
	}

	@Override
	public FoodVO getFoodList(int food_id) {
		// TODO Auto-generated method stub
		return foodDAO.getFood(food_id);
	}
}
