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
	
	//Default no arguments constructor for Hibernate
	public Update() {
		
	}
	
	//Parametrised constructor for use by the system
	public Update(String message, Job job, User user) {
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
		
		this.setMessage(message);
		this.setUpdateAbout(job);
		this.setDateEntered(date);
		
		job.postUpdate(message, user, this);
		
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
		Update other = (Update) obj;
		if (id != other.id)
			return false;
		return true;
	} 
	
	
	

	//End of Auto-Generated getters and setters
	//
	
	

}
