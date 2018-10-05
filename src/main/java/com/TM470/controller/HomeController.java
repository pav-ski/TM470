package com.TM470.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.TM470.domain.Company;
import com.TM470.domain.Element;
import com.TM470.domain.Guest;
import com.TM470.domain.Job;
import com.TM470.domain.Location;
import com.TM470.domain.LocationArea;
import com.TM470.domain.Staff;
import com.TM470.domain.User;
import com.TM470.service.CompanyService;
import com.TM470.service.ElementService;
import com.TM470.service.JobService;
import com.TM470.service.LocationService;
import com.TM470.service.UserService;

@Controller
public class HomeController {

	
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired 
	private UserService userService;
	
	@Autowired 
	private JobService jobService;
	
	@Autowired 
	private LocationService locationService;
	
	public User user;
	
	
	//First landing page which mocks a user login
	@RequestMapping("/")
	public String welcome() {

		//CompanyService return list of Companies - there is only one
		List<Company> listCompany = companyService.getCompanyList();
		
		//New model object
        ModelAndView model = new ModelAndView("home");
        
        //Model is inserted with the company list
        model.addObject("companyList", listCompany);
        
        assert (model != null);
        
        //Below code was used to reset all faulty elements after testing
        //for(Location location: locationService.getLocations()) {
        //	for(LocationArea area : location.getHasAreas()) {
        //		for(Element element:area.getHasElements()) {
        //			elementService.resetElementScore(element);
        //		}
        //	}
        	
        //}
        
       
 
        //Returns index which directs the flow to jsp page
        return "index";
		
	}
	
	//Dashboard displays the active jobs, past jobs, connected Location objects and 
	//user notifications
	@RequestMapping("/dashboard")
	public ModelAndView dashboard(
			@RequestParam(value = "id", required = false) int id,Model model) {
		
		//Model and view is created with dashboard key to direct to JSP page
		ModelAndView mv = new ModelAndView("dashboard");
	
		//User identification is hard-coded at this stage
		//below can be changed to identify the class of user
		if(id==4)
		{
			userService.setSessionUser(id);
			user = userService.getById(id);
		}
		else if(id==6)
		{
			userService.setSessionUser(id);
			user = userService.getById(id);
		}
		
		
		
		//Get all locations for the company and add them to the model
		Company company = user.getIsUsingSystemOf();
		Set<Location> locations = company.getHaveLocations();
		mv.addObject("locations",locations);
		
		//Create two lists for the model - active and past jobs
		//They will be used by the if statements below which determine if user 
		//is a member of Staff or Guest
		List<Job> activeJobs = new ArrayList<Job>();
		List<Job> pastJobs = new ArrayList<Job>();
		
		//Staff users should be able to see all active and past jobs
		//
		if(user.getClass() == Staff.class) {
			
			//Iterate over the jobs from database and separate jobs 
			//into active and completed jobs
			for(Job eachJob:jobService.getJobs()) {
				if(eachJob.getActive()==true) {
					activeJobs.add(eachJob);
				}
				else if(eachJob.getActive()==false){
					pastJobs.add(eachJob);
				}
				
			//Add lists to the model and view object
			mv.addObject("jobs", activeJobs);
			mv.addObject("pastJobs", pastJobs);
			mv.addObject("notifications", user.getHasNotifications());
			}
			
			
		}
		
		//If user is a guest only jobs created by guest should be visible
		else if(user.getClass() == Guest.class) {
			
			//Add all jobs posted by user to the list
			List<Job> jobs = new ArrayList<Job>();
			jobs.addAll(user.getPostedA());
			
			
			
			//Iterate over the jobs which user posted and separate them
			//into active and completed jobs
			for(Job eachJob:jobs) {
				if(eachJob.getActive()==true) {
					activeJobs.add(eachJob);
				}
				else if(eachJob.getActive()==false){
					pastJobs.add(eachJob);
				}
			
				mv.addObject("jobs", activeJobs);
				mv.addObject("pastJobs", pastJobs);
				mv.addObject("notifications", user.getHasNotifications());
			
		}
			}
		
		//Return ModelAndView object which holds all the data
		return mv;
        }
		

	
	
	//Location displays all the rooms available in the location
	@RequestMapping("/location")
	public String welcome(
			@RequestParam(value = "id", required = false) int id,
			@RequestParam(value = "name") String name,Model model) {
		
		//By selecting one of the links id associated with location is passed on 
		//to locationService which searches for it in database
		
		Location location = locationService.getById(id);
		List<LocationArea> orderedAreas = new ArrayList<LocationArea>();
		orderedAreas.addAll(location.getHasAreas());
		
		model.addAttribute("areas", orderedAreas);

        return "location";
		
	}
	
	
	
	
	

}
