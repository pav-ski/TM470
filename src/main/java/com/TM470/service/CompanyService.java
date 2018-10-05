package com.TM470.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TM470.dao.CompanyDAO;
import com.TM470.domain.Company;


@Service
public class CompanyService {
	
	@Autowired
	private CompanyDAO companyDAO;

	
	//get a list of companies
	public List<Company> getCompanyList(){
		return companyDAO.list();
	}
	
}
