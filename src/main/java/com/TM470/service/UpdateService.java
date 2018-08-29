package com.TM470.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TM470.dao.UpdateDAO;
import com.TM470.domain.Job;
import com.TM470.domain.UpdateRequest;
import com.TM470.domain.User;


@Service
public class UpdateService {
	
	@Autowired
	private JobService jobService;

	@Autowired
	private UpdateDAO updateDAO;
	
	public void requestUpdate(User user,Job job,String message) {
		
		System.out.println("******************IN THE REQUEST UPDATE SERVICE to QUCIK*********************");
		System.out.println(user);
		System.out.println(job);
		System.out.println(message);
		
		UpdateRequest update = new UpdateRequest();
		
		user.requestUpdate(job, message,update);
		
		jobService.update(job);
		System.out.println("******************IN THE REQUEST UPDATE SERVICE to QUCIK*********************");
		updateDAO.saveOrUpdate(update);

		
		
	}
	
	public void postUpdate(User user,Job job,String message) {
		
		System.out.println("******************IN THE UPDATE SERVICE*********************");
		System.out.println(user);
		System.out.println(job);
		System.out.println(message);
		
		UpdateRequest update = new UpdateRequest();
		
		user.postUpdate(job, message,update);
		
		System.out.println(job.getUpdateRequested());
		jobService.update(job);
		System.out.println("******************IN THE UPDATE SERVICE to QUCIK*********************");
		updateDAO.saveOrUpdate(update);

		
		
	}
	
	
	

}
