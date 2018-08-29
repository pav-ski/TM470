package com.TM470.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;



@Entity
@Table (name= "updates" )
@DiscriminatorValue("2")
@Proxy(lazy=false)
public class UpdateRequest extends Update{
	
	
	

	
	
	

	//End of Auto-Generated getters and setters
	//
	
	

}
