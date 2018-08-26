package com.TM470.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TM470.dao.JobDAO;
import com.TM470.dao.UserDAO;
import com.TM470.domain.User;


@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;

	@Autowired
	private JobDAO jobDAO;
	
	public void update(User user) {
		userDAO.updateUser(user);
	}
	
	

}
