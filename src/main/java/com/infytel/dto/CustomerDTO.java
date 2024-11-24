package com.infytel.dto;

import com.infytel.entity.Customer;

public class CustomerDTO {

	private Long phoneNumber;
	private String name;
	private Integer age;
	private Character gender;
	private String address;
	private Integer planId;

	public CustomerDTO(Long phoneNumber, String name, Integer age, Character gender, String address, Integer planId) {
		super();
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.planId = planId;
	}

	public CustomerDTO() {
		super();
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	@Override
	public String toString() {
		return "Customer [phoneNumber=" + phoneNumber + ", name=" + name + ", age=" + age + ", gender=" + gender
				+ ", address=" + address + ", planId=" + planId + "]";
	}
	
	public static Customer prepareCustomerEntity(CustomerDTO customerDto) {
		Customer customerEntity = new Customer();
		customerEntity.setPhoneNumber(customerDto.getPhoneNumber());
		customerEntity.setName(customerDto.getName());
		customerEntity.setAge(customerDto.getAge());
		customerEntity.setGender(customerDto.getGender());
		customerEntity.setAddress(customerDto.getAddress());
		customerEntity.setPlanId(customerDto.getPlanId());
		
		return customerEntity;
	}

}
