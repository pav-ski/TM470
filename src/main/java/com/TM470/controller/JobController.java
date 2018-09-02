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


import com.TM470.domain.Job;
import com.TM470.domain.LocationArea;
import com.TM470.formModel.JobForm;
import com.TM470.formModel.UpdateForm;
import com.TM470.service.JobService;
import com.TM470.service.LocationAreaService;

@Controller
public class JobController {

	
	
	@Autowired 
	private JobService jobService;
	
	@Autowired 
	private LocationAreaService areaService;
	
	////////////////////////////////////////
	//Methods corresponding to use cases////
	////////////////////////////////////////
		//UC1 Report Issue
	 	@RequestMapping(value = "/jobForm", method = RequestMethod.GET)
	    public ModelAndView showForm(@RequestParam(value = "id", required = false) int id,Model model) {
		 
		 		//jobForm is the page to be viewed.
		 		ModelAndView modelAndView = new ModelAndView("jobForm","jobModel",new JobForm());
		 		
		 		//The area has already been selected, the object is again located by repository and to the model
		 		LocationArea area = areaService.getById(id);
		 	
		 		model.addAttribute("elements", area.getHasElements());
		 		model.addAttribute("area", area);
		 		model.addAttribute("areaId", area.getAreaID());
		 		
		 		//Return model and view which will direct to post form
		        return modelAndView;
	    }
	 	
	 
	 	//UC1 Report Issue
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
	            
	            //Values from jobForm are parsed to integers (isFaulty and isFor) to allow extraction from database by jobService
	            //severity is parsed as it should be an integer not a String
	            //description is left as String  
	            
	            int isFaultyId = Integer.parseInt(jobForm.getIsFaulty());
	            int isForId = Integer.parseInt(model.get("isFor").toString());
	            
	            jobService.postJob(jobForm.getDescription(), isFaultyId, jobForm.getSeverity(), isForId );
	            
	            System.out.println("POST submitted");


	           return "index";
	        }
	    
	    
	    //UC5 Repair an issue
	    @RequestMapping(value = "/completeJob", method = RequestMethod.GET)
	    public ModelAndView completeJob(@RequestParam(value = "id", required = false) int id,Model model) {
	    		
	    	
	    		
		 	//jobForm is the page to be viewed.
		 		ModelAndView mav = new ModelAndView("completeJob","updateForm",new UpdateForm());
		 		Job job = jobService.getById(id);
		 		mav.addObject("job", job);

		 		System.out.println(job);
		 		
				
				

		        return mav;
	    }
	    
	    
	    //UC5 Repair an issue
	    @RequestMapping(value = "/postCompleteJob", method = RequestMethod.POST)
        public String postCompleteJob(@ModelAttribute("updateForm")UpdateForm updateForm, @ModelAttribute("job")Job job,
    		      BindingResult result, ModelMap model) {
    	       if (result.hasErrors()) {
    	    	   System.out.println("ERRORS");
        	         return "error";
                  }

    	       //message and id are taken out of the model
    	       //id is converted from String to int
    	       String message = updateForm.getMessage();
    	       
    	       int id = Integer.parseInt(updateForm.getUpdateAbout());
    	       
    	       //jobService is responsible for completing the job
    	       jobService.completeJob(id,message);
    	       
    	      
             System.out.println("POST submitted");


           return "dashboard";
        }
	    
	    
	    //////////////////////////////////////////////
	    //End of methods corresponding to use cases///
	    //////////////////////////////////////////////
	    
	    //View all jobs for the area
	    @RequestMapping("/viewJobs")
		public String goToJobs(@RequestParam(value = "id", required = true)int id,Model model) {
	    	LocationArea area = areaService.getById(id);
	    	Set<Job> jobs = area.getHasJobs();
	    	model.addAttribute("jobs", jobs);
	    	
	    	return "viewJobs";
	    }
	    
	    //View one job in detail
	    @RequestMapping("/viewJob")
		public String goToJob(@RequestParam(value = "id", required = true)int id,Model model) {
	    	
	    	Job job = jobService.getById(id);
	    	model.addAttribute("job", job);
	    	
	    	return "viewJob";
	    }
	
	
	
	
	
	

}
