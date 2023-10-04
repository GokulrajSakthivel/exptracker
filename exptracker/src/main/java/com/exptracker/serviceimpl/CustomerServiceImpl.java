package com.exptracker.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exptracker.entity.Customer;
import com.exptracker.exception.CustomerException;
import com.exptracker.repository.CustomerRepository;
import com.exptracker.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer createCustomer(Customer customer) throws CustomerException {

		Customer customer2 = null;
		if (customer.getCustomerId() >= 1000 && customer.getCustomerId() <= 9999) {
			if (String.valueOf(customer.getContactNumber()).length() == 10) {
				customer2 = customerRepository.save(customer);
			} else {
				throw new CustomerException(" Enter Valid Contact Number ");
			}
		} else {
			throw new CustomerException(" Invalid Customer ID ");
		}
		return customer2;
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

		Optional<Customer>  optional= customerRepository.findById(custId);
		String message = "";
		if (optional.isPresent()) {
			customerRepository.delete(optional.get());
			message = " Deleted Successfully";
		} else {
			throw new CustomerException("Customer not found for ID: " + custId);
		}
		return message;
	}

}
