package com.amit.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "project")
public class ProjectEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_id")
	private Integer projectId;
	
	@Column(name = "project_name")
	private String projectName;
	
	@ManyToMany(mappedBy = "projectEntityList")
	private List<EmployeeEntity> employeeEntityList;

	public Integer getProjectId() {
		return projectId;
	}

	public ProjectEntity setProjectId(Integer projectId) {
		this.projectId = projectId;
		return this;
	}

	public String getProjectName() {
		return projectName;
	}

	public ProjectEntity setProjectName(String projectName) {
		this.projectName = projectName;
		return this;
	}

	public List<EmployeeEntity> getEmployeeEntityList() {
		return employeeEntityList;
	}

	public ProjectEntity setEmployeeEntityList(List<EmployeeEntity> employeeEntityList) {
		this.employeeEntityList = employeeEntityList;
		return this;
	}
	
	

}
