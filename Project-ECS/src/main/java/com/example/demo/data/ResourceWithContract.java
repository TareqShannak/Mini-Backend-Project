package com.example.demo.data;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ResourceWithContract {

	private String resourceName;

	private String companyName;

	private String position;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date hireDate;

	public ResourceWithContract() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResourceWithContract(String resourceName, String companyName, String position, Date hireDate) {
		super();
		this.resourceName = resourceName;
		this.companyName = companyName;
		this.position = position;
		this.hireDate = hireDate;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	@Override
	public String toString() {
		return "resourceWithContract [resourceName=" + resourceName + ", companyName=" + companyName + ", position="
				+ position + ", hireDate=" + hireDate + "]";
	}

}
