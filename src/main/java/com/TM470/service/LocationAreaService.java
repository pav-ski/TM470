package com.TM470.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TM470.dao.LocationAreaDAO;
import com.TM470.domain.Element;
import com.TM470.domain.Job;
import com.TM470.domain.LocationArea;
import com.TM470.domain.Staff;
import com.TM470.domain.User;


@Service
public class LocationAreaService {
	
	@Autowired
	private LocationAreaDAO locationAreaDAO;
	
	@Autowired
	private JobService jobService;
	
	public void update(LocationArea area) {
		locationAreaDAO.updateLocationArea(area);
	}

	
	
	public void createJob(String description,Element element,LocationArea area, int severity, User user) {
		Job job = new Job(description,element,area,severity,user);
		System.out.println("Area service element score :" + element.getScore());
		System.out.println("Area service area score :" + area.getRoomScore());
		
		jobService.saveOrUpdate(job);
		

		
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
