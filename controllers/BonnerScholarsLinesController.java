package org.celts.db.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.celts.db.domain.StudentLines;
import org.celts.db.domain.Semesters;
import org.celts.db.domain.Years;
import org.celts.db.domain.CommunityServiceProgram;
import org.celts.db.dto.SemestersDTO;
import org.celts.db.dto.YearsDTO;
import org.celts.db.dto.CommunityServiceProgramDTO;
import org.celts.db.service.StudentLinesService;
import org.celts.db.service.StudentVolunteerService;
import org.celts.db.service.BonnerScholarsService;

import org.celts.db.service.SemesterService;
import org.celts.db.service.YearService;
import org.celts.db.service.CommunityServiceProgramService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles and retrieves StudentVolunteer request
 * 
 * @author Marvin Yormie
 */
@Controller
@RequestMapping("/bnrSchlrLn")
public class BonnerScholarsLinesController {

	protected static Logger logger = Logger.getLogger("controller");
	
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

	@Resource(name="bonnerScholarsService")
	private BonnerScholarsService bonnerScholarsService;
	
    /**
     * Retrieves the "Add New Student Lines ()" page
     */
    @RequestMapping(value = "/addBnr", method = RequestMethod.GET)
    public String getAdddBnr(@RequestParam("id") Integer bonnerScholarsId, Model model) {
    	logger.debug("Received request to show add page");
    	
      	// Retrieve all semesters
    	List<Semesters> semesters = semesterService.getAll();
    	
    	// Prepare model object
    	List<SemestersDTO> semesterDTO = new ArrayList<SemestersDTO>();
    	
    	for (Semesters semester: semesters) {
    	
    		SemestersDTO dto = new SemestersDTO();
    		dto.setId(semester.getId());
    		dto.setSementry(semester.getSementry());
    		dto.setSemdeptr(semester.getSemdeptr());	
    		
    		semesterDTO.add(dto);
    	}
    	
    	
      	// Retrieve all semesters
    	//List<Years> years = yearService.getAll20122013();
    	List<Years> years = yearService.getAll();
    	
    	// Prepare model object
    	List<YearsDTO> yearDTO = new ArrayList<YearsDTO>();
    	
    	for (Years year: years) {
    	
    		YearsDTO dto = new YearsDTO();
    		dto.setId(year.getId());
    		dto.setYr(year.getYr());
    		dto.setYrofdeptr(year.getYrofdeptr());
    		
    		yearDTO.add(dto);
    	}
    	
    	
    	
      	// Retrieve all semesters
    	List<CommunityServiceProgram> communityServiceProgram = communityServiceProgramService.getAll();
    	
    	// Prepare model object
    	List<CommunityServiceProgramDTO> communityServiceProgramDTO = new ArrayList<CommunityServiceProgramDTO>();
    	
    	for (CommunityServiceProgram communityServicePrograms: communityServiceProgram) {
    	
    		CommunityServiceProgramDTO dto = new CommunityServiceProgramDTO();
    		dto.setId(communityServicePrograms.getId());
    		dto.setProg(communityServicePrograms.getProg());
    		//dto.setYr(semester.getYr());
    		
    		communityServiceProgramDTO.add(dto);
    	}
    	
    	
    	// Prepare model object
    	//BonnerScholarsLines bonnerScholarsLines = new BonnerScholarsLines();
    	//bonnerScholarsLines.setBonnerScholars(bonnerScholarsService.get(bonnerScholarsId));
    	
    	// Prepare model object
    	StudentLines studentLines = new StudentLines();
    	studentLines.setBonnerScholars(bonnerScholarsService.get(bonnerScholarsId));
    	//setStudentVolunteer(studentVolunteerService.get(studentVolunteerId));

    	// Add to model
    	model.addAttribute("studentLinesAttribute", studentLines);
    	
    	model.addAttribute("semesters", semesterDTO);
    	
    	model.addAttribute("years", yearDTO);
    	
    	model.addAttribute("communityServiceProgram", communityServiceProgramDTO);

    	// This will resolve to /WEB-INF/jsp/add-sem-and-prog.jsp
    	return "add-semEntry-semdDepature-and-prog";
	}
 
   
    
    /**
     * Adds a new Student Lines
     */
    @RequestMapping(value = "/addBnr", method = RequestMethod.POST)
    public String postAddBnr(@RequestParam("id") Integer bonnerScholarsId, 
    						    @ModelAttribute("studentLinesAttribute") StudentLines studentLines) {
		logger.debug("Received request to add new student line");
		
		// Delegate to service
		studentLinesService.addBnr(bonnerScholarsId, studentLines);

		// Redirect to url
		return "redirect:/bnrSchlr/bnrScholars";
	}
    
    
    
    
    /**
     * Deletes a student line
     */
    @RequestMapping(value = "/deleteBnr", method = RequestMethod.GET)
    public String getDeleteBnr(@RequestParam("id") Integer studentLinesId) {
    	logger.debug("Received request to delete student line");
    
    	// Delegate to service
    	studentLinesService.delete(studentLinesId);
    

		// Redirect to url
		return "redirect:/bnrSchlr/bnrScholars";
	}
   
    /**
     * Retrieves the "Edit Existing student line" page
     */
    @RequestMapping(value = "/editBnr", method = RequestMethod.GET)
    public String getEditBnr(@RequestParam("id") Integer studentLinesId, Model model) {
    	logger.debug("Received request to show edit page");
    	
    	
    	
     	// Retrieve all semesters
    	List<Semesters> semesters = semesterService.getAll();
    	
    	// Prepare model object
    	List<SemestersDTO> semesterDTO = new ArrayList<SemestersDTO>();
    	
    	for (Semesters semester: semesters) {
        	
    		SemestersDTO dto = new SemestersDTO();
    		dto.setId(semester.getId());
    		dto.setSementry(semester.getSementry());
    		dto.setSemdeptr(semester.getSementry());	
    		
    		semesterDTO.add(dto);
    	}
    	
    	
      	// Retrieve all semesters
    	List<Years> years = yearService.getAll();
    	
    	// Prepare model object
    	List<YearsDTO> yearDTO = new ArrayList<YearsDTO>();
    	
    	for (Years year: years) {
    	
    		YearsDTO dto = new YearsDTO();
    		dto.setId(year.getId());
    		dto.setYr(year.getYr());
    		dto.setYrofdeptr(year.getYrofdeptr());
    		
    		yearDTO.add(dto);
    	}
    	
      	// Retrieve all semesters
    	List<CommunityServiceProgram> communityServiceProgram = communityServiceProgramService.getAll();
    	
    	// Prepare model object
    	List<CommunityServiceProgramDTO> communityServiceProgramDTO = new ArrayList<CommunityServiceProgramDTO>();
    	
    	for (CommunityServiceProgram communityServicePrograms: communityServiceProgram) {
    	
    		CommunityServiceProgramDTO dto = new CommunityServiceProgramDTO();
    		dto.setId(communityServicePrograms.getId());
    		dto.setProg(communityServicePrograms.getProg());
    		//dto.setYr(semester.getYr());
    		
    		communityServiceProgramDTO.add(dto);
    	}
    	
    	
    	// Retrieve student line by id
    	StudentLines existingBonnerScholars = studentLinesService.get(studentLinesId);
       	
    	model.addAttribute("semesters", semesterDTO);
    	
    	model.addAttribute("years", yearDTO);
    	
    	model.addAttribute("communityServiceProgram", communityServiceProgramDTO);

    	
      	// Add to model
    	model.addAttribute("studentLinesAttribute", existingBonnerScholars);
    	
    	
    	// This will resolve to /WEB-INF/jsp/edit-sem-and-prog.jsp
    	return "edit-semEntry-semdDepature-and-prog";
	}
 
    /**
     * Edits an existing student line
     */
    @RequestMapping(value = "/editBnr", method = RequestMethod.POST)
    public String postEditt(@RequestParam("id") Integer studentLinesId, 
    						    @ModelAttribute("studentLinesAttribute") StudentLines studentLines) {
		logger.debug("Received request to add new student line");
		
		// Assign id
		studentLines.setId(studentLinesId);
		
		// Delegate to service
		studentLinesService.edit(studentLines);

		// Redirect to url
		return "redirect:/bnrSchlr/bnrScholars";
	} 
}
