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
import javax.persistence.ManyToMany;
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
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="updateAbout", cascade = CascadeType.ALL)
	private Set<Update> hasUpdate;
	
	@ManyToMany(fetch = FetchType.EAGER,mappedBy = "subscribedTo")
	private Set<User> hasSubscribers = new HashSet<User>();
	
	
	@OneToOne
	@JoinColumn(name="is_faulty")
	private Element isFaulty;
	
	@OneToOne
	@JoinColumn(name="was_faulty")
	private Element wasFaulty;
	
	//Default empty constructor to be used by Hibernate
	public Job() {
		
		
	}
	
	//UC 1 Report Issue
	//This constructor will only be used in UC1
	public Job(String description,Element element,LocationArea area,int severity,User user) {
		
		
		
		//Create new Date object and format it
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("dd-MM-yyyy").format(date);
		
		//Set attributes and connect links
		this.setActive(true);
		assert this.getActive();
		
		this.setDescription(description);
		assert this.getDescription()==description;
		
		this.setIsFaulty(element);
		assert this.getIsFaulty() != null && Element.class.isAssignableFrom(this.getIsFaulty().getClass());
		
		this.setSeverity(severity);
		assert this.getSeverity() == severity;
		
		this.setIsFor(area);
		assert this.getIsFor() != null && LocationArea.class.isAssignableFrom(this.getIsFor().getClass());
		
		this.setPostedBy(user);
		assert this.getPostedBy() != null && User.class.isAssignableFrom(this.getPostedBy().getClass());
		
		this.setDatePosted(date);



		if(user.getClass()==Guest.class) {
			this.addObserver(user);
		}
		
		this.addStaffObservers(area.getStaff());
		
		
	}
	
	
	
	//UC 2 Request Update
	//
	public void requestUpdate(String message,User user,Update update) {
		
		//Set value for updateRequested
		this.setUpdateRequested(true);
		assert this.getUpdateRequested();
		
		//Add the update object
		this.hasUpdate.add(update);
		assert this.getHasUpdate().contains(update);

	}
	
	
	//UC 3 Post Update
	//
	public void postUpdate(String message,User user,Update update) {
		
			this.setUpdateRequested(false);
			assert !this.getUpdateRequested();
			
			this.hasUpdate.add(update);
			
			
		}
		
	//UC 5 Repair issue
	public void completeJob() {
			
			setActive(false);
			assert !this.getActive();
			
			setWasFor(getIsFor());
			assert getIsFor().equals(getWasFor());
			
			setIsFor(null);
			assert getIsFor()==null;
			
			this.setWasFaulty(isFaulty);
			assert this.getWasFaulty().equals(this.getIsFaulty());
			
			setIsFaulty(null);
			assert getIsFaulty()==null;
			
		}
	
	//Listener interface methods
	public void addObserver(User user) {
		this.hasSubscribers.add(user);
		
	}
	
	public void addStaffObservers(Set<Staff> staff) {

		Set<Staff> staffSet = new HashSet<Staff>();
		staffSet.addAll(staff);

	}
	
	public void removeObserver() {
		
	}
	
	
	//method which derives the association to all observers
	public Set<User> getObservers(){
		
		Set<User> observers = new HashSet<User>();
		observers.add(postedBy);
		
		if(this.getActive()) {
			observers.addAll(this.getIsFor().getStaff());	
			
		}
		else if(!this.getActive()) {
			observers.addAll(this.getWasFor().getStaff());	
		}

		assert observers != null;
		return observers;
		
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

	public void setHasSubscribers(Set<User> subscribers) {
		this.hasSubscribers = subscribers;
	}

	public Element getWasFaulty() {
		return wasFaulty;
	}

	public void setWasFaulty(Element wasFaulty) {
		this.wasFaulty = wasFaulty;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + jobId;
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
		Job other = (Job) obj;
		if (jobId != other.jobId)
			return false;
		return true;
	}

	
	
	
	
	//
	//
	//End of Auto-Generated getters and setters
	


	
	
}
