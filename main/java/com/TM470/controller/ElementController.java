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
	

	//Inventory displays all the elements in the location area 
	//RequestParam passes on id as integer from the activated link 
	//which will locate selected LocationArea in the database and obtain all
	//assosisated Element objects from the hasElements collection.
	@RequestMapping("/inventory")
	public String inventory(@RequestParam(value = "id", required = false) int id,Model model) {
		
		//areaService locates the LocationArea object matching provided id in database
		//List of all elements for the area is added to the model
		model.addAttribute("elements",areaService.getById(id).getHasElements());		
		
		return "inventory";
	}

}
