package com.TM470.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	private NotificationService notificationService;
	
	@Autowired
	private ElementService elementService;
	
	@Autowired
	private LocationAreaService locationAreaService;
	
	@Autowired
	private JobService jobService;
	

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
	
	//UC1 Report Issue
	public void postJob(String description,int isFaulty, int severity, int isFor) {
		
		//Get user in current session
		User user = getSessionUser();
		
		//Get the element by its id
		Element element = elementService.getById(isFaulty);
		
		//Get the location area by its id
		LocationArea area = locationAreaService.getById(isFor);
		
		//Pre-condition checks for reportIssue()
		assert area != null;
		assert (element.getIsIn().equals(area));
		assert severity >=1 && severity <=5;
		assert user != null;
		assert element != null;
		
		//forward reportIssue message to jobService for object creation
		jobService.reportIssue(description, area, element, severity, user);
		
		//Update and refresh user
		this.update(user);
		this.refresh(user);

	}
	
	public void refresh(User user) {
	
		userDAO.refresh(user);
	}

}
