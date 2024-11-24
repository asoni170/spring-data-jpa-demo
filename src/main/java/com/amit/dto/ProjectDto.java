package com.amit.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProjectDto {

	private Integer projectId;

	@NotNull(message = "Project Name can not be null")
	@NotEmpty(message = "Project Name can not be empty")
	@Size(min = 2, max = 30, message = "Project Name should be in the range of 2 to 30")
	private String projectName;

	public Integer getProjectId() {
		return projectId;
	}

	public ProjectDto setProjectId(Integer projectId) {
		this.projectId = projectId;
		return this;
	}

	public String getProjectName() {
		return projectName;
	}

	public ProjectDto setProjectName(String projectName) {
		this.projectName = projectName;
		return this;
	}
	
	

}
