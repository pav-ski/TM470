package com.TM470.domain;



import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
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
