package org.celts.db.dto;

import java.util.List;

//import org.celts.db.domain.CommunityParticipants;

import org.celts.db.domain.CommunityServiceProgramsLines;

public class CommunityParticipantsDTO {
	
	private  Integer id;
	private String firstName;
	private String lastName;
	private String semptcpd;
	private String yrptcpd;
	
	private  List<CommunityServiceProgramsLines>  communityServiceProgramsLines;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
	
	public String getSemptcpd() {
		return semptcpd;
	}
	public void setSemptcpd(String semptcpd) {
		this.semptcpd = semptcpd;
	}

	
	public String getYrptcpd() {
		return yrptcpd;
	}
	public void setYrptcpd(String yrptcpd) {
		this.yrptcpd = yrptcpd;
	}
	
	
	public List<CommunityServiceProgramsLines> getCommunityServiceProgramsLines() {
		return communityServiceProgramsLines;
	}
	public void setCommunityServiceProgramsLines(List<CommunityServiceProgramsLines> communityServiceProgramsLines) {
		this.communityServiceProgramsLines = communityServiceProgramsLines;
	}

}
