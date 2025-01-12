package com.amit.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.amit.dto.AccountDto;
import com.amit.dto.AddressDto;
import com.amit.dto.EmployeeDto;
import com.amit.dto.EmployeeListDto;
import com.amit.entity.AccountEntity;
import com.amit.entity.AddressEntity;
import com.amit.entity.EmployeeEntity;
import com.amit.exception.EmployeeAlreadyExistsException;
import com.amit.exception.MissingMandetoryFieldException;
import com.amit.exception.NoEmployeeFoundException;
import com.amit.exception.ResourceNotFoundException;
import com.amit.exception.UpdateNotAllowedException;
import com.amit.repository.AccountRepository;
import com.amit.repository.AddressRepository;
import com.amit.repository.EmployeeRepository;
import com.amit.repository.query.EmployeeDao;
import com.amit.service.IEmployeeService;
import com.amit.specifications.EmployeeSpecification;

import jakarta.validation.constraints.Positive;

@Service
@Transactional
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired
	private AddressRepository addressRepo;
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private EmployeeDao empDao;

	@Override
	public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {

		EmployeeEntity employee = modelMapper.map(employeeDto, EmployeeEntity.class);
		employee.populateEmployeeInAccountList();

		validateEmployeeBeforeSave(employee);

		boolean isCustomerAlreadyExist = false;

		if (StringUtils.hasText(employee.getEmployeeEmail())) {
			isCustomerAlreadyExist = isEmployeeDataAlreadyExists(employee.getEmployeeEmail());
		} else {
			throw new MissingMandetoryFieldException("Email");
		}

		if (!isCustomerAlreadyExist) {
			employee = empRepo.saveAndFlush(employee);
		}

		employeeDto = modelMapper.map(employee, EmployeeDto.class);

		return employeeDto;
	}

	private void validateEmployeeBeforeSave(EmployeeEntity employee) {
//		if(!StringUtils.hasText(employee.getEmployeeName())) {
//			
//		}
	}

	private boolean isEmployeeDataAlreadyExists(String email) {
		boolean employeeAlreadyExists = false;

		Optional<EmployeeEntity> optionalEmployee = empRepo.findByEmployeeEmail(email);

		employeeAlreadyExists = optionalEmployee.isPresent();

		if (employeeAlreadyExists) {
			throw new EmployeeAlreadyExistsException(email);
		}

//		optionalEmployee.orElseThrow(() -> new EmployeeAlreadyExistsException(email));

		return employeeAlreadyExists;
	}

	@Override
	public EmployeeListDto findAllEmployee(Integer employeeId, String employeeName, String employeeEmail,
			String department, String city, String pincode, String accountType, String bankName, Integer pageNumber, @Positive Integer pageSize) {

		EmployeeListDto returnObject = new EmployeeListDto();
		List<EmployeeDto> employeeReturnList = new ArrayList<EmployeeDto>();

		Pageable pagable = PageRequest.of(pageNumber, pageSize, Sort.by(Direction.ASC, "employeeName"));
		
		Specification<EmployeeEntity> empSpec = EmployeeSpecification.filterEmployee(
				employeeId, employeeName, employeeEmail, department, city, pincode, accountType, bankName);

		Page<EmployeeEntity> employeeEntityPage = empRepo.findAll(empSpec, pagable);

		if (employeeEntityPage.isEmpty()) {
			throw new NoEmployeeFoundException();
		} else {
			employeeEntityPage.getContent().stream()
					.forEach(entity -> employeeReturnList.add(modelMapper.map(entity, EmployeeDto.class)));
		}

		returnObject.setEmployeeList(employeeReturnList);
		returnObject.setTotalRecords(employeeEntityPage.getTotalElements());

		return returnObject;
	}

	@Override
	public EmployeeDto updateEmployeeAddress(Integer employeeId, AddressDto addressDto) {

		EmployeeEntity employeeEntity = findEmployeeById(employeeId);
		
		AddressEntity addressEntity = new AddressEntity();
		
		if(employeeEntity.getEmployeeAddress() != null && employeeEntity.getEmployeeAddress().getAddressId() != null) {
			addressEntity.setAddressId(employeeEntity.getEmployeeAddress().getAddressId());
		}
		
		addressEntity.setCity(addressDto.getCity());
		addressEntity.setPincode(addressDto.getPincode());
		
		addressEntity = addressRepo.saveAndFlush(addressEntity);
		
		employeeEntity.setEmployeeAddress(addressEntity);
		
		EmployeeDto employeeDto = modelMapper.map(employeeEntity, EmployeeDto.class);

		return employeeDto;
	}

	private EmployeeEntity findEmployeeById(Integer employeeId) {
		Optional<EmployeeEntity> employeeOptional = empRepo.findById(employeeId);

		EmployeeEntity employeeEntity = employeeOptional
				.orElseThrow(() -> new NoEmployeeFoundException("Employee", "EmployeeId", String.valueOf(employeeId)));
		return employeeEntity;
	}

	@Override
	public EmployeeDto addAccountForExistingEmployee(Integer employeeId, List<AccountDto> accountDtoList) {
		
		EmployeeEntity employeeEntity = findEmployeeById(employeeId);
		
		List<AccountEntity> accountEntityList = new ArrayList<AccountEntity>();
		
		accountDtoList.stream().forEach(account -> accountEntityList.add(modelMapper.map(account, AccountEntity.class)));
		
		employeeEntity.getEmployeeAccountList().addAll(accountEntityList);
		
		employeeEntity.populateEmployeeInAccountList();
		
		employeeEntity = empRepo.saveAndFlush(employeeEntity);
		
		EmployeeDto employeeDto = modelMapper.map(employeeEntity, EmployeeDto.class);
		
		return employeeDto;
	}

	@Override
	public EmployeeDto updateAccount(Integer employeeId, Integer accountId, AccountDto accountDto) {
		EmployeeEntity employeeEntity = findEmployeeById(employeeId);
		
		AccountEntity accountEntity = employeeEntity.getEmployeeAccountList().stream().filter(acc -> acc.getAccountId() == accountId)
				.findFirst().orElseThrow(() -> new UpdateNotAllowedException("Employee and Account"));
		
		accountEntity =  modelMapper.map(accountDto, AccountEntity.class);
		accountEntity.setAccountId(accountId);
		accountEntity.setEmployeeEntity(employeeEntity);
		
		accountEntity = accountRepo.saveAndFlush(accountEntity);
		
		for(AccountEntity account : employeeEntity.getEmployeeAccountList()) {
			if(account.getAccountId() == accountId) {
				account.setAccountNumber(accountDto.getAccountNumber());
				account.setAccountType(accountDto.getAccountType());
				account.setBankName(accountDto.getBankName());
			}
		}
		
		EmployeeDto employeeDto = modelMapper.map(employeeEntity, EmployeeDto.class);
		
		return employeeDto;
	}

	@Override
	public EmployeeDto findCustomerUsingCustomQuery(String empName) {
		List<EmployeeEntity> empList = empRepo.getEmployeeByNameUsingCustomQuery(empName);
		
		EmployeeEntity employeeEntity = empList.stream().findFirst()
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "EmployeeName", empName));
		EmployeeDto employeeDto = modelMapper.map(employeeEntity, EmployeeDto.class);
		
		return employeeDto;
	}

	@Override
	public List<EmployeeDto> findEmployeeByNames(List<String> empNames) {
		List<EmployeeEntity> empEntityList = empDao.fetchEmployeesByName(empNames);
		
		List<EmployeeDto> empDtoList = new ArrayList<EmployeeDto>();
		
		empEntityList.stream().forEach(emp -> empDtoList.add(modelMapper.map(emp, EmployeeDto.class)));
		
		return empDtoList;
	}

}
