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
		UpdateRequest update = new UpdateRequest(message,job, user);


		//Commit changes to the database
		jobService.update(job);
		
		//As we are in updateService updateDAO can be accessed directly
		updateDAO.saveOrUpdate(update);
		
		//Post-condtion checks and pre-condtion checks for addNotification();
		assert job != null;
		assert user != null;
		assert update != null;
		assert message != null;
		
		//Notification service is responsible for creation of new notification
		notificationService.addNotification(message,job.getIsFor(), job, "NEW UPDATE REQUEST");

		
		
	}
	
	//Posts update for the job matching provided id 
	public void postUpdate(int id,String message) {
		
		//Get the user in current session
		User user = userService.getSessionUser();
		
		//Locate the job
		Job job = jobService.getById(id);
		
		//Create new update with message for a job
		Update update = new Update(message,job, user);
		
		//Post-condtion checks and pre-condtion checks for addNotification();
		assert job != null;
		assert user != null;
		assert update != null;
		assert message != null;
				
		//Update job and save or update the Update object
		jobService.update(job);
		updateDAO.saveOrUpdate(update);
		
		String type;
		
		//Create new notification for users
		if(job.getActive()) {
		type = "NEW UPDATE";
		assert job.getIsFor() != null;
		notificationService.addNotification(message,job.getIsFor(), job,"NEW UPDATE");
		}
		else if(!job.getActive()) {
		type = "JOB COMPLETED";
		assert job.getWasFor() != null;
		notificationService.addNotification(message,job.getWasFor(), job,"NEW UPDATE");
		}

	}

}
