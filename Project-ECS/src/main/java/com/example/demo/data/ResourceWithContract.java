package com.example.demo.data;

import java.time.LocalDate;
import java.util.Comparator;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ResourceWithContract {

	private long id;

	private String resourceName;

	private String companyName;

	private String position;

	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate hireDate;

	public ResourceWithContract() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResourceWithContract(long id, String resourceName, String companyName, String position, LocalDate hireDate) {
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

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
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
