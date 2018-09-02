package com.TM470.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;



@Entity
@Table (name= "notifications" )
@Proxy(lazy=false)
public class Notification{
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="message")
	private String message;

	
	@ManyToMany(fetch = FetchType.EAGER,mappedBy = "hasNotifications")
    private Set<User> notificationFor = new HashSet<User>();
	
	
	public Notification() {
		
	}
	
	public Notification(Set<User> users) {
		
		this.setNotificationFor(users);
		
		for(User eachUser: users) {
			eachUser.hasNotifications.add(this);
		}
		
	}
	
	public Notification(String message,Set<User> users) {
		

		this.setMessage(message);
		this.setNotificationFor(users);
		
		for(User eachUser: users) {
			eachUser.hasNotifications.add(this);
		}
	}
	
	public void formatNewJobNotification(LocationArea area) {
		
		this.setMessage("New issue has been posted for location" + area.getIsInLocation().getName() + 
				" in area " + area.getAreaID() + " ,the description is : " + message);
		
	}
	
	public void formatJobCompletedNotification(Job job) {
		
		this.setMessage("The job with id" + job.getJobId() +" and description " + job.getDescription() + 
				"has been fixed");
		
	}
	
	public void formatNewUpdateRequestNotification(Job job) {
		
		this.setMessage("New update has been requested for job number :" + job.getJobId() + 
				" in area " + job.getIsFor().getDescriptionOfArea() + " ,the description is : " + message);
		
	}
	
	public void formatNewUpdateNotification(Job job) {
		
		this.setMessage("A new update has been added to job id:  " + job.getJobId());
	}
	
	
	//Auto-Generated getters and setters
	//
	
	
	public int getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Set<User> getNotificationFor() {
		return notificationFor;
	}

	public void setNotificationFor(Set<User> notificationFor) {
		this.notificationFor = notificationFor;
	}
	



	//End of Auto-Generated getters and setters
	//
	
	

}
