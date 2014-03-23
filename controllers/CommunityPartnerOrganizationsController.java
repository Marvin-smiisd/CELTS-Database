package org.celts.db.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.celts.db.domain.CommunityPartnerOrganizations;
import org.celts.db.dto.CommunityPartnerOrganizationsDTO;
import org.celts.db.service.CommunityPartnerOrganizationsService;
import org.celts.db.service.CommunityServiceProgramsLinesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/cmmPtnrOrg")
public class CommunityPartnerOrganizationsController {

	protected static Logger logger = Logger.getLogger("controller");
	
	@Resource(name="communityPartnerOrganizationsService")
	private CommunityPartnerOrganizationsService communityPartnerOrganizationsService;

	@Resource(name="communityServiceProgramsLinesService")
	private CommunityServiceProgramsLinesService communityServiceProgramsLinesService;	
	/**
	 * Retrieves the "Records" page
	 */
    @RequestMapping(value = "/Org", method = RequestMethod.GET)
    public String getOrg(Model model) {
    	logger.debug("Received request to show records page");
    	
    	// Retrieve all StudentVolunteers
    	List<CommunityPartnerOrganizations> communityPartnerOrganizations = communityPartnerOrganizationsService.getAll();
    	
    	// Prepare model object
    	List<CommunityPartnerOrganizationsDTO> communityPartnerOrganizationsDTO = new ArrayList<CommunityPartnerOrganizationsDTO>();
    	
    	for (CommunityPartnerOrganizations communityPartnerOrganization: communityPartnerOrganizations) {
    		// Create new data transfer object
    		CommunityPartnerOrganizationsDTO dto = new CommunityPartnerOrganizationsDTO();
    		
    		dto.setId(communityPartnerOrganization.getId());
			dto.setOrg(communityPartnerOrganization.getOrg());
			dto.setContact(communityPartnerOrganization.getContact());
			dto.setCommunityServiceProgramsLines(communityServiceProgramsLinesService.getAllCmmPntnrOrgs(communityPartnerOrganization.getId()));	
			
			// Add to model list
			communityPartnerOrganizationsDTO.add(dto);
    	}
    	
    	// Add to model
    	model.addAttribute("communityPartnerOrganizations", communityPartnerOrganizationsDTO);

    	// This will resolve to /WEB-INF/jsp/records.jsp
		return "cmmPartnerOrgRrecords";
	}
	
	
	
	
	
	
	
    /**
     * Retrieves the "Add New Student Lines ()" page
     */
    @RequestMapping(value = "/addCmmPtnrOrg", method = RequestMethod.GET)
    public String getAddSem(Model model) {
    	logger.debug("Received request to show add page");
    
    	// Prepare model object
    	CommunityPartnerOrganizations communityPartnerOrganizations = new CommunityPartnerOrganizations();
    	
    	// Add to model
    	model.addAttribute("communityPartnerOrganizationsAttribute", communityPartnerOrganizations);

    	// This will resolve to /WEB-INF/jsp/add-sem-and-prog.jsp
    	return "add-community-partner-orgs";
	}
 
    
 
    /**
     * Adds a new Student Lines
     */
    @RequestMapping(value = "/addCmmPtnrOrg", method = RequestMethod.POST)
    public String postAddSem(@ModelAttribute("communityPartnerOrganizationsAttribute") CommunityPartnerOrganizations communityPartnerOrganizations) {
		logger.debug("Received request to add new student line");
		
		// Delegate to service
		//semesterService.add(semesterId, semester);
		communityPartnerOrganizationsService.add(communityPartnerOrganizations);

		// Redirect to url
		return "redirect:/cmmPtnrOrg/Org";
	}
    

    
    /**
     * Deletes a student line
     */
    @RequestMapping(value = "/deleteCmmPtnrOrg", method = RequestMethod.GET)
    public String getDeleteSem(@RequestParam("id") Integer communityPartnerOrganizationsId) {
    	logger.debug("Received request to delete student line");
    
    	
    	// Delete all associated Student Volunteers first
    	communityServiceProgramsLinesService.deleteAll(communityPartnerOrganizationsId);
    	
    	// Delegate to service
    	communityPartnerOrganizationsService.delete(communityPartnerOrganizationsId);

		// Redirect to url
		return "redirect:/cmmPtnrOrg/Org";
	}
   
    /**
     * Retrieves the "Edit Existing student line" page
     */
    @RequestMapping(value = "/editCmmPtnrOrg", method = RequestMethod.GET)
    public String getEditSem(@RequestParam("id") Integer communityPartnerOrganizationsId, Model model) {
    	logger.debug("Received request to show edit page");
    	
    	// Retrieve student line by id
    	CommunityPartnerOrganizations existingCommunityPartnerOrganizations = communityPartnerOrganizationsService.get(communityPartnerOrganizationsId);

    	// Add to model
    	model.addAttribute("communityPartnerOrganizationsAttribute", existingCommunityPartnerOrganizations);

    	// This will resolve to /WEB-INF/jsp/edit-sem-and-prog.jsp
    	return "edit-community-partner-orgs";
	}
 
    /**
     * Edits an existing student line
     */
    @RequestMapping(value = "/editCmmPtnrOrg", method = RequestMethod.POST)
    public String postEditSem(@RequestParam("id") Integer communityPartnerOrganizationsId, 
    						    @ModelAttribute("communityPartnerOrganizationsAttribute")CommunityPartnerOrganizations communityPartnerOrganizations) {
		logger.debug("Received request to add new semester");
		
		// Assign id
		communityPartnerOrganizations.setId(communityPartnerOrganizationsId);
		
		// Delegate to service
		communityPartnerOrganizationsService.edit(communityPartnerOrganizations);

		// Redirect to url
		return "redirect:/cmmPtnrOrg/Org";
	}
}
