package com.example.demo.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.pojo.Bike;
import com.example.demo.service.BikeService;

/*
 * 标记这个类是一个用于接收请求和相应的控制器
 * 加上@controller后，spring容器就会对他进行实例化
 * 
 * 
 * 
 */

@Controller
public class BikeController {
	
	//到spring容器中，查找BikeService类型的实例，然后注入BikeController中
	@Autowired
	private BikeService bikeService; 
	
	@RequestMapping("/bike/add")
	@ResponseBody
	public String add(@RequestBody Bike bike) {
		//调用service，将数据保存到MongoDB中
		bikeService.save(bike);
		return "success";
	}
	
	@RequestMapping("/bike/findNear")
	@ResponseBody
	public List<GeoResult<Bike>> findNear(double longtiude, double latitude) {
		//调用service，将数据保存到MongoDB中
		List<GeoResult<Bike>> bikes = bikeService.findNear(longtiude, latitude);
//		System.out.println(bikes);
//		System.out.println(1111);
		return bikes;
	}
}
