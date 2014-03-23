package org.celts.db.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.celts.db.domain.Semesters;
import org.celts.db.service.SemesterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class SemestersController {
	
protected static Logger logger = Logger.getLogger("controller");
	
	@Resource(name="semesterService")
	private SemesterService semesterService;
	
    /**
     * Retrieves the "Add New Student Lines ()" page
     */
    @RequestMapping(value = "/addSem", method = RequestMethod.GET)
    public String getAddSem(Model model) {
    	logger.debug("Received request to show add page");
    
    	// Prepare model object
    	Semesters semesters = new Semesters();
    	
    	// Add to model
    	model.addAttribute("semestersAttribute", semesters);

    	// This will resolve to /WEB-INF/jsp/add-sem-and-prog.jsp
    	return "add-sem-and-prog";
	}
 
    
 
    /**
     * Adds a new Student Lines
     */
    @RequestMapping(value = "/addSem", method = RequestMethod.POST)
    public String postAddSem(@ModelAttribute("studentLinesAttribute") Semesters semester) {
		logger.debug("Received request to add new student line");
		
		// Delegate to service
		//semesterService.add(semesterId, semester);
		semesterService.add(semester);

		// Redirect to url
		return "redirect:/stu";
	}
    

    
    /**
     * Deletes a student line
     */
    @RequestMapping(value = "/deleteSem", method = RequestMethod.GET)
    public String getDeleteSem(@RequestParam("id") Integer semesterId) {
    	logger.debug("Received request to delete student line");
    
    	// Delegate to service
		semesterService.delete(semesterId);

		// Redirect to url
		return "redirect:/stu";
	}
   
    /**
     * Retrieves the "Edit Existing student line" page
     */
    @RequestMapping(value = "/editSem", method = RequestMethod.GET)
    public String getEditSem(@RequestParam("id") Integer semesterId, Model model) {
    	logger.debug("Received request to show edit page");
    	
    	// Retrieve student line by id
    	Semesters existingSemesters = semesterService.get(semesterId);

    	// Add to model
    	model.addAttribute("semestersAttribute", existingSemesters);

    	// This will resolve to /WEB-INF/jsp/edit-sem-and-prog.jsp
    	return "edit-sem-and-prog";
	}
 
    /**
     * Edits an existing student line
     */
    @RequestMapping(value = "/editSem", method = RequestMethod.POST)
    public String postEditSem(@RequestParam("id") Integer semesterId, 
    						    @ModelAttribute("studentLinesAttribute") Semesters semesters) {
		logger.debug("Received request to add new semester");
		
		// Assign id
		semesters.setId(semesterId);
		
		// Delegate to service
		semesterService.edit(semesters);

		// Redirect to url
		return "redirect:/stu";
	}
   
}
