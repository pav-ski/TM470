package com.TM470.formModel;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.TM470.domain.Job;



@Component
public class UpdateForm{
	

	private int id;
	
	private String message;
	
	private Date dateEntered;

	private String updateAbout;

	//Auto-Generated getters and setters
	//
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDateEntered() {
		return dateEntered;
	}

	public void setDateEntered(Date dateEntered) {
		this.dateEntered = dateEntered;
	}

	public String getUpdateAbout() {
		return updateAbout;
	}

	public void setUpdateAbout(String updateAbout) {
		this.updateAbout = updateAbout;
	} 
	
	
	

	//End of Auto-Generated getters and setters
	//
	
	

}
