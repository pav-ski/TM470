package com.TM470.tests;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.TM470.service.JobService;
import com.TM470.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration("file:src/test/resources/test-web.xml") //classes = {UserService.class, JobService.class}
@WebAppConfiguration
public class AutomatedTest {
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private JobService jobService;
	
	@Before
	@Ignore
	public void beforeTests(){
		
		userService.setSessionUser(4);

	}
	
	
	@Test
	public void testUC1() {

		
		String message = "test UC 1";
		int fault = 1;
		int severity = 1;
		int isFor = 44;
		
		jobService.postJob(message, fault, severity, isFor);
		
		
	}

}
