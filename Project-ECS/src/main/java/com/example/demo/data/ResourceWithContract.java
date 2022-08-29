package com.example.demo.data;

import java.util.Comparator;
import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;

public class ResourceWithContract {

	private long id;

	private String resourceName;

	private String companyName;

	private String position;

	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Asia/Hebron")
	private Date hireDate;

	public ResourceWithContract() {
		super();
	}

	public ResourceWithContract(long id, String resourceName, String companyName, String position, Date hireDate) {
		super();
		this.id = id;
		this.resourceName = resourceName;
		this.companyName = companyName;
		this.position = position;
		this.hireDate = hireDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
	
	public static Comparator<ResourceWithContract> idComparator = new Comparator<ResourceWithContract>() {

		public int compare(ResourceWithContract s1, ResourceWithContract s2) {
		   long id1 = s1.getId();
		   long id2 = s2.getId();

		   //ascending order
		   return Long.compare(id1, id2);
	    }};

	@Override
	public String toString() {
		return "resourceWithContract [resourceName=" + resourceName + ", companyName=" + companyName + ", position="
				+ position + ", hireDate=" + hireDate + "]";
	}

}
