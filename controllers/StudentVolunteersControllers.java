package org.celts.db.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import org.celts.db.domain.StudentVolunteer;
import org.celts.db.domain.StudentVolunteerFormValidator;
import org.celts.db.dao.StudentVolunteerDAO;
import org.celts.db.service.StudentLinesService;

/**
 * @author Marvin Yormie
 *
 */

@Controller
@RequestMapping("/")
public class StudentVolunteersControllers
{
	@Autowired
	private StudentVolunteerDAO studentVolunteerDAO;
	
	@Autowired
	private StudentVolunteerFormValidator validator;
	
	@Resource(name="studentLinesService")
	private StudentLinesService studentLinesService;
	
	@RequestMapping("/home")
	public String home()
	{
		return "home";
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
		
	@RequestMapping("/searchStudentVolunteers")
	public ModelAndView searchStudentVolunteers(@RequestParam(required= false, defaultValue="") String firstName)
	{
		ModelAndView mav = new ModelAndView("searchcelts");
		List<StudentVolunteer> studentVolunteer = studentVolunteerDAO.searchStudentVolunteers(firstName.trim());
		mav.addObject("SEARCH_STUDENTVOLUNTER_RESULTS_KEY", studentVolunteer);
		return mav;
	}
	
	@RequestMapping("/viewAllStudentVolunteers")
	public ModelAndView getAllStudentVolunteers()
	{
		ModelAndView mav = new ModelAndView("searchcelts");
		List<StudentVolunteer> studentVolunteer = studentVolunteerDAO.getAllStudentVolunteers();
		mav.addObject("SEARCH_STUDENTVOLUNTER_RESULTS_KEY", studentVolunteer);
		return mav;
	}
	
	@RequestMapping(value="/saveStudentVolunteers", method=RequestMethod.GET)
	public ModelAndView newuserForm()
	{
		ModelAndView mav = new ModelAndView("newStudentVolunteer");
		StudentVolunteer studentVolunteer = new StudentVolunteer();
		mav.getModelMap().put("newStudentVolunteer", studentVolunteer);
		return mav;
	}
	
	@RequestMapping(value="/saveStudentVolunteers", method=RequestMethod.POST)
	public String create(@ModelAttribute("newStudentVolunteer")StudentVolunteer studentVolunteer, BindingResult result, SessionStatus status)
	{
		validator.validate(studentVolunteer, result);
		if (result.hasErrors()) 
		{				
			return "newStudentVolunteer";
		}
		studentVolunteerDAO.save(studentVolunteer);
		status.setComplete();
		return "redirect:viewAllStudentVolunteers.do";
	}
	
	@RequestMapping(value="/updateStudentVolunteers", method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam("id")Integer id)
	{
		ModelAndView mav = new ModelAndView("editStudentVolunteer");
		StudentVolunteer studentVolunteer = studentVolunteerDAO.getById(id);
		mav.addObject("editStudentVolunteer", studentVolunteer);
		return mav;
	}
	
	@RequestMapping(value="/updateStudentVolunteers", method=RequestMethod.POST)
	public String update(@ModelAttribute("editStudentVolunteer") StudentVolunteer studentVolunteer, BindingResult result, SessionStatus status)
	{
		validator.validate(studentVolunteer, result);
		if (result.hasErrors()) {
			return "editStudentVolunteer";
		}
		studentVolunteerDAO.update(studentVolunteer);
		status.setComplete();
		return "redirect:viewAllStudentVolunteers.do";
	}
	
	
	@RequestMapping("deleteStudentVolunteers")
	public ModelAndView delete(@RequestParam("id")Integer id)
	{
		ModelAndView mav = new ModelAndView("redirect:viewAllStudentVolunteers.do");
		
	   	// Delete all associated Student Volunteers first
    	studentLinesService.deleteAll(id);
		
		studentVolunteerDAO.delete(id);
		return mav;
	}
	
}
