package com.simple.payment.dao;

import java.util.List;

import com.simple.payment.model.BeverageVO;

public interface BeverageDAO {

	List<BeverageVO> getBeverageList();

	BeverageVO getBeverage(int beverage_id);

}
