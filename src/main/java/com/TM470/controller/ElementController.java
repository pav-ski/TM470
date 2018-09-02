package com.TM470.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.TM470.service.LocationAreaService;

@Controller
public class ElementController {

	
	
	@Autowired 
	private LocationAreaService areaService;
	
	
	
	
	@RequestMapping("/inventory")
	public String inventory(@RequestParam(value = "id", required = false) int id,Model model) {
		
		

		model.addAttribute("elements",areaService.getById(id).getHasElements());		
		
	
		

			
		
		return "inventory";
	}
	

	

	
	
	
	

}
