package com.TM470.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.TM470.dao.CompanyDAO;
import com.TM470.dao.LocationAreaDAO;
import com.TM470.dao.LocationDAO;
import com.TM470.domain.Company;
import com.TM470.domain.Location;
import com.TM470.domain.LocationArea;

@Controller
public class HomeController {

	
	
	@Autowired
	private CompanyDAO companyDAO;
	
	@Autowired
	private LocationDAO locationDAO;
	
	@Autowired LocationAreaDAO locationAreaDAO;
	
	
	@RequestMapping("/")
	public String welcome() {

		
		List<Company> listCompany = companyDAO.list();
		List<Location> listLocation = locationDAO.list();
        ModelAndView model = new ModelAndView("home");
        model.addObject("companyList", listCompany);
        System.out.println(listCompany);
        System.out.println(listLocation);
        
        
        for (Company eachCompany: listCompany) {
        	System.out.println(eachCompany.getName());
        	for(Location eachLocation: eachCompany.getHaveLocations())
        	System.out.println(eachLocation.getName());
        	
      
        }
        
        //Creation and persistance of new domain object
        //Location newLocation = new Location();
       // 		newLocation.setName("Queen Street");
       // 		newLocation.setNumOfUnits(20);
       // 		newLocation.setBelongsTo(company);
       // 		
       // 		company.addLocation(newLocation);
       // locationDAO.addLocation(newLocation);
        
        
        //Room room = new Room();
        
        //PrivateArea area = new PrivateArea();
        
        //PublicArea area = new PublicArea();
        //area.createArea("Staff room", "Staff room 1", 5.0, listLocation.get(0));
        //locationAreaDAO.addLocationArea(area);
        

        return "index";
		
	}
	
	
	
	

}
