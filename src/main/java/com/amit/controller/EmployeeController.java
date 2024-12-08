package com.amit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amit.dto.AccountDto;
import com.amit.dto.AddressDto;
import com.amit.dto.EmployeeDto;
import com.amit.dto.EmployeeListDto;
import com.amit.dto.ProjectDto;
import com.amit.service.IEmployeeService;
import com.amit.service.IProjectServcie;
import com.amit.util.SqlQueryUtil;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping(path = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Employee Controller", 
     description = "Http methods to perform create delete update and fetch operation for employee details")
public class EmployeeController {
	
	@Autowired
	private IEmployeeService empService;
	
	@Autowired
	IProjectServcie projectService;
	
	@Autowired
	private SqlQueryUtil queryUtil;
	
	@PostMapping("/create")
	public ResponseEntity<EmployeeDto> createNewEmployeeWithAddress(@Valid @RequestBody EmployeeDto employeeDto){
		
		employeeDto = empService.createNewEmployee(employeeDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeDto);
	}
	
	@GetMapping("/all")
	public ResponseEntity<EmployeeListDto> getAllEmployee(
			@RequestParam(required = false) Integer employeeId,
			@RequestParam(required = false) String employeeName,
			@RequestParam(required = false) String employeeEmail,
			@RequestParam(required = false) String department,
			@RequestParam(required = false) String city,
			@RequestParam(required = false) String pincode,
			@RequestParam(required = false) String accountType,
			@RequestParam(required = false) String bankName,
			@Positive @RequestParam(required = false) Integer pageNumber,
			@Positive @RequestParam(required = false) Integer pageSize){
		
		pageNumber = pageNumber != null ? pageNumber : 1;
		pageSize = pageSize != null ? pageSize : 5;
		
		EmployeeListDto returnObject = empService.findAllEmployee(employeeId, employeeName,employeeEmail, department, city, pincode,
				accountType, bankName, pageNumber - 1, pageSize);
		
		return ResponseEntity.status(HttpStatus.OK).body(returnObject);
	}
	
	@PutMapping("/update-address/{employeeId}")
	public ResponseEntity<EmployeeDto> updateEmployeeAddress(
			@PathVariable Integer employeeId, @Valid @RequestBody AddressDto addressDto){
		
		EmployeeDto employee = empService.updateEmployeeAddress(employeeId, addressDto);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(employee);
	}
	
	@PutMapping("/add-account/{employeeId}")
	public ResponseEntity<EmployeeDto> addAccountForExistingEmployee(
			@PathVariable Integer employeeId, @RequestBody List<AccountDto> accountDtoList){
		
		EmployeeDto employeeDto = empService.addAccountForExistingEmployee(employeeId, accountDtoList);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeDto);
	}
	
	@PatchMapping("{employeeId}/account/{accountId}")
	public ResponseEntity<EmployeeDto> updateAccountForEmployee(
			@PathVariable Integer employeeId, @PathVariable Integer accountId, @RequestBody AccountDto accountDto){
		EmployeeDto employeeDto = empService.updateAccount(employeeId, accountId, accountDto);
		
		return ResponseEntity.status(HttpStatus.OK).body(employeeDto);
	}
	
	@PostMapping("project/create")
	public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto){
		
		projectDto = projectService.createProject(projectDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(projectDto);
	}

	@PostMapping("{employeeId}/project/{projectId}/associate")
	public ResponseEntity<EmployeeDto> createEmployeeProjectAssociation(
			@PathVariable Integer employeeId, @PathVariable Integer projectId){
		
		EmployeeDto employeeDto = projectService.associateNewProject(employeeId, projectId);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeDto);
	}
	
	@GetMapping("/custom-query-search/{empName}")
	public ResponseEntity<EmployeeDto> searchEmployeeByCustomQuery(@PathVariable String empName){
		
		EmployeeDto employeeDto = empService.findCustomerUsingCustomQuery(empName);
		
		return ResponseEntity.status(HttpStatus.OK).body(employeeDto);
	}
	
	@GetMapping("/query")
	public String getQuery() {
		return queryUtil.getSql("SELECT_CUSTOMER_BY_NAME_WITH_ADDRESS");
	}

}
