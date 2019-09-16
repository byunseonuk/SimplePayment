package com.simple.payment.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.payment.model.BeverageVO;
import com.simple.payment.service.BeverageService;

@Controller
public class BeverageController {
	
	@Autowired
	BeverageService beverageService;
	
	@RequestMapping(value="/getBeverageList")
	@ResponseBody
	public List<BeverageVO> getBeverageList(){
		return beverageService.getBeverageList();
	}
	
	@RequestMapping(value="/getBeverage")
	@ResponseBody
	public BeverageVO getBeverage(HttpServletRequest request){
		int beverage_id = Integer.parseInt(request.getParameter("_id"));
		return beverageService.getBeverage(beverage_id);
	}
	
}
