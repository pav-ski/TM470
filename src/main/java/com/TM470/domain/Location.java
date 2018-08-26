package com.TM470.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;


@Entity
@Table(name="locations")
@Proxy(lazy=false)
public class Location {
	

	
	@Id
	@GeneratedValue
	@Column(name="location_id")
	private int locationId;
	
	@Column
	private String name;
	
	@Column
	private int numOfUnits;
	
	@ManyToOne
	@JoinColumn(name="company_id",nullable=false)
	private Company belongsTo;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="isInLocation", cascade = CascadeType.ALL)//fetch = FetchType.EAGER, 
	private Set<LocationArea> hasAreas;
	
	@OneToMany(mappedBy="reserved", cascade = CascadeType.ALL)//fetch = FetchType.EAGER, 
	private List<Guest> hasGuest;
	
	//create new rooms and set forward attribute values
	public void newRoom(String description,String areaID) {
		Room room = new Room();
		room.createRoom(description, areaID, this);
	}

	
	//Auto-generated getters and setters
	//
	//
	
	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumOfUnits() {
		return numOfUnits;
	}

	public void setNumOfUnits(int numOfUnits) {
		this.numOfUnits = numOfUnits;
	}

	public Company getBelongsTo() {
		return belongsTo;
	}

	public void setBelongsTo(Company belongsTo) {
		this.belongsTo = belongsTo;
	}

	public Set<LocationArea> getHasAreas() {
		return hasAreas;
	}

	public void setHasAreas(Set<LocationArea> hasAreas) {
		this.hasAreas = hasAreas;
	}

	public List<Guest> getHasGuest() {
		return hasGuest;
	}

	public void setHasGuest(List<Guest> hasGuest) {
		this.hasGuest = hasGuest;
	}
	
	
	
	//
	//
	//Auto-generated getters and setters
	

}
