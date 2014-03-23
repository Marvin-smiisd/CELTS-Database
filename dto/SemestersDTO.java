package org.celts.db.dto;

public class SemestersDTO {
	
	
	private  Integer id;
	private  String sem;
	private String sementry;
	private String semdeptr;
	private String semptcpd;
	private String sempartnered;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getSem() {
		return sem;
	}
	public void setSem(String sem) {
		this.sem = sem;
	}
	
	
	public String getSementry() {
		return sementry;
	}
	public void setSementry(String sementry) {
		this.sementry = sementry;
	}
	
	
	public String getSemdeptr() {
		return semdeptr;
	}
	public void setSemdeptr(String semdeptr) {
		this.semdeptr = semdeptr;
	}
	
	public String getSempartnered() {
		return sempartnered;
	}
	public void setSempartnered(String sempartnered) {
		this.sempartnered = sempartnered;
	}
	

	public String getSemptcpd() {
		return semptcpd;
	}
	public void setSemptcpd(String semptcpd) {
		this.semptcpd = semptcpd;
	}
	
}
