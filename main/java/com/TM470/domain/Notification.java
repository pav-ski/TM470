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
	
	
	public Notification(String message,Set<User> users,String type,Job job) {
		
		//Set message to provided message
		this.setMessage(message);
		//Post condition check
		assert this.getMessage()==message;

		//Link the subscribed users
		this.setNotificationFor(users);
		
		//Switch for how the message should be formatted
		switch(type) {
		case "NEW JOB":this.formatNewJobNotification(job);break;
		case "JOB COMPLETED":this.formatJobCompletedNotification(job);break;
		case "NEW UPDATE REQUEST":this.formatNewUpdateRequestNotification(job);break;
		case "NEW UPDATE":this.formatNewUpdateNotification(job);break;
		}
		
		//Iterate over subscribed users and link them to new notification
		for(User eachUser: users) {
			eachUser.hasNotifications.add(this);
			
			//Post conditions check
			assert eachUser.hasNotifications.contains(this);
		}
		
		//Post condition checks
		assert this.getNotificationFor().containsAll(users);

		
	}
	
	public void formatNewJobNotification(Job job) {
		System.out.println("In new job notification");
		
		this.setMessage("New issue has been posted for location" +  job.getIsFor().getIsInLocation().getName() + 
				" in area " + job.getIsFor().getAreaID() + " ,the description is : " + message);
		
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
		System.out.println("In new udpate notificaiton setter");
		this.setMessage("A new update has been added to job id:  " + job.getJobId() + "  with message :" + message);
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}


	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notification other = (Notification) obj;
		if (id != other.id)
			return false;
		return true;
	}

	



	//End of Auto-Generated getters and setters
	//
	
	

}
