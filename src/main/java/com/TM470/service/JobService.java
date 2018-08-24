package com.TM470.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TM470.dao.JobDAO;
import com.TM470.dao.UpdateDAO;
import com.TM470.domain.Job;
import com.TM470.domain.Update;
import com.TM470.domain.User;


@Service
public class JobService {
	
	@Autowired
	private JobDAO jobDAO;

	@Autowired
	private UpdateDAO updateDAO;
	
	public void update(Job job) {
		jobDAO.updateJob(job);
	}
	
	

}
