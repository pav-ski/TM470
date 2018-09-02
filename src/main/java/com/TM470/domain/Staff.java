package com.TM470.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;


@Entity
@DiscriminatorValue("2")
@Table (name= "users" )
@Proxy(lazy=false)
public class Staff extends User{
	
	@Column(name="on_shift")
	private Boolean isOnShift;
	
	@Column(name="is_admin")
	private Boolean isAdministrator;
	
	@ManyToOne
	@JoinColumn(name="works_for",nullable=false)
	private Company worksFor;
	
	@OneToMany(mappedBy="willBeFixedBy", cascade = CascadeType.ALL)//fetch = FetchType.EAGER, 
	private List<Job> isAttendingTo;
	
	@OneToMany
	@JoinColumn(name="can_outcall")
	private Set<Contractor> canOutcall;

	//Setter for required fields
	
	public void constructorSetter(String name,String password,Company company) {
		this.setName(name);
		this.setPassword(password);
		this.setWorksFor(company);
		this.setIsUsingSystemOf(company);
		
	}
	
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

	public List<Job> getIsAttendingTo() {
		return isAttendingTo;
	}

	public void setIsAttendingTo(List<Job> isAttendingTo) {
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
