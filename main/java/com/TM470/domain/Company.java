package com.TM470.domain;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;



@Entity
@Table (name= "company" )
@Proxy(lazy=false)
public class Company{
	
	@Id
	@GeneratedValue
	@Column(name="company_id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="belongsTo", cascade = CascadeType.ALL)
	private Set<Location> haveLocations;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="isUsingSystemOf", cascade = CascadeType.ALL)
	private Set<User> hasUsers;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="worksFor", cascade = CascadeType.ALL)
	private Set<Staff> employs;
	
	//Add another location to haveLocations
	public void addLocation(Location location) {
		this.haveLocations.add(location);
	}
	
	
	
	//Auto-Generated getters and setters
	//
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Location> getHaveLocations() {
		return haveLocations;
	}
	
	public void setHaveLocations(Set<Location> haveLocations) {
		this.haveLocations = haveLocations;
	}



	public Set<User> getHasUsers() {
		return hasUsers;
	}



	public void setHasUsers(Set<User> hasUsers) {
		this.hasUsers = hasUsers;
	}



	public Set<Staff> getEmploys() {
		return employs;
	}



	public void setEmploys(Set<Staff> employs) {
		this.employs = employs;
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
		Company other = (Company) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	//Auto-Generated getters and setters
	//
	
	

}
