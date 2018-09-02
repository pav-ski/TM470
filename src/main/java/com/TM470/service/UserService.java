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
	private JobService jobService;
	
	
	
	@Autowired
	private NotificationService notificationService;
	

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
		
		//Get user in current session
		User user = getSessionUser();
		
		//Create new Job object and assign attribute values
		Job job = new Job(description,element,area,severity,user);
		
		//userService updates current user
		this.update(user);
		
		
		//jobService is responsible for committing new job to database
		jobService.saveOrUpdate(job);
		
		
		
		//notificationService pushes through the notification
		notificationService.addNewJobNotification(description, area, job.getHasSubscribers());
		
		
		
		
	}
	
	
	
	
	
	

}
