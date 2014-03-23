
package org.celts.db.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.celts.db.domain.CommunityParticipants;
import org.celts.db.dto.CommunityParticipantsDTO;
import org.celts.db.service.CommunityParticipantService;
import org.celts.db.service.CommunityServiceProgramsLinesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/commServPtcp")
public class CommunityParticipantsController {
	
protected static Logger logger = Logger.getLogger("controller");
	
	@Resource(name="communityParticipantService")
	private CommunityParticipantService communityParticipantService;
	
	@Resource(name="communityServiceProgramsLinesService")
	private CommunityServiceProgramsLinesService communityServiceProgramsLinesService;
	/**
	 * Retrieves the "Records" page
	 */
    @RequestMapping(value = "/Participants", method = RequestMethod.GET)
    public String getStu(Model model) {
    	logger.debug("Received request to show records page");
    	
    	// Retrieve all StudentVolunteers
    	List<CommunityParticipants> communityParticipants = communityParticipantService.getAll();
    	
    	// Prepare model object
    	List<CommunityParticipantsDTO> communityParticipantsDTO = new ArrayList<CommunityParticipantsDTO>();
    	
    	for (CommunityParticipants communityParticipant: communityParticipants) {
    		// Create new data transfer object
    		CommunityParticipantsDTO dto = new CommunityParticipantsDTO();
    		
			dto.setId(communityParticipant.getId());
			dto.setFirstName(communityParticipant.getFirstName());
			dto.setLastName(communityParticipant.getLastName());
			dto.setCommunityServiceProgramsLines(communityServiceProgramsLinesService.getAllCmmPtcp(communityParticipant.getId()));
			//dto.setStudentLines(studentLinesService.getAll(studentVolunteer.getId()));		
			
			// Add to model list
			communityParticipantsDTO.add(dto);
    	}
    	
    	// Add to model
    	model.addAttribute("communityParticipants", communityParticipantsDTO);

    	// This will resolve to /WEB-INF/jsp/records.jsp
		return "cmmParticipantRrecords";
	}
	
	
	
	
    /**
     * Retrieves the "Add New Student Lines ()" page
     */
    @RequestMapping(value = "/addParticipant", method = RequestMethod.GET)
    public String getAddParticipant(Model model) {
    	logger.debug("Received request to show add page");
    
    	// Prepare model object
    	CommunityParticipants communityParticipants = new CommunityParticipants();
    	
    	// Add to model
    	model.addAttribute("communityParticipantsAttribute", communityParticipants);

    	// This will resolve to /WEB-INF/jsp/add-sem-and-prog.jsp
    	return "add-community-participant";
	}
 
    
 
    /**
     * Adds a new Student Lines
     */
    @RequestMapping(value = "/addParticipant", method = RequestMethod.POST)
    public String postAddParticipant(@ModelAttribute("communityParticipantsAttribute") CommunityParticipants communityParticipants) {
		logger.debug("Received request to add new student line");
		
		// Delegate to service
		//semesterService.add(semesterId, semester);
		communityParticipantService.add(communityParticipants);

		// Redirect to url
		return "redirect:/commServPtcp/Participants";
	}
    

    
    /**
     * Deletes a student line
     */
    @RequestMapping(value = "/deleteParticipant", method = RequestMethod.GET)
    public String getDeleteParticipant(@RequestParam("id") Integer communityParticipantsId) {
    	logger.debug("Received request to delete student line");
    
    	// Delegate to service
    	communityParticipantService.delete(communityParticipantsId);

		// Redirect to url
		return "redirect:/commServPtcp/Participants";
	}
   
    /**
     * Retrieves the "Edit Existing student line" page
     */
    @RequestMapping(value = "/editParticipant", method = RequestMethod.GET)
    public String getEditParticipant(@RequestParam("id") Integer communityParticipantsId, Model model) {
    	logger.debug("Received request to show edit page");
    	
    	// Retrieve student line by id
    	CommunityParticipants existingCommunityParticipants = communityParticipantService.get(communityParticipantsId);

    	// Add to model
    	model.addAttribute("communityParticipantsAttribute", existingCommunityParticipants);

    	// This will resolve to /WEB-INF/jsp/edit-sem-and-prog.jsp
    	return "edit-community-participants";
	}
 
    /**
     * Edits an existing student line
     */
    @RequestMapping(value = "/editParticipant", method = RequestMethod.POST)
    public String postEditSem(@RequestParam("id") Integer communityParticipantsId, 
    						    @ModelAttribute("communityParticipantsAttribute") CommunityParticipants communityParticipants) {
		logger.debug("Received request to add new semester");
		
		// Assign id
		communityParticipants.setId(communityParticipantsId);
		
		// Delegate to service
		communityParticipantService.edit(communityParticipants);

		// Redirect to url
		return "redirect:/commServPtcp/Participants";
	}
   

}
