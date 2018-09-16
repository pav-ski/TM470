package com.TM470.domain;

import java.util.HashSet;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;


@Entity
@Table (name= "users" )
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type")
@Proxy(lazy=false)
public abstract class User{
	
	@Id
	@GeneratedValue
	@Column(name="user_id")
	private int userId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@ManyToOne
	@JoinColumn(name="is_using_system_of",nullable=false)
	private Company isUsingSystemOf;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="postedBy", cascade = CascadeType.ALL)
	private Set<Job> postedA;
	
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = { CascadeType.ALL })
    @JoinTable(name = "user_notification", 
    	joinColumns = { @JoinColumn(name = "iduser_notification") }, 
        inverseJoinColumns = { @JoinColumn(name = "notification_id") }
    )
    Set<Notification> hasNotifications = new HashSet<Notification>();
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name="subscription", joinColumns = {@JoinColumn(name="id_user")},
				inverseJoinColumns = {@JoinColumn(name="idsubscription")})
	private Set<Job> subscribedTo = new HashSet<>();



	
		
		
		
		
		
		
		
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Notification> getHasNotifications() {
		return hasNotifications;
	}

	public void setHasNotifications(Set<Notification> hasNotification) {
		this.hasNotifications = hasNotification;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + userId;
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
		User other = (User) obj;
		if (userId != other.userId)
			return false;
		return true;
	}


	
	
	
	//
	//
	//End of Auto-Generated getters and setters
	
	
	


}
