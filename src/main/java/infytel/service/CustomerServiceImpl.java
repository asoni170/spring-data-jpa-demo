package infytel.service;

import com.infytel.dto.CustomerDTO;

import infytel.repository.CustomerDAOImpl;
import infytel.repository.ICustomerDAO;

public class CustomerServiceImpl implements ICustomerService{
	
	private ICustomerDAO customerDao = new CustomerDAOImpl();

	@Override
	public void insert(CustomerDTO customer) {
		customerDao.insert(CustomerDTO.prepareCustomerEntity(customer));
	}

	@Override
	public int remove(Long phoneNo) {
		return customerDao.remove(phoneNo);
	}

}
