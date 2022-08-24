package com.example.demo.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "employee")
public class Resource {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;
	
	// TODO: Fix "@JsonFormat set date with one day less"

	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date hireDate;

	@ManyToMany(mappedBy = "resources", fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<User> users;

	@OneToMany(mappedBy = "resource", cascade = CascadeType.ALL, fetch= FetchType.EAGER)
	private Set<Contract> contracts = new HashSet<Contract>();
	
	@OneToMany(mappedBy = "resource", cascade = CascadeType.ALL, fetch= FetchType.EAGER)
	@JsonIgnore
	private Set<Feedback> feedbacks = new HashSet<Feedback>();

	public Resource() {
		super();
	}

	public Resource(Long id, String email, Date hireDate) {
		super();
		this.id = id;
		this.email = email;
		this.hireDate = hireDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(Set<Contract> contracts) {
		this.contracts = contracts;
	}

	public void addUser(User user) {
		if (user != null) {
			if (users == null)
				users = new HashSet<User>();
			users.add(user);
		}
	}

	public void addFeedback(Feedback feedback) {
		if (feedback != null) {
			if (feedbacks == null)
				feedbacks = new HashSet<Feedback>();
			feedbacks.add(feedback);
		}
	}
	
	public Set<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(Set<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	@Override
	public String toString() {
		return "Resource [id=" + id + ", email=" + email + ", hireDate=" + hireDate + "]";
	}

}
