package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.example.demo.service.UserService;

@Controller
public class Usercontroller {
	
	@Autowired
	private UserService userService;//创建UserService的对象
	
	
	@RequestMapping("/user/genCode")
	@ResponseBody
	public boolean genVerifyCode(String countryCode, String phoneNum) {
		boolean flag = userService.sendMsg(countryCode, phoneNum);
		return flag;
	}
	
	@RequestMapping("/user/verify")
	@ResponseBody
	public boolean verify(String phoneNum, String code) {
		
		return userService.verify(phoneNum, code);
	}
	
	 
	
}