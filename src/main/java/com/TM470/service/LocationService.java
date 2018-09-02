package com.TM470.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TM470.dao.LocationDAO;
import com.TM470.domain.Location;


@Service
public class LocationService {
	
	@Autowired
	private LocationDAO locationDAO;
	

	
	public void update(Location location) {
		locationDAO.updateLocation(location);
	}
	
	public Location getById(int id) {
		return locationDAO.getLocationById(id);
	}
	
	public List<Location> getLocations() {
		return locationDAO.list();
	}

	
	
	
	
	

}
