package com.example.demo.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


//Bike这个类 和 MongoDB中的bikes Collection关联
@Document(collection="bikes")
public class Bike {
	
	//主键，唯一，可以建立索引 这里的id 对应的是 MongoDB中的 _id
	@Id
	private String id;
	
	//建立索引
	@Indexed
	private long   bikeNum;
	
	private double longitude;
	
	private double latitude;
	
	private int    status;
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}

	public long getBikeNum() {
		return bikeNum;
	}

	public void setBikeNum(long bikeNum) {
		this.bikeNum = bikeNum;
	}
	
	
}