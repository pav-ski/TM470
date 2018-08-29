package com.TM470.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TM470.dao.JobDAO;
import com.TM470.dao.UpdateDAO;
import com.TM470.domain.Element;
import com.TM470.domain.Job;
import com.TM470.domain.LocationArea;
import com.TM470.domain.User;


@Service
public class JobService {
	
	@Autowired
	private JobDAO jobDAO;

	@Autowired
	private UpdateDAO updateDAO;
	
	@Autowired
	private ElementService elementService;
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private LocationAreaService locationAreaService;
	
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
	
	
	public void postJob(String description,int isFaulty, int severity, int isFor) {
		Element element = elementService.getById(isFaulty);
		LocationArea area = locationAreaService.getById(isFor);

		
		userService.reportIssue(description, area, element, severity);
		System.out.println("Job service element score :" + element.getScore());
		System.out.println("Job service area score :" + area.getRoomScore());
		
		
		System.out.println(element);
		elementService.adjustFaultyElementScore(element, severity);
		System.out.println("Element score is :" + element.getScore());
		System.out.println("area score is :" + area.getRoomScore());
		


		
		
		
		
		
	}
	
	public void completeJob(Job job) {
		
		elementService.adjustFaultyElementScore(job.getIsFaulty(), 5);
		
		job.completeJob();
		update(job);
	}
	
		//Method assisting job creation with setting attribute values
		//Uses JobDAO object to commit new object to database
		public void setAttributesAndCommit(String description,Element element,int severity,LocationArea area,User user) {
			
			
			
		}
	
	

}
