package com.TM470.dao;

import java.util.List;

import com.TM470.domain.Location;

public interface LocationDAO {
	
	public void addLocation(Location location);
	public void updateLocation(Location location);
	public List<Location> list();
	public Location getLocationById(int id);
	public void removeLocation(int id);
	
	

}
