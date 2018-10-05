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


import com.TM470.domain.Job;
import com.TM470.formModel.UpdateForm;
import com.TM470.service.JobService;
import com.TM470.service.UpdateService;

@Controller
public class UpdateController {
	
	@Autowired
	private UpdateService updateService;

	@Autowired
	private JobService jobService;
	
	////////////////////////////////////////
	//Methods corresponding to use cases////
	////////////////////////////////////////
	
	//UC 2 - Request Update
	//Request mapping for update request
	//Passes on id which will get the job from jobService
	 @RequestMapping(value = "/requestUpdate", method = RequestMethod.GET)
	    public ModelAndView requestUpdate(@RequestParam(value = "id", required = false) Integer id,Model model) {
		 

		 		ModelAndView mav = new ModelAndView("requestUpdate","updateForm",new UpdateForm());
		 		Job job = jobService.getById(id);
		 		model.addAttribute("job", job);

		        return mav;
	    }
	 
	 //UC 2 - Request Update
	 //Post method for update request form
	 //UpdateForm is a form backing bean
	 //ModelMap model holds any parameters passed on from GET method
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
         
         //Html does not pass objects updateAbout is converted to int to be located by updateService
         int id = Integer.parseInt(updateForm.getUpdateAbout());
         String message = updateForm.getMessage();

         //Pre-condition checks
         assert id >= 0;
         assert message != null;
         
         //Argument are passed to updateService
         updateService.requestUpdate(id,message);

          
          //Returns index to redirect back to front page
        return "index";
     }
	 
	 //UC3 Request Update
	 //Method which opens the form for new update
	 @RequestMapping(value = "/update", method = RequestMethod.GET)
	    public ModelAndView update(@RequestParam(value = "id", required = false) Integer id,Model model) {
		 
		 		//Model and view contains update - page ot be viewed
		 		//and the form backing object - updateForm 
		 		ModelAndView mav = new ModelAndView("update","updateForm",new UpdateForm());
		 		Job job = jobService.getById(id);
		 		model.addAttribute("job", job);

		        return mav;
	    }
	 
	 //UC3 Request Update
	 //Post method for creation of new update
	 @RequestMapping(value = "/postUpdate", method = RequestMethod.POST)
     public String postUpdate(@ModelAttribute("updateForm")UpdateForm updateForm, 
 		      BindingResult result, ModelMap model) {
 	       if (result.hasErrors()) {
 	    	   System.out.println("ERRORS");
     	         return "error";
               }
 	       
 	     //The form backing object is filled with values from the form
         model.addAttribute("message", updateForm.getMessage());
         model.addAttribute("updateAbout", updateForm.getUpdateAbout());
         
         //Html does not pass objects, element id in form of string is converted to int
         //and located by elementDAO in database.
         int id = Integer.parseInt(updateForm.getUpdateAbout());

         String message = updateForm.getMessage();
         
         //pre-condition checks for postUpdate
         assert id >=0;
         assert message != null;
         updateService.postUpdate(id,message);


        return "index";
     }

}
