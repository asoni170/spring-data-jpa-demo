package com.amit.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.amit.entity.AddressEntity;
import com.amit.entity.EmployeeEntity;

public class EmployeeRowMapper implements RowMapper<EmployeeEntity>{

	@Override
	public EmployeeEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmployeeEntity employee = new EmployeeEntity();
		AddressEntity address = new AddressEntity();
		
		address.setAddressId(rs.getInt("address_id"));
		address.setCity(rs.getString("city"));
		address.setPincode(rs.getString("pincode"));
		
		employee.setEmployeeId(rs.getInt("emp_id"));
		employee.setEmployeeEmail(rs.getString("emp_email"));
		employee.setEmployeeName(rs.getString("emp_name"));
		employee.setDepartment(rs.getString("department"));
		
		employee.setEmployeeAddress(address);
		
		return employee;
	}}
