package com.TM470.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.TM470.dao.JobDAO;
import com.TM470.domain.Element;
import com.TM470.domain.Job;
import com.TM470.domain.LocationArea;
import com.TM470.domain.User;


@Service
public class JobService {
	
	@Autowired
	private JobDAO jobDAO;
	
	@Autowired
	private ElementService elementService;
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private LocationAreaService locationAreaService;
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private UpdateService updateService;
	
	//Methods for interaction with database
	public void update(Job job) {
		jobDAO.updateJob(job);
	}
	
	public void saveOrUpdate(Job job) {
		jobDAO.saveOrUpdate(job);
	}
	
	public List<Job> getJobs() {
		return jobDAO.list();
		
	}
	
	public Job getById(int id) {
		return jobDAO.getJobById(id);
	}
	//End of database methods
	
	
	//UC1 Post Job
	public void postJob(String description,int isFaulty, int severity, int isFor) {
		
		//Using DAO's element and area are located by their id's
		Element element = elementService.getById(isFaulty);
		LocationArea area = locationAreaService.getById(isFor);
		
		assert element != null;
		assert area != null;

		//values converted to objects are passed on to userService to be posted 
		//this will allow extraction of user in current session
		userService.reportIssue(description, area, element, severity);

		//elementService adjusts score of the faulty element
		elementService.adjustFaultyElementScore(element, severity);
		
	}
	
	
	//UC5 Repair an issue
	public void completeJob(int id, String message) {
		
		
		Job job = jobDAO.getJobById(id);

		elementService.adjustFaultyElementScore(job.getIsFaulty(), 5);
		
		job.completeJob();
		update(job);
		
		updateService.postUpdate(id, message);
		notificationService.addJobCompletedNotification(job);
		
	}
	
		//Method assisting job creation with setting attribute values
		//Uses JobDAO object to commit new object to database
		public void setAttributesAndCommit(String description,Element element,int severity,LocationArea area,User user) {
			
			
			
		}
	
	

}
