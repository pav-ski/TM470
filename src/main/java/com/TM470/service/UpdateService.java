package com.TM470.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TM470.dao.JobDAO;
import com.TM470.dao.UpdateDAO;
import com.TM470.domain.Job;
import com.TM470.domain.Update;
import com.TM470.domain.User;
import com.TM470.service.JobService;


@Service
public class UpdateService {
	
	@Autowired
	private JobService jobService;

	@Autowired
	private UpdateDAO updateDAO;
	
	public void requestUpdate(User user,Job job,String message) {
		
		System.out.println("******************IN THE UPDATE SERVICE*********************");
		System.out.println(user);
		System.out.println(job);
		System.out.println(message);
		
		Update update = new Update();
		
		user.requestUpdate(job, message,update);
		
		jobService.update(job);
		System.out.println("******************IN THE UPDATE SERVICE to QUCIK*********************");
		updateDAO.addUpdate(update);
		
		
	}
	
	

}
