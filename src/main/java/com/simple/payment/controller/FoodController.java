package com.simple.payment.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.payment.model.FoodVO;
import com.simple.payment.model.UserVO;
import com.simple.payment.service.FoodService;


@Controller
public class FoodController {
	
	@Autowired
	FoodService foodService;
	
	@RequestMapping(value="/getFoodList")
	@ResponseBody
	public List<FoodVO> getFoodList(){
		return foodService.getFoodList();
	}
	
	@RequestMapping(value="/getFood")
	@ResponseBody
	public FoodVO getFood(HttpServletRequest request){
		int food_id = Integer.parseInt(request.getParameter("_id"));
		return foodService.getFoodList(food_id);
	}
}
