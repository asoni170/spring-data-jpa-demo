package com.amit.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

@Schema(name = "Address", description = "Schema to hold address of an employee")
public class AddressDto {

	@Schema(name = "AddressId", description = "Unique id for address of an employee")
	@Null(message = "Address Id will be system generated, no need to provide manuallly")
	private Integer addressId;
	
	@Schema(name = "City", example = "Taxus")
	@NotNull(message = "To add new address city is required")
	private String city;
	
	@Schema(name = "pincode", description = "Pincode related to address", example = "123456")
	@NotNull(message = "To add new address, pincode is required")
	private String pincode;

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

}
