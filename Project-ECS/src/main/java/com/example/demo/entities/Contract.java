package com.example.demo.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Contract {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String position;

	
	@CreationTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Asia/Hebron")
	private Date startDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Asia/Hebron")
	private Date endDate;

	@JoinColumn(name = "employee_id")
	@ManyToOne
	@JsonIgnore
	private Resource resource;

	@JoinColumn(name = "customer_id")
	@ManyToOne
	@JsonIgnore
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	@Override
	public String toString() {
		return "Contract [id=" + id + ", position=" + position + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", resource=" + resource + ", user=" + user + "]";
	}

}
