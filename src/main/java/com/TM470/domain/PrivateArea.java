package com.TM470.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@DiscriminatorValue("2")
@Table (name= "location_areas" )
@Proxy(lazy=false)
public class PrivateArea extends LocationArea {
	
	
	
	//After creation of room object this method sets attributes as required
	public void createArea(String description,String areaID, Location location) {
		this.setDescriptionOfArea(description);
		this.setAreaID(areaID);
		this.setIsInLocation(location);
		
		
		
	}
	
	
	//Auto-Generated getters and setters
	//
	//
	
	
	
	//
	//
	//End of Auto-Generated getters and setters

}
