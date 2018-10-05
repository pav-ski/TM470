package com.TM470.domain;



import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;



@Entity
@Table (name= "updates" )
@DiscriminatorValue("2")
@Proxy(lazy=false)
public class UpdateRequest extends Update{
	
	public UpdateRequest() {
		
	}
	
	
	//Constructor for UpdateRequest objects, sets attribute values and forwards
	//message to job object to update it
	public UpdateRequest(String message, Job job, User user) {
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
		
		this.setMessage(message);
		this.setUpdateAbout(job);
		this.setDateEntered(date);
		
		//Post-condition checks
		assert this.getMessage()==message;
		assert this.getUpdateAbout().equals(job);
		assert this != null; //this is also pre-condition check for requestUpdate()
		
		//Pre condition checks for requestUpdate()
		assert job.getActive();
		assert user != null;
		
		//Update job object
		job.requestUpdate(message, user, this);
		
	}
	
	

	
	
	

	//End of Auto-Generated getters and setters
	//
	
	

}
