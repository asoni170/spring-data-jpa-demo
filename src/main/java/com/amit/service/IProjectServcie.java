package com.amit.service;

import com.amit.dto.EmployeeDto;
import com.amit.dto.ProjectDto;

public interface IProjectServcie {
	
	public ProjectDto createProject(ProjectDto projectDto);

	public EmployeeDto associateNewProject(Integer employeeId, Integer projectId);

}
