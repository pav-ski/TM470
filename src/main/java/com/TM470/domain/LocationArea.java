package com.TM470.domain;



import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name= "location_areas" )
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="area_type")
public abstract class LocationArea{
	
	@Id
	@GeneratedValue
	@Column(name="area_id")
	private int id;
	
	@Column(name = "description")
	private String descriptionOfArea;
	
	@Column(name = "area_number")
	private String areaID;
	
	@ManyToOne
	@JoinColumn(name="location_id",nullable=false)
	private Location isInLocation;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="isFor", cascade = CascadeType.ALL)
	private Set<Job> hasJobs;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="wasFor", cascade = CascadeType.ALL)
	private Set<Job> hadJobs;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="isIn", cascade = CascadeType.ALL)
	private Set<Element> hasElements;
	
	//Auto-Generated setters and getters
	//
	//
		
	public String getDescriptionOfArea() {
		return descriptionOfArea;
	}

	public void setDescriptionOfArea(String descriptionOfArea) {
		this.descriptionOfArea = descriptionOfArea;
	}

	public String getAreaID() {
		return areaID;
	}

	public void setAreaID(String areaID) {
		this.areaID = areaID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Location getIsInLocation() {
		return isInLocation;
	}

	public void setIsInLocation(Location isInLocation) {
		this.isInLocation = isInLocation;
	}

	public Set<Job> getHasJobs() {
		return hasJobs;
	}

	public void setHasJobs(Set<Job> hasJobs) {
		this.hasJobs = hasJobs;
	}

	public Set<Job> getHadJobs() {
		return hadJobs;
	}

	public void setHadJobs(Set<Job> hadJobs) {
		this.hadJobs = hadJobs;
	}

	public Set<Element> getHasElements() {
		return hasElements;
	}

	public void setHasElements(Set<Element> hasElements) {
		this.hasElements = hasElements;
	}
	
	
	
	//
	//
	//End of Auto-Generated setters and getters
	
	
	
	
	
	

}
