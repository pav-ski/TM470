package com.TM470.domain;




import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table (name= "elements" )
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="element_type")
@Proxy(lazy=false)
public abstract class Element{
	
	@Id
	@GeneratedValue
	@Column(name="element_id")
	private int id;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "score")
	private Double score;
	
	@Column(name = "date_bought")
	private String dateBought;
	
	@ManyToOne
	@JoinColumn(name="is_in",nullable=false)
	private LocationArea isIn;
	
	public void adjustFaultyElementScore(double newScore) {
		
		double score = this.getScore();
		this.setScore(newScore);
		assert this.getScore()==newScore;
		
		
	}
	
	
	//Auto-Generated setters and getters
	//
	//
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getDateBought() {
		return dateBought;
	}

	public void setDateBought(String dateBought) {
		this.dateBought = dateBought;
	}

	public LocationArea getIsIn() {
		return isIn;
	}

	public void setIsIn(LocationArea locationArea) {
		this.isIn = locationArea;
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
		Element other = (Element) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
		
	
	//
	//
	//End of Auto-Generated setters and getters
	
	
	
	
	
	

}
