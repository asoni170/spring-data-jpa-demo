package com.amit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Account")
public class AccountEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Integer accountId;
	
	@Column(name = "account_type")
	private String accountType;
	
	@Column(name = "account_number")
	private String accountNumber;
	
	@Column(name = "bank_name")
	private String bankName;

	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = false)
	private EmployeeEntity employeeEntity;

	public Integer getAccountId() {
		return accountId;
	}

	public AccountEntity setAccountId(Integer accountId) {
		this.accountId = accountId;
		return this;
	}

	public String getAccountType() {
		return accountType;
	}

	public AccountEntity setAccountType(String accountType) {
		this.accountType = accountType;
		return this;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public AccountEntity setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
		return this;
	}

	public String getBankName() {
		return bankName;
	}

	public AccountEntity setBankName(String bankName) {
		this.bankName = bankName;
		return this;
	}

	public EmployeeEntity getEmployeeEntity() {
		return employeeEntity;
	}

	public AccountEntity setEmployeeEntity(EmployeeEntity employeeEntity) {
		this.employeeEntity = employeeEntity;
		return this;
	}
	
	
}
