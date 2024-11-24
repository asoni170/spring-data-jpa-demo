package com.amit.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

@Schema(name = "Employee", description = "Schema to hold employee details")
public class EmployeeDto {

	@Schema(name = "EmployeeId", description = "Employee unique id", example = "123456")
	@Null(message = "EmployeeId will be system generated, no need to provide manually")
	private Integer employeeId;
	
	@Schema(name = "EmployeeName", example = "Demo Name", 
			description = "Schema to hold full name(firt and last name) of employee")
	@NotNull(message = "Employee Name cannot be null")
	@Size(min = 2, max = 30, message = "Employee name length should be between 2 to 3 character")
	private String employeeName;
	
	@Schema(name = "EmployeeDepartment", example = "Associate", 
			description = "Schema to hold department of employee", allowableValues = {"HR", "Manager", "Assocate"})
	@NotNull(message = "Employee department need to be provided")
	private String department;
	
	@Schema(name = "EmployeeEmail", description = "Schema to hold employee email", example = "abc@me.com")
	@Email(message = "Please provide valid email for employee")
	@NotNull(message = "Email is required to add new employee record")
	private String employeeEmail;
	
	@Schema(name = "EmployeeAddress", description = "Schema to hold employee address.")
	@NotNull(message = "Employee address is required to add new record")
	private AddressDto employeeAddress;
	
	private List<AccountDto> employeeAccountList;
	
	private List<ProjectDto> employeeProjectList;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public EmployeeDto setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
		return this;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public EmployeeDto setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
		return this;
	}

	public String getDepartment() {
		return department;
	}

	public EmployeeDto setDepartment(String department) {
		this.department = department;
		return this;
	}

	public AddressDto getEmployeeAddress() {
		return employeeAddress;
	}

	public EmployeeDto setEmployeeAddress(AddressDto employeeAddress) {
		this.employeeAddress = employeeAddress;
		return this;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public List<AccountDto> getEmployeeAccountList() {
		return employeeAccountList;
	}

	public void setEmployeeAccountList(List<AccountDto> employeeAccountList) {
		this.employeeAccountList = employeeAccountList;
	}

	public List<ProjectDto> getEmployeeProjectList() {
		return employeeProjectList;
	}

	public void setEmployeeProjectList(List<ProjectDto> employeeProjectList) {
		this.employeeProjectList = employeeProjectList;
	}

	
}
