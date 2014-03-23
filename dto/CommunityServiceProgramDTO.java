package org.celts.db.dto;

import java.util.List;

import org.celts.db.domain.CommunityServiceProgramsLines;

public class CommunityServiceProgramDTO {

	private  Integer id;
	private String org;
	private String prog;
	private String contact;
	private String sempartnered;
	private String yrpartnered;
	private String cmmsrvprog;
	
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

	
	public String getProg() {
		return prog;
	}
	public void setProg(String prog) {
		this.prog = prog;
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


	public String getCmmsrvprog() {
		return cmmsrvprog;
	}
	public void setCmmsrvprog(String cmmsrvprog) {
		this.cmmsrvprog = cmmsrvprog;
	}
	
	public List<CommunityServiceProgramsLines> getCommunityServiceProgramsLines() {
		return communityServiceProgramsLines;
	}
	public void setCommunityServiceProgramsLines(List<CommunityServiceProgramsLines> communityServiceProgramsLines) {
		this.communityServiceProgramsLines = communityServiceProgramsLines;
	}
}
