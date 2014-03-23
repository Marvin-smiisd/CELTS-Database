package org.celts.db.dto;

import java.util.List;

import org.celts.db.domain.StudentLines;
import org.celts.db.domain.Semesters;
import org.celts.db.domain.Years;
import org.celts.db.domain.CommunityServiceProgram;

/**
 * Data Transfer Object for displaying purposes
 * 
 * @author
 */
public class StudentVolunteerDTO {

	private  Integer id;
	private  String num;
	private String firstName;
	private String lastName;
	private Integer year;
	private List<StudentLines> studentLines;
	private List<Semesters> semesters;
	private List<Years> years;
	private List<CommunityServiceProgram> communityServiceProgram;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}

	public List<StudentLines> getStudentLines() {
		return studentLines;
	}
	public void setStudentLines(List<StudentLines> studentLines) {
		this.studentLines = studentLines;
	}
	
	
	public List<Semesters> getSemesters() {
		return semesters;
	}
	public void setSemesters(List<Semesters> semesters) {
		this.semesters = semesters;
	}
	
	
	public List<Years> getYears() {
		return years;
	}
	public void setYears(List<Years> years) {
		this.years = years;
	}
	
	
	
	public List<CommunityServiceProgram> getCommunityServiceProgram() {
		return communityServiceProgram;
	}
	public void setCommunityServiceProgram(List<CommunityServiceProgram> communityServiceProgram) {
		this.communityServiceProgram = communityServiceProgram;
	}
}
