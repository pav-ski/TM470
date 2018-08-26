package com.TM470.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.TM470.dao.CompanyDAO;
import com.TM470.dao.ElementDAO;
import com.TM470.dao.JobDAO;
import com.TM470.dao.LocationAreaDAO;
import com.TM470.dao.LocationDAO;
import com.TM470.dao.UserDAO;
import com.TM470.domain.Element;
import com.TM470.domain.Job;
import com.TM470.domain.LocationArea;
import com.TM470.domain.User;
import com.TM470.formModel.JobForm;
import com.TM470.service.JobService;

@Controller
public class JobController {

	
	
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
	private HomeController homeController;
	
	
	@Autowired
	private JobDAO jobDAO;
	
	@Autowired 
	private JobService jobService;
	
	
	 @RequestMapping(value = "/jobForm", method = RequestMethod.GET)
	    public ModelAndView showForm(@RequestParam(value = "id", required = false) int id,Model model) {
		 
		 	//jobForm is the page to be viewed.
		 		ModelAndView mav = new ModelAndView("jobForm","jobModel",new JobForm());
		 		LocationArea area = locationAreaDAO.getLocationAreaById(id);
		 		model.addAttribute("elements", area.getHasElements());
		 		model.addAttribute("area", area);
		 		model.addAttribute("areaId", area.getAreaID());
		 		
		 		Job newJob = new Job();

		 		
				
				

		        return mav;
	    }
	 
	    @RequestMapping(value = "/postJob", method = RequestMethod.POST)
	        public String submit(@ModelAttribute("job")JobForm jobForm, 
	    		      BindingResult result, ModelMap model) {
	    	       if (result.hasErrors()) {
	    	    	   System.out.println("ERRORS");
	        	         return "error";
	                  }
	    	       
	    	    //The form backing bean is filled with values from the form
	            model.addAttribute("description", jobForm.getDescription());
	            model.addAttribute("isFaulty", jobForm.getIsFaulty());
	            model.addAttribute("severity", jobForm.getSeverity());
	            model.addAttribute("isFor", jobForm.getIsFor());
	            
	            System.out.println(jobForm.getDescription());
	            System.out.println(model.get("description").toString());
	            
	            jobService.postJob(jobForm.getDescription(), Integer.parseInt(jobForm.getIsFaulty()), jobForm.getSeverity(), Integer.parseInt(model.get("isFor").toString()) );
	            
	            //Html does not pass objects, element id in form of string is converted to int
	            //and located by elementDAO in database.
	            Element element = elementDAO.getElementById(Integer.parseInt(jobForm.getIsFaulty()));
	            
	            //LocationArea is also taken out from the model map, converted to String, parsed to int
	            //obtained value is then used to get an object form repository
	            LocationArea area = locationAreaDAO.getLocationAreaById(Integer.parseInt(model.get("isFor").toString()));
	            
	            
	            String description = jobForm.getDescription();
	            int severity = jobForm.getSeverity();
	            
	            System.out.println(homeController.user);
	            User user = homeController.user;
	            //user.reportIssue(description, area, element, severity);

	            
	            
	            
	            //element.adjustFaultyElementScore(severity);
	            //elementDAO.updateElement(element);
	            //System.out.println("Element id" + element.getId() + " score  :" +element.getScore());
	            //for (Element eachElement: area.getHasElements()) {
	            //	System.out.println("Scores for elements in the collection : "+eachElement.getId() +"   " + eachElement.getScore());
	            //}
	            //LocationArea area2 = locationAreaDAO.getLocationAreaById(element.getIsIn().getId());
	            
	            //for (Element eachElement: area2.getHasElements()) {
	            //	System.out.println("Scores for elements in the reloaded collection : "+eachElement.getId() +"   " + eachElement.getScore());
	            //}
	            //elementDAO.refresh(element);
	            //locationAreaDAO.refresh(area);
	            
	            //area.updateAreaScore();
	            //jobDAO.saveOrUpdate(newJob);
	            //elementDAO.updateElement(element);
	            System.out.println("And actual score for the update:");
	            System.out.println(area);
	            System.out.println(area.getRoomScore());
	            //locationAreaDAO.updateLocationArea(area);
	            
	            
	             System.out.println("POST submitted");
	        
	        //Creation and return of new Job object to repository via commit() method

	           return "/dashboard";
	        }
	    
	    @RequestMapping("/viewJobs")
		public String goToJobs(@RequestParam(value = "id", required = true)int id,Model model) {
	    	LocationArea area = locationAreaDAO.getLocationAreaById(id);
	    	Set<Job> jobs = area.getHasJobs();
	    	model.addAttribute("jobs", jobs);
	    	
	    	return "viewJobs";
	    }
	    
	    @RequestMapping("/viewJob")
		public String goToJob(@RequestParam(value = "id", required = true)int id,Model model) {
	    	Job job = jobDAO.getJobById(id);
	    	model.addAttribute("job", job);
	    	
	    	return "viewJob";
	    }
	
	
	
	
	
	
	
	

}
