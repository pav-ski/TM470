package com.TM470.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TM470.dao.UpdateDAO;
import com.TM470.domain.Job;
import com.TM470.domain.Update;
import com.TM470.domain.UpdateRequest;
import com.TM470.domain.User;


@Service
public class UpdateService {
	
	@Autowired
	private JobService jobService;

	@Autowired
	private UpdateDAO updateDAO;
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private UserService userService;
	
	
	//UC2 - Request Update
	public void requestUpdate(int id,String message) {
		
		//Locate job in repository for the id value
		Job job = jobService.getById(id);
		
		//Obtain user in current session
		User user = userService.getSessionUser();
		
		//Create new UpdateRequest object
		UpdateRequest update = new UpdateRequest();
		
		//pass on arguments to user who will initiate request update in the domain
		user.requestUpdate(job, message,update);
		
		//Commit changes to the database
		jobService.update(job);
		
		//As we are in updateService updateDAO can be accessed directly
		updateDAO.saveOrUpdate(update);
		
		//Notification service is responsible for creation of new notification
		notificationService.addNewUpdateRequestNotification(message, job);

		
		
	}
	
	public void postUpdate(int id,String message) {
		
		System.out.println("******************IN THE UPDATE SERVICE*********************");
		
		User user = userService.getSessionUser();
		
		Update update = new Update();
		
		Job job = jobService.getById(id);
		
		user.postUpdate(job, message,update);
		
		jobService.update(job);
		System.out.println("******************IN THE UPDATE SERVICE to QUCIK*********************");
		updateDAO.saveOrUpdate(update);
		
		notificationService.addUpdateNotification(message, job);

		
		
	}
	
	
	

}
