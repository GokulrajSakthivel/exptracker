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
	public Customer createCustomer(Customer customer) throws TrackerException {

//		String contactNumber = String.valueOf(customer.getContactNumber()).replaceAll("\\s", "");
//		
//		System.out.println(contactNumber);
//		if (contactNumber.length() != 10) {
//			throw new CustomerException("Invalid contact number format. It should be 10 digits.");
//		}

		boolean contactNumber = customer.getContactNumber().matches("^(\\\\+91|0)?[6789]\\\\d{9}$");

		if (!contactNumber) {
			throw new TrackerException("Enter a valid contact number");
		}

//		if (customer.getContactNumber().length() != 10) {
//			throw new TrackerException("Enter a valid 10-digit contact number");
//		}

		if (customer.getPassword().length() <= 6) {
			throw new TrackerException("password length should be greater than 6 character ");
		}

		boolean isDuplicate = customerRepository.existsByUserNameAndPassword(customer.getUserName(),
				customer.getPassword());

		if (isDuplicate) {
			throw new TrackerException("Username and password already exist Try different ");
		}

		return customerRepository.save(customer);

	}

	@Override
	public Customer readCustomerById(int CustId) throws TrackerException {

		Optional<Customer> customer = customerRepository.findById(CustId);
		Customer customer2 = null;
		if (customer.isPresent()) {
			customer2 = customer.get();
		} else {
			throw new TrackerException("Customer not found for ID: " + CustId);
		}
		return customer2;
	}

	@Override
	public String updateCustomer(Customer customer, int customerId) throws TrackerException {
		boolean contactNumber = customer.getContactNumber().matches("^(\\\\+91|0)?[6789]\\\\d{9}$");
		if (!contactNumber) {
			throw new TrackerException("Enter a valid contact number");
		}

//		if (customer.getContactNumber().length() != 10) {
//			throw new TrackerException("Enter a valid 10-digit contact number");
//		}
//		boolean isDuplicate = customerRepository.existsByUserNameAndPassword(customer.getUserName(),
//				customer.getPassword());
//		if (isDuplicate) {
//			throw new CustomerException("Username and password already exist");
//		}
//		Customer savedCustomer = customerRepository.save(customer);
//		return savedCustomer;

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
		String message = "";
		if (optional.isPresent()) {
			customerRepository.delete(optional.get());
			message = " Deleted Successfully";
		} else {
			throw new TrackerException("Customer not found for ID: " + custId);
		}
		return message;
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
