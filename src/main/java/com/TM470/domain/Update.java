package com.TM470.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table (name= "updates" )
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
	@JoinColumn(name="update_about",nullable=false)
	private Job updateAbout; 
	
	
	//Auto-Generated getters and setters
	//

	//End of Auto-Generated getters and setters
	//
	
	

}
