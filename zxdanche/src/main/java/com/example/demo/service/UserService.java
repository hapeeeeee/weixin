package com.example.demo.service;

import com.example.demo.pojo.User;

public interface UserService {

	

	boolean sendMsg(String countryCode, String phoneNum);//对象中的属性。方法

	boolean verify(String phoneNum, String code);

	void register(User user);

	void updata(User user);

}