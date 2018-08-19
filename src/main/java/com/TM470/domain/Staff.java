package com.TM470.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@DiscriminatorValue("2")
@Table (name= "users" )
public class Staff extends User{
	
	@Column(name="on_shift")
	private Boolean isOnShift;
	
	@Column(name="is_admin")
	private Boolean isAdministrator;
	
	@ManyToOne
	@JoinColumn(name="works_for",nullable=false)
	private Company worksFor;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="willBeFixedBy", cascade = CascadeType.ALL)
	private Set<Job> isAttendingTo;
	
	@OneToMany
	@JoinColumn(name="can_outcall")
	private Set<Contractor> canOutcall;


	
	//Auto-Generated getters and setters
	//
	//
	
	public Boolean getIsOnShift() {
		return isOnShift;
	}

	public void setIsOnShift(Boolean isOnShift) {
		this.isOnShift = isOnShift;
	}

	public Boolean getIsAdministrator() {
		return isAdministrator;
	}

	public void setIsAdministrator(Boolean isAdministrator) {
		this.isAdministrator = isAdministrator;
	}

	public Company getWorksFor() {
		return worksFor;
	}

	public void setWorksFor(Company worksFor) {
		this.worksFor = worksFor;
	}

	public Set<Job> getIsAttendingTo() {
		return isAttendingTo;
	}

	public void setIsAttendingTo(Set<Job> isAttendingTo) {
		this.isAttendingTo = isAttendingTo;
	}

	public Set<Contractor> getCanOutcall() {
		return canOutcall;
	}

	public void setCanOutcall(Set<Contractor> canOutcall) {
		this.canOutcall = canOutcall;
	}

	
	

	//
	//
	//End of Auto-Generated getters and setters
	
	
	


}
