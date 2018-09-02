package com.TM470.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TM470.dao.LocationAreaDAO;
import com.TM470.domain.Element;
import com.TM470.domain.LocationArea;


@Service
public class LocationAreaService {
	
	@Autowired
	private LocationAreaDAO locationAreaDAO;
	
	public void update(LocationArea area) {
		locationAreaDAO.updateLocationArea(area);
	}

	

	
	public LocationArea getById(int id) {
		return locationAreaDAO.getLocationAreaById(id);
	}
	
	
	public void updateAreaScore(LocationArea area) {
		Double score = 0.0;
		for(Element eachElement:area.getHasElements()) {
			System.out.println("score =" + eachElement.getScore());
			score = score + eachElement.getScore();
		}
		System.out.println("final :" + score + "divided by " + area.getHasElements().size());
		area.setRoomScore(score/area.getHasElements().size());
		System.out.println(" = " + area.getRoomScore());
		update(area);
	}
	
	

}
