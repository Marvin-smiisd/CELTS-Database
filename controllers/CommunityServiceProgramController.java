package org.celts.db.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.celts.db.domain.CommunityServiceProgram;
import org.celts.db.service.CommunityServiceProgramService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class CommunityServiceProgramController {
	
protected static Logger logger = Logger.getLogger("controller");
	
	@Resource(name="communityServiceProgramService")
	private CommunityServiceProgramService communityServiceProgramService;
	
    /**
     * Retrieves the "Add New Student Lines ()" page
     */
    @RequestMapping(value = "/addProg", method = RequestMethod.GET)
    public String getAddProg(Model model) {
    	logger.debug("Received request to show add page");
    
    	// Prepare model object
    	CommunityServiceProgram communityServiceProgram = new CommunityServiceProgram();
    	
    	// Add to model
    	model.addAttribute("communityServiceProgramAttribute", communityServiceProgram);

    	// This will resolve to /WEB-INF/jsp/add-sem-and-prog.jsp
    	return "add-sem-and-prog";
	}
 
    
 
    /**
     * Adds a new Student Lines
     */
    @RequestMapping(value = "/addProg", method = RequestMethod.POST)
    public String postAddProg(@ModelAttribute("studentLinesAttribute") CommunityServiceProgram communityServiceProgram) {
		logger.debug("Received request to add new student line");
		
		// Delegate to service
		//semesterService.add(semesterId, semester);
		communityServiceProgramService.add(communityServiceProgram);

		// Redirect to url
		return "redirect:/stu";
	}
    

    
    /**
     * Deletes a student line
     */
    @RequestMapping(value = "/deleteProg", method = RequestMethod.GET)
    public String getDeleteProg(@RequestParam("id") Integer communityServiceProgramId) {
    	logger.debug("Received request to delete student line");
    
    	// Delegate to service
    	communityServiceProgramService.delete(communityServiceProgramId);

		// Redirect to url
		return "redirect:/stu";
	}
   
    /**
     * Retrieves the "Edit Existing student line" page
     */
    @RequestMapping(value = "/editProg", method = RequestMethod.GET)
    public String getEditProg(@RequestParam("id") Integer communityServiceProgramId, Model model) {
    	logger.debug("Received request to show edit page");
    	
    	// Retrieve student line by id
    	CommunityServiceProgram existingCommunityServiceProgram = communityServiceProgramService.get(communityServiceProgramId);

    	// Add to model
    	model.addAttribute("semestersAttribute", existingCommunityServiceProgram);

    	// This will resolve to /WEB-INF/jsp/edit-sem-and-prog.jsp
    	return "edit-sem-and-prog";
	}
 
    /**
     * Edits an existing student line
     */
    @RequestMapping(value = "/editProg", method = RequestMethod.POST)
    public String postEditYr(@RequestParam("id") Integer communityServiceProgramId, 
    						    @ModelAttribute("studentLinesAttribute") CommunityServiceProgram communityServiceProgram) {
		logger.debug("Received request to add new semester");
		
		// Assign id
		communityServiceProgram.setId(communityServiceProgramId);
		
		// Delegate to service
		communityServiceProgramService.edit(communityServiceProgram);

		// Redirect to url
		return "redirect:/stu";
	}
   

}
