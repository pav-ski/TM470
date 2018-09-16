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

import org.hibernate.annotations.Proxy;

@Entity
@Table (name= "location_areas" )
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="area_type")
@Proxy(lazy=false)
public abstract class LocationArea{
	
	@Id
	@GeneratedValue
	@Column(name="area_id")
	private int id;
	
	@Column(name = "description")
	private String descriptionOfArea;
	
	@Column(name = "area_number")
	private String areaID;
	
	@Column(name = "room_score")
	private Double roomScore;
	
	@ManyToOne
	@JoinColumn(name="location_id",nullable=false)
	private Location isInLocation;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="isFor", cascade = CascadeType.ALL)
	private Set<Job> hasJobs;
	
	@OneToMany(mappedBy="wasFor", cascade = CascadeType.ALL)//fetch = FetchType.EAGER, 
	private Set<Job> hadJobs;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="isIn", cascade = CascadeType.ALL)//fetch = FetchType.EAGER, 
	private Set<Element> hasElements;
	

	//Gets all the staff in the company
	public Set<Staff> getStaff() {

		return this.getIsInLocation().getBelongsTo().getEmploys();
	}
	
	public void updateScore() {
		
		//New variable score - it will add score for each element in the area
		Double elementScoreSum = 0.0;
		
		//Save the previous score to make sure it has been changed later
		double previousScore = this.getRoomScore();
		
				//For each element in the area add all the scores of the elements
				for(Element eachElement:this.getHasElements()) {
					//Score is a sum of element scores
					elementScoreSum = elementScoreSum + eachElement.getScore();
				}
				
				//Set the new score for the area by dividing the sum by the number of elements
				this.setRoomScore(elementScoreSum/this.getHasElements().size());
				
				//Post-condition checks
				assert(this.getRoomScore()!= previousScore);
				assert this.getRoomScore() > 0 && this.getRoomScore() <= 5.0;
		
		
	}
	
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
	
	public Double getRoomScore() {
		return roomScore;
	}

	public void setRoomScore(Double roomScore) {
		this.roomScore = roomScore;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocationArea other = (LocationArea) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	
	//
	//
	//End of Auto-Generated setters and getters
	
	
	
	
	
	

}
