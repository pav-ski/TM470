package com.TM470.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("1")
@Table (name= "elements" )
public class Appliance extends Element {
	
	@Column(name="pat_tested")
	private Boolean patTested;
	
	@Column(name="next_test_date")
	private Date nextTestDue;
	
	@Column(name="num_of_faults")
	private int numberOfFaults;

	
	//Auto-Generated getters and setters
	//
	//
	
	public Boolean getPatTested() {
		return patTested;
	}

	public void setPatTested(Boolean patTested) {
		this.patTested = patTested;
	}

	public Date getNextTestDue() {
		return nextTestDue;
	}

	public void setNextTestDue(Date nextTestDue) {
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
