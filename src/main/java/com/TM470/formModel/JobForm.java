package com.TM470.formModel;

import java.util.Date;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.TM470.domain.Contractor;
import com.TM470.domain.LocationArea;
import com.TM470.domain.Staff;
import com.TM470.domain.Update;
import com.TM470.domain.User;

@Component
public class JobForm {

		
		
		
		
		private int jobId;

		
		private String description;
		
		
		private Date datePosted;
		
		
		private Boolean active;
		
		
		private int severity;
		
		
		private Boolean updateRequested;
		
		
		private String isFor;
		
		
		private String wasFor;	


		
		private User postedBy;
		
		
		private Staff willBeFixedBy;

		
		private Contractor requires; 
		
		
		private Set<Update> hasUpdate;
		
		
		private String isFaulty;
		
		
	
		
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

		public String getIsFor() {
			return isFor;
		}

		public void setIsFor(String isFor) {
			this.isFor = isFor;
		}

		public String getWasFor() {
			return wasFor;
		}

		public void setWasFor(String wasFor) {
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

		public String getIsFaulty() {
			return isFaulty;
		}

		public void setIsFaulty(String isFaulty) {
			this.isFaulty = isFaulty;
		}
		
		
		
		//
		//
		//End of Auto-Generated getters and setters
		


		

}
