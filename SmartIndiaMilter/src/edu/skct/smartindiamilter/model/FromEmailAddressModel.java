package edu.skct.smartindiamilter.model;

public class FromEmailAddressModel {
	private String fromName;
	private String fromEmail;
	private String fromDomain;
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getFromEmail() {
		return fromEmail;
	}
	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}
	public String getFromDomain() {
		return fromDomain;
	}
	public void setFromDomain(String fromDomain) {
		this.fromDomain = fromDomain;
	}
	
	@Override
	public String toString() {
		return "FromEmailAddressModel [fromName=" + fromName + ", fromEmail=" + fromEmail + ", fromDomain=" + fromDomain
				+ "]";
	}
	
	

}
