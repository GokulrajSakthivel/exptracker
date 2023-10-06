package com.exptracker.serviceimpl;

import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.exptracker.dto.CustomerDto;
import com.exptracker.entity.Customer;
import com.exptracker.exception.CustomerException;
import com.exptracker.repository.CustomerRepository;
import com.exptracker.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public Customer createCustomer(Customer customer) throws CustomerException {

		if (String.valueOf(customer.getContactNumber()).length() != 10) {
			throw new CustomerException("Enter a valid 10-digit contact number");
		}

		boolean isDuplicate = customerRepository.existsByUserNameAndPassword(customer.getUserName(),
				customer.getPassword());

		if (isDuplicate) {
			throw new CustomerException("Username and password already exist");
		}

		Customer savedCustomer = customerRepository.save(customer);
		return savedCustomer;
	}



	@Override
	public Customer readCustomerById(int CustId) throws CustomerException {

		Optional<Customer> customer = customerRepository.findById(CustId);
		Customer customer2 = null;
		if (customer.isPresent()) {
			customer2 = customer.get();
		} else {
			throw new CustomerException("Customer not found for ID: " + CustId);
		}
		return customer2;
	}

	@Override
	public String updateCustomer(Customer customer) throws CustomerException {

		Optional<Customer> data = customerRepository.findById(customer.getCustomerId());

		Customer updatedCustomer = new Customer();

		String message = "";
		if (String.valueOf(customer.getContactNumber()).length() == 10) {
			if (data.isPresent()) {
				Customer existingCustomer = customerRepository.findById(customer.getCustomerId()).get();
				updatedCustomer.setUserName(customer.getUserName());
				updatedCustomer.setPassword(customer.getPassword());
				updatedCustomer.setCustomerId(existingCustomer.getCustomerId());
				updatedCustomer.setCustomerName(customer.getCustomerName());
				updatedCustomer.setContactNumber(customer.getContactNumber());
				customerRepository.save(updatedCustomer);
				message = "Updated Successfully";
			} else {
				throw new CustomerException("Customer not found for ID : " + customer.getCustomerId());
			}
			return message;
		} else {
			throw new CustomerException(" Enter Valid Contact Number ");
		}

	}

	@Override
	public String deleteCustomer(int custId) throws CustomerException {

		Optional<Customer> optional = customerRepository.findById(custId);
		String message = "";
		if (optional.isPresent()) {
			customerRepository.delete(optional.get());
			message = " Deleted Successfully";
		} else {
			throw new CustomerException("Customer not found for ID: " + custId);
		}
		return message;
	}

	@Override
	public CustomerDto loginCustomer(String userName, String password) throws CustomerException {

		Customer customers = customerRepository.findByUserNameAndPassword(userName, password);
		CustomerDto dtoModel = null;
		List<Customer> customer = customerRepository.findAll();

		boolean isDuplicate = customer.stream().anyMatch(
				customer1 -> customer1.getUserName().equals(userName) && customer1.getPassword().equals(password));

		if (isDuplicate) {
			dtoModel = mapper.map(customers, CustomerDto.class);
			return dtoModel;
		} else {
			throw new CustomerException("Invalid Username or Password ");
		}
	}

}
