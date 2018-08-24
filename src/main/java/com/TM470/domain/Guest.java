package com.TM470.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@DiscriminatorValue("1")
@Table (name= "users" )
@Proxy(lazy=false)
public class Guest extends User {
	
	@OneToOne
	@JoinColumn(name="stays_in")
	private Room staysIn;
	
	
	@OneToOne
	@JoinColumn(name="reserved")
	private Location reserved;
	
	//Constructor setters
	public void constructorSetter(String name, String password,Company company, Location location)
	{
		this.setName(name);
		this.setPassword(password);
		this.setIsUsingSystemOf(company);
		this.setReserved(location);
	}
	
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
