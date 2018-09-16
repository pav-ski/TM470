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
import com.TM470.service.UserService;

@Controller
public class JobController {

	
	
	@Autowired 
	private JobService jobService;
	
	@Autowired 
	private LocationAreaService areaService;
	
	@Autowired 
	private UserService userService;
	
	////////////////////////////////////////
	//Methods corresponding to use cases////
	////////////////////////////////////////
	
	
		//UC1 Report Issue - GET
	 	@RequestMapping(value = "/jobForm", method = RequestMethod.GET)
	    public ModelAndView showForm(@RequestParam(value = "id", required = false) int id,Model model) {
		 
		 		//jobForm is the page to be viewed.
		 		ModelAndView modelAndView = new ModelAndView("jobForm","jobModel",new JobForm());
		 		
		 		//The area has already been selected, the object is again located by repository 
		 		LocationArea area = areaService.getById(id);
		 	
		 		model.addAttribute("elements", area.getHasElements());
		 		model.addAttribute("area", area);
		 		model.addAttribute("areaId", area.getAreaID());
		 		
		 		//Return model and view which will direct to post form
		        return modelAndView;
	    }
	 	
	 
	 	//UC1 Report Issue - POST
	 	//The form populates form backing bean jobForm with attribute values filled in by user
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
	            
	            assert isFaultyId >= 0;
	            assert isForId >= 0;
	            assert jobForm.getSeverity() >= 1 && jobForm.getSeverity() <=5;
	            assert jobForm.getDescription() != null;
	            
	            userService.postJob(jobForm.getDescription(), isFaultyId, jobForm.getSeverity(), isForId );

	           return "index";
	        }
	    
	    
	    //UC5 Repair an issue - GET method
	    //By selecting the job link passes on the id of the selected job
	    @RequestMapping(value = "/completeJob", method = RequestMethod.GET)
	    public ModelAndView completeJob(@RequestParam(value = "id", required = false) int id,Model model) {
	    		
	    	
	    		
		 		//jobForm is the page to be viewed.
	    		//updateForm is the form backing bean
		 		ModelAndView mav = new ModelAndView("completeJob","updateForm",new UpdateForm());
		 		
		 		//Get the job from repository by its id and add it to the ModelAndView
		 		Job job = jobService.getById(id);
		 		mav.addObject("job", job);
		 		
		 		//Return ModelAndView object with job and form backing bean inserted
		        return mav;
	    }
	    
	    
	    //UC5 Repair an issue - POST method
	    //
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

    	       //Pre-condition checks for completeJob()
    	       assert id >=0;
    	       assert message != null;

    	       //jobService is responsible for completing the job
    	       jobService.completeJob(id,message);
    	       
           return "dashboard";
        }
	    
	    
	    //////////////////////////////////////////////
	    //End of methods corresponding to use cases///
	    //////////////////////////////////////////////
	    //******************************************//
	    //////////////////////////////////////////////
	    //Utility Methods for displaying content   ///
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
	
	    /////////////////////////////////////////////////////
	    //End of Utility Methods for displaying content   ///
	    /////////////////////////////////////////////////////
	
	
	
	

}
