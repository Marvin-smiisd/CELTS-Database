package org.celts.db.dto;

import org.celts.db.domain.CommunityServiceProgramsLines;

import java.util.List;


public class CommunityPartnerOrganizationsDTO {
	
	private  Integer id;
	private String org;
	private String contact;
	private String sempartnered;
	private String yrpartnered;
	
	private List<CommunityServiceProgramsLines> communityServiceProgramsLines;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	
	
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}

		
	public String getSempartnered() {
		return sempartnered;
	}
	public void setSempartnered(String sempartnered) {
		this.sempartnered = sempartnered;
	}
	
	
	public String getYrpartnered() {
		return yrpartnered;
	}
	public void setYrpartnered(String yrpartnered) {
		this.yrpartnered = yrpartnered;
	}
	
	
	public List<CommunityServiceProgramsLines> getCommunityServiceProgramsLines() {
		return communityServiceProgramsLines;
	}
	public void setCommunityServiceProgramsLines(List<CommunityServiceProgramsLines> communityServiceProgramsLines) {
		this.communityServiceProgramsLines = communityServiceProgramsLines;
	}
}
