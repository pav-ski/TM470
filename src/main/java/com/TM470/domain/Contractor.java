package com.TM470.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@DiscriminatorValue("3")
@Table (name= "users" )
public class Contractor extends User{
	

	@Column(name="on_site")
	private Boolean onSite;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="requires", cascade = CascadeType.ALL)
	private Set<Job> outcallTo;	

	
	
	//Auto-Generated getters and setters
	//
	//
	public Boolean getOnSite() {
		return onSite;
	}

	public void setOnSite(Boolean onSite) {
		this.onSite = onSite;
	}

	public Set<Job> getOutcallTo() {
		return outcallTo;
	}

	public void setOutcallTo(Set<Job> outcallTo) {
		this.outcallTo = outcallTo;
	}

	
	
	//
	//
	//End of Auto-Generated getters and setters
	
	
	


}
