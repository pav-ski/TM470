package com.TM470.service;

import java.util.List;
import org.junit.Assert;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public void reportIssue(String description,LocationArea area,Element element,int severity,User user) {
		
		//Create new job object
		Job job = new Job(description,element,area,severity,user);
		this.saveOrUpdate(job);
		
		//Pre-condition checks for addNotification()
		assert job != null;
		String type = "NEW JOB";
		assert type != null;
		assert area != null;
		assert description != null;
		
		//notificationService pushes through the notification
		notificationService.addNotification(description, area, job, type);
		
		
		double newScore = (double)severity;
		
		//Pre-condition checks for adjustElementScore()
		assert newScore >= 1.0 && newScore <= 5.0;
		assert element != null;
		
		//elementService adjusts score of the faulty element
		elementService.adjustElementScore(element, newScore);
		
	}
	
	
	//UC5 Repair an issue
	public void completeJob(int id, String message) {
		
		//jobDAO locates the job to be fixed in the database
		Job job = jobDAO.getJobById(id);
		assert job!=null;
		
		
		//Check if score has been updated
		//newScore and oldScore will hold values for previous score
		double newScore = 4.0;
		double oldScore = job.getIsFaulty().getScore();
		boolean scoreChanged = false;
		
		//if score is the same assertion would always be thrown
		if(oldScore != newScore) {
			scoreChanged = true;

		}
		
		//Affected element score is updated to 4 (5 is new, 4 is 'as new')
		//elementService.adjustFaultyElementScore(job.getIsFaulty(), 4);
		elementService.adjustElementScore(job.getIsFaulty(), newScore);
		
		if(scoreChanged) {
			assert job.getIsFaulty().getScore() != oldScore;
		}
		
		
		//job object itself is responsible for completion
		job.completeJob();
		assert !job.getActive();
	
		//update repository
		update(job);
		assert !this.getById(id).getActive();
		
		assert id >= 0;
		assert message != null;
		//post automatic update with the job completion
		updateService.postUpdate(id, message);
		
		String type = "JOB COMPLETED";
		
		//create notification to be displayed on dashboards
		assert job.getWasFor() != null;
		assert job != null;
		assert type != null;
		notificationService.addNotification(message,job.getWasFor(),job, type);
		
	}
	

	
	

}
