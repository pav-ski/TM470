package com.TM470.dao;

import java.util.List;

import com.TM470.domain.Job;


public interface JobDAO {
	
	public void addJob(Job job);
	public void updateJob(Job job);
	public List<Job> list();
	public Job getJobById(int id);
	public void removeJob(int id);
	
	

}
