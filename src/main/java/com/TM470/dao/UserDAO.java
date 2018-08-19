package com.TM470.dao;

import java.util.List;

import com.TM470.domain.User;


public interface UserDAO {
	
	public void addUser(User user);
	public void updateUser(User user);
	public List<User> list();
	public User getUserById(int id);
	public void removeUser(int id);

}
