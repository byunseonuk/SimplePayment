package com.simple.payment.service;

import java.util.List;

import com.simple.payment.model.FoodVO;

public interface FoodService {

	List<FoodVO> getFoodList();

	FoodVO getFoodList(int food_id);

}
