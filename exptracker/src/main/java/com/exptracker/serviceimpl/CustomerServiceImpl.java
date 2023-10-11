package com.exptracker.serviceimpl;

import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.exptracker.dto.CustomerDto;
import com.exptracker.entity.Customer;
import com.exptracker.exception.TrackerException;
import com.exptracker.repository.CustomerRepository;
import com.exptracker.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public String createCustomer(Customer customer) throws TrackerException {

		boolean contactNumber = customer.getContactNumber().matches("^(\\+91)?[6789]\\d{9}$");

		if (!contactNumber) {
			throw new TrackerException("Enter a valid contact number");
		}

		if (customer.getPassword().length() < 6) {
			throw new TrackerException("password length should be greater than 6 character ");
		}

//		boolean isDuplicate = customerRepository.existsByUserNameAndPassword(customer.getUserName(),
//				customer.getPassword());
//
//		if (isDuplicate) {
//			throw new TrackerException("Username and password already exist Try different ");
//		}
		
		boolean isDuplicate = customerRepository.existsByUserName(customer.getUserName());
		if (isDuplicate) {
			throw new TrackerException("Username already exist Try different ");
		}
		
		boolean isDuplicateContact = customerRepository.existsByContactNumber(customer.getContactNumber());
		if (isDuplicateContact) {
			throw new TrackerException("ContactNumber already exist Try different ");
		}
		
		customerRepository.save(customer);
		
		return "Customer Created Successfully";

	}

	@Override
	public CustomerDto readCustomerById(int custId) throws TrackerException {
		Optional<Customer> customer = customerRepository.findById(custId);
		if (!customer.isPresent()) {
			throw new TrackerException("Customer not found for ID: " + custId);
		}
		return mapper.map(customer.get(), CustomerDto.class);
	}

	@Override
	public String updateCustomer(Customer customer, int customerId) throws TrackerException {
		
		boolean contactNumber = customer.getContactNumber().matches("^(\\+91)?[6789]\\d{9}$");
		if (!contactNumber) {
			throw new TrackerException("Enter a valid contact number");
		}


		Optional<Customer> data = customerRepository.findById(customerId);
		if (!data.isPresent()) {
			throw new TrackerException("Customer not found for ID : " + customerId);
		}
		
		

		Customer existingCustomer = data.get();

		existingCustomer.setCustomerName(customer.getCustomerName());
		existingCustomer.setContactNumber(customer.getContactNumber());
		customerRepository.save(existingCustomer);
		return "Updated Successfully";

	}

	@Override
	public String deleteCustomer(int custId) throws TrackerException {
		Optional<Customer> optional = customerRepository.findById(custId);
		if (!optional.isPresent()) {
			throw new TrackerException("Customer not found for ID: " + custId);
		}
		customerRepository.delete(optional.get());
		return " Deleted Successfully";
	}

	@Override
	public CustomerDto loginCustomer(String userName, String password) throws TrackerException {

		Customer customers = customerRepository.findByUserNameAndPassword(userName, password);
		CustomerDto dtoModel = null;
		List<Customer> customer = customerRepository.findAll();

		boolean isDuplicate = customer.stream().anyMatch(
				customer1 -> customer1.getUserName().equals(userName) && customer1.getPassword().equals(password));

		if (!isDuplicate) {
			throw new TrackerException("Invalid Username or Password ");
		}

		dtoModel = mapper.map(customers, CustomerDto.class);
		return dtoModel;
	}

}
