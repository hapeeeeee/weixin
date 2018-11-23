package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.Bike;

@Service
public class BikeServiceImpl implements BikeService {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public void save(Bike bike) {
		//调用具体业务，在这里是把单车存入mongodb
		//mongoTemplate.insert(bike, "bikes");
		
		mongoTemplate.insert(bike);//在bike类中添加了注解，@Docunmeng =(bikes)	
		}

	
	
}
