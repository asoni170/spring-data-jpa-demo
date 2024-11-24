package com.amit.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Employee")
public class EmployeeEntity {

	@Id
	@Column(name = "emp_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employeeId;

	@Column(name = "emp_name")
	private String employeeName;

	@Column(name = "department")
	private String department;

	@Column(name = "emp_email")
	private String employeeEmail;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "address_id")
	private AddressEntity employeeAddress;

	@OneToMany(mappedBy = "employeeEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AccountEntity> employeeAccountList;
	
	@ManyToMany
	@JoinTable(name = "emp_project", joinColumns = @JoinColumn(name = "emp_id"), 
	                          inverseJoinColumns = @JoinColumn(name = "project_id"))
	private List<ProjectEntity> projectEntityList;
	
	public Integer getEmployeeId() {
		return employeeId;
	}

	public EmployeeEntity setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
		return this;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public EmployeeEntity setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
		return this;
	}

	public String getDepartment() {
		return department;
	}

	public EmployeeEntity setDepartment(String department) {
		this.department = department;
		return this;
	}

	public AddressEntity getEmployeeAddress() {
		return employeeAddress;
	}

	public EmployeeEntity setEmployeeAddress(AddressEntity employeeAddress) {
		this.employeeAddress = employeeAddress;
		return this;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public List<AccountEntity> getEmployeeAccountList() {
		return employeeAccountList;
	}

	public EmployeeEntity setEmployeeAccountList(List<AccountEntity> employeeAccountList) {
		this.employeeAccountList = employeeAccountList;
		return this;
	}
	
	public List<ProjectEntity> getProjectEntityList() {
		return projectEntityList;
	}

	public void setProjectEntityList(List<ProjectEntity> projectEntityList) {
		this.projectEntityList = projectEntityList;
	}

	public EmployeeEntity populateEmployeeInAccountList() {
		this.employeeAccountList.stream().forEach(account -> account.setEmployeeEntity(this));
		return this;
	}

}
