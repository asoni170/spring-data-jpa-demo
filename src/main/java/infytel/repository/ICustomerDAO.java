package infytel.repository;

import com.infytel.entity.Customer;

public interface ICustomerDAO {
	
	public void insert(Customer customer);
	
	public int remove(Long phoneNo);

}
