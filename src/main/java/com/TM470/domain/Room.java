package com.TM470.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("1")
@Table (name= "location_areas" )
public class Room extends LocationArea {
	
	@Column(name = "room_score")
	private Double roomScore;
	
	@OneToOne(mappedBy= "staysIn")
	private Guest isOccupiedBy;
	
	
	//After creation of room object this method sets attributes as required
	public void createRoom(String description,String areaID, Double roomScore, Location location) {
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
