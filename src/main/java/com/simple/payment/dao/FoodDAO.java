package com.simple.payment.dao;

import java.util.List;

import com.simple.payment.model.FoodVO;

public interface FoodDAO {
	List<FoodVO> getFoodList();
	FoodVO getFood(int food_id);
}
