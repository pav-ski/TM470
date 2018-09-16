package com.TM470.dao;

import java.util.List;

import com.TM470.domain.Guest;
import com.TM470.domain.Staff;
import com.TM470.domain.User;


public interface UserDAO {
	
	public void addUser(User user);
	public void updateUser(User user);
	public List<User> list();
	public User getUserById(int id);
	public void removeUser(int id);
	public Guest getGuest(int id);
	public Staff getStaff(int id);
	public void refresh(User user);

}
