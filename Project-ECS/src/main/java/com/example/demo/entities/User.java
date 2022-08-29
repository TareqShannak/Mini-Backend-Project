package com.example.demo.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "customer")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	@Column(name = "pass")
	private String password;

	@Column(name = "company_name", length = 20, nullable = false)
	private String companyName;

	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate contractDate;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "customers_employees", joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"))
	@JsonIgnore
	private Set<Resource> resources;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Contract> contracts;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Feedback> feedbacks = new HashSet<Feedback>();

	@JoinColumn(name = "manager_id")
	@ManyToOne
	@JsonIgnore
	private Manager manager;

	public User() {
		super();
	}

	public User(Long id, String email, String password, String companyName, LocalDate contractDate) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.companyName = companyName;
		this.contractDate = contractDate;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public LocalDate getContractDate() {
		return contractDate;
	}

	public void setContractDate(LocalDate contractDate) {
		this.contractDate = contractDate;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	public Set<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(Set<Contract> contracts) {
		this.contracts = contracts;
	}

	public void addResource(Resource resource) {
		if (resource != null) {
			if (resources == null)
				resources = new HashSet<Resource>();
			resource.addUser(this);
			resources.add(resource);
		}
	}

	public Set<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(Set<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public void addFeedback(Feedback feedback) {
		if (feedback != null) {
			if (feedbacks == null)
				feedbacks = new HashSet<Feedback>();
			feedbacks.add(feedback);
		}
	}
	

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", companyName=" + companyName
				+ ", contractDate=" + contractDate + ", resources=" + resources + "]";
	}

}
