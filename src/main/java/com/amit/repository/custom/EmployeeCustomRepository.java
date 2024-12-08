package com.amit.repository.custom;

import java.util.List;

import com.amit.entity.EmployeeEntity;

public interface EmployeeCustomRepository {

	public List<EmployeeEntity> getEmployeeByNameUsingCustomQuery(String empName);

}
