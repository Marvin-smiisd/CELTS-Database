package org.celts.db.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.celts.db.domain.Years;
import org.celts.db.service.YearService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/")
public class YearController {
	
protected static Logger logger = Logger.getLogger("controller");
	
	@Resource(name="yearService")
	private YearService yearService;
	
    /**
     * Retrieves the "Add New Student Lines ()" page
     */
    @RequestMapping(value = "/addYr", method = RequestMethod.GET)
    public String getAddYr(Model model) {
    	logger.debug("Received request to show add page");
    
    	// Prepare model object
    	Years years = new Years();
    	
    	// Add to model
    	model.addAttribute("yearsAttribute", years);

    	// This will resolve to /WEB-INF/jsp/add-sem-and-prog.jsp
    	return "add-sem-and-prog";
	}
 
    
 
    /**
     * Adds a new Student Lines
     */
    @RequestMapping(value = "/addYr", method = RequestMethod.POST)
    public String postAddYr(@ModelAttribute("studentLinesAttribute") Years year) {
		logger.debug("Received request to add new student line");
		
		// Delegate to service
		//semesterService.add(semesterId, semester);
		yearService.add(year);

		// Redirect to url
		return "redirect:/stu";
	}
    

    
    /**
     * Deletes a student line
     */
    @RequestMapping(value = "/deleteYr", method = RequestMethod.GET)
    public String getDeleteYr(@RequestParam("id") Integer yearId) {
    	logger.debug("Received request to delete student line");
    
    	// Delegate to service
		yearService.delete(yearId);

		// Redirect to url
		return "redirect:/stu";
	}
   
    /**
     * Retrieves the "Edit Existing student line" page
     */
    @RequestMapping(value = "/editYr", method = RequestMethod.GET)
    public String getEditYr(@RequestParam("id") Integer yearId, Model model) {
    	logger.debug("Received request to show edit page");
    	
    	// Retrieve student line by id
    	Years existingYears = yearService.get(yearId);

    	// Add to model
    	model.addAttribute("semestersAttribute", existingYears);

    	// This will resolve to /WEB-INF/jsp/edit-sem-and-prog.jsp
    	return "edit-sem-and-prog";
	}
 
    /**
     * Edits an existing student line
     */
    @RequestMapping(value = "/editYr", method = RequestMethod.POST)
    public String postEditYr(@RequestParam("id") Integer yearId, 
    						    @ModelAttribute("studentLinesAttribute") Years year) {
		logger.debug("Received request to add new semester");
		
		// Assign id
		year.setId(yearId);
		
		// Delegate to service
		yearService.edit(year);

		// Redirect to url
		return "redirect:/stu";
	}
   
	
}
