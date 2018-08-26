package com.TM470.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TM470.dao.JobDAO;
import com.TM470.dao.UserDAO;
import com.TM470.domain.Element;
import com.TM470.domain.Job;
import com.TM470.domain.LocationArea;
import com.TM470.domain.User;


@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;

	@Autowired
	private JobDAO jobDAO;
	
	@Autowired
	private LocationAreaService areaService;
	

	private User sessionUser;
	
	public void update(User user) {
		userDAO.updateUser(user);
	}
	
	public User getById(int id) {
		return userDAO.getUserById(id);
	}

	
	
	public User getSessionUser() {
		return sessionUser;
	}

	public void setSessionUser(int id) {
		this.sessionUser = userDAO.getUserById(id);
	}
	
	public void reportIssue(String description,LocationArea area,Element element,int severity) {

		areaService.createJob(description, element,area, severity, getSessionUser());
		
	}
	
	
	
	
	
	

}
