package com.TM470.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "jobs")
public class Job {
	
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
	@JoinColumn(name="is_for",nullable=false)
	private LocationArea isFor;
	
	@ManyToOne
	@JoinColumn(name="was_for",nullable=false)
	private LocationArea wasFor;	


	@ManyToOne
	@JoinColumn(name="posted_by",nullable=false)
	private User postedBy;
	
	@ManyToOne
	@JoinColumn(name="attending_staff",nullable=false)
	private Staff willBeFixedBy;

	@ManyToOne
	@JoinColumn(name="requires",nullable=false)
	private Contractor requires; 
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="updateAbout", cascade = CascadeType.ALL)
	private Set<Update> hasUpdate;

	
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
	
	
	
	//
	//
	//End of Auto-Generated getters and setters
	


	
	
}
