package org.celts.db.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.celts.db.dao.StudentLinesDAO;
import org.celts.db.domain.StudentLines;
import org.celts.db.domain.Semesters;
import org.celts.db.domain.StudentVolunteerFormValidator;
import org.celts.db.domain.Years;
import org.celts.db.domain.CommunityServiceProgram;
import org.celts.db.dto.SemestersDTO;
import org.celts.db.dto.YearsDTO;
import org.celts.db.dto.CommunityServiceProgramDTO;
import org.celts.db.service.StudentLinesService;
import org.celts.db.service.StudentVolunteerService;
import org.celts.db.service.SemesterService;
import org.celts.db.service.YearService;
import org.celts.db.service.CommunityServiceProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;


/**
 * Handles and retrieves StudentVolunteer request
 * 
 * @author Marvin Yormie
 */
@Controller
@RequestMapping("/")
public class StudentLinesController {

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
	
    /**
     * Retrieves the "Add New Student Lines ()" page
     */
    @RequestMapping(value = "/addd", method = RequestMethod.GET)
    public String getAddd(@RequestParam("id") Integer studentVolunteerId, Model model) {
    	logger.debug("Received request to show add page");
    	
      	// Retrieve all semesters
    	List<Semesters> semesters = semesterService.getAll();
    	
    	// Prepare model object
    	List<SemestersDTO> semesterDTO = new ArrayList<SemestersDTO>();
    	
    	for (Semesters semester: semesters) {
    	
    		SemestersDTO dto = new SemestersDTO();
    		dto.setId(semester.getId());
    		dto.setSem(semester.getSem());
    		
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
    		
    		communityServiceProgramDTO.add(dto);
    	}
    	
    	
    	// Prepare model object
    	StudentLines studentLines = new StudentLines();
    	studentLines.setStudentVolunteer(studentVolunteerService.get(studentVolunteerId));

    	// Add to model
    	model.addAttribute("studentLinesAttribute", studentLines);
    	
    	model.addAttribute("semesters", semesterDTO);
    	
    	model.addAttribute("years", yearDTO);
    	
    	model.addAttribute("communityServiceProgram", communityServiceProgramDTO);

    	// This will resolve to /WEB-INF/jsp/add-sem-and-prog.jsp
    	return "add-sem-and-prog";
	}
 
    /**
     * Adds a new Student Lines
     */
    @RequestMapping(value = "/addd", method = RequestMethod.POST)
    public String postAdds(@RequestParam("id") Integer studentVolunteerId, 
    						    @ModelAttribute("studentLinesAttribute") StudentLines studentLines) {
		logger.debug("Received request to add new student line");
		
		// Delegate to service
		studentLinesService.add(studentVolunteerId, studentLines);

		// Redirect to url
		return "redirect:/stu";
	}
    
    
    /**
     * Deletes a student line
     */
    @RequestMapping(value = "/deletee", method = RequestMethod.GET)
    public String getDelete(@RequestParam("id") Integer studentLinesId) {
    	logger.debug("Received request to delete student line");
    
    	// Delegate to service
		studentLinesService.delete(studentLinesId);

		// Redirect to url
		return "redirect:/stu";
	}
   
    /**
     * Retrieves the "Edit Existing student line" page
     */
    @RequestMapping(value = "/editt", method = RequestMethod.GET)
    public String getEditt(@RequestParam("id") Integer studentLinesId, Model model) {
    	logger.debug("Received request to show edit page");
    	

     	// Retrieve all semesters
    	List<Semesters> semesters = semesterService.getAll();
    	
    	// Prepare model object
    	List<SemestersDTO> semesterDTO = new ArrayList<SemestersDTO>();
    	
    	for (Semesters semester: semesters) {
    	
    		SemestersDTO dto = new SemestersDTO();
    		dto.setId(semester.getId());
    		dto.setSem(semester.getSem());
    		
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
    	
    		communityServiceProgramDTO.add(dto);
    	}
    	

    	// Retrieve student line by id
    	StudentLines existingStudentLines = studentLinesService.get(studentLinesId);

    	model.addAttribute("semesters", semesterDTO);
    	
    	model.addAttribute("years", yearDTO);
    	
    	model.addAttribute("communityServiceProgram", communityServiceProgramDTO);


    	// Add to model
    	model.addAttribute("studentLinesAttribute", existingStudentLines);

    	// This will resolve to /WEB-INF/jsp/edit-sem-and-prog.jsp
    	return "edit-sem-and-prog";
	}
 
    /**
     * Edits an existing student line
     */
    @RequestMapping(value = "/editt", method = RequestMethod.POST)
    public String postEditt(@RequestParam("id") Integer studentLinesId, 
    						    @ModelAttribute("studentLinesAttribute") StudentLines studentLines) {
		logger.debug("Received request to add new student line");
		
		// Assign id
		studentLines.setId(studentLinesId);
		
		// Delegate to service
		studentLinesService.edit(studentLines);

		// Redirect to url
		return "redirect:/stu";
	}
    
    
    
    
    
    
    
    
    
    
    
    @Autowired
	private StudentLinesDAO studentLinesDAO;
	
	@Autowired
	private StudentVolunteerFormValidator validator;
	
	/*
	@RequestMapping("/home")
	public String home()
	{
		return "home";
	}
	*/
	
	@InitBinder
	public void initBinder(WebDataBinder binder) 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
		
	@RequestMapping("/searchProg")
	public ModelAndView searchStudentLines(@RequestParam(required= false, defaultValue="") String prog)
	{
		ModelAndView mav = new ModelAndView("searchcelts");
		List<StudentLines> studentLines = studentLinesDAO.searchSemAndProg(prog.trim());
		mav.addObject("SEARCH_STUDENTLINES_RESULTS_KEY", studentLines);
		return mav;
	}
	
	@RequestMapping("/viewAllProg")
	public ModelAndView getAllSemAndProg()
	{
		ModelAndView mav = new ModelAndView("searchcelts");
		List<StudentLines> studentLines = studentLinesDAO.getAllSemAndProg();
		mav.addObject("SEARCH_STUDENTLINES_RESULTS_KEY", studentLines);
		return mav;
	}
	
	@RequestMapping(value="/saveProg", method=RequestMethod.GET)
	public ModelAndView newuserForm()
	{
		ModelAndView mav = new ModelAndView("newProg");
		StudentLines studentLines = new StudentLines();
		mav.getModelMap().put("newProg", studentLines);
		return mav;
	}
	
	@RequestMapping(value="/saveProg", method=RequestMethod.POST)
	public String create(@ModelAttribute("newProg")StudentLines studentLines, BindingResult result, SessionStatus status)
	{
		validator.validate(studentLines, result);
		if (result.hasErrors()) 
		{				
			return "newProg";
		}
		studentLinesDAO.save(studentLines);
		status.setComplete();
		return "redirect:viewAllProg.do";
	}
	
	@RequestMapping(value="/updateProg", method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id")Integer id)
	{
		ModelAndView mav = new ModelAndView("editProg");
		StudentLines studentLines = studentLinesDAO.getById(id);
		mav.addObject("editProg", studentLines);
		return mav;
	}
	
	@RequestMapping(value="/updateProg", method=RequestMethod.POST)
	public String update(@ModelAttribute("editStudentVolunteer") StudentLines studentLines, BindingResult result, SessionStatus status)
	{
		validator.validate(studentLines, result);
		if (result.hasErrors()) {
			return "editProg";
		}
		studentLinesDAO.update(studentLines);
		status.setComplete();
		return "redirect:viewAllProg.do";
	}
	
	
	@RequestMapping("deleteProg")
	public ModelAndView delete(@RequestParam("id")Integer id)
	{
		ModelAndView mav = new ModelAndView("redirect:viewAllProg.do");
		
	   	// Delete all associated Student Volunteers first
    	studentLinesService.deleteAll(id);
		
		studentLinesDAO.delete(id);
		return mav;
	}
    
    
}
