package com.simple.payment.service;

import java.util.List;

import com.simple.payment.model.BeverageVO;

public interface BeverageService {

	List<BeverageVO> getBeverageList();

	BeverageVO getBeverage(int beverage_id);

}
