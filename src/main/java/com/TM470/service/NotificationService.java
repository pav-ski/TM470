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

	
	
	//UC1 Post Job - creation of notification for users
	public void addNewJobNotification(String description,LocationArea area, Set<User> subscribedUsers) {
		
		//New notification is created
		Notification notification = new Notification(description,subscribedUsers);
		
		//notification is formatted to match the post job
		notification.formatNewJobNotification(area);
		
		for (User user:notification.getNotificationFor()) {
			
			//Each user subscribed to job receives a notification - this must be committed
			userService.update(user);
		}
		
		//NotificationDAO saves or updates notification -database commit
		notificationDAO.saveOrUpdate(notification);
		
		
	}
	
	public void addNewUpdateRequestNotification(String description,Job job) {
		
		Set<User> subscribers = new HashSet<>();
		subscribers.addAll(job.getIsFor().getStaff());
		subscribers.add(job.getPostedBy());
		
		Notification notification = new Notification(description,subscribers);
		notification.formatNewUpdateRequestNotification(job);

		for (User user:notification.getNotificationFor()) {
			userService.update(user);
		}
		notificationDAO.saveOrUpdate(notification);
		
		
	}
	
	public void addUpdateNotification(String description,Job job) {
		
		Set<User> subscribers = new HashSet<>();
		System.out.println(subscribers);
		
		if(job.getActive()) {
			subscribers.addAll(job.getIsFor().getStaff());	
		}
		else if(!job.getActive()) {
			subscribers.addAll(job.getWasFor().getStaff());	
		}

		subscribers.add(job.getPostedBy());
		
		Notification notification = new Notification(description,subscribers);
		notification.formatNewUpdateNotification(job);
		
		for (User user:notification.getNotificationFor()) {
			userService.update(user);
		}
		
		notificationDAO.saveOrUpdate(notification);
		
		
	}
	
	
	public void addJobCompletedNotification(Job job) {
		
		Set<User> subscribers = new HashSet<>();
		subscribers.addAll(job.getWasFor().getStaff());
		subscribers.add(job.getPostedBy());
		
		Notification notification = new Notification(subscribers);
		notification.formatJobCompletedNotification(job);
		
		for (User user:notification.getNotificationFor()) {
			userService.update(user);
		}
		notificationDAO.saveOrUpdate(notification);
		
		
	}
	

	
	
	
	
	
	

}
