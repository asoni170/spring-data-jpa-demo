package com.amit.specifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.amit.entity.AccountEntity;
import com.amit.entity.AddressEntity;
import com.amit.entity.EmployeeEntity;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class EmployeeSpecification {

	public static Specification<EmployeeEntity> filterEmployee(
			Integer employeeId, String employeeName, String employeeEmail, String department, String city, String pincode,
			String accountType, String bankName) {
		
		return new Specification<EmployeeEntity>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 6523709353977441286L;

			@Override
			public Predicate toPredicate(Root<EmployeeEntity> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicateList = new ArrayList<Predicate>();

				if (employeeId != null)
					predicateList.add(criteriaBuilder.equal(root.get("employeeId"), employeeId));

				if (StringUtils.hasText(employeeName))
					predicateList.add(criteriaBuilder.like(root.get("employeeName"), "%" + employeeName + "%"));

				if (StringUtils.hasText(employeeEmail))
					predicateList.add(criteriaBuilder.equal(root.get("employeeEmail"), employeeEmail));

				if (StringUtils.hasText(department))
					predicateList.add(criteriaBuilder.like(root.get("department"), "%" + department + "%"));
				
				Join<EmployeeEntity, AddressEntity> addressJoin = root.join("employeeAddress", JoinType.LEFT);
				
				if(StringUtils.hasText(city))
					predicateList.add(criteriaBuilder.equal(addressJoin.get("city"), city));
				
				if(StringUtils.hasText(pincode))
					predicateList.add(criteriaBuilder.equal(addressJoin.get("pincode"), pincode));
				
				Join<EmployeeEntity, AccountEntity> accountJoin = root.join("employeeAccountList", JoinType.LEFT);
				
				if(StringUtils.hasText(accountType))
					predicateList.add(criteriaBuilder.equal(accountJoin.get("accountType"), accountType));
				
				if(StringUtils.hasText(bankName))
					predicateList.add(criteriaBuilder.equal(accountJoin.get("bankName"), bankName));

				return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
			}

		};
	}

}
