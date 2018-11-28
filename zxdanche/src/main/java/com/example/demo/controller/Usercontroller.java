package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.pojo.User;
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
	
	@RequestMapping("/user/register")
	@ResponseBody
	public boolean reg(@RequestBody User user) {
		boolean flag = true;
		//调用service将用户数据保存起来
		try {
			userService.register(user);
		}catch(Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	@RequestMapping("/user/deposit")
	@ResponseBody
	public boolean deposit(@RequestBody User user) {
		boolean flag = true;
		try {
			userService.updata(user);
		}catch(Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	 
	
}
