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
	
	
	
	//UC11 Update Score
	public void adjustElementScore(Element element,double newScore) {
		
		//Set the new score
		element.setScore(newScore);
		
		//Update Repository
		update(element);
		
		//Post-condition checks
		assert element.getScore()==newScore;
		
		//Pre-condition check for updateAreaScore
		assert element.getIsIn() != null;
		
		//Notify areaService to update LocationArea score in which element was located 
		areaService.updateAreaScore(element.getIsIn());
		
		
		
	}
	
	//Below method was used to revert scores after tests
	public void resetElementScore(Element element) {
		double score = 5.0;
		element.setScore(score);
		areaService.updateAreaScore(element.getIsIn());
		update(element);
	}
	

}
