package com.TM470.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TM470.dao.JobDAO;
import com.TM470.dao.UpdateDAO;
import com.TM470.domain.Job;
import com.TM470.domain.Update;
import com.TM470.domain.User;


@Service
public class ElementService {
	
	@Autowired
	private JobDAO jobDAO;

	@Autowired
	private UpdateDAO updateDAO;
	
	public void update(Job job) {
		jobDAO.updateJob(job);
	}
	
	public void postJob(String description,int isFaulty, int severity, int isFor) {
		Element element = elementService.getById(isFaulty);
		LocationArea area = locationAreaService.getById(isFor);
		
		
	}
	
	

}
