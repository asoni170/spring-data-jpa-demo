package com.amit.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amit.dto.EmployeeDto;
import com.amit.dto.ProjectDto;
import com.amit.entity.EmployeeEntity;
import com.amit.entity.ProjectEntity;
import com.amit.exception.ResourceAlreadyExistsException;
import com.amit.exception.ResourceNotFoundException;
import com.amit.repository.EmployeeRepository;
import com.amit.repository.ProjectRepository;
import com.amit.service.IProjectServcie;

@Service
public class ProjectServiceImpl implements IProjectServcie{
	
	@Autowired
	private ProjectRepository projectRepo;
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProjectDto createProject(ProjectDto projectDto) {
		
		Optional<ProjectEntity> optioanlProject = findProjectByName(projectDto.getProjectName());
		
		if(optioanlProject.isPresent()) {
			throw new ResourceAlreadyExistsException("Project", "Name", projectDto.getProjectName());
		}
		
		ProjectEntity projectEntity = modelMapper.map(projectDto, ProjectEntity.class);
		
		projectEntity = projectRepo.saveAndFlush(projectEntity);
		
		projectDto = modelMapper.map(projectEntity, ProjectDto.class);
		
		return projectDto;
	}
	
	private Optional<ProjectEntity> findProjectByName(String projectName) {
		Optional<ProjectEntity> projectOptional = projectRepo.findByProjectName(projectName);
		
		return projectOptional;
	}

	@Override
	public EmployeeDto associateNewProject(Integer employeeId, Integer projectId) {
		
		EmployeeEntity employeeEntity = empRepo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "EmployeeId", String.valueOf(employeeId)));
		
		ProjectEntity projectEntity = projectRepo.findById(projectId)
				.orElseThrow(() -> new ResourceNotFoundException("Project", "ProjectId", String.valueOf(projectId)));
		
		employeeEntity.getProjectEntityList().add(projectEntity);
		projectEntity.getEmployeeEntityList().add(employeeEntity);
		
		employeeEntity = empRepo.saveAndFlush(employeeEntity);
		
		EmployeeDto employeeDto = modelMapper.map(employeeEntity, EmployeeDto.class);
		
		return employeeDto;
	}

}
