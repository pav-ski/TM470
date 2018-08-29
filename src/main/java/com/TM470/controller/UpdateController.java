package com.TM470.controller;

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
import com.TM470.domain.Job;
import com.TM470.domain.User;
import com.TM470.formModel.UpdateForm;
import com.TM470.service.JobService;
import com.TM470.service.UpdateService;

@Controller
public class UpdateController {

	@Autowired
	private HomeController homeController;
	
	@Autowired
	private UpdateService updateService;

	@Autowired
	private JobService jobService;
	
	
	 @RequestMapping(value = "/requestUpdate", method = RequestMethod.GET)
	    public ModelAndView requestUpdate(@RequestParam(value = "id", required = false) Integer id,Model model) {
		 

		 		ModelAndView mav = new ModelAndView("requestUpdate","updateForm",new UpdateForm());
		 		Job job = jobService.getById(id);
		 		model.addAttribute("job", job);
		 		
		 		
				
				

		        return mav;
	    }
	 
	 @RequestMapping(value = "/postRequestUpdate", method = RequestMethod.POST)
     public String postUpdateRequest(@ModelAttribute("updateForm")UpdateForm updateForm, 
 		      BindingResult result, ModelMap model) {
 	       if (result.hasErrors()) {
 	    	   System.out.println("ERRORS");
     	         return "error";
               }
 	       
 	    //The form backing bean is filled with values from the form
         model.addAttribute("message", updateForm.getMessage());
         model.addAttribute("updateAbout", updateForm.getUpdateAbout());
         
         //Html does not pass objects, element id in form of string is converted to int
         //and located by elementDAO in database.
         Job job = jobService.getById(Integer.parseInt(updateForm.getUpdateAbout()));
         
         User user = homeController.user;
         
         updateService.requestUpdate(user,job,updateForm.getMessage());

         
         
          System.out.println("POST submitted");
     
          //Creation and return of new Job object to repository via commit() method

        return "/dashboard";
     }
	 
	 
	 @RequestMapping(value = "/update", method = RequestMethod.GET)
	    public ModelAndView update(@RequestParam(value = "id", required = false) Integer id,Model model) {
		 

		 		ModelAndView mav = new ModelAndView("update","updateForm",new UpdateForm());
		 		Job job = jobService.getById(id);
		 		model.addAttribute("job", job);
		 		
		 		
				
				

		        return mav;
	    }
	    
	 @RequestMapping(value = "/postUpdate", method = RequestMethod.POST)
     public String postUpdate(@ModelAttribute("updateForm")UpdateForm updateForm, 
 		      BindingResult result, ModelMap model) {
 	       if (result.hasErrors()) {
 	    	   System.out.println("ERRORS");
     	         return "error";
               }
 	       
 	    //The form backing bean is filled with values from the form
         model.addAttribute("message", updateForm.getMessage());
         model.addAttribute("updateAbout", updateForm.getUpdateAbout());
         
         //Html does not pass objects, element id in form of string is converted to int
         //and located by elementDAO in database.
         Job job = jobService.getById(Integer.parseInt(updateForm.getUpdateAbout()));
         
         User user = homeController.user;
         
         updateService.postUpdate(user,job,updateForm.getMessage());
         
         
          System.out.println("POST submitted");
     
     //Creation and return of new Job object to repository via commit() method

        return "/dashboard";
     }
	
	
	
	
	
	
	
	

}
