package com.example.demo.service;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.User;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired 
	private StringRedisTemplate stringRedisTemplate; 
	
	@Autowired
	private MongoTemplate mongoTemplate; 
	
	@Override
	public boolean sendMsg(String countryCode, String phoneNum) {
			int appid = Integer.parseInt(stringRedisTemplate.opsForValue().get("appid"));
			String appkey = stringRedisTemplate.opsForValue().get("appkey");
			boolean flag = true;
 			//调用腾讯云短信的 API 
		    //String code = (int)(Math.random() * 9 + 1) * 1000 + ""; 
			String code = "8888";
		    SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
		    
			try {
				SmsSingleSenderResult result = ssender.send(0, countryCode, phoneNum, code + "是您的登录注册码，请于{2}分钟内注册。如非本人操作，请忽略此短信", "", "");
				stringRedisTemplate.opsForValue().set(phoneNum, code, 300, TimeUnit.SECONDS);
			} catch (Exception e) {
				flag = false;  
				e.printStackTrace();
			}
			return flag;
	}

	@Override
	public boolean verify(String phoneNum, String code) {
		
		boolean flag = false;
		
		//调用stringRedisTemplate
		String real_code = stringRedisTemplate.opsForValue().get(phoneNum);
		if (real_code != null && real_code.equals(code)) {
			flag = true;
		}
		System.out.println(flag);
		return flag;
	}

	@Override
	public void register(User user) {
		mongoTemplate.insert(user);
		
	}

	@Override
	public void updata(User user) {
		//如果数据不存在，就插入，如果存在就更新数据
		mongoTemplate.insert(user);
		
	}
	
	
	

}
