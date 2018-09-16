package com.TM470.dao;

import java.util.List;

import com.TM470.domain.LocationArea;

public interface LocationAreaDAO {
	
	public void addLocationArea(LocationArea locationArea);
	public void updateLocationArea(LocationArea locationArea);
	public List<LocationArea> list();
	public LocationArea getLocationAreaById(int id);
	public void removeLocationArea(int id);
	public void refresh(LocationArea area);
	
	

}
