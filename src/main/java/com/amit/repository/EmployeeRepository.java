package com.amit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.amit.entity.EmployeeEntity;

@Repository
public interface EmployeeRepository
		extends JpaRepository<EmployeeEntity, Integer>, JpaSpecificationExecutor<EmployeeEntity> {

	public Optional<EmployeeEntity> findByEmployeeEmail(String email);

}
