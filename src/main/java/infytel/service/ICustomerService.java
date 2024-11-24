package infytel.service;

import com.infytel.dto.CustomerDTO;

public interface ICustomerService {
	
	public void insert(CustomerDTO customer);
	
	public int remove(Long phoneNo);

}
