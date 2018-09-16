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
	
	
	//Method which prompts LocationArea provided as parameter to update its score
	public void updateAreaScore(LocationArea area) {
		
		//double value which will check post-condition
		double oldScore = area.getRoomScore();
		
		//Let area know to update its score
		area.updateScore();
		
		//Update the area
		update(area);
		
		//Post-condition check
		assert oldScore != area.getRoomScore();
		
		

		
		
	}
	
	

}
