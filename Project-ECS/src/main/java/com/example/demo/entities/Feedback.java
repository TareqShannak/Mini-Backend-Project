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
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String text;

	@CreationTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "Asia/Hebron")
	private Date publishedDate;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	@JsonIgnore
	private Resource resource;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	@JsonIgnore
	private User user;

	public Feedback() {
		super();
	}

	public Feedback(Long id, String text, Date publishedDate, Resource resource) {
		super();
		this.id = id;
		this.text = text;
		this.publishedDate = publishedDate;
		this.resource = resource;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Feedback [id=" + id + ", text=" + text + ", publishedDate=" + publishedDate + ", resource=" + resource
				+ "]";
	}

}
