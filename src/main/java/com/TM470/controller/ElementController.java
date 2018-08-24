package com.TM470.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.TM470.dao.CompanyDAO;
import com.TM470.dao.ElementDAO;
import com.TM470.dao.LocationAreaDAO;
import com.TM470.dao.LocationDAO;
import com.TM470.domain.User;

@Controller
public class ElementController {

	
	
	@Autowired
	private CompanyDAO companyDAO;
	
	@Autowired
	private LocationDAO locationDAO;
	
	
	@Autowired 
	LocationAreaDAO locationAreaDAO;
	
	@Autowired 
	ElementDAO elementDAO;
	
	@Autowired 
	private HomeController homeController;
	
	
	
	@RequestMapping("/inventory")
	public String inventory(@RequestParam(value = "id", required = false) int id,Model model) {
		
		

		model.addAttribute("elements", locationAreaDAO.getLocationAreaById(id).getHasElements());
		
		
	
		

			
		
		return "inventory";
	}
	

	

	
	
	
	

}
