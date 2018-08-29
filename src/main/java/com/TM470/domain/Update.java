package com.TM470.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;



@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="update_type")
@DiscriminatorValue("1")
@Table (name= "updates" )
@Proxy(lazy=false)
public class Update{
	
	@Id
	@GeneratedValue
	@Column(name="update_id")
	private int id;
	
	@Column(name="message")
	private String message;
	
	@Column(name="date_entered")
	private Date dateEntered;

	@ManyToOne
	@JoinColumn(name="update_about")
	private Job updateAbout;
	
	//set attributes
	public void setAttributesAfterCreation(String message,Job job) {
		
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
		
		this.setMessage(message);
		this.setUpdateAbout(job);
		this.setDateEntered(date);
		
	}

	
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

	public Job getUpdateAbout() {
		return updateAbout;
	}

	public void setUpdateAbout(Job updateAbout) {
		this.updateAbout = updateAbout;
	} 
	
	
	

	//End of Auto-Generated getters and setters
	//
	
	

}
