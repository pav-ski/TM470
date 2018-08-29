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
import com.TM470.formModel.UpdateForm;
import com.TM470.service.ElementService;
import com.TM470.service.JobService;
import com.TM470.service.LocationAreaService;
import com.TM470.service.LocationService;
import com.TM470.service.UpdateService;

@Controller
public class JobController {

	
	
	@Autowired
	private HomeController homeController;
	
	@Autowired 
	private JobService jobService;
	
	@Autowired 
	private UpdateService updateService;
	
	@Autowired 
	private LocationService locationService;
	
	@Autowired 
	private LocationAreaService areaService;
	
	@Autowired 
	private ElementService elementSerivce;
	
	
	 @RequestMapping(value = "/jobForm", method = RequestMethod.GET)
	    public ModelAndView showForm(@RequestParam(value = "id", required = false) int id,Model model) {
		 
		 	//jobForm is the page to be viewed.
		 		ModelAndView mav = new ModelAndView("jobForm","jobModel",new JobForm());
		 		LocationArea area = areaService.getById(id);
		 		model.addAttribute("elements", area.getHasElements());
		 		model.addAttribute("area", area);
		 		model.addAttribute("areaId", area.getAreaID());
		 		


		 		
				
				

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
	            Element element = elementSerivce.getById(Integer.parseInt(jobForm.getIsFaulty()));
	            
	            //LocationArea is also taken out from the model map, converted to String, parsed to int
	            //obtained value is then used to get an object form repository
	            LocationArea area = areaService.getById(Integer.parseInt(model.get("isFor").toString()));
	            
	            
	            String description = jobForm.getDescription();
	            int severity = jobForm.getSeverity();
	            
	            System.out.println(homeController.user);
	            User user = homeController.user;

	            
	            
	             System.out.println("POST submitted");


	           return "/dashboard";
	        }
	    
	    @RequestMapping("/viewJobs")
		public String goToJobs(@RequestParam(value = "id", required = true)int id,Model model) {
	    	LocationArea area = areaService.getById(id);
	    	Set<Job> jobs = area.getHasJobs();
	    	model.addAttribute("jobs", jobs);
	    	
	    	return "viewJobs";
	    }
	    
	    @RequestMapping("/viewJob")
		public String goToJob(@RequestParam(value = "id", required = true)int id,Model model) {
	    	
	    	Job job = jobService.getById(id);
	    	model.addAttribute("job", job);
	    	
	    	return "viewJob";
	    }
	
	    @RequestMapping(value = "/completeJob", method = RequestMethod.GET)
	    public ModelAndView completeJob(@RequestParam(value = "id", required = false) int id,Model model) {
	    		
	    	
	    		
		 	//jobForm is the page to be viewed.
		 		ModelAndView mav = new ModelAndView("completeJob","updateForm",new UpdateForm());
		 		Job job = jobService.getById(id);
		 		mav.addObject("job", job);

		 		System.out.println(job);
		 		
				
				

		        return mav;
	    }
	    
	    @RequestMapping(value = "/postCompleteJob", method = RequestMethod.POST)
        public String postCompleteJob(@ModelAttribute("updateForm")UpdateForm updateForm, @ModelAttribute("job")Job job,
    		      BindingResult result, ModelMap model) {
    	       if (result.hasErrors()) {
    	    	   System.out.println("ERRORS");
        	         return "error";
                  }

    	       User user = homeController.user;
    	       job = jobService.getById(Integer.parseInt(updateForm.getUpdateAbout()));
    	       updateService.postUpdate(user, job, updateForm.getMessage());
    	       jobService.completeJob(job);
    	       
    	      
             System.out.println("POST submitted");


           return "/dashboard";
        }
	
	
	
	
	
	

}
