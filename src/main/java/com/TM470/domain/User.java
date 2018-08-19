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
@Table (name= "users" )
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type")
public abstract class User{
	
	@Id
	@GeneratedValue
	@Column(name="user_id")
	private int userId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="password")
	private String password;
	
	@ManyToOne
	@JoinColumn(name="is_using_system_of",nullable=false)
	private Company isUsingSystemOf;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="postedBy", cascade = CascadeType.ALL)
	private Set<Job> postedA;
	
	
	//Auto-Generated getters and setters
	//
	//
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Company getIsUsingSystemOf() {
		return isUsingSystemOf;
	}

	public void setIsUsingSystemOf(Company isUsingSystemOf) {
		this.isUsingSystemOf = isUsingSystemOf;
	}

	public Set<Job> getPostedA() {
		return postedA;
	}

	public void setPostedA(Set<Job> postedA) {
		this.postedA = postedA;
	}
	
	
	
	//
	//
	//End of Auto-Generated getters and setters
	
	
	


}
