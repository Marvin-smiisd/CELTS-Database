package org.celts.db.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.celts.db.domain.StudentVolunteer;
import org.celts.db.dto.StudentVolunteerDTO;
import org.celts.db.service.CommunityServiceProgramService;
import org.celts.db.service.StudentLinesService;
import org.celts.db.service.YearService;
import org.celts.db.service.SemesterService;
import org.celts.db.service.StudentVolunteerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles and retrieves StudentVolunteer request
 * 
 * @author:
 */
@Controller
// @RequestMapping("/main")
public class MainController {

	protected static Logger logger = Logger.getLogger("controller");

	@Resource(name = "studentVolunteerService")
	private StudentVolunteerService studentVolunteerService;

	@Resource(name = "studentLinesService")
	private StudentLinesService studentLinesService;

	@Resource(name = "semesterService")
	private SemesterService semesterService;

	@Resource(name = "yearService")
	private YearService yearService;

	@Resource(name = "communityServiceProgramService")
	private CommunityServiceProgramService communityServiceProgramService;

	/**
	 * Retrieves the "Records" page
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String get(Model model) {
		logger.debug("Received request to show records page");

		// This will resolve to /WEB-INF/jsp/home.jsp
		return "home";
	}

	/**
	 * Retrieves the "Records" page
	 */
	@RequestMapping(value = "/stu", method = RequestMethod.GET)
	public String getStu(Model model) {
		logger.debug("Received request to show records page");

		// Retrieve all StudentVolunteers
		// List<StudentVolunteer> studentVolunteers =
		// studentVolunteerService.getAll();
		List<StudentVolunteer> studentVolunteers = studentVolunteerService
				.getAllByYear();

		// Prepare model object
		List<StudentVolunteerDTO> studentVolunteersDTO = new ArrayList<StudentVolunteerDTO>();

		for (StudentVolunteer studentVolunteer : studentVolunteers) {
			// Create new data transfer object
			StudentVolunteerDTO dto = new StudentVolunteerDTO();

			dto.setId(studentVolunteer.getId());
			dto.setNum(studentVolunteer.getNum());
			dto.setFirstName(studentVolunteer.getFirstName());
			dto.setLastName(studentVolunteer.getLastName());
			dto.setCommunityServiceProgram(communityServiceProgramService
					.getAll());
			dto.setYears(yearService.getAll());
			// dto.setSemesters(semesters)
			dto.setStudentLines(studentLinesService.getAll(studentVolunteer
					.getId()));

			// Add to model list
			studentVolunteersDTO.add(dto);
		}

		// Add to model
		model.addAttribute("studentVolunteers", studentVolunteersDTO);

		// This will resolve to /WEB-INF/jsp/records.jsp
		return "stuVolunteerRecords";
	}

	/**
	 * Retrieves the "Add New Record" page
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAdd(Model model) {
		// public String getAdd(@RequestParam("id") Integer studentVolunteerId,
		// Model model) {
		logger.debug("Received request to show add page");

		/*
		 * // Retrieve all semesters List<Semesters> semesters =
		 * semesterService.getAll();
		 * 
		 * // Prepare model object List<SemestersDTO> semesterDTO = new
		 * ArrayList<SemestersDTO>();
		 * 
		 * for (Semesters semester: semesters) {
		 * 
		 * SemestersDTO dto = new SemestersDTO(); dto.setId(semester.getId());
		 * dto.setSem(semester.getSem()); //dto.setYr(semester.getYr());
		 * 
		 * semesterDTO.add(dto); }
		 * 
		 * 
		 * // Retrieve all semesters List<Years> years =
		 * yearService.getAll20122013();
		 * 
		 * // Prepare model object List<YearsDTO> yearDTO = new
		 * ArrayList<YearsDTO>();
		 * 
		 * for (Years year: years) {
		 * 
		 * YearsDTO dto = new YearsDTO(); dto.setId(year.getId());
		 * dto.setYr(year.getYr());
		 * 
		 * yearDTO.add(dto); }
		 * 
		 * 
		 * 
		 * // Retrieve all semesters List<CommunityServiceProgram>
		 * communityServiceProgram = communityServiceProgramService.getAll();
		 * 
		 * // Prepare model object List<CommunityServiceProgramDTO>
		 * communityServiceProgramDTO = new
		 * ArrayList<CommunityServiceProgramDTO>();
		 * 
		 * for (CommunityServiceProgram communityServicePrograms:
		 * communityServiceProgram) {
		 * 
		 * CommunityServiceProgramDTO dto = new CommunityServiceProgramDTO();
		 * dto.setId(communityServicePrograms.getId());
		 * dto.setProg(communityServicePrograms.getProg());
		 * //dto.setYr(semester.getYr());
		 * 
		 * communityServiceProgramDTO.add(dto); }
		 */
		// Create new StudentVolunteer and add to model
		model.addAttribute("studentVolunteerAttribute", new StudentVolunteer());

		/*
		 * // Prepare model object StudentLines studentLines = new
		 * StudentLines();
		 * studentLines.setStudentVolunteer(studentVolunteerService
		 * .get(studentVolunteerId));
		 * 
		 * // Add to model model.addAttribute("studentLinesAttribute",
		 * studentLines);
		 * 
		 * 
		 * model.addAttribute("semesters", semesterDTO);
		 * 
		 * model.addAttribute("years", yearDTO);
		 * 
		 * model.addAttribute("communityServiceProgram",
		 * communityServiceProgramDTO);
		 */
		// This will resolve to /WEB-INF/jsp/add-student-volunteer.jsp
		return "add-student-volunteer";

	}

	/**
	 * Adds a new record
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String postAdd(
			@ModelAttribute("studentVolunteerAttribute") StudentVolunteer studentVolunteer) {
		logger.debug("Received request to add new record");

		// Delegate to service
		studentVolunteerService.add(studentVolunteer);

		// Redirect to url
		return "redirect:/stu";
	}

	/**
	 * Deletes a record including all the associated Student Volunteers
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String getDelete(@RequestParam("id") Integer studentVolunteerId) {
		logger.debug("Received request to delete record");

		// Delete all associated Student Volunteers first
		studentLinesService.deleteAll(studentVolunteerId);

		// Delete StudentVolunteer
		studentVolunteerService.delete(studentVolunteerId);

		// Redirect to url
		return "redirect:/stu";
	}

	/**
	 * Retrieves the "Edit Existing Record" page
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String getEdit(@RequestParam("id") Integer studentVolunteerId,
			Model model) {
		logger.debug("Received request to show edit page");

		// Retrieve StudentVolunteer by id
		StudentVolunteer existingStudentVolunteer = studentVolunteerService
				.get(studentVolunteerId);

		// Add to model
		model.addAttribute("studentVolunteerAttribute",
				existingStudentVolunteer);

		// This will resolve to /WEB-INF/jsp/edit-studen-volunteer.jsp
		return "edit-student-volunteer";
	}

	/**
	 * Edits an existing record
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String postEdit(
			@RequestParam("id") Integer studentVolunteerId,
			@ModelAttribute("studentVolunteerAttribute") StudentVolunteer studentVolunteer) {
		logger.debug("Received request to add new StudentVolunteer");

		// Assign id
		studentVolunteer.setId(studentVolunteerId);

		// Delegate to service
		studentVolunteerService.edit(studentVolunteer);

		// Redirect to url
		return "redirect:/stu";
	}

	/**
	 * Retrieves the "Add New Record" page
	 */
	/*
	 * @RequestMapping(value = "/con", method = RequestMethod.GET) public String
	 * getSearch(Model model) {
	 * logger.debug("Received request to show add page");
	 * 
	 * return "searchcelts"; }
	 */

	/**
	 * Retrieves the "Add New Record" page
	 */
	@RequestMapping(value = "/stats", method = RequestMethod.GET)
	public String getStatistics(Model model) {
		logger.debug("Received request to show add page");

		return "statistics";
	}

	/**
	 * Handles and retrieves the common JSP page that everyone can see
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/common", method = RequestMethod.GET)
	public String getCommonPage() {
		logger.debug("Received request to show common page");

		// Do your work here. Whatever you like
		// i.e call a custom service to do your business
		// Prepare a model to be used by the JSP page

		// This will resolve to /WEB-INF/jsp/commonpage.jsp
		return "commonpage";
	}

	/**
	 * Handles and retrieves the admin JSP page that only admins can see
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String getAdminPage() {
		logger.debug("Received request to show admin page");

		// This will resolve to /WEB-INF/jsp/adminpage.jsp
		return "adminpage";
	}

}
