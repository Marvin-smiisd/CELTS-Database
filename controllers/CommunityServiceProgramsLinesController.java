package org.celts.db.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.celts.db.domain.CommunityServiceProgramsLines;
import org.celts.db.domain.Semesters;
import org.celts.db.domain.Years;
import org.celts.db.domain.CommunityServiceProgram;
import org.celts.db.dto.SemestersDTO;
import org.celts.db.dto.YearsDTO;
import org.celts.db.dto.CommunityServiceProgramDTO;
import org.celts.db.service.CommunityServiceProgramsLinesService;
import org.celts.db.service.CommunityPartnerOrganizationsService;
import org.celts.db.service.CommunityParticipantService;
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
@RequestMapping("/cmmSrvcProg")
public class CommunityServiceProgramsLinesController {

	protected static Logger logger = Logger.getLogger("controller");
	
	@Resource(name="semesterService")
	private SemesterService semesterService;
	
	@Resource(name="yearService")
	private YearService yearService;
	
	@Resource(name="communityServiceProgramService")
	private CommunityServiceProgramService communityServiceProgramService;
	
	@Resource(name="communityPartnerOrganizationsService")
	private CommunityPartnerOrganizationsService communityPartnerOrganizationsService;
	
	@Resource(name="communityServiceProgramsLinesService")
	private CommunityServiceProgramsLinesService communityServiceProgramsLinesService;
	
	@Resource(name="communityParticipantService")
	private CommunityParticipantService communityParticipantService;

	
    /**
     * Retrieves the "Add New Student Lines ()" page
     */
    @RequestMapping(value = "/addd", method = RequestMethod.GET)
    public String getAddd(@RequestParam("id") Integer cmmPartnerOrgId, Model model) {
    	logger.debug("Received request to show add page");
    	
      	// Retrieve all semesters
    	List<Semesters> semesters = semesterService.getAll();
    	
    	// Prepare model object
    	List<SemestersDTO> semesterDTO = new ArrayList<SemestersDTO>();
    	
    	for (Semesters semester: semesters) {
    	
    		SemestersDTO dto = new SemestersDTO();
    		
    		dto.setId(semester.getId());
    		dto.setSempartnered(semester.getSempartnered());

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
    		dto.setYrpartnered(year.getYrpartnered());
    		
    		yearDTO.add(dto);
    	}
    	
    	
    	
      	// Retrieve all semesters
    	List<CommunityServiceProgram> communityServiceProgram = communityServiceProgramService.getAll();
    	
    	// Prepare model object
    	List<CommunityServiceProgramDTO> communityServiceProgramDTO = new ArrayList<CommunityServiceProgramDTO>();
    	
    	for (CommunityServiceProgram communityServicePrograms: communityServiceProgram) {
    	
    		CommunityServiceProgramDTO dto = new CommunityServiceProgramDTO();
    		dto.setId(communityServicePrograms.getId());
    		dto.setCmmsrvprog(communityServicePrograms.getCmmsrvprog());

    		communityServiceProgramDTO.add(dto);
    	}
    	
    	
    	// Prepare model object
    	CommunityServiceProgramsLines communityServiceProgramsLines = new CommunityServiceProgramsLines();

    	communityServiceProgramsLines.setCommunityPartnerOrganizations(communityPartnerOrganizationsService.get(cmmPartnerOrgId));

    	// Add to model
    	model.addAttribute("communityServiceProgramsLinesAttribute", communityServiceProgramsLines);
    	
    	model.addAttribute("semesters", semesterDTO);
    	
    	model.addAttribute("years", yearDTO);
    	
    	model.addAttribute("communityServiceProgram", communityServiceProgramDTO);

    	// This will resolve to /WEB-INF/jsp/add-sem-and-prog.jsp
    	return "add-semPartnered-and-progPartneredWith";
	}
 
    /**
     * Adds a new Student Lines
     */
    @RequestMapping(value = "/addd", method = RequestMethod.POST)
    public String postAdds(@RequestParam("id") Integer cmmPartnerOrgId, 
    						    @ModelAttribute("communityServiceProgramsLinesAttribute") CommunityServiceProgramsLines communityServiceProgramsLines) {
		logger.debug("Received request to add new student line");
		
		// Delegate to service
		communityServiceProgramsLinesService.add(cmmPartnerOrgId, communityServiceProgramsLines);

		// Redirect to url
		return "redirect:/cmmPtnrOrg/Org";
	}
    
    
    
    
    
    
    /**
     * Retrieves the "Add New Student Lines ()" page
     */
    @RequestMapping(value = "/addSempProgp", method = RequestMethod.GET)
    public String getaddSempProgp(@RequestParam("id") Integer cmmPtpcId, Model model) {
    	logger.debug("Received request to show add page");
    	
      	// Retrieve all semesters
    	List<Semesters> semesters = semesterService.getAll();
    	
    	// Prepare model object
    	List<SemestersDTO> semesterDTO = new ArrayList<SemestersDTO>();
    	
    	for (Semesters semester: semesters) {
    	
    		SemestersDTO dto = new SemestersDTO();
    		
    		dto.setId(semester.getId());
    		dto.setSemptcpd(semester.getSemptcpt());

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
    		dto.setYrptcpd(year.getYrptcpd());
    		
    		yearDTO.add(dto);
    	}
    	
    	
    	
      	// Retrieve all semesters
    	List<CommunityServiceProgram> communityServiceProgram = communityServiceProgramService.getAll();
    	
    	// Prepare model object
    	List<CommunityServiceProgramDTO> communityServiceProgramDTO = new ArrayList<CommunityServiceProgramDTO>();
    	
    	for (CommunityServiceProgram communityServicePrograms: communityServiceProgram) {
    	
    		CommunityServiceProgramDTO dto = new CommunityServiceProgramDTO();
    		dto.setId(communityServicePrograms.getId());
    		dto.setCmmsrvprog(communityServicePrograms.getCmmsrvprog());

    		communityServiceProgramDTO.add(dto);
    	}
    	
    	
    	// Prepare model object
    	CommunityServiceProgramsLines communityServiceProgramsLines = new CommunityServiceProgramsLines();

    	communityServiceProgramsLines.setCommunityParticipants(communityParticipantService.get(cmmPtpcId));

    	// Add to model
    	model.addAttribute("communityServiceProgramsLinesAttribute", communityServiceProgramsLines);
    	
    	model.addAttribute("semesters", semesterDTO);
    	
    	model.addAttribute("years", yearDTO);
    	
    	model.addAttribute("communityServiceProgram", communityServiceProgramDTO);

    	// This will resolve to /WEB-INF/jsp/add-sem-and-prog.jsp
    	return "add-semParticipated-and-progParticipated";
	}
    
    
    
    /**
     * Adds a new Student Lines
     */
    @RequestMapping(value = "/addSempProgp", method = RequestMethod.POST)
    public String postaddSempProgp(@RequestParam("id") Integer cmmPtcpId, 
    						    @ModelAttribute("communityServiceProgramsLinesAttribute") CommunityServiceProgramsLines communityServiceProgramsLines) {
		logger.debug("Received request to add new student line");
		
		// Delegate to service
		communityServiceProgramsLinesService.addPtcp(cmmPtcpId, communityServiceProgramsLines);

		// Redirect to url
		return "redirect:/commServPtcp/Participants";
	}
    
    
    
    
    /**
     * Deletes a student line
     */
    @RequestMapping(value = "/deletee", method = RequestMethod.GET)
    public String getDelete(@RequestParam("id") Integer cmmServcProgLinesId) {
    	logger.debug("Received request to delete student line");
    
    	// Delegate to service
    	communityServiceProgramsLinesService.delete(cmmServcProgLinesId);

		// Redirect to url
		return "redirect:/cmmPtnrOrg/Org";
	}
   
    
    
    /**
     * Deletes a student line
     */
    @RequestMapping(value = "/deleteSempProgp", method = RequestMethod.GET)
    public String getDeleteSempProgp(@RequestParam("id") Integer cmmServcProgLinesId) {
    	logger.debug("Received request to delete student line");
    
    	// Delegate to service
    	communityServiceProgramsLinesService.delete(cmmServcProgLinesId);

		// Redirect to url
		return "redirect:/commServPtcp/Participants";
	}
   
    
    
    
    /**
     * Retrieves the "Edit Existing student line" page
     */		
    @RequestMapping(value = "/editCmmSrvcProg", method = RequestMethod.GET)
    public String getEditt(@RequestParam("id") Integer cmmServcLinesId, Model model) {
    	logger.debug("Received request to show edit page");
    	
    	
     	// Retrieve all semesters
    	List<Semesters> semesters = semesterService.getAll();
    	
    	// Prepare model object
    	List<SemestersDTO> semesterDTO = new ArrayList<SemestersDTO>();
    	
    	for (Semesters semester: semesters) {
    	
    		SemestersDTO dto = new SemestersDTO();
    		dto.setId(semester.getId());
    		dto.setSempartnered(semester.getSempartnered());
    		
    		semesterDTO.add(dto);
    	}
    	
    	
      	// Retrieve all semesters
    	List<Years> years = yearService.getAll();
    	
    	// Prepare model object
    	List<YearsDTO> yearDTO = new ArrayList<YearsDTO>();
    	
    	for (Years year: years) {
    	
    		YearsDTO dto = new YearsDTO();
    		dto.setId(year.getId());
    		dto.setYrpartnered(year.getYrpartnered());
    		
    		yearDTO.add(dto);
    	}
    	
    	
    	
      	// Retrieve all semesters
    	List<CommunityServiceProgram> communityServiceProgram = communityServiceProgramService.getAll();
    	
    	// Prepare model object
    	List<CommunityServiceProgramDTO> communityServiceProgramDTO = new ArrayList<CommunityServiceProgramDTO>();
    	
    	for (CommunityServiceProgram communityServicePrograms: communityServiceProgram) {
    	
    		CommunityServiceProgramDTO dto = new CommunityServiceProgramDTO();
    		dto.setId(communityServicePrograms.getId());
    		dto.setCmmsrvprog(communityServicePrograms.getCmmsrvprog());
    		
    		communityServiceProgramDTO.add(dto);
    	}
    	
    	
    	// Retrieve student line by id
    	CommunityServiceProgramsLines existingcommunityServiceProgramsLines = communityServiceProgramsLinesService.get(cmmServcLinesId);
    	
    	model.addAttribute("semesters", semesterDTO);
    	
    	model.addAttribute("years", yearDTO);
    	
    	model.addAttribute("communityServiceProgram", communityServiceProgramDTO);
    	

    	// Add to model
    	model.addAttribute("communityServiceProgramsLinesAttribute", existingcommunityServiceProgramsLines);

    	// This will resolve to /WEB-INF/jsp/edit-sem-and-prog.jsp
    	return "edit-semPartnered-and-progPartneredWith";
	}
  
    /**
     * Edits an existing student line
     */
    @RequestMapping(value = "/editCmmSrvcProg", method = RequestMethod.POST)
    public String postEditt(@RequestParam("id") Integer cmmServcProgLinesId, 
    						    @ModelAttribute("communityServiceProgramsLinesAttribute") CommunityServiceProgramsLines communityServiceProgramsLines) {
		logger.debug("Received request to add new student line");
		
		// Assign id
		communityServiceProgramsLines.setId(cmmServcProgLinesId);
		
		// Delegate to service
		communityServiceProgramsLinesService.editCmm(communityServiceProgramsLines);

		// Redirect to url
		return "redirect:/cmmPtnrOrg/Org";
	} 
    
    
    
    
    
    /**
     * Retrieves the "Edit Existing student line" page
     */
    @RequestMapping(value = "/editSempProgp", method = RequestMethod.GET)
    public String getEditSempProgp(@RequestParam("id") Integer cmmServcLinesId, Model model) {
    	logger.debug("Received request to show edit page");
    	
    	
     	// Retrieve all semesters
    	List<Semesters> semesters = semesterService.getAll();
    	
    	// Prepare model object
    	List<SemestersDTO> semesterDTO = new ArrayList<SemestersDTO>();
    	
    	for (Semesters semester: semesters) {
    	
    		SemestersDTO dto = new SemestersDTO();
    		dto.setId(semester.getId());
    		dto.setSemptcpd(semester.getSemptcpt());
    		
    		semesterDTO.add(dto);
    	}
    	
    	
    	
       	// Retrieve all semesters
    	List<Years> years = yearService.getAll();
    	
    	// Prepare model object
    	List<YearsDTO> yearDTO = new ArrayList<YearsDTO>();
    	
    	for (Years year: years) {
    	
    		YearsDTO dto = new YearsDTO();
    		
    		dto.setId(year.getId());
    		dto.setYrptcpd(year.getYrptcpd());
    		
    		yearDTO.add(dto);
    	}
    	
    	
    	
    	
      	// Retrieve all semesters
    	List<CommunityServiceProgram> communityServiceProgram = communityServiceProgramService.getAll();
    	
    	// Prepare model object
    	List<CommunityServiceProgramDTO> communityServiceProgramDTO = new ArrayList<CommunityServiceProgramDTO>();
    	
    	for (CommunityServiceProgram communityServicePrograms: communityServiceProgram) {
    	
    		CommunityServiceProgramDTO dto = new CommunityServiceProgramDTO();
    		dto.setId(communityServicePrograms.getId());
    		dto.setCmmsrvprog(communityServicePrograms.getCmmsrvprog());
    		
    		communityServiceProgramDTO.add(dto);
    	}
    	
    	
    	// Retrieve student line by id
    	CommunityServiceProgramsLines existingcommunityServiceProgramsLines = communityServiceProgramsLinesService.get(cmmServcLinesId);
    	
    	model.addAttribute("semesters", semesterDTO);
    	
    	model.addAttribute("years", yearDTO);
    	
    	model.addAttribute("communityServiceProgram", communityServiceProgramDTO);
    	

    	// Add to model
    	model.addAttribute("communityServiceProgramsLinesAttribute", existingcommunityServiceProgramsLines);

    	// This will resolve to /WEB-INF/jsp/edit-sem-and-prog.jsp
    	return "edit-semParticipated-and-progParticipated";
	}
  
    /**
     * Edits an existing student line
     */
    @RequestMapping(value = "/editSempProgp", method = RequestMethod.POST)
    public String postEditSempProgp(@RequestParam("id") Integer cmmServcProgLinesId, 
    						    @ModelAttribute("communityServiceProgramsLinesAttribute") CommunityServiceProgramsLines communityServiceProgramsLines) {
		logger.debug("Received request to add new student line");
		
		// Assign id
		communityServiceProgramsLines.setId(cmmServcProgLinesId);
		
		// Delegate to service
		communityServiceProgramsLinesService.editCmmPctp(communityServiceProgramsLines);

		// Redirect to url
		return "redirect:/commServPtcp/Participants";
	} 
}
