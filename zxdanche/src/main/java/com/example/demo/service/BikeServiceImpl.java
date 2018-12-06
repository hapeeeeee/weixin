package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metric;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.connection.RedisZSetCommands.Limit;
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

	@Override
	public List<GeoResult<Bike>> findNear(double longtiude, double latitude) {
//		return mongoTemplate.findAll(Bike.class);
		NearQuery nearQuery = NearQuery.near(longtiude, latitude);
		nearQuery.maxDistance(0.2, Metrics.KILOMETERS);
		GeoResults<Bike> geoResults = mongoTemplate.geoNear(nearQuery.query(new Query(Criteria.where("status").is(0)).limit(5)), Bike.class);  
		//GeoResult<Bike> geoResults = mongoTemplate.geoNear(nearQuery.query(new Query(Criteria)), Bike.class);
		return geoResults.getContent();
		
	}

	
	
}
