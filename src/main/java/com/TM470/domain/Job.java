package com.TM470.domain;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;



@Entity
@Table(name = "jobs")
@Proxy(lazy=false)
public class Job implements Listener{
	
	
	@Id
	@GeneratedValue
	@Column(name="job_id")
	private int jobId;

	@Column(name="description")
	private String description;
	
	@Column(name="date_posted")
	private Date datePosted;
	
	@Column(name="active")
	private Boolean active;
	
	@Column(name="severity")
	private int severity;
	
	@Column(name="update_requested")
	private Boolean updateRequested;
	
	@ManyToOne
	@JoinColumn(name="is_for")
	private LocationArea isFor;
	
	@ManyToOne
	@JoinColumn(name="was_for")
	private LocationArea wasFor;	


	@ManyToOne
	@JoinColumn(name="posted_by",nullable=false)
	private User postedBy;
	
	@ManyToOne
	@JoinColumn(name="attending_staff")
	private Staff willBeFixedBy;

	@ManyToOne
	@JoinColumn(name="requires")
	private Contractor requires; 
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="updateAbout", cascade = CascadeType.ALL)//
	private Set<Update> hasUpdate;
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name="subscribers",joinColumns = @JoinColumn( name="job_id"),inverseJoinColumns = @JoinColumn( name="user_id")
    )
	private Set<User> hasSubscribers;
	
	@OneToOne
	@JoinColumn(name="is_faulty")
	private Element isFaulty;
	

	public Job() {
		
	}
	
	public Job(String description,Element element,LocationArea area,int severity,User user) {
		this.hasSubscribers = new HashSet<User>();
		
		
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
		
		this.setActive(true);
		this.setDescription(description);
		this.setIsFaulty(element);
		this.setSeverity(severity);
		element.setScore((double)severity);
		this.setIsFor(area);
		this.setPostedBy(user);
		this.setDatePosted(date);
		if(user.getClass()==Guest.class) {
			this.addObserver(user);
		}
		System.out.println(area.getStaff());
		
		this.addStaffObservers(area.getStaff());
		
		
	}
	
	
	
	//UC 2 Request Update
	//
	public void requestUpdate(String message,User user,Update update) {
		this.setUpdateRequested(true);
		this.hasUpdate.add(update);
		System.out.println("IN JOB");
		update.setAttributesAfterCreation(message, this);
		
		if(user.getClass()==Staff.class) {
			updateStaff();
		}
		else if(user.getClass()==Guest.class) {
			updateAll();
		}
		
		
	}
	
	//Listener interface methods
	public void addObserver(User user) {
		this.hasSubscribers.add(user);
	}
	
	public void addStaffObservers(Set<Staff> staff) {
		System.out.println(staff);
		System.out.println(this);
		Set<Staff> staffSet = new HashSet<Staff>();
		staffSet.addAll(staff);
		this.hasSubscribers.addAll(staffSet);
	}
	
	public void removeObserver() {
		
	}
	
	
	public void updateStaff() {
		
	}
	
	public void updateGuest() {
		
	}
	
	public void updateAll() {
		
	}
	//End of Listener interface methods
	
	//Auto-Generated getters and setters
	//
	//
	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public Boolean getUpdateRequested() {
		return updateRequested;
	}

	public void setUpdateRequested(Boolean updateRequested) {
		this.updateRequested = updateRequested;
	}

	public LocationArea getIsFor() {
		return isFor;
	}

	public void setIsFor(LocationArea isFor) {
		this.isFor = isFor;
	}

	public LocationArea getWasFor() {
		return wasFor;
	}

	public void setWasFor(LocationArea wasFor) {
		this.wasFor = wasFor;
	}

	public User getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(User postedBy) {
		this.postedBy = postedBy;
	}

	public Staff getWillBeFixedBy() {
		return willBeFixedBy;
	}

	public void setWillBeFixedBy(Staff willBeFixedBy) {
		this.willBeFixedBy = willBeFixedBy;
	}

	public Contractor getRequires() {
		return requires;
	}

	public void setRequires(Contractor requires) {
		this.requires = requires;
	}

	public Set<Update> getHasUpdate() {
		return hasUpdate;
	}

	public void setHasUpdate(Set<Update> hasUpdate) {
		this.hasUpdate = hasUpdate;
	}

	public Element getIsFaulty() {
		return isFaulty;
	}

	public void setIsFaulty(Element isFaulty) {
		this.isFaulty = isFaulty;
	}


	public Set<User> getHasSubscribers() {
		return hasSubscribers;
	}

	public void setHasSubscribers(Set<User> hasSubscribers) {
		this.hasSubscribers = hasSubscribers;
	}

	
	
	
	
	//
	//
	//End of Auto-Generated getters and setters
	


	
	
}
