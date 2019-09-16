package com.simple.payment.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import com.simple.payment.dao.CardDAO;
import com.simple.payment.dao.UserDAO;
import com.simple.payment.model.CardVO;
import com.simple.payment.model.OrderVO;
import com.simple.payment.model.PaymentVO;
import com.simple.payment.model.UserVO;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDAO userDAO;
	@Autowired
	CardDAO cardDAO;
    
	@Override
	public int createUser(UserVO uservo) {
		return userDAO.createUser(uservo);
		
	}

	@Override
	public int login(UserVO uservo) {
		UserVO user = userDAO.getUser(uservo.getEmail());
		if(user!=null) {
			if(user.getPassword().equals(uservo.getPassword())){
				return user.get_id();
			}else {
				return 0;
			}
		}else {
			return 0;
		}
	}

	@Override
	public JSONObject getToken() {
		String rtoken = getReToken();
		//System.out.println("rtoken : "+rtoken);
		String atoken;
		JSONObject jo = new JSONObject();
		if(!rtoken.equals("false")) {
			atoken = getAcToken(rtoken);
			//System.out.println("atoken : "+atoken);
			if(!atoken.equals("false")) {
				jo.put("rtoken", rtoken);
				jo.put("atoken", atoken);
			}else {
				jo.put("error", "false");
			}
		}
		else
			jo.put("error", "false");
		
		//System.out.println(jo);
		return jo;
	}
	
	public String getReToken() {
		RestTemplate rt = new RestTemplate();
		StringBuffer sb = new StringBuffer();
		String toReturn=null;
		String input = "kisa01";
		try {
			  MessageDigest digest = MessageDigest.getInstance("SHA-512");
			  digest.reset();
			  digest.update(input.getBytes("utf8"));
			  toReturn = String.format("%040x", new BigInteger(1, digest.digest()));
		}catch(Exception e) {
			throw new RuntimeException(e);
		}

		MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
		map.add("pw", toReturn);
		ResponseEntity<JSONObject> rejo = rt.postForEntity("http://54.180.42.30:8888/api/auth/kisa01",map,JSONObject.class);

		JSONObject jo2 = rejo.getBody();
		
		int errCode = (int)jo2.get("errCode");

		if(errCode==0) {
			return (String)jo2.get("rtoken");
		}
		return "false";
	}
	
	public String getAcToken(String rtoken) {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<JSONObject> acjo = rt.getForEntity("http://54.180.42.30:8888/api/token/kisa01?rt="+rtoken, JSONObject.class);

		JSONObject jo = acjo.getBody();
		int errCode = (int)jo.get("errCode");
		if(errCode==0) {
			return (String) jo.get("token");
		}
		return "false";
	}

	@Override
	public boolean checkID(String email) {
		
		return userDAO.checkID(email);
	}

	@Override
	public List<OrderVO> getOrderList() {
		
		return userDAO.getOrderList();
	}


	@Override
	public String requestPayment(String atoken, int owner_id, int amount) {
		RestTemplate rt = new RestTemplate();
		CardVO card = cardDAO.getCard(owner_id);
		System.out.println(card);
		MultiValueMap<String,Object> map = new LinkedMultiValueMap<>();
		map.add("token", atoken);
		map.add("cardno", card.getCardno());
		map.add("amount", amount);
		map.add("exprY", card.getExprY());
		map.add("exprM", card.getExprM());
		map.add("quota", "00");
		map.add("bno", card.getBno());
		map.add("card2pw", card.getCard2pw());
		ResponseEntity<JSONObject> rejo = rt.postForEntity("http://54.180.42.30:8888/api/payment/receipt/card/kisa01",map,JSONObject.class);
		System.out.println("Payment result : "+rejo);
		JSONObject jo = rejo.getBody();
		System.out.println("Payment jsonBody : "+jo);
		int errCode = (int)jo.get("errCode");
		if(errCode==0) {
			PaymentVO p = new PaymentVO();
			p.setApp_no((String)jo.get("app_no"));
			p.setOwner(owner_id);
			p.setTno((String)jo.get("tno"));
			int pno = userDAO.insertPayment(p);
			System.out.println("pno : "+pno);
			if(pno!=0) {
				return p.getTno();
			}else {
				return "false";
			}
		}
		return Integer.toString(errCode);
	}

	@Override
	public int orderProduct(OrderVO order) {
		order.setState("ready");
		return userDAO.orderProduct(order);
	}

	@Override
	public JSONObject getReceipt(int tno, String atoken) {
		RestTemplate rt = new RestTemplate();
		String url = "http://54.180.42.30:8888/api/payment/receipt/card/kisa01/";
		url=url+tno+"?token="+atoken;
		ResponseEntity<JSONObject> rejo = rt.getForEntity(url,JSONObject.class);
		JSONObject jo = rejo.getBody();
		return jo;
	}
	
	@Override
	public JSONObject getReceiptList(String atoken) {
		RestTemplate rt = new RestTemplate();
		String url = "http://54.180.42.30:8888/api/payment/receipt/card/kisa01";
		url=url+"?token="+atoken;
		ResponseEntity<JSONObject> rejo = rt.getForEntity(url,JSONObject.class);
		JSONObject jo = rejo.getBody();
		return jo;
	}

	@Override
	public void completeOrder(int _id) {
		userDAO.completeOrder(_id);

	}

	

}
