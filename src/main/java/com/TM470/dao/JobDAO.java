package com.TM470.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.TM470.domain.Job;

@Repository
public interface JobDAO {
	
	public void addJob(Job job);
	public void updateJob(Job job);
	public List<Job> list();
	public Job getJobById(int id);
	public void removeJob(int id);
	
	

}
