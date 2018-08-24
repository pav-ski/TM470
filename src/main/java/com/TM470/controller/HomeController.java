package com.TM470.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.TM470.dao.CompanyDAO;
import com.TM470.dao.ElementDAO;
import com.TM470.dao.JobDAO;
import com.TM470.dao.LocationAreaDAO;
import com.TM470.dao.LocationDAO;
import com.TM470.dao.UserDAO;
import com.TM470.domain.Company;
import com.TM470.domain.Job;
import com.TM470.domain.Location;
import com.TM470.domain.LocationArea;
import com.TM470.domain.Staff;
import com.TM470.domain.User;

@Controller
public class HomeController {

	
	
	@Autowired
	private CompanyDAO companyDAO;
	
	@Autowired
	private LocationDAO locationDAO;
	
	@Autowired 
	private UserDAO userDAO;
	
	@Autowired 
	private LocationAreaDAO locationAreaDAO;
	
	@Autowired 
	private ElementDAO elementDAO;
	
	@Autowired
	private JobDAO jobDAO;
	
	public User user;
	
	
	
	@RequestMapping("/")
	public String welcome() {

		
		List<Company> listCompany = companyDAO.list();
        ModelAndView model = new ModelAndView("home");
        model.addObject("companyList", listCompany);
        
        for(Location eachLocation:locationDAO.list()) {
        	System.out.println("Updating Scores");
        	for(LocationArea eachArea:eachLocation.getHasAreas()) {
        		eachArea.setRoomScore();
        		locationAreaDAO.updateLocationArea(eachArea);
        	}
        }
 

        return "index";
		
	}
	
	@RequestMapping("/dashboard")
	public ModelAndView dashboard(
			@RequestParam(value = "id", required = false) int id,Model model) {

		ModelAndView mv = new ModelAndView("dashboard");
	
		
		if(id==4)
		{
			user = userDAO.getStaff(id);
		}
		else if(id==6)
		{
			user = userDAO.getGuest(id);
		}
		
		if(user.getClass() == Staff.class) {
			Company company = user.getIsUsingSystemOf();
		Set<Location> locations = company.getHaveLocations();
		mv.addObject("locations",locations);
		
		List<Job> jobs= jobDAO.list();
		mv.addObject("jobs", jobs);

        	
		}
		
		
		return mv;
        }
		

	
	
	
	@RequestMapping("/location")
	public String welcome(
			@RequestParam(value = "id", required = false) int id,Model model) {
		Location location = locationDAO.getLocationById(id);
		model.addAttribute("areas", location.getHasAreas());
		
		
		
		
		
		

        
        

 
       

        return "location";
		
	}
	
	
	
	
	

}
