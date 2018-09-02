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
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="updateAbout", cascade = CascadeType.ALL)//
	private Set<Update> hasUpdate;
	
	
	//@OneToMany(fetch = FetchType.EAGER,mappedBy="userId", cascade = CascadeType.ALL)
    //Set<User> hasSubscribers = new HashSet<User>();
	
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
		this.setDescription(description);
		this.setIsFaulty(element);
		this.setSeverity(severity);
		this.setIsFor(area);
		this.setPostedBy(user);
		this.setDatePosted(date);
		
		//Update score of the faulty element
		element.setScore((double)severity);

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
		
		//Add the update object
		this.hasUpdate.add(update);

		//Set the attributes of update
		update.setAttributesAfterCreation(message, this);
		
		
		if(user.getClass()==Staff.class) {
			updateStaff();
		}
		else if(user.getClass()==Guest.class) {
			updateAll();
		}
		
		
	}
	
	
		//UC 3 Post Update
		//
		public void postUpdate(String message,User user,Update update) {
			this.setUpdateRequested(false);
			this.hasUpdate.add(update);
			System.out.println("IN JOB");
			update.setAttributesAfterCreation(message, this);
			
			if(user.getClass()==Staff.class) {
				updateAll();
			}
			else if(user.getClass()==Contractor.class) {
				updateStaff();
			}
			
			
		}
		
		//UC 5 Repair issue
		public void completeJob() {
			
			setActive(false);
			setWasFor(getIsFor());
			setIsFor(null);
			
		}
	
	//Listener interface methods
	public void addObserver(User user) {
		this.hasSubscribers.add(user);
		
	}
	
	public void addStaffObservers(Set<Staff> staff) {
		System.out.println("Staff to be added" + staff);
		System.out.println("Added to job :" + this);
		Set<Staff> staffSet = new HashSet<Staff>();
		staffSet.addAll(staff);
		this.hasSubscribers.addAll(staffSet);
		System.out.println("Added to job :" + this.hasSubscribers);
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

	public void setHasSubscribers(Set<User> subscribers) {
		this.hasSubscribers = subscribers;
	}

	public Element getWasFaulty() {
		return wasFaulty;
	}

	public void setWasFaulty(Element wasFaulty) {
		this.wasFaulty = wasFaulty;
	}

	
	
	
	
	//
	//
	//End of Auto-Generated getters and setters
	


	
	
}
