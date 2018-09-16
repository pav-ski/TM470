
package com.TM470.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.TM470.dao.NotificationDAO;
import com.TM470.domain.Job;
import com.TM470.domain.LocationArea;
import com.TM470.domain.Notification;
import com.TM470.domain.User;


@Service
public class NotificationService {

	@Autowired
	private NotificationDAO notificationDAO;
	
	@Autowired 
	private UserService userService;
	
	
	//UC 12 Notify Users
	public void addNotification(String description,LocationArea area, Job job, String type) {
		
		//Get all the derived observers
		Set<User> subscribers = job.getObservers();
		
		//Assertions 
		assert description != null;
		assert area != null;
		assert job != null;
		assert type == "NEW JOB"  || type == "JOB COMPLETED"|| type == "NEW UPDATE REQUEST"||type == "NEW UPDATE";
		
		//New notification is created
		Notification notification = new Notification(description,subscribers, type, job);
		
		assert notification != null;
		
		//NotificationDAO saves or updates notification -database commit
		notificationDAO.saveOrUpdate(notification);
		
		for (User user:notification.getNotificationFor()) {
			//Each user subscribed to job receives a notification - this must be committed
			userService.update(user);
		}
		
		
		
		assert notification.getNotificationFor().contains(userService.getSessionUser());
		assert notification.getNotificationFor().containsAll(area.getStaff());
		
		
	}

}
