package com.TM470.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@DiscriminatorValue("1")
@Table (name= "elements" )
@Proxy(lazy=false)
public class Appliance extends Element {
	
	@Column(name="pat_tested")
	private Boolean patTested;
	
	@Column(name="next_test_date")
	private String nextTestDue;
	
	@Column(name="num_of_faults")
	private int numberOfFaults;
	
	public void setAttributes(String desc, Double score, String bought, Boolean pat, String nextTest, int faults, LocationArea locationArea) {
		this.setDescription(desc);
		this.setScore(score);
		this.setDateBought(bought);
		this.setPatTested(pat);
		this.setNextTestDue(nextTest);
		this.setNumberOfFaults(faults);
		this.setIsIn(locationArea);
	}
	
	//Auto-Generated getters and setters
	//
	//
	
	public Boolean getPatTested() {
		return patTested;
	}

	public void setPatTested(Boolean patTested) {
		this.patTested = patTested;
	}

	public String getNextTestDue() {
		return nextTestDue;
	}

	public void setNextTestDue(String nextTestDue) {
		this.nextTestDue = nextTestDue;
	}

	public int getNumberOfFaults() {
		return numberOfFaults;
	}

	public void setNumberOfFaults(int numberOfFaults) {
		this.numberOfFaults = numberOfFaults;
	}


	//
	//
	//End of Auto-Generated getters and setters

}
