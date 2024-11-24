package com.infosys;

import com.infytel.dto.CustomerDTO;

import infytel.service.CustomerServiceImpl;
import infytel.service.ICustomerService;

public class ClientApplication {
    public static void main(String[] args) {
       
    	ICustomerService service = new CustomerServiceImpl();
//    	CustomerDTO customerDto = new CustomerDTO(9876543210L, "Amit", 28, 'M', "India", 3);
//    	
//    	service.insert(customerDto);
    	
//    	System.out.println("Record Created");
    	
    	service.remove(9876543210L);
    	
    }
}
