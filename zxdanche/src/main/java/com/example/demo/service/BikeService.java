package com.example.demo.service;

import java.util.List;

import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;

import com.example.demo.pojo.Bike;

public interface BikeService {

	public void save(Bike bike);

	public List<GeoResult<Bike>> findNear(double longtiude, double latitude);

}
