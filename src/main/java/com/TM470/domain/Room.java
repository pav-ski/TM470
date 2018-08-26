package com.TM470.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@DiscriminatorValue("1")
@Table (name= "location_areas" )
@Proxy(lazy=false)
public class Room extends LocationArea {
	
	
	@OneToOne(mappedBy= "staysIn")
	private Guest isOccupiedBy;
	
	
	//After creation of room object this method sets attributes as required
	public void createRoom(String description,String areaID, Location location) {
		this.setDescriptionOfArea(description);
		this.setAreaID(areaID);
		this.setIsInLocation(location);
		
		
		
	}
	
	
	//Auto-Generated getters and setters
	//
	//
	



	public Guest getIsOccupiedBy() {
		return isOccupiedBy;
	}


	public void setIsOccupiedBy(Guest isOccupiedBy) {
		this.isOccupiedBy = isOccupiedBy;
	}
	
	
	
	//
	//
	//End of Auto-Generated getters and setters

}
