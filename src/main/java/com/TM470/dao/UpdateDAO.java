package com.TM470.dao;

import java.util.List;

import com.TM470.domain.Location;
import com.TM470.domain.Update;

public interface UpdateDAO {
	
	public void addUpdate(Update update);
	public void updateUpdate(Update update);
	public List<Update> list();
	public Location getUpdateById(int id);
	public void removeUpdate(int id);
	
	

}
