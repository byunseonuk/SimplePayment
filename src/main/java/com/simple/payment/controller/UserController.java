package com.simple.payment.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.simple.payment.model.OrderProductVO;
import com.simple.payment.model.OrderVO;
import com.simple.payment.model.UserVO;
import com.simple.payment.service.UserService;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/testUser")
	@ResponseBody
	public String test(HttpServletRequest request) {
		String id = request.getParameter("identifier");
		System.out.println(id);
		
		return "ok";
	}

	@RequestMapping(value="/signup", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject signup(UserVO uservo) {
		int _id = userService.createUser(uservo);
		JSONObject jo = new JSONObject();
		if(_id!=0) {
			jo = userService.getToken();
			jo.put("_id", _id);
		}
		else
			jo.put("error", "false");
		return jo;
	}
	
	@RequestMapping(value="/checkID", method=RequestMethod.GET)
	@ResponseBody
	public String checkId(String email) {
		System.out.println("get Email: "+email);
		if(userService.checkID(email)) {
			return "false";
		}
		return "true";
		
	}
	
	@RequestMapping(value="/token")
	@ResponseBody
	public JSONObject testToken() {
		JSONObject jo = userService.getToken();
		return jo;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject login(UserVO user) {
		int _id = userService.login(user);
		System.out.println(user);
		JSONObject jo;
		if(_id!=0) {
			System.out.println("true");
			jo = userService.getToken();
			jo.put("_id", _id);
			System.out.println("at : "+jo.get("atoken"));
			return jo;
		}else {
			System.out.println("false");
			jo = new JSONObject();
			jo.put("error", "false");
			return jo;
		}
			
	}
	
	@RequestMapping(value="/restTest")
	@ResponseBody
	public void rest() {
		RestTemplate rt = new RestTemplate();
		ResponseEntity<JSONObject> jo = rt.postForEntity("http://54.180.42.30:8888/api/auth/kisa01",null,JSONObject.class);
		System.out.println(jo);
	}
	
	@RequestMapping(value="/getReToken", method=RequestMethod.POST)
	@ResponseBody
	public String getRToken() {
		String rtoken = userService.getReToken();
		return rtoken;
	}
	
	@RequestMapping(value="/getAcToken", method=RequestMethod.POST)
	@ResponseBody
	public String getAcToken(String rtoken) {
		String atoken = userService.getAcToken(rtoken);
		return atoken;
	}
	
	@RequestMapping(value="/orderProduct" , method=RequestMethod.POST)
	@ResponseBody
	public int orderProduct(OrderProductVO opVO) {
		String tno = userService.requestPayment(opVO.getAtoken(),opVO.getOwner_id(),opVO.getAmount());
		
		System.out.println(tno);
		JSONObject json;
		OrderVO order = new OrderVO();
		order.setOwner_id(opVO.getOwner_id());
		order.setProduct_id(opVO.getProduct_id());
		int ono = userService.orderProduct(order);
		System.out.println(ono);
		if(ono!=0 && !tno.equals("false")) {
			//json = userService.getReceipt(tno, (String)map.get("atoken"));
			//if(!json.containsKey("errCode")) {
			//
			//}
			
			return ono;
		}else {
			return -1;
		}
	}

	
	@RequestMapping(value="/getReceipt")
	@ResponseBody
	public JSONObject getReceipt(HttpServletRequest request) {
		int tno = Integer.parseInt(request.getParameter("tno"));
		String atoken = request.getParameter("atoken");
		JSONObject json = userService.getReceipt(tno, atoken);
		return json;
	}
	
	@RequestMapping(value="/getReceiptList")
	@ResponseBody
	public List<JSONObject> getReceiptList(HttpServletRequest reqest) {
		String atoken = reqest.getParameter("atoken");
		System.out.println("atoken : "+atoken);
		JSONObject json = userService.getReceiptList(atoken);
		List<JSONObject> array = (List<JSONObject>) json.get("list");

		System.out.println("array : "+array);
		return array;
	}

}
