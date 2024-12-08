package com.amit.repository.custom.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.amit.entity.EmployeeEntity;
import com.amit.repository.custom.EmployeeCustomRepository;
import com.amit.rowmapper.EmployeeRowMapper;
import com.amit.util.SqlQueryUtil;

@Repository
public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository{

	@Autowired
	private SqlQueryUtil queryUtils;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String SELECT_CUSTOMER_BY_NAME_WITH_ADDRESS = "SELECT_CUSTOMER_BY_NAME_WITH_ADDRESS";

	@SuppressWarnings("deprecation")
	@Override
	public List<EmployeeEntity> getEmployeeByNameUsingCustomQuery(String empName) {

		String query = queryUtils.getSql(SELECT_CUSTOMER_BY_NAME_WITH_ADDRESS);

		return jdbcTemplate.query(query, new Object[] { empName }, new EmployeeRowMapper());

	}

}
