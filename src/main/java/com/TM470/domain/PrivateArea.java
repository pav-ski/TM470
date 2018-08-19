package com.TM470.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("2")
@Table (name= "location_areas" )
public class PrivateArea extends LocationArea {
	
	@Column(name = "room_score")
	private Double roomScore;
	
	
	//After creation of room object this method sets attributes as required
	public void createArea(String description,String areaID, Double roomScore, Location location) {
		this.setDescriptionOfArea(description);
		this.setAreaID(areaID);
		this.setRoomScore(roomScore);
		this.setIsInLocation(location);
		
		
		
	}
	
	
	//Auto-Generated getters and setters
	//
	//
	
	public Double getRoomScore() {
		return roomScore;
	}

	public void setRoomScore(Double roomScore) {
		this.roomScore = roomScore;
	}
	
	
	
	//
	//
	//End of Auto-Generated getters and setters

}
