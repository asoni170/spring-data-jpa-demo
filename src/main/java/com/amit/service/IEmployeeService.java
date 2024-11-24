package com.amit.service;

import java.util.List;

import com.amit.dto.AccountDto;
import com.amit.dto.AddressDto;
import com.amit.dto.EmployeeDto;
import com.amit.dto.EmployeeListDto;

import jakarta.validation.constraints.Positive;

public interface IEmployeeService {

	public EmployeeDto createNewEmployee(EmployeeDto employee);

	public EmployeeListDto findAllEmployee(Integer employeeId, String employeeName, String employeeEmail,
			String department, String city, String pincode, String accountType, String bankName, Integer pageNumber, @Positive Integer pageSize);

	public EmployeeDto updateEmployeeAddress(Integer employeeId, AddressDto addressDto);

	public EmployeeDto addAccountForExistingEmployee(Integer employeeId, List<AccountDto> accountDtoList);

	public EmployeeDto updateAccount(Integer employeeId, Integer accountId, AccountDto accountDto);

}
