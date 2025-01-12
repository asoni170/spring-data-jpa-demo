package com.amit.repository.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amit.entity.EmployeeEntity;
import com.amit.util.SqlQueryUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;

@Component
public class EmployeeDao {
	
	@Autowired
	private SqlQueryUtil queryUtils;

//	@Autowired
//	private JdbcTemplate jdbcTemplate;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private static final String SELECT_EMP_BY_NAMES = "SELECT_EMP_BY_NAMES";

	public List<EmployeeEntity> fetchEmployeesByName(List<String> empNames) {
		
		String queryString = queryUtils.getSql(SELECT_EMP_BY_NAMES);
		
		Query query = entityManager.createNativeQuery(queryString, Tuple.class);
		
		query.setParameter("empNameIn", empNames);
		
		List<Tuple> tuple = query.getResultList();
		
		return null;
	}

}
