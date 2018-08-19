package com.TM470.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("1")
@Table (name= "users" )
public class Guest extends User {
	
	@OneToOne
	@JoinColumn(name="stays_in")
	private Room staysIn;
	
	
	@OneToOne
	@JoinColumn(name="reserved")
	private Location reserved;
	
	
	
	//Auto-Generated getters and setters
	//
	//
	public Room getStaysIn() {
		return staysIn;
	}

	public void setStaysIn(Room staysIn) {
		this.staysIn = staysIn;
	}

	public Location getReserved() {
		return reserved;
	}

	public void setReserved(Location location) {
		this.reserved = location;
	}
	
	
	
	
	
	

	//
	//
	//End of Auto-Generated getters and setters

}
