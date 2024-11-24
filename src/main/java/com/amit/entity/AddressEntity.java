package com.amit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Address")
public class AddressEntity {

	@Id
	@Column(name = "address_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addressId;

	@Column(name = "city")
	private String city;

	@Column(name = "pincode")
	private String pincode;

	@OneToOne(mappedBy = "employeeAddress")
	private EmployeeEntity employeeEntity;

	public Integer getAddressId() {
		return addressId;
	}

	public AddressEntity setAddressId(Integer addressId) {
		this.addressId = addressId;
		return this;
	}

	public String getCity() {
		return city;
	}

	public AddressEntity setCity(String city) {
		this.city = city;
		return this;
	}

	public String getPincode() {
		return pincode;
	}

	public AddressEntity setPincode(String pincode) {
		this.pincode = pincode;
		return this;
	}

	public EmployeeEntity getEmployeeEntity() {
		return employeeEntity;
	}

	public AddressEntity setEmployeeEntity(EmployeeEntity employeeEntity) {
		this.employeeEntity = employeeEntity;
		return this;
	}

}
