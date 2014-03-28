package org.celts.db.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.celts.db.domain.BonnerScholars;
import org.celts.db.dto.BonnerScholarsDTO;
import org.celts.db.service.CommunityServiceProgramService;
import org.celts.db.service.SemesterService;
import org.celts.db.service.StudentLinesService;
import org.celts.db.service.BonnerScholarsService;
import org.celts.db.service.StudentVolunteerService;
import org.celts.db.service.YearService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/bnrSchlr")
public class BonnerScholarsController {
	
	protected static Logger logger = Logger.getLogger("controller");
	
	@Resource(name="bonnerScholarsService")
	private BonnerScholarsService bonnerScholarsService;
	
	@Resource(name="studentVolunteerService")
	private StudentVolunteerService studentVolunteerService;
	
	@Resource(name="studentLinesService")
	private StudentLinesService studentLinesService;

	@Resource(name="semesterService")
	private SemesterService semesterService;

	@Resource(name="yearService")
	private YearService yearService;
	
	@Resource(name="communityServiceProgramService")
	private CommunityServiceProgramService communityServiceProgramService;
	
	//@Resource(name="bonnerScholarsLinesService")
	//private BonnerScholarsLinesService bonnerScholarsLinesService;
		
	/**
	 * Retrieves the "Records" page
	 */
    @RequestMapping(value = "/bnrScholars", method = RequestMethod.GET)
    public String getSBnrScholars(Model model) {
    	logger.debug("Received request to show records page");
    	
    	// Retrieve all StudentVolunteers
    	//List<StudentVolunteer> studentVolunteers = studentVolunteerService.getAll();
    	List<BonnerScholars> bonnerScholars = bonnerScholarsService.getAll();
    	
    	// Prepare model object
    	List<BonnerScholarsDTO> bonnerScholarsDTO = new ArrayList<BonnerScholarsDTO>();
    	
    	for (BonnerScholars bonnerScholar: bonnerScholars) {
    		// Create new data transfer object
    		BonnerScholarsDTO dto = new BonnerScholarsDTO();
    		
			dto.setId(bonnerScholar.getId());
			dto.setFirstName(bonnerScholar.getFirstName());
			dto.setLastName(bonnerScholar.getLastName());
			dto.setCommunityServiceProgram(communityServiceProgramService.getAll());
	        dto.setYears(yearService.getAll());
	        dto.setSemesters(semesterService.getAll());
	        dto.setStudentLines(studentLinesService.getAllBonnerSchalars(bonnerScholar.getId()));
		
			// Add to model list
			bonnerScholarsDTO.add(dto);
    	}
    	
    	// Add to model
    	model.addAttribute("bonnerScholars", bonnerScholarsDTO);

    	// This will resolve to /WEB-INF/jsp/records.jsp
		return "bnrScholarsRecords";
	}
    
    /**
     *  Retrieves the "Add New Record" page
     */
    @RequestMapping(value = "/addBnrScholars", method = RequestMethod.GET)
    public String getAddBnrScholars(Model model) {
   //public String getAdd(@RequestParam("id") Integer studentVolunteerId, Model model) {
    	logger.debug("Received request to show add page");
    
   
     	// Create new StudentVolunteer and add to model
    	model.addAttribute("bonnerScholarsAttribute", new BonnerScholars());
    	
  
    	// This will resolve to /WEB-INF/jsp/add-student-volunteer.jsp
    	return "add-bonner-scholars";
    	
	}
 
    /**
     * Adds a new record
     */
    @RequestMapping(value = "/addBnrScholars", method = RequestMethod.POST)
    public String postAddBnrScholars(@ModelAttribute("bonnerScholarsAttribute") BonnerScholars bonnerScholars) {
		logger.debug("Received request to add new record");

		// Delegate to service
		bonnerScholarsService.add(bonnerScholars);

		// Redirect to url
		return "redirect:/bnrSchlr/bnrScholars";
	}
    
    /**
     * Deletes a record including all the associated Student Volunteers
     */
    @RequestMapping(value = "/deleteBrnScholars", method = RequestMethod.GET)
    public String getDeleteBnrScholars(@RequestParam("id") Integer bonnerScholarsId) {
    	logger.debug("Received request to delete record");
    
    	// Delete all associated Student Volunteers first
    	studentLinesService.deleteAllBnrSchloars(bonnerScholarsId);
    	
    	// Delete StudentVolunteer
		bonnerScholarsService.delete(bonnerScholarsId);

		// Redirect to url
		return "redirect:/bnrSchlr/bnrScholars";
	}
    
    /**
     * Retrieves the "Edit Existing Record" page
     */
    @RequestMapping(value = "/editBnrScholars", method = RequestMethod.GET)
    public String getEditBnrScholars(@RequestParam("id") Integer bonnerScholarsId, Model model) {
    	logger.debug("Received request to show edit page");
    	
    	// Retrieve StudentVolunteer by id
    	BonnerScholars existingBonnerScholars = bonnerScholarsService.get(bonnerScholarsId);

    	// Add to model
    	model.addAttribute("bonnerScholarsAttribute", existingBonnerScholars);

    	// This will resolve to /WEB-INF/jsp/edit-studen-volunteer.jsp
    	return "edit-bonner-scholars";
	}
 
    /**
     * Edits an existing record
     */
    @RequestMapping(value = "/editBnrScholars", method = RequestMethod.POST)
    public String postEditBnrScholars(@RequestParam("id") Integer bonnerScholarsId, 
    						    @ModelAttribute("bonnerScholarsAttribute") BonnerScholars bonnerScholars) {
		logger.debug("Received request to add new StudentVolunteer");
		
		// Assign id
		bonnerScholars.setId(bonnerScholarsId);
		
		// Delegate to service
		bonnerScholarsService.edit(bonnerScholars);

		// Redirect to url
		return "redirect:/bnrSchlr/bnrScholars";
	}
}
