package com.TM470.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TM470.dao.ElementDAO;
import com.TM470.domain.Element;


@Service
public class ElementService {
	
	@Autowired
	private ElementDAO elementDAO;
	
	@Autowired 
	private LocationAreaService areaService;

	
	public void update(Element element) {
		elementDAO.updateElement(element);
	}

	public Element getById(int id) {
		return elementDAO.getElementById(id);
	}
	

	public void adjustFaultyElementScore(Element element,int severity) {
		double score = element.getScore();
		if(score-severity >= 0) {
			element.setScore(score);
		}
		else if(score-severity < 0) {
			element.setScore(0.0);
		}
		
		areaService.updateAreaScore(element.getIsIn());
		update(element);
		
		
		System.out.println("Updated area score is: " + element.getIsIn().getRoomScore() );
	}
	

}
